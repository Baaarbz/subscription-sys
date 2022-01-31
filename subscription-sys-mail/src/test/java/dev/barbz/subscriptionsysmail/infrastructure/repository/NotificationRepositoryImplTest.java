package dev.barbz.subscriptionsysmail.infrastructure.repository;

import dev.barbz.subscriptionsysmail.domain.Notification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class NotificationRepositoryImplTest {

    @SpyBean
    NotificationRepositoryImpl notificationRepository;

    @MockBean
    NotificationMongoRepository repository;

    @Test
    void findByCampaign_then_OK() {
        when(repository.findNotificationByCampaign(anyString()))
                .thenReturn(Optional.of(new Notification()));

        assertDoesNotThrow(() -> notificationRepository.findByCampaign("test"));
    }
}
