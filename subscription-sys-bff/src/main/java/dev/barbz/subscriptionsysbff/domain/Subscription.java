package dev.barbz.subscriptionsysbff.domain;

import dev.barbz.subscriptionsysbff.domain.enums.Gender;

import java.time.LocalDate;

/**
 * Business domain subscription
 */
public record Subscription(String id,
                           String firstName,
                           String lastName,
                           String email,
                           Gender gender,
                           LocalDate birthday,
                           Boolean consent,
                           String campaign) {
}
