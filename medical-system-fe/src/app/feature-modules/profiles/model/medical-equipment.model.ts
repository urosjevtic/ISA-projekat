import { Appointment } from "./appointment.model";

export interface MedicalEquipment{
    id: number
    name: string,
    description: string,
    companyId: number,
    count?: number
}

export interface ReservationItem{
    equipment: MedicalEquipment,
    count: number
}

export interface Reservation{
    id?: number,
    reservationItems: ReservationItem[],
    appointment: Appointment,
    reserverId: number,
    delivered: boolean
}
