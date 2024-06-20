import {StaffRole} from "./StaffRole";
import {Clinic} from "@/domain/clinic";

export class StaffRoleBuilder {

  private id?: string
  private name?: string
  private clinic?: Clinic
  private rules?: string[]

  setId(id: string) {
    this.id = id
    return this
  }

  setName(name: string) {
    this.name = name
    return this
  }

  setClinic(clinic: Clinic) {
    this.clinic = clinic
    return this
  }

  addRule(rule: string) {
    if (!this.rules) {
      this.rules = []
    }
    if (StaffRole.listRules.has(rule)) this.rules.push(rule)
    return this
  }

  setRules(rules: string[]) {
    this.rules = rules
    return this
  }

  allRule() {
    this.rules = [...StaffRole.listRules.values()]
    return this
  }

  build() {
    return new StaffRole(this.id, this.name, this.clinic, this.rules)
  }
}
