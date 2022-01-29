package dev.barbz.subscriptionsysmail.domain.service;

import dev.barbz.subscriptionsysmail.application.message.MailReceiver;
import dev.barbz.subscriptionsysmail.domain.Notification;
import dev.barbz.subscriptionsysmail.domain.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record MailServiceImpl(NotificationRepository notificationRepository) implements MailService {

    @Override
    public void sendNotificationMail(MailReceiver mailReceiver) {
        Optional<Notification> notification = notificationRepository.findByCampaign(mailReceiver.getCampaign());


    }
}
