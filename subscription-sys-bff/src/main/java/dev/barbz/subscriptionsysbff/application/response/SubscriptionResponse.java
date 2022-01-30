package dev.barbz.subscriptionsysbff.application.response;

import dev.barbz.subscriptionsysbff.domain.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

/**
 * Body response when a subscription it is requested.
 */
@Schema(description = "Subscriber saved data in the system.")
public record SubscriptionResponse(
        @Schema(description = "Subscription id.", example = "61f4588813a91001bf1bf486")
        String id,
        @Schema(description = "Subscriber first name.", example = "John")
        String firstName,
        @Schema(description = "Subscriber last name.", example = "Cena")
        String lastName,
        @Schema(description = "Mail to send the notification.", example = "john.cena@email.com")
        String email,
        @Schema(description = "Subscriber gender to, in case of needed, chose the right treat in the mail.", example = "MALE")
        Gender gender,
        @Schema(description = "Birthday", example = "2000-12-31", format = "yyyy-MM-dd")
        LocalDate birthday,
        @Schema(description = "Explicit consent to send mails", example = "true")
        Boolean consent,
        @Schema(description = "Campaign which is subscribed", example = "adidas-campaign")
        String campaign) {
}
