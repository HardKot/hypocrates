package com.hypocrates.hypocrates.useCase.clinicInteract;

import com.hypocrates.hypocrates.useCase.InteractError;

public sealed class ClinicInteractError extends InteractError {
    public ClinicInteractError(String message) {
        super(message);
    }

    public int getCode() {
        return 100;
    }
    public static final class ClinicEmailExistsException extends ClinicInteractError {
        public ClinicEmailExistsException(String message) {
            super(message);
        }
    }
    public static final class ClinicNoCreated extends ClinicInteractError {
        public ClinicNoCreated() {
            super("Не удалось создать клинику. Попробуйте позже");
        }

        public int getCode() {
            return super.getCode() + 1;
        }
    }

    public static final class ErrorSenderEmail extends ClinicInteractError {
        public ErrorSenderEmail() {
            super("Не удалось отправит подтверждающиее сообщение");
        }

    }
}
