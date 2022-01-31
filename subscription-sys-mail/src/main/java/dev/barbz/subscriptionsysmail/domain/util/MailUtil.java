package dev.barbz.subscriptionsysmail.domain.util;

import dev.barbz.subscriptionsysmail.domain.MailReceiver;
import dev.barbz.subscriptionsysmail.domain.exception.MailException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

import static dev.barbz.subscriptionsysmail.domain.util.MailConstants.EMAIL_PATTERN;
import static java.util.Objects.isNull;

/**
 * Mail utility class.
 * This utility class contains methods to do validations.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MailUtil {

    /**
     * Validate the mail receiver data.
     * In case the field is not valid, the {@link MailException} will be fired
     *
     * @param mailReceiver mail receiver data.
     */
    public static void validateReceiver(MailReceiver mailReceiver) {
        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

        if (isNull(mailReceiver.getCampaign()) || mailReceiver.getCampaign().length() == 0) {
            throw new MailException("campaign can not be null or empty");
        }
        if (isNull(mailReceiver.getEmail()) || !emailPattern.matcher(mailReceiver.getEmail()).matches()) {
            throw new MailException("email can not be null or empty");
        }
        if (isNull(mailReceiver.getFirstName()) || mailReceiver.getFirstName().length() == 0) {
            throw new MailException("firstName can not be null or empty");
        }
        if (isNull(mailReceiver.getGender())) {
            throw new MailException("gender can not be null or empty");
        }
    }
}
