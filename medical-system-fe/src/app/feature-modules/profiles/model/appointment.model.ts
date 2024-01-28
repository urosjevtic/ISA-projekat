export interface Appointment {
    id?: number
    companyId: number;
    adminId:number
    date: Date;
    duration: number;
    adminName: string;
    adminLastName: string;
}