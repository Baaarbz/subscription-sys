package dev.barbz.subscriptionsyscore.infrastructure.messaging;

import dev.barbz.subscriptionsyscore.domain.Subscription;
import dev.barbz.subscriptionsyscore.domain.enums.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
public class MessageQueueImplTest {

    @SpyBean
    MessageQueueImpl messageQueue;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @Test
    void sendNewSubscriptionMessage_then_OK() {
        doNothing().when(rabbitTemplate).convertAndSend(anyString(), anyString());

        assertDoesNotThrow(() -> messageQueue.sendNewSubscriptionMessage(new Subscription()
                .setId(null)
                .setFirstName("name")
                .setLastName("lastName")
                .setEmail("email@mail.com")
                .setGender(Gender.NA)
                .setBirthday(LocalDate.now())
                .setConsent(true)
                .setCampaign("test")));
    }
}
