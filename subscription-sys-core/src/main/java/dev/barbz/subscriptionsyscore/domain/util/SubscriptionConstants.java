package dev.barbz.subscriptionsyscore.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constant class
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SubscriptionConstants {

    /**
     * This constant will be used to fire an exception and also use it in the error field of
     * {@link dev.barbz.subscriptionsyscore.application.response.ErrorResponse}.
     */
    public final static String NO_VALID_FIELD_EXCEPTION = "NO_VALID_FIELDS";

    /**
     * Mail regex pattern.
     */
    public final static String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
}
