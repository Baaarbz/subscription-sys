package dev.barbz.subscriptionsyscore.domain;

import dev.barbz.subscriptionsyscore.domain.enums.Gender;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

//@Document(collection = "subscription")
//public record Subscription(@Id String id,
//                           String firstName,
//                           String lastName,
//                           String email,
//                           Gender gender,
//                           LocalDate birthday,
//                           Boolean consent,
//                           String campaign) {
//}
@Document(collection = "subscription")
@Data
@Accessors(chain = true, fluent = true)
public class Subscription {
    @Id
    String id;
    String firstName;
    String lastName;
    String email;
    Gender gender;
    LocalDate birthday;
    Boolean consent;
    String campaign;


}

