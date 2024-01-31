export interface Contract{
    username: string,
    startDate: Date,
    companyName: string,
    equipment: string[]
    contractCanceled?: boolean;
}
