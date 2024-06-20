import {ClinicRegistrationForm} from "./ClinicRegistrationForm";
import {StaffBuilder, StaffInteract, StaffRoleBuilder} from "@/domain/user";
import {ClinicBuilder, ClinicInteract} from "@/domain/clinic";

export class ClinicRegistration {
  private clinicBuilder = new ClinicBuilder();
  private staffBuilder = new StaffBuilder();
  private staffRoleBuilder = new StaffRoleBuilder();

  constructor(
    private readonly staffInteract: StaffInteract,
    private readonly clinicInteract: ClinicInteract,
  ) {
  }


  async execute(form: ClinicRegistrationForm) {
    this.readForm(form);
    this.initRole();

    let staff = await this.staffInteract.getByEmail(form.email);
    if (staff) return new Error("User with this email already exists");

    const clinic = await this.clinicInteract.create(this.clinicBuilder.build());
    const ownerRole = await this.staffInteract.createRole(this.staffRoleBuilder.setClinic(clinic).build());
    staff = await this.staffInteract.create(this.staffBuilder.setRole(ownerRole).setClinic(clinic).build());

    return staff
  }

  private readForm(form: ClinicRegistrationForm) {
    this.staffBuilder
      .setFirstname(form.firstname)
      .setLastname(form.lastname)
      .setPatronymic(form.patronymic)
      .setBirthday(form.birthday)
      .setAvatarUrl(form.avatarUrl)
      .setPhone(form.phone)
      .setEmail(form.email)

    this.clinicBuilder
      .setName(form.clinicName)
      .setAvatarUrl(form.clinicAvatarUrl)
      .setAddress(form.clinicAddress)
  }

  private initRole() {
    this.staffRoleBuilder
      .setName("owner")
      .allRule()
  }
}
