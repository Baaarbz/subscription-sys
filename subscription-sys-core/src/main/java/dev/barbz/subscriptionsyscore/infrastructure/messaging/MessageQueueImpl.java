package dev.barbz.subscriptionsyscore.infrastructure.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.barbz.subscriptionsyscore.domain.Subscription;
import dev.barbz.subscriptionsyscore.domain.messaging.MessageQueue;
import dev.barbz.subscriptionsyscore.domain.messaging.SubscriptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static dev.barbz.subscriptionsyscore.domain.util.SubscriptionUtil.mapToSubscriptionMessage;

@Slf4j
@Component
public class MessageQueueImpl implements MessageQueue {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;
    @Value("${messaging.queue.send-notification-mail}")
    private String sendNotificationMailQueue;

    public MessageQueueImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = new ObjectMapper();
    }

    @Override
    public void sendNewSubscriptionMessage(Subscription subscription) {
        try {
            SubscriptionMessage subscriptionMessage = mapToSubscriptionMessage(subscription);
            String json = mapper.writeValueAsString(subscriptionMessage);
            rabbitTemplate.convertAndSend(sendNotificationMailQueue, json);
        } catch (JsonProcessingException e) {
            log.error("Error trying to convert subscription object to JSON", e);
        }
    }
}
