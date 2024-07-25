package com.hypocrates.hypocrates.gateway;

import com.hypocrates.hypocrates.database.schema.StaffRoleSchema;
import com.hypocrates.hypocrates.database.schema.StaffSchema;
import com.hypocrates.hypocrates.core.domain.staff.IStaffGateway;
import com.hypocrates.hypocrates.core.domain.staff.Staff;
import com.hypocrates.hypocrates.core.domain.staff.StaffRole;
import com.hypocrates.hypocrates.service.EmailSender;
import com.hypocrates.hypocrates.service.StaffRoleService;
import com.hypocrates.hypocrates.service.StaffService;
import com.hypocrates.hypocrates.service.TokenService;
import com.hypocrates.hypocrates.core.useCase.RegistrationStaff.ICreateStaffForm;


import com.hypocrates.hypocrates.service.mapper.StaffMapper;
import com.hypocrates.hypocrates.service.mapper.StaffRoleMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
@AllArgsConstructor
public class StaffGateway implements IStaffGateway {
    private StaffService staffService;
    private EmailSender emailSender;
    private TokenService tokenService;
    private StaffRoleService staffRoleService;
    private StaffMapper staffMapper;
    private StaffRoleMapper staffRoleMapper;

    @Override
    public void sendEmailRegistration(String email, String actionLink, Staff staff) {
        var model = new HashMap<String, String>();
        model.put("actionLink", actionLink);

        emailSender.sendEmail(email, "Registration.ftl", model);
    }

    @Override
    public Staff createStaff(Staff staff) {
        var staffSchema = staffMapper.toSchema(staff, new StaffSchema());
        staffSchema = staffService.createStaff(staffSchema);
        staff = staffMapper.toEntity(staffSchema);
        return staff;
    }

    @Override
    public String generateToken(UUID userId) {
        return tokenService.generateToken(userId);
    }

    public Staff createdFormToEntity(ICreateStaffForm form) {
        return null; //conversionService.convert(form, Staff.class);
    }

    @Override
    public StaffRole getStaffRoleByName(String name) {
        var roleSchema = staffRoleService.getStaffRoleByName(name);
        return staffRoleMapper.toEntity(roleSchema);
    }

    @Override
    public StaffRole createStaffRole(StaffRole role) {
        var roleSchema = staffRoleMapper.toSchema(role, new StaffRoleSchema());
        roleSchema = staffRoleService.createStaffRole(roleSchema);
        return staffRoleMapper.toEntity(roleSchema);
    }

    @Override
    public Staff mapToFrom(ICreateStaffForm form) {
        return null;  // conversionService.convert(form, Staff.class);
    }

    @Override
    public Staff saveStaff(Staff staff) {
        var staffSchema = staffMapper.toSchema(staff, new StaffSchema());
        staffSchema = staffService.createStaff(staffSchema);
        staff = staffMapper.toEntity(staffSchema);
        return staff;
    }

    @Override
    public Staff getOwner() {
        var staffSchema = staffService.getOwner();
        if (staffSchema == null) { return null; }
        return staffMapper.toEntity(staffSchema);
    }

    @Override
    public Staff getByEmail(String email) {
        var staffSchema = staffService.findByEmail(email);
        if (staffSchema == null) { return null; }

        return staffMapper.toEntity(staffSchema);
    }

}
