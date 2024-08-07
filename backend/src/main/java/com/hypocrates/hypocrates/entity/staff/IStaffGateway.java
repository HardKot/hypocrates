package com.hypocrates.hypocrates.entity.staff;

import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.UUID;

public interface IStaffGateway {
    StaffModel getByEmail(String email);
    String sendActiveEmail(StaffModel staff) throws TemplateException, IOException;
    StaffModel saveStaff(StaffModel staff);

    StaffRoleModel saveStaffRole(StaffRoleModel staffRole);

    StaffRoleModel getStaffRoleByName(String roleName);
}
