import {Injectable} from "@nestjs/common";
import {PrismaService} from "../service/prisma.service";
import {ClinicModel} from "@/infrastructure/models/Clinic.model";

@Injectable()
export class ClinicRepository {
  constructor(private prismaService: PrismaService) {}

  async getById(id: string) {
    return this
      .prismaService
      .clinicSchema
      .findUnique({where: {id}, include: {  }})
      .then(value => ClinicModel.fromSchema(value))
  }
}
