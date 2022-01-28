package dev.barbz.subscriptionsysmail.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageQueue {

    private final ObjectMapper mapper;

    private static final String SEND_NOTIFICATION_MAIL_QUEUE = "send-notification-mail";

    public MessageQueue() {
        this.mapper = new ObjectMapper()
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    @RabbitListener(queues = SEND_NOTIFICATION_MAIL_QUEUE)
    public void listenSendMailQueue(String jsonSubscription) {
        try {
            Subscription subscription = mapper.readValue(jsonSubscription, Subscription.class);
            System.out.println(subscription);
        } catch (JsonProcessingException e) {
            log.error("Error trying to convert subscription object to JSON", e);
        }
    }
}
