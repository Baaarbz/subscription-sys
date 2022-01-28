package dev.barbz.subscriptionsyscore.infrastructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfig {

    @Value("${messaging.queue.send-mail}")
    private String sendMailQueue;

    @Bean
    public Queue mailingQueue() {
        return new Queue(sendMailQueue);
    }
}
