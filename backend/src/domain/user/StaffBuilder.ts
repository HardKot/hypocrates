import {Staff} from "./Staff";
import {Clinic} from "@/domain/clinic";
import {StaffRole} from "./StaffRole";

export class StaffBuilder {
  private id?: string
  private firstname?: string
  private lastname?: string
  private patronymic?: string
  private birthday?: Date
  private avatarUrl?: string
  private phone?: string
  private email?: string
  private clinic?: Clinic
  private role?: StaffRole

  public setClinic(clinic: Clinic) {
    this.clinic = clinic
    return this;
  }

  public setRole(role: StaffRole) {
    this.role = role
    return this;
  }

  public setId(id: string) {
    this.id = id
    return this;
  }

  public setFirstname(firstname: string) {
    this.firstname = firstname
    return this;
  }

  public setLastname(lastname: string) {
    this.lastname = lastname
    return this;
  }

  public setPatronymic(patronymic: string) {
    this.patronymic = patronymic
    return this;
  }

  public setBirthday(birthday: Date) {
    this.birthday = birthday
    return this;
  }

  public setAvatarUrl(avatarUrl: string) {
    this.avatarUrl = avatarUrl
    return this;
  }

  public setPhone(phone: string) {
    this.phone = phone
    return this;
  }

  public setEmail(email: string) {
    this.email = email
    return this;
  }

  public build() {
    return new Staff(
      this.id,
      this.firstname,
      this.lastname,
      this.patronymic,
      this.birthday,
      this.avatarUrl,
      this.phone,
      this.email,
      this.clinic,
      this.role,
    )
  }
}
