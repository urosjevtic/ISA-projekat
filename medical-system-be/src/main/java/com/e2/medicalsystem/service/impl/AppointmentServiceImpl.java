package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.model.Appointment;
import com.e2.medicalsystem.model.CompanyProfile;
import com.e2.medicalsystem.model.ERole;
import com.e2.medicalsystem.model.User;
import com.e2.medicalsystem.repository.AppointmentRepository;
import com.e2.medicalsystem.repository.CompanyProfileRepository;
import com.e2.medicalsystem.repository.UsersRepository;
import com.e2.medicalsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CompanyProfileRepository companyProfileRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointmentsByCompanyId(long companyId) {
        return appointmentRepository.findAllByCompanyId(companyId);
    }

    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.getById(id);
    }

    @Override
    public List<Appointment> getAllFreeAppointmentsByCompanyId(long companyId) {
        return appointmentRepository.findAllByCompanyIdAndTakenFalse(companyId);
    }

    @Override
    public List<Appointment> getFreeAppointments(long companyId, LocalDate forDate) {

        List<Appointment> appointments = appointmentRepository.findAllByCompanyId(companyId).
                stream().filter(x -> forDate.isEqual( x.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate())).toList();

        CompanyProfile company = companyProfileRepository.findById(companyId).orElse(new CompanyProfile());
        List<User> companyAdmins = company.getCompanyAdmins();

        LocalTime wrkHrFrom;
        wrkHrFrom = company.getWrkHrFrom().toLocalTime();

        LocalTime wrkHrTo;
        wrkHrTo = company.getWrkHrTo().toLocalTime();

        LocalTime currentTime = wrkHrFrom;
        boolean conflict = false;
        List<Appointment> freeAppointments = new ArrayList<>();

        while (currentTime.isBefore(wrkHrTo) || currentTime.equals(wrkHrFrom)) {

            conflict = false;

            List<User> freeAdmins = new ArrayList<>(companyAdmins);

            for (Appointment app:
                 appointments)
            {
                if(isTimeConflicted(currentTime,
                                    currentTime.plusMinutes(15),
                                    convertToLocalTime(app.getDate()),
                                    convertToLocalTime(app.getDate()).plusMinutes(app.getDuration())) )
                {
                    if(!app.isTaken())
                    {
                        freeAppointments.add(app);
                    }
                    freeAdmins = freeAdmins.stream().filter(x -> x.getId() != app.getAdminId().longValue()).toList();
                }


            }


            if(freeAdmins.isEmpty()) conflict = true;

            currentTime = currentTime.plusMinutes(15);
            if(conflict) continue;

            LocalDateTime date = forDate.atTime(currentTime.minusMinutes(15));
            User companyAdmin = freeAdmins.stream().findFirst().orElse(new User());

            Appointment freeApp = new Appointment(0L,companyId,companyAdmin.getId().longValue(),convertToDate(date),15,companyAdmin.getName(), companyAdmin.getSurname(), false);
            freeAppointments.add(freeApp);

        }

        return freeAppointments;
    }

    private static boolean isTimeConflicted(LocalTime timeFrom1, LocalTime timeTo1, LocalTime timeFrom2, LocalTime timeTo2) {
        return !timeTo1.isBefore(timeFrom2) && !timeTo2.isBefore(timeFrom1);
    }

    private static LocalTime convertToLocalTime(Date utilDate) {
        var instant = utilDate.toInstant();

        return instant.atZone(java.time.ZoneId.systemDefault()).toLocalTime();
    }

    private static Date convertToDate(LocalDateTime localDateTime) {
        var instant = localDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant();

        return Date.from(instant);
    }
}
