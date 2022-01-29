package dev.barbz.subscriptionsysmail.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Receiver {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String campaign;
}
