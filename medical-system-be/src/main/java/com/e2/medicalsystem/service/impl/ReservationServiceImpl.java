package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.dto.LatLng;
import com.e2.medicalsystem.dto.ReservationDto;
import com.e2.medicalsystem.model.*;
import com.e2.medicalsystem.repository.*;
import com.e2.medicalsystem.service.ReservationService;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationItemRepository reservationItemRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalEquipmentRepository medicalEquipmentRepository;

    private final UsersRepository usersRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationItemRepository reservationItemRepository, AppointmentRepository appointmentRepository, UsersRepository usersRepository, MedicalEquipmentRepository medicalEquipmentRepository)
    {
        this.reservationRepository = reservationRepository;
        this.reservationItemRepository = reservationItemRepository;
        this.appointmentRepository = appointmentRepository;
        this.usersRepository = usersRepository;
        this.medicalEquipmentRepository = medicalEquipmentRepository;
    }


    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Transactional
    @Override
    public Reservation saveReservation(ReservationDto reservationDto) {
        try{
            Reservation reservation = new Reservation();
            Appointment appointment = appointmentRepository.getByIdWithOptimisticLocking(reservationDto.getAppointment().getId().intValue());
            if(appointment.isTaken()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Termin already occupied");
            }
            appointment.setTaken(true);
            appointmentRepository.save(appointment);
            List<ReservationItem> items = reservation.getReservationItems();


            reservation.setAppointment(appointment);
            List<ReservationItem> reservationItems = new ArrayList<>();
            for (var item:
                    reservationDto.getReservationItems()) {

                ReservationItem reservationItem = new ReservationItem(item);
                reservationItem.setEquipment(medicalEquipmentRepository.getByIdWithOptimisticLocking(item.getEquipment().getId()));
                reservationItemRepository.save(reservationItem);
                reservationItems.add(reservationItem);
                updateEquipment(reservationItem.getEquipment(), reservationItem.getCount(), true);
            }
            reservation.setReservationItems(reservationItems);
            reservation.setReserverId(reservationDto.getReserverId());
            reservation.setCanceled(false);

            return reservationRepository.save(reservation);
        }catch(OptimisticLockException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already reserved or not enough equipment");
        }


    }

    private void updateEquipment(MedicalEquipment equipment, int count, boolean lowering){
            if(lowering){
                if(equipment.getCount() - count > 0){
                    equipment.setCount(equipment.getCount() - count);
                    medicalEquipmentRepository.save(equipment);
                }else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough equipment");
                }
            }else{
                equipment.setCount(equipment.getCount() + count);
                medicalEquipmentRepository.save(equipment);
            }

    }
    @Transactional
    @Override
    public Reservation getReservationById(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        Reservation reservation = optionalReservation.orElseThrow(() -> new RuntimeException("Reservation not present"));
        return reservation;
    }

    @Override
    @Transactional
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.getById(id);
        reservationRepository.delete(reservation);
    }

    @Override
    public List<Reservation> getAllReservationsByReserverId(Long reserverId){
        return reservationRepository.findAllByReserverId(reserverId);
    }


    @Override
    @Transactional
    public void finishDelivery(Long id)
    {
        Reservation reservation = reservationRepository.getReferenceById(id);
        if(!Objects.equals(reservation.getId(), id)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation does not exist!");
        reservation.setDelivered(true);
        reservationRepository.save(reservation);
    }

    @Override
    @Transactional
    public ReservationDto cancelReservation(Long reservationId, Long userId) {
        Reservation reservation = reservationRepository.getById(reservationId);
        User user = usersRepository.getById(userId.intValue());
        ReservationDto reservationDto = new ReservationDto(reservation);
        LocalDate currentDate = LocalDate.now();

        Date currentDateAsDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        if(reservation.getAppointment().getDate().after(currentDateAsDate)){
            for(ReservationItem item :reservation.getReservationItems()){
                updateEquipment(item.getEquipment(), item.getCount(), false);
            }

            try{
                Appointment appointment = appointmentRepository.getByIdWithOptimisticLocking(reservation.getAppointment().getId().intValue());
                reservation.setCanceled(true);
                reservationRepository.save(reservation);
                appointment.setTaken(false);
                appointmentRepository.save(appointment);
                long timeDifference = reservation.getAppointment().getDate().getTime() - currentDateAsDate.getTime();
                long timeDifferenceSeconds = timeDifference/ (60 * 60 * 1000);
                if(timeDifferenceSeconds < 24){
                    user.setPenalPoints(user.getPenalPoints()+2);
                }else{
                    user.setPenalPoints(user.getPenalPoints()+2);

                }
                usersRepository.save(user);
                return reservationDto;
            }catch (OptimisticLockException e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Optimistic lock exception");
            }

        }
        else{
            throw new IllegalArgumentException("Invalid operation: You can't do this");
        }

    }
}
