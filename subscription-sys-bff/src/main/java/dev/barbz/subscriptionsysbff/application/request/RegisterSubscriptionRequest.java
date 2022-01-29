package dev.barbz.subscriptionsysbff.application.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.barbz.subscriptionsysbff.domain.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;


@Schema(description = "Subscriber needed data to save in the system and send mails.")
public record RegisterSubscriptionRequest(
        @Schema(description = "Subscriber first name.", example = "John")
        @JsonProperty(required = true)
        String firstName,
        @Schema(description = "Subscriber last name.", example = "Cena")
        @JsonProperty(required = true)
        String lastName,
        @Schema(description = "Mail to send the notification.", example = "john.cena@email.com")
        @JsonProperty(required = true)
        String email,
        @Schema(description = "Subscriber gender to, in case of needed, chose the right treat in the mail.", example = "MALE")
        @JsonProperty(required = true)
        Gender gender,
        @Schema(description = "Birthday", example = "2000-12-31", format = "yyyy-MM-dd")
        @JsonProperty(required = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate birthday,
        @Schema(description = "Explicit consent to send mails", example = "true")
        @JsonProperty(required = true)
        Boolean consent,
        @Schema(description = "Campaign which is subscribed", example = "adidas-campaign")
        @JsonProperty(required = true)
        String campaign
) {
}
