export interface RegistrationInfo{
    email: string,
    password: string,
    confirmPassword: string,
    name: string,
    surname: string,
    city: string,
    country: string,
    phone: string
    profession?: string,

    companyName: string,
    companyCountry: string,
    companyCity: string,
    companyAddress: string,
    companyWebsite?: string
}