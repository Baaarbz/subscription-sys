package dev.barbz.subscriptionsysbff.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionException extends RuntimeException {
    private String detailedMessage;
    public SubscriptionException(String message, String detailedMessage) {
        super(message);
        this.detailedMessage = detailedMessage;
    }
}
