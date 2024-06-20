export interface ClinicRegistrationForm {
  readonly firstname: string,
  readonly lastname: string,
  readonly patronymic?: string,
  readonly birthday?: Date,
  readonly avatarUrl?: string,
  readonly phone: string,
  readonly email: string,

  readonly clinicName: string,
  readonly clinicAddress: string,
  readonly clinicAvatarUrl?: string,
}
