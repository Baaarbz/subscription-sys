package dev.barbz.subscriptionsysbff.application.response;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriberRequest;

import java.time.LocalDate;
import java.util.UUID;

public record SubscriptionResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        RegisterSubscriberRequest.Gender gender,
        LocalDate birthday,
        Boolean consent,
        String campaign) {
}
