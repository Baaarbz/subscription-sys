package dev.barbz.subscriptionsysmail.domain.service;

import dev.barbz.subscriptionsysmail.domain.MailReceiver;

public interface MailService {

    void sendNotificationMail(MailReceiver mailReceiver);
}
