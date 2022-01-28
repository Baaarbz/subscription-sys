package dev.barbz.subscriptionsyscore.domain.messaging;

import dev.barbz.subscriptionsyscore.domain.enums.Gender;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SubscriptionMessage {
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String campaign;
}
