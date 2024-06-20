import {Field, GraphQLISODateTime, ObjectType, Scalar } from "@nestjs/graphql";
import {ClinicModel} from "./Clinic.model";
import {StaffRoleModel} from "./StaffRole.model";
import {Staff, StaffBuilder} from "@/domain/user";
import {ClinicSchema, StaffRoleSchema, StaffSchema, UserSchema} from "@prisma/client";

@ObjectType()
export class StaffModel {
  @Field()
  id: string

  @Field()
  firstname: string

  @Field()
  lastname: string

  @Field()
  patronymic: string

  @Field(type => GraphQLISODateTime)
  birthday: Date

  @Field()
  avatarUrl: string

  @Field(type => ClinicModel)
  clinic: ClinicModel

  @Field(type => [StaffRoleModel])
  role: StaffRoleModel


  @Field(type => GraphQLISODateTime)
  createAt: Date

  @Field(type => GraphQLISODateTime)
  updateAt: Date

  private userId: string

  static from(staff: Staff) {
    const staffModel = new StaffModel();

    staffModel.id = staff.id;
    staffModel.firstname = staff.firstname;
    staffModel.lastname = staff.lastname;
    staffModel.patronymic = staff.patronymic;
    staffModel.birthday = staff.birthday;
    staffModel.avatarUrl = staff.avatarUrl;
    staffModel.clinic = ClinicModel.from(staff.clinic);
    staffModel.role = StaffRoleModel.from(staff.role);

    return staffModel;
  }
  static fromSchema(schema: StaffSchema & { clinic: ClinicSchema, role: StaffRoleSchema & { clinic: ClinicSchema }, user: UserSchema }) {
    const staffModel = new StaffModel();
    staffModel.id = schema.id;
    staffModel.firstname = schema.user.firstname;
    staffModel.lastname = schema.user.lastname;
    staffModel.patronymic = schema.user.patronymic;
    staffModel.birthday = schema.user.birthday;
    staffModel.avatarUrl = schema.user.avatarUrl;
    staffModel.clinic = ClinicModel.fromSchema(schema.clinic);
    staffModel.role = StaffRoleModel.fromSchema(schema.role);
    staffModel.userId = schema.userId
  }

  toStaff() {
    const builder = new StaffBuilder();
    builder
      .setId(this.id)
      .setFirstname(this.firstname)
      .setLastname(this.lastname)
      .setPatronymic(this.patronymic)
      .setBirthday(this.birthday)
      .setAvatarUrl(this.avatarUrl)
      .setClinic(this.clinic.toClinic())
      .setRole(this.role.toStaffRole())
    return builder.build();
  }
  toSchema(): StaffSchema & { user: UserSchema } {
    return {
      id: this.id,
      userId: this.userId,
      user: {
        id: this.userId,
        firstname: this.firstname,
        lastname: this.lastname,
        patronymic: this.patronymic,
        birthday: this.birthday,
        avatarUrl: this.avatarUrl,
      },
      clinicId: this.clinic.id,
      staffRoleId: this.role.id,
      updateAt: this.updateAt,
      createAt:  this.createAt,
    }
  }
}
