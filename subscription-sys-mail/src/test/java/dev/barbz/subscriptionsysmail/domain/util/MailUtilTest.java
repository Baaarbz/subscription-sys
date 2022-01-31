package dev.barbz.subscriptionsysmail.domain.util;

import dev.barbz.subscriptionsysmail.domain.MailReceiver;
import dev.barbz.subscriptionsysmail.domain.enums.Gender;
import dev.barbz.subscriptionsysmail.domain.exception.MailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MailUtilTest {

    @Test
    void validateReceiver_then_OK() {
        MailReceiver mailReceiver = new MailReceiver()
                .setCampaign("test")
                .setEmail("email@email.com")
                .setFirstName("test")
                .setGender(Gender.NA)
                .setLastName("last name");
        assertDoesNotThrow(() -> MailUtil.validateReceiver(mailReceiver));
    }

    @Test
    void validateReceiver_then_throwsMailException() {
        MailReceiver mailReceiver = new MailReceiver()
                .setCampaign("test")
                .setEmail("email@email.com")
                .setFirstName("test")
                .setGender(Gender.NA)
                .setLastName("last name");

        mailReceiver.setGender(null);
        assertThrows(MailException.class, () -> MailUtil.validateReceiver(mailReceiver));

        mailReceiver.setFirstName("");
        assertThrows(MailException.class, () -> MailUtil.validateReceiver(mailReceiver));

        mailReceiver.setFirstName(null);
        assertThrows(MailException.class, () -> MailUtil.validateReceiver(mailReceiver));

        mailReceiver.setEmail("test");
        assertThrows(MailException.class, () -> MailUtil.validateReceiver(mailReceiver));

        mailReceiver.setEmail(null);
        assertThrows(MailException.class, () -> MailUtil.validateReceiver(mailReceiver));

        mailReceiver.setCampaign("");
        assertThrows(MailException.class, () -> MailUtil.validateReceiver(mailReceiver));

        mailReceiver.setCampaign(null);
        assertThrows(MailException.class, () -> MailUtil.validateReceiver(mailReceiver));
    }
}
