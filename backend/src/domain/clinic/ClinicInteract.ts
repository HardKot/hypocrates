import {Clinic} from "./Clinic";

export interface ClinicInteract {
  create(clinic: Clinic): Promise<Clinic>
}
