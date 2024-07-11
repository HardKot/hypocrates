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


import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StaffGateway implements IStaffGateway {
    private StaffService staffService;
    private EmailSender emailSender;
    private ConversionService conversionService;
    private TokenService tokenService;
    private StaffRoleService staffRoleService;

    @Override
    public void sendEmailRegistration(String email, String actionLink, Staff staff) {
        var model = new HashMap<String, String>();
        model.put("actionLink", actionLink);

        emailSender.sendEmail(email, "Registration.tfl", model);
    }

    @Override
    public Staff createStaff(Staff staff) {
        var staffSchema = conversionService.convert(staff, StaffSchema.class);
        staffSchema = staffService.createStaff(staffSchema);
        staff = conversionService.convert(staffSchema, Staff.class);
        return staff;
    }

    @Override
    public String generateToken(UUID userId) {
        return "adasdad";
    }

    public String generateToken(Long userId) {
        return tokenService.generateToken(userId);
    }

    public Staff createdFormToEntity(ICreateStaffForm form) {
        return conversionService.convert(form, Staff.class);
    }

    @Override
    public StaffRole getStaffRoleByName(String name) {
        var roleSchema = staffRoleService.getStaffRoleByName(name);
        return conversionService.convert(roleSchema, StaffRole.class);
    }

    @Override
    public StaffRole createStaffRole(StaffRole role) {
        var roleSchema = conversionService.convert(role, StaffRoleSchema.class);
        roleSchema =staffRoleService.createStaffRole(roleSchema);
        return conversionService.convert(roleSchema, StaffRole.class);
    }

    @Override
    public Staff mapToFrom(ICreateStaffForm form) {
        return conversionService.convert(form, Staff.class);
    }

    @Override
    public Staff saveStaff(Staff staff) {
        return null;
    }

}
