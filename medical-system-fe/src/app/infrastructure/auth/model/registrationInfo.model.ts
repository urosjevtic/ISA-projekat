export interface RegistrationInfo{
    email: string,
    username: string,
    password: string,
    confirmPassword: string,
    name: string,
    surname: string,
    phone: string
    profession?: string,

    country: string,
    city: string,
    companyName: string,
    address: string,
}

export interface Country{
    id: number,
    name: string
}

export interface City{
    country: string,
    cities: string[]
}