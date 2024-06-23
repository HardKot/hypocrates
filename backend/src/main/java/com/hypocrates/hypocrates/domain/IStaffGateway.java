package com.hypocrates.hypocrates.domain;

public interface IStaffGateway {
    void sendEmail(String email, String message);
    void validatePhone(String phone);

    boolean emailUsed(String email);
    boolean phoneUsed(String phone);

    Staff createStaff(Staff staff);

    String generateToken(Long userId);
}
