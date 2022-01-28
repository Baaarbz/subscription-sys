package dev.barbz.subscriptionsyscore.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SubscriptionConstants {

    public final static String NO_VALID_FIELD_EXCEPTION = "NO_VALID_FIELDS";

    public final static String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
}
