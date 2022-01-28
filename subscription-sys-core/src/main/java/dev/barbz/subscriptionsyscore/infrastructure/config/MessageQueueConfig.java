package dev.barbz.subscriptionsyscore.infrastructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfig {

    @Value("${messaging.queue.send-notification-mail}")
    private String sendMailNotificationQueue;

    @Bean
    public Queue mailingQueue() {
        return new Queue(sendMailNotificationQueue);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
