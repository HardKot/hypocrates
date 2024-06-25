package com.hypocrates.hypocrates.domain;

public interface IStaffGateway {
    void sendEmail(String email, String message);

    Staff createStaff(Staff staff);

    String generateToken(Long userId);
}
