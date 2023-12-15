import { Appointment } from "./appointment.model"
import { MedicalEquipment, Order } from "./medical-equipment.model"

export interface Reservation {
    id?: number
    appointment: Appointment,
    equipment: MedicalEquipment[]
}