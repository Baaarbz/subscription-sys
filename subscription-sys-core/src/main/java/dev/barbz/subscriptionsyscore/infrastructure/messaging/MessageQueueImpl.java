package dev.barbz.subscriptionsyscore.infrastructure.messaging;

import dev.barbz.subscriptionsyscore.domain.Subscription;
import dev.barbz.subscriptionsyscore.domain.messaging.MessageQueue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageQueueImpl implements MessageQueue {

    @Value("${messaging.queue.send-mail}")
    private String sendMailQueue;

    private final RabbitTemplate rabbitTemplate;

    public MessageQueueImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendNewSubscriptionMessage(Subscription subscription) {
        rabbitTemplate.convertAndSend(sendMailQueue, subscription);
    }
}
