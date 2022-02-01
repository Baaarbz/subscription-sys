package dev.barbz.subscriptionsysmail.domain.service;

import dev.barbz.subscriptionsysmail.domain.MailReceiver;
import dev.barbz.subscriptionsysmail.domain.Notification;
import dev.barbz.subscriptionsysmail.domain.exception.MailException;
import dev.barbz.subscriptionsysmail.domain.repository.NotificationRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static dev.barbz.subscriptionsysmail.domain.util.MailConstants.DEFAULT_MAIL;
import static dev.barbz.subscriptionsysmail.domain.util.MailConstants.MAIL_TEXT_CLIENT_TO_REPLACE;
import static dev.barbz.subscriptionsysmail.domain.util.MailUtil.validateReceiver;

/**
 * Implementation of mail service contract.
 */
@Service
public class MailServiceImpl implements MailService {

    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    /**
     * Mail service constructor
     *
     * @param notificationRepository notification repository
     * @param mailSender             mail sender data.
     */
    public MailServiceImpl(NotificationRepository notificationRepository, JavaMailSender mailSender) {
        this.notificationRepository = notificationRepository;
        this.mailSender = mailSender;
    }

    /**
     * Send to the user the mail with the data received from the message.
     * This message will contain also the campaign, that will be used to retrieve the correct mail template that will
     * be used.
     *
     * @param receiver mail receiver needed that to send the mail
     */
    @Override
    public void sendNotificationMail(MailReceiver receiver) {
        // Validate receiver fields
        validateReceiver(receiver);

        Optional<Notification> notificationOptional = notificationRepository.findByCampaign(receiver.getCampaign());
        // Check if there are a notification associated with the received campaign
        if (notificationOptional.isEmpty()) {
            notificationOptional = notificationRepository.findByCampaign(DEFAULT_MAIL);
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
