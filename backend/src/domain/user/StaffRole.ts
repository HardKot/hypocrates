import {Clinic} from "@/domain/clinic";

export class StaffRole {
  constructor(
    public id?: string,
    public name?: string,
    public clinic?: Clinic,
    public rules?: string[],
  ) {
  }

  static listRules = new Set<string>([

  ])
}
