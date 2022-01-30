package dev.barbz.subscriptionsyscore.domain;

import dev.barbz.subscriptionsyscore.domain.enums.Gender;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Business domain subscription
 */
@Document(collection = "subscriptions")
@Data
@Accessors(chain = true)
public class Subscription {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private LocalDate birthday;
    private Boolean consent;
    private String campaign;
}

