package dev.barbz.subscriptionsysmail.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Mail constants
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MailConstants {

    /**
     * In case of not found the mail associated to the campaign then send a default message.
     */
    public final static String DEFAULT_MAIL = "default";

    /**
     * Static field in the mail template to replace with the name of the subscriber.
     */
    public final static String MAIL_TEXT_CLIENT_TO_REPLACE = "{subscriber}";

    /**
     * Mail regex pattern.
     */
    public final static String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
}
