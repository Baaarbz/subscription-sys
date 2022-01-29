package dev.barbz.subscriptionsysmail.domain.service;

import dev.barbz.subscriptionsysmail.application.message.MailReceiver;

public interface MailService {

    void sendNotificationMail(MailReceiver mailReceiver);
}
