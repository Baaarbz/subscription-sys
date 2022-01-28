package dev.barbz.subscriptionsyscore.application.response;

import dev.barbz.subscriptionsyscore.domain.enums.Gender;

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
