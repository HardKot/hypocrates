import {Field, GraphQLISODateTime, ObjectType} from "@nestjs/graphql";
import {Clinic, ClinicBuilder} from "@/domain/clinic";
import {ClinicSchema} from "@prisma/client";

@ObjectType()
export class ClinicModel {
  @Field()
  id: string

  @Field()
  name: string

  @Field({ nullable: true })
  address: string

  @Field({ nullable: true })
  avatarUrl: string

  @Field(type => GraphQLISODateTime)
  createAt: Date

  @Field(type => GraphQLISODateTime)
  updateAt: Date

  static from(clinic: Clinic) {
    const clinicModel = new ClinicModel();
    clinicModel.id = clinic.id;
    clinicModel.name = clinic.name;
    clinicModel.address = clinic.address;
    clinicModel.avatarUrl = clinic.avatarUrl;
    return clinicModel;
  }
  static fromSchema(schema: ClinicSchema) {
    const clinicModel = new ClinicModel();
    clinicModel.id = schema.id;
    clinicModel.name = schema.name;
    clinicModel.address = schema.address;
    clinicModel.avatarUrl = schema.avatarUrl;
    return clinicModel;
  }

  toClinic() {
    const builder = new ClinicBuilder();
      builder
        .setId(this.id)
        .setName(this.name)
        .setAddress(this.address)
        .setAvatarUrl(this.avatarUrl)
    return builder.build();
  }
  toSchema(): ClinicSchema {
    return {
      id: this.id,
      name: this.name,
      address: this.address,
      avatarUrl: this.avatarUrl,
      updateAt: this.updateAt,
      createAt:  this.createAt
    }
  }
}
