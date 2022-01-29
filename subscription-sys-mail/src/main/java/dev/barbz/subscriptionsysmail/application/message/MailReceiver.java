package dev.barbz.subscriptionsysmail.application.message;

import dev.barbz.subscriptionsysmail.domain.Gender;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MailReceiver {
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String campaign;
}

