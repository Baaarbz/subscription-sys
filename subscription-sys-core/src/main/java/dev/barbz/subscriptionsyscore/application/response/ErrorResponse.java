package dev.barbz.subscriptionsyscore.application.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response body when an error is fired.
 */
@Schema(description = "Error response body.")
public record ErrorResponse(
        @Schema(description = "Short error description.", example = "INVALID_BODY_REQUEST")
        String error,
        @Schema(description = "Detailed description of the error.", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
        String detailedMessage) {
}
