package dev.barbz.subscriptionsysmail.domain;

import dev.barbz.subscriptionsysmail.domain.enums.Gender;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Mail receiver domain.
 * This object will contain the needed data to send the mail
 */
@Data
@Accessors(chain = true)
public class MailReceiver {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String campaign;
}
