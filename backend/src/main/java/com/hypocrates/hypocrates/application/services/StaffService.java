package com.hypocrates.hypocrates.application.services;

import com.hypocrates.hypocrates._entities.staff.AppRule;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.mapper.IStaffMapper;
import com.hypocrates.hypocrates.repositories.ConfirmedCodeRepository;
import com.hypocrates.hypocrates.configs.database.admin.schema.ConfirmedCodeSchema;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.IStaffRepository;
import com.hypocrates.hypocrates.configs.database.clinics.schema.StaffSchema;
import com.hypocrates.hypocrates.infrastructure.configs.exception.NotFoundSchema;

import com.hypocrates.hypocrates.infrastructure.configs.exception.StaffNoHaveAccess;
import com.hypocrates.hypocrates.application.useCase.staffInteract.dto.IStaffRegistration;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@Validated
@AllArgsConstructor
public class StaffService {
    private final IStaffRepository staffRepository;
    private final ConfirmedCodeRepository confirmedCodeRepository;
    private final IStaffMapper staffMapper;

    public StaffSchema save(StaffSchema staffSchema) {
        if (staffSchema.getId() == null)
            throw new NotFoundSchema("Сотрудник с данным id не найден");
        return staffRepository.save(staffSchema);
    }

    public StaffSchema createStaffByForm(@Valid IStaffRegistration form) {
        StaffSchema staffSchema = staffMapper.formToSchema(form);
        return staffRepository.save(staffSchema);
    }

    public StaffSchema inviteStaff(@Valid IStaffRegistration form, StaffSchema invited) {
        if (!invited.hasAccess(AppRule.STAFF_CREATE)) throw new StaffNoHaveAccess("Пользователь не может создавать сотрудников");
        StaffSchema staffSchema = staffRepository.save(staffMapper.formToSchema(form));


        return staffSchema;
    }

    public StaffSchema findByEmail(String email) throws NotFoundSchema {
        return staffRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundSchema("Сотрудник не найден"));
    }

    public StaffSchema confirmedEmail(UUID confirmedId, String code) throws NotFoundSchema {
        ConfirmedCodeSchema confirmedCodeSchema = confirmedCodeRepository.findById(confirmedId).orElseThrow(() -> new NotFoundSchema("Код доступа не найден"));
        if (confirmedCodeSchema.checkCode(code)) {
            UUID uid = confirmedCodeSchema.getId();
            StaffSchema staff = staffRepository.findById(uid)
                    .orElseThrow(() -> new NotFoundSchema("Сотрудник с данным id не найден"));

            staff.setEmailIsActive(true);
            staffRepository.save(staff);
            return staff;
        }
        return null;
    }

}
