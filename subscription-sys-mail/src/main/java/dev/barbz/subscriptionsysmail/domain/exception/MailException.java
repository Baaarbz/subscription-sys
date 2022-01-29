package dev.barbz.subscriptionsysmail.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MailException extends RuntimeException {

    public MailException(String message) {
        super(message);
    }
}
