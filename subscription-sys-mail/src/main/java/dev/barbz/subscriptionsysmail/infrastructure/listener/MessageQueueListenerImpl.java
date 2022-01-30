package dev.barbz.subscriptionsysmail.infrastructure.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.barbz.subscriptionsysmail.domain.MailReceiver;
import dev.barbz.subscriptionsysmail.domain.listener.MessageQueueListener;
import dev.barbz.subscriptionsysmail.domain.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Implementation of the message queue listener contract
 */
@Slf4j
@Component
public class MessageQueueListenerImpl implements MessageQueueListener {

    private static final String SEND_NOTIFICATION_MAIL_QUEUE = "send-notification-mail";

    private final ObjectMapper mapper;

    private final MailService mailService;

    /**
     * Message queue listener constructor
     *
     * @param mailService mail service
     */
    public MessageQueueListenerImpl(MailService mailService) {
        this.mailService = mailService;
        this.mapper = new ObjectMapper()
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    /**
     * Listen the queue to send mail notification with the user specified in the message.
     * This message will be received as a JSON and properly mapped to {@link dev.barbz.subscriptionsysmail.domain.MailReceiver}
     * to lately use that data to send the mail.
     *
     * @param message subscription message as JSON
     */
    @Override
    @RabbitListener(queues = SEND_NOTIFICATION_MAIL_QUEUE)
    public void listenSendMailQueue(String message) {
        try {
            MailReceiver mailReceiver = mapper.readValue(message, MailReceiver.class);
            mailService.sendNotificationMail(mailReceiver);
        } catch (JsonProcessingException e) {
            log.error("Error trying to convert subscription object to JSON", e);
        }
    }
}
