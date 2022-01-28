package dev.barbz.subscriptionsysmail.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Subscription {
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private String campaign;
}

