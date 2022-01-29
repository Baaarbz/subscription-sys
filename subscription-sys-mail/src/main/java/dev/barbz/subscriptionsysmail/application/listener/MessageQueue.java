package dev.barbz.subscriptionsysmail.application.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.barbz.subscriptionsysmail.application.message.MailReceiver;
import dev.barbz.subscriptionsysmail.domain.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageQueue {

    private static final String SEND_NOTIFICATION_MAIL_QUEUE = "send-notification-mail";

    private final ObjectMapper mapper;

    private final MailService mailService;

    public MessageQueue(MailService mailService) {
        this.mailService = mailService;
        this.mapper = new ObjectMapper()
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    @RabbitListener(queues = SEND_NOTIFICATION_MAIL_QUEUE)
    public void listenSendMailQueue(String jsonSubscription) {
        try {
            MailReceiver mailReceiver = mapper.readValue(jsonSubscription, MailReceiver.class);
            mailService.sendNotificationMail(mailReceiver);
        } catch (JsonProcessingException e) {
            log.error("Error trying to convert subscription object to JSON", e);
        }
    }
}
