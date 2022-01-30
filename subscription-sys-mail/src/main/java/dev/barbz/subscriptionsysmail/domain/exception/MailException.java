package dev.barbz.subscriptionsysmail.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Mail runtime exception thrown when an invalid data is received.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MailException extends RuntimeException {

    /**
     * Mail exception constructor.
     *
     * @param message short message error.
     */
    public MailException(String message) {
        super(message);
    }
}
