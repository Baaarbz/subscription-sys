package dev.barbz.subscriptionsysbff.application.response;

import dev.barbz.subscriptionsysbff.domain.enums.Gender;

import java.time.LocalDate;

public record SubscriptionResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Gender gender,
        LocalDate birthday,
        Boolean consent,
        String campaign) {
}
