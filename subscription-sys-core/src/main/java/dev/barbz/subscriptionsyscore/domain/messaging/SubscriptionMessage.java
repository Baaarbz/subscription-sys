package dev.barbz.subscriptionsyscore.domain.messaging;

import dev.barbz.subscriptionsyscore.domain.enums.Gender;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Subscription data to send the message to the queue
 */
@Data
@Accessors(chain = true)
public class SubscriptionMessage {
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String campaign;
}
