package com.hypocrates.hypocrates.infrastructure.staff;

import com.hypocrates.hypocrates.entity.staff.IStaffGateway;
import com.hypocrates.hypocrates.entity.staff.StaffModel;
import com.hypocrates.hypocrates.entity.staff.StaffRoleModel;
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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class StaffGateway implements IStaffGateway {
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    private final RandomLib randomLib;
    private final EmailSenderLib emailSender;
    private final ConfirmedCodeRepository confirmedCodeRepository;
    private final TemplateLibs templateLibs;
    private final StaffRoleMapper staffRoleMapper;
    private final StaffRoleRepository staffRoleRepository;

    @Override
    public StaffModel getByEmail(String email) {
        return staffRepository.findByEmail(email).map(staffMapper::schemaToModel).orElse(null);
    }

    @Override
    public String sendActiveEmail(StaffModel staff) throws TemplateException, IOException {
        var code = new ConfirmedCodeSchema();
        code.setCode(randomLib.randomCode());
        var message = templateLibs.getBody("ConfirmationEmailStaff", Map.of("code", code.getCode()));
        emailSender.sendEmail(staff.getEmail(), message);
        code = confirmedCodeRepository.save(code);
        return code.getId().toString();
    }

    @Override
    public StaffModel saveStaff(StaffModel staff) {
        StaffSchema staffSchema = staffMapper.modelToSchema(staff);
        staffSchema = staffRepository.save(staffSchema);
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
}
