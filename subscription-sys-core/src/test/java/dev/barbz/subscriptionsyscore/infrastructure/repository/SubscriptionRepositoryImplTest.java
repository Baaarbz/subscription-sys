package dev.barbz.subscriptionsyscore.infrastructure.repository;

import dev.barbz.subscriptionsyscore.domain.Subscription;
import dev.barbz.subscriptionsyscore.domain.enums.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SubscriptionRepositoryImplTest {

    @SpyBean
    SubscriptionRepositoryImpl subscriptionRepository;

    @MockBean
    SubscriptionMongoRepository repository;

    static Subscription subscription;

    @BeforeAll
    static void setUp() {
        subscription = new Subscription()
                .setId(null)
                .setFirstName("name")
                .setLastName("lastName")
                .setEmail("email@mail.com")
                .setGender(Gender.NA)
                .setBirthday(LocalDate.now())
                .setConsent(true)
                .setCampaign("test");
    }

    @Test
    void save_then_OK() {
        when(repository.save(any(Subscription.class))).thenReturn(subscription);

        assertDoesNotThrow(() -> subscriptionRepository.save(subscription));
    }

    @Test
    void findById_then_OK() {
        when(repository.findById(anyString())).thenReturn(Optional.of(subscription));

        assertDoesNotThrow(() -> subscriptionRepository.findById("test"));
    }

    @Test
    void findAll_then_OK() {
        when(repository.findAll()).thenReturn(Collections.singletonList(subscription));

        assertDoesNotThrow(() -> subscriptionRepository.findAll());
    }

    @Test
    void delete_then_OK() {
        doNothing().when(repository).delete(subscription);

        assertDoesNotThrow(() -> subscriptionRepository.delete(subscription));
    }
}
