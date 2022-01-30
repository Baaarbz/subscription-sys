package dev.barbz.subscriptionsysmail.domain.service;

import dev.barbz.subscriptionsysmail.domain.MailReceiver;

/**
 * Definition of mail service contract.
 */
public interface MailService {

    /**
     * Send to the user the mail with the data received from the message.
     * This message will contain also the campaign, that will be used to retrieve the correct mail template that will
     * be used.
     *
     * @param receiver mail receiver needed that to send the mail
     */
    void sendNotificationMail(MailReceiver receiver);
}
