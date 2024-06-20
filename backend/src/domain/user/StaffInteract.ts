import {Staff} from "./Staff";
import {StaffRole} from "./StaffRole";

export interface StaffInteract {
  getByEmail(email: string): Promise<Staff | null>
  create(staff: Staff): Promise<Staff>

  createRole(staffRole: StaffRole): Promise<StaffRole>
}
