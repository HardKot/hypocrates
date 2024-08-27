package com.hypocrates.hypocrates.infrastructure.gateway;

import com.hypocrates.hypocrates._entities.staff.IStaffGateway;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.StaffRole;
import com.hypocrates.hypocrates.application.services.ConfirmedService;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.mapper.IStaffRoleMapper;
import com.hypocrates.hypocrates.repositories.ConfirmedCodeRepository;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.IStaffRepository;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.IStaffRoleRepository;
import com.hypocrates.hypocrates.configs.database.clinics.schema.StaffRoleSchema;
import com.hypocrates.hypocrates.configs.database.clinics.schema.StaffSchema;
import com.hypocrates.hypocrates.infrastructure.facade.EmailSenderFacade;
import com.hypocrates.hypocrates.infrastructure.facade.RandomFacade;
import com.hypocrates.hypocrates.infrastructure.facade.TemplateFacade;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.mapper.IStaffMapper;
import com.hypocrates.hypocrates.application.services.StaffService;
import com.hypocrates.hypocrates.application.useCase.staffInteract.dto.IStaffRegistration;
import freemarker.template.TemplateException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class StaffGateway implements IStaffGateway {
    private final IStaffMapper staffMapper;
    private final RandomFacade randomLib;
    private final EmailSenderFacade emailSender;
    private final ConfirmedCodeRepository confirmedCodeRepository;
    private final TemplateFacade templateLibs;
    private final IStaffRoleMapper staffRoleMapper;
    private final IStaffRoleRepository staffRoleRepository;
    private final StaffService staffService;
    private final ConfirmedService confirmedService;
    private final PasswordEncoder passwordEncoder;
    private final IStaffRepository staffRepository;

    @Override
    public Staff getByEmail(String email) {
        try {
            StaffSchema staffSchema = staffService.findByEmail(email);
            return staffMapper.schemaToModel(staffSchema);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public String sendActiveEmail(Staff staff) throws TemplateException, IOException {
        var code = confirmedService.createConfirmedCode();
        var message = templateLibs.getBody("ConfirmationEmailStaff", Map.of("code", code.getCode()));
        emailSender.sendEmail(staff.getEmail(), message);
        return code.getId().toString();
    }

    @Override
    public Staff saveStaff(Staff staff) {
        StaffSchema staffSchema = staffMapper.modelToSchema(staff);
        staffSchema = staffService.save(staffSchema);
        return staffMapper.schemaToModel(staffSchema);
    }

    @Override
    public StaffRole saveStaffRole(StaffRole staffRoleModel) {
        StaffRoleSchema staffRoleSchema = staffRoleMapper.modelToSchema(staffRoleModel);
        staffRoleSchema = staffRoleRepository.save(staffRoleSchema);
        staffRoleModel = staffRoleMapper.schemaToModel(staffRoleSchema);
        return staffRoleModel;
    }

    @Override
    public StaffRole getStaffRoleByName(String name) {
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

    @Override
    public Staff createStaffByForm(IStaffRegistration form) {
        return staffMapper.schemaToModel(staffService.createStaffByForm(form));
    }
}
