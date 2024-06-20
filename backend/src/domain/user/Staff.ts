import { Clinic } from "@/domain/clinic";
import {StaffRole} from "./StaffRole";
import {User} from "./User";

export class Staff extends User {
  constructor(id?: string,
              firstname?: string,
              lastname?: string,
              patronymic?: string,
              birthday?: Date,
              avatarUrl?: string,
              phone?: string,
              email?: string,
              public clinic?: Clinic,
              public role?: StaffRole) {
    super(id,
    firstname,
    lastname,
    patronymic,
    birthday,
    avatarUrl,
    phone,
    email);
  }
}
