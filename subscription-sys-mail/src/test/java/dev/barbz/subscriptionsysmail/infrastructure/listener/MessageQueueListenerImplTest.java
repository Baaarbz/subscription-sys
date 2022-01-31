package dev.barbz.subscriptionsysmail.infrastructure.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.barbz.subscriptionsysmail.domain.MailReceiver;
import dev.barbz.subscriptionsysmail.domain.service.MailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(SpringExtension.class)
class MessageQueueListenerImplTest {

    @SpyBean
    MessageQueueListenerImpl messageQueueListener;

    @MockBean
    MailService mailService;

    @Test
    void listenSendMailQueue_then_OK() throws JsonProcessingException {
        MailReceiver mailReceiver = new MailReceiver();
        String jsonReceived = new ObjectMapper().writeValueAsString(mailReceiver);

        doNothing().when(mailService).sendNotificationMail(any(MailReceiver.class));

        assertDoesNotThrow(() -> messageQueueListener.listenSendMailQueue(jsonReceived));
    }
}
