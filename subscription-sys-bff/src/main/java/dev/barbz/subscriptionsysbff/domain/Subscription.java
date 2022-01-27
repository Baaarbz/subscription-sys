package dev.barbz.subscriptionsysbff.domain;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;

import java.time.LocalDate;

public record Subscription(String id,
                           String firstName,
                           String lastName,
                           String email,
                           RegisterSubscriptionRequest.Gender gender,
                           LocalDate birthday,
                           Boolean consent,
                           String campaign) {
}
