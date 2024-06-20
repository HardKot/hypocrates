import {Injectable} from "@nestjs/common";
import {PrismaService} from "../service/prisma.service";
import {ClinicModel} from "@/infrastructure/models/Clinic.model";
import {StaffModel} from "@/infrastructure/models/Staff.model";

@Injectable()
export class StaffRepository {
  constructor(private prismaService: PrismaService) {}

  async getById(id: string) {
    return this
      .prismaService
      .userSchema
      .findUnique({where: {id}, include: { Staff: { include: { clinic: true, role: true } } , UserEmail: true, UserPhone: true }})
      .then(value => {
        if (!value) return null;
        const { Staff, UserEmail: email, UserPhone: phone, ...user } = value;
        const {  } = Staff;
        return null
      })
  }
}
