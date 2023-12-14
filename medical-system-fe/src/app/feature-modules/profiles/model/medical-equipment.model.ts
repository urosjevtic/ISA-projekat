export interface MedicalEquipment{
    id: number
    name: string,
    description: string,
    companyId: number,
}

export interface Order{
    equipment: MedicalEquipment,
    count: number
}

export interface UserOrder{
    id: number,
    order: Order[];
}
