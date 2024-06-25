package com.hypocrates.hypocrates.gateway;

import com.hypocrates.hypocrates.domain.IStaffGateway;
import com.hypocrates.hypocrates.domain.Staff;
import com.hypocrates.hypocrates.dto.StaffDTO;
import com.hypocrates.hypocrates.service.EmailSender;
import com.hypocrates.hypocrates.service.StaffService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StaffGateway implements IStaffGateway {
    private StaffService staffService;
    private EmailSender emailSender;

    @Override
    public void sendEmail(String email, String message) {
        emailSender.sendEmail(email, message);
    }

    @Override
    public Staff createStaff(Staff staff) {
//        staffService.createStaff(new StaffDTO());
        return staff;
    }

    @Override
    public String generateToken(Long userId) {
        return null;
    }
}
