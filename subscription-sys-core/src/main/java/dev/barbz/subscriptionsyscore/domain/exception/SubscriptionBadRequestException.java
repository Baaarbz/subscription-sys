package dev.barbz.subscriptionsyscore.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubscriptionBadRequestException extends RuntimeException {

    private String detailedMessage;

    public SubscriptionBadRequestException(String message, String detailedMessage) {
        super(message);
        this.detailedMessage = detailedMessage;
    }
}
