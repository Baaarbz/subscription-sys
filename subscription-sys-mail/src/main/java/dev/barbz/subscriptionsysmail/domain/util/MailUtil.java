package dev.barbz.subscriptionsysmail.domain.util;

import dev.barbz.subscriptionsysmail.application.message.MailReceiver;
import dev.barbz.subscriptionsysmail.domain.MailReceiver;
import dev.barbz.subscriptionsysmail.domain.exception.MailException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MailUtil {

    public static MailReceiver mapToReceiver(MailReceiver mailReceiver) {
        validateReceiver(mailReceiver);
        return new MailReceiver()
                .setEmail(mailReceiver.getEmail())
                .setGender(mailReceiver.getGender())
                .setFirstName(mailReceiver.getFirstName())
                .setLastName(mailReceiver.getLastName())
                .setCampaign(mailReceiver.getCampaign());
    }

    private static void validateReceiver(MailReceiver mailReceiver) {
        if (isNull(mailReceiver.getCampaign()) || mailReceiver.getCampaign().length() == 0) {
            throw new MailException("campaign can not be null or empty");
        }
        if (isNull(mailReceiver.getEmail()) || mailReceiver.getEmail().length() == 0) {
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
