package dev.barbz.subscriptionsyscore.domain;

import dev.barbz.subscriptionsyscore.domain.enums.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public record Subscription(@Id String id,
                           String firstName,
                           String lastName,
                           String email,
                           Gender gender,
                           LocalDate birthday,
                           Boolean consent,
                           String campaign) {
}

