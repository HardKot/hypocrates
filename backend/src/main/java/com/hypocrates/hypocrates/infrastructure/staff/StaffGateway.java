package com.hypocrates.hypocrates.infrastructure.staff;

import com.hypocrates.hypocrates.entity.staff.IStaffGateway;
import com.hypocrates.hypocrates.entity.staff.StaffModel;
import com.hypocrates.hypocrates.entity.staff.StaffRoleModel;
import com.hypocrates.hypocrates.infrastructure.common.ConfirmedService;
import com.hypocrates.hypocrates.infrastructure.config.database.admin.repository.ConfirmedCodeRepository;
import com.hypocrates.hypocrates.infrastructure.config.database.admin.schema.ConfirmedCodeSchema;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.repository.StaffRepository;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.repository.StaffRoleRepository;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.StaffRoleSchema;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.schema.StaffSchema;
import com.hypocrates.hypocrates.infrastructure.libs.EmailSenderLib;
import com.hypocrates.hypocrates.infrastructure.libs.RandomLib;
import com.hypocrates.hypocrates.infrastructure.libs.TemplateLibs;
import com.hypocrates.hypocrates.infrastructure.staffRole.StaffRoleMapper;
import freemarker.template.TemplateException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class StaffGateway implements IStaffGateway {
    private final StaffMapper staffMapper;
    private final RandomLib randomLib;
    private final EmailSenderLib emailSender;
    private final ConfirmedCodeRepository confirmedCodeRepository;
    private final TemplateLibs templateLibs;
    private final StaffRoleMapper staffRoleMapper;
    private final StaffRoleRepository staffRoleRepository;
    private final StaffService staffService;
    private final ConfirmedService confirmedService;
    private final PasswordEncoder passwordEncoder;
    private final StaffRepository staffRepository;

    @Override
    public StaffModel getByEmail(String email) {
        try {
            StaffSchema staffSchema = staffService.findByEmail(email);
            return staffMapper.schemaToModel(staffSchema);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public String sendActiveEmail(StaffModel staff) throws TemplateException, IOException {
        var code = confirmedService.createConfirmedCode();
        var message = templateLibs.getBody("ConfirmationEmailStaff", Map.of("code", code.getCode()));
        emailSender.sendEmail(staff.getEmail(), message);
        return code.getId().toString();
    }

    @Override
    public StaffModel saveStaff(StaffModel staff) {
        StaffSchema staffSchema = staffMapper.modelToSchema(staff);
        staffSchema = staffService.save(staffSchema);
        return staffMapper.schemaToModel(staffSchema);
    }

    @Override
    public StaffRoleModel saveStaffRole(StaffRoleModel staffRoleModel) {
        StaffRoleSchema staffRoleSchema = staffRoleMapper.modelToSchema(staffRoleModel);
        staffRoleSchema = staffRoleRepository.save(staffRoleSchema);
        staffRoleModel = staffRoleMapper.schemaToModel(staffRoleSchema);
        return staffRoleModel;
    }

    @Override
    public StaffRoleModel getStaffRoleByName(String name) {
        return staffRoleRepository.findByName(name).map(staffRoleMapper::schemaToModel).orElse(null);
    }

    @Override
    public String passwordEncoder(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean emailExists(String email) {
        return staffRepository.existsByEmail(email);
    }
}