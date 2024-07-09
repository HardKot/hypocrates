package com.hypocrates.hypocrates.core.useCase;

public sealed class StaffInteractError {
    public static final class EmailUsed extends StaffInteractError {}
    public static final class PhoneUsed extends StaffInteractError {}
    public static final class PasswordNotMatch extends StaffInteractError {}
}
