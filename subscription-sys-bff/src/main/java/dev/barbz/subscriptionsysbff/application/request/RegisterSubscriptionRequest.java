package dev.barbz.subscriptionsysbff.application.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.barbz.subscriptionsysbff.domain.enums.Gender;

import java.time.LocalDate;

public record RegisterSubscriptionRequest(@JsonProperty(required = true) String firstName,
                                          @JsonProperty(required = true) String lastName,
                                          @JsonProperty(required = true) String email,
                                          @JsonProperty(required = true) Gender gender,
                                          @JsonProperty(required = true) @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDate birthday,
                                          @JsonProperty(required = true) Boolean consent,
                                          @JsonProperty(required = true) String campaign) {
}
