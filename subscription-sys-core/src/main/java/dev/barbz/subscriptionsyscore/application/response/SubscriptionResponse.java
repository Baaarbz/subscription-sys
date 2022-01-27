package dev.barbz.subscriptionsyscore.application.response;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;

import java.time.LocalDate;

public record SubscriptionResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        RegisterSubscriptionRequest.Gender gender,
        LocalDate birthday,
        Boolean consent,
        String campaign) {
}
