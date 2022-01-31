package dev.barbz.subscriptionsysmail.domain.service;

import dev.barbz.subscriptionsysmail.domain.MailReceiver;
import dev.barbz.subscriptionsysmail.domain.Notification;
import dev.barbz.subscriptionsysmail.domain.enums.Gender;
import dev.barbz.subscriptionsysmail.domain.exception.MailException;
import dev.barbz.subscriptionsysmail.domain.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MailServiceImplTest {

    private static MailReceiver mailReceiver = new MailReceiver()
            .setCampaign("test")
            .setEmail("email@email.com")
            .setFirstName("test")
            .setGender(Gender.NA)
            .setLastName("last name");
    @SpyBean
    MailServiceImpl mailService;
    @MockBean
    NotificationRepository notificationRepository;
    @MockBean
    JavaMailSender mailSender;

    @Test
    void sendNotificationMail_then_OK() {
        when(notificationRepository.findByCampaign(anyString()))
                .thenReturn(
                        Optional.of(new Notification().setText("mock"))
                );

        doNothing().when(mailSender).send(any(SimpleMailMessage.class));
        assertDoesNotThrow(() -> mailService.sendNotificationMail(mailReceiver));
    }

    @Test
    void sendNotificationMail_then_throwException() {
        when(notificationRepository.findByCampaign(anyString()))
                .thenReturn(Optional.empty());

        assertThrows(MailException.class, () -> mailService.sendNotificationMail(mailReceiver));
    }
}
