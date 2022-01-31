package dev.barbz.subscriptionsysmail.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Mail constants
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MailConstants {

    /**
     * Static field in the mail template to replace with the name of the subscriber.
     */
    public final static String MAIL_TEXT_CLIENT_TO_REPLACE = "{subscriber}";

    /**
     * Mail regex pattern.
     */
    public final static String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
}
