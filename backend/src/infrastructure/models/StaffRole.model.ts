import {Field, GraphQLISODateTime, ObjectType} from "@nestjs/graphql";
import {ClinicModel} from "./Clinic.model";
import {StaffRole, StaffRoleBuilder} from "@/domain/user";
import {ClinicSchema, StaffRoleSchema} from "@prisma/client";

@ObjectType()
export class StaffRoleModel {
  @Field()
  id: string

  @Field()
  name: string

  @Field(type => ClinicModel)
  clinic: ClinicModel

  @Field()
  rules: string[]

  @Field(type => GraphQLISODateTime)
  createAt: Date

  @Field(type => GraphQLISODateTime)
  updateAt: Date

  static from(staffRole: StaffRole) {
    const staffRoleModel = new StaffRoleModel();
    staffRoleModel.id = staffRole.id;
    staffRoleModel.name = staffRole.name;
    staffRoleModel.rules = staffRole.rules;
    staffRoleModel.clinic = ClinicModel.from(staffRole.clinic);
    return staffRoleModel;
  }
  static fromSchema(schema: StaffRoleSchema & { clinic: ClinicSchema }) {
    const staffRoleModel = new StaffRoleModel();
    staffRoleModel.id = schema.id;
    staffRoleModel.name = schema.name;
    staffRoleModel.rules = schema.rules;
    staffRoleModel.clinic = ClinicModel.fromSchema(schema.clinic);
    return staffRoleModel;
  }

  toStaffRole() {
    const builder = new StaffRoleBuilder();
    builder
      .setId(this.id)
      .setName(this.name)
      .setRules(this.rules)
      .setClinic(this.clinic.toClinic())
    return builder.build();
  }

  toSchema(): StaffRoleSchema {
    return {
      id: this.id,
      name: this.name,
      rules: this.rules,
      clinicId: this.clinic.id,
      updateAt: this.updateAt,
      createAt:  this.createAt
    }
  }
}
