package dev.barbz.subscriptionsysmail.domain.service;

import dev.barbz.subscriptionsysmail.application.message.MailReceiver;
import dev.barbz.subscriptionsysmail.domain.Notification;
import dev.barbz.subscriptionsysmail.domain.Receiver;
import dev.barbz.subscriptionsysmail.domain.exception.MailException;
import dev.barbz.subscriptionsysmail.domain.repository.NotificationRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static dev.barbz.subscriptionsysmail.domain.util.MailConstants.MAIL_TEXT_CLIENT_TO_REPLACE;
import static dev.barbz.subscriptionsysmail.domain.util.MailUtil.mapToReceiver;

@Service
public record MailServiceImpl(NotificationRepository notificationRepository,
                              JavaMailSender mailSender) implements MailService {

    @Override
    public void sendNotificationMail(MailReceiver mailReceiver) {
        Receiver receiver = mapToReceiver(mailReceiver);

        Optional<Notification> notificationOptional = notificationRepository.findByCampaign(receiver.getCampaign());
        // Check if there are a notification associated with the received campaign
        if (notificationOptional.isEmpty()) {
            throw new MailException("There aren't notification for the campaign: " + mailReceiver.getCampaign());
        }
        Notification notification = notificationOptional.get();
        // Replace mail text with client name
        String text = notification.getText().replace(MAIL_TEXT_CLIENT_TO_REPLACE, receiver.getFirstName());

        // Set mail data
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(receiver.getEmail());
        mail.setSubject(notification.getSubject());
        mail.setText(text);
        // Send mail
        mailSender.send(mail);
    }
}
