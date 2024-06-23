package com.hypocrates.hypocrates.useCase;

public sealed class StaffInteractError {
    public static final class EmailUsed extends StaffInteractError {}
    public static final class PhoneUsed extends StaffInteractError {}
    public static final class PasswordNotMatch extends StaffInteractError {}
}
