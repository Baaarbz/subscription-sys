package dev.barbz.subscriptionsyscore.domain.util;

import dev.barbz.subscriptionsyscore.application.request.CreateSubscriptionRequest;
import dev.barbz.subscriptionsyscore.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsyscore.domain.Subscription;
import dev.barbz.subscriptionsyscore.domain.enums.Gender;
import dev.barbz.subscriptionsyscore.domain.exception.SubscriptionBadRequestException;
import dev.barbz.subscriptionsyscore.domain.messaging.SubscriptionMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubscriptionUtilTest {


    private static Subscription subscription;
    private static CreateSubscriptionRequest createRequest;

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

        createRequest = new CreateSubscriptionRequest(
                "name",
                "lastName",
                "email@mail.com",
                Gender.NA,
                LocalDate.now(),
                true,
                "test"
        );
    }

    @Test
    void mapToDomainSubscription_OK() {
        Subscription mappedSubscription = SubscriptionUtil.mapToDomainSubscription(createRequest);

        assertNull(mappedSubscription.getId());
        assertEquals(subscription, mappedSubscription);
    }

    @Test
    void mapToDomainSubscription_then_validateFields_throwsException() {
        // Fail campaign validation
        CreateSubscriptionRequest campaignNull = new CreateSubscriptionRequest("name", "lastName", "email@mail.com", Gender.NA, LocalDate.now(), true, null);
        assertThrows(SubscriptionBadRequestException.class, () -> SubscriptionUtil.mapToDomainSubscription(campaignNull));
        CreateSubscriptionRequest campaignEmpty = new CreateSubscriptionRequest("name", "lastName", "email@mail.com", Gender.NA, LocalDate.now(), true, "");;
        assertThrows(SubscriptionBadRequestException.class, () -> SubscriptionUtil.mapToDomainSubscription(campaignEmpty));

        // Fail first name validation
        CreateSubscriptionRequest firstNameNull = new CreateSubscriptionRequest(null, "lastName", "email@mail.com", Gender.NA, LocalDate.now(), true, "test");
        assertThrows(SubscriptionBadRequestException.class, () -> SubscriptionUtil.mapToDomainSubscription(firstNameNull));
        CreateSubscriptionRequest firstNameEmpty = new CreateSubscriptionRequest("", "lastName", "email@mail.com", Gender.NA, LocalDate.now(), true, "test");
        assertThrows(SubscriptionBadRequestException.class, () -> SubscriptionUtil.mapToDomainSubscription(firstNameEmpty));

        // Fail birthday validation
        CreateSubscriptionRequest birthdayNull = new CreateSubscriptionRequest("name", "lastName", "email@mail.com", Gender.NA, null, true, "test");
        assertThrows(SubscriptionBadRequestException.class, () -> SubscriptionUtil.mapToDomainSubscription(birthdayNull));

        // Fail consent validation
        CreateSubscriptionRequest consentNull = new CreateSubscriptionRequest("name", "lastName", "email@mail.com", Gender.NA, LocalDate.now(), null, "test");
        assertThrows(SubscriptionBadRequestException.class, () -> SubscriptionUtil.mapToDomainSubscription(consentNull));
        CreateSubscriptionRequest consentFalse = new CreateSubscriptionRequest("name", "lastName", "email@mail.com", Gender.NA, LocalDate.now(), false, "test");
        assertThrows(SubscriptionBadRequestException.class, () -> SubscriptionUtil.mapToDomainSubscription(consentFalse));

        // Fail mail validation
        CreateSubscriptionRequest mailNull = new CreateSubscriptionRequest("name", "lastName", null, Gender.NA, LocalDate.now(), true, "test");
        assertThrows(SubscriptionBadRequestException.class, () -> SubscriptionUtil.mapToDomainSubscription(mailNull));
        CreateSubscriptionRequest mailInvalid = new CreateSubscriptionRequest("name", "lastName", "emailmail-com", Gender.NA, LocalDate.now(), true, "test");
        assertThrows(SubscriptionBadRequestException.class, () -> SubscriptionUtil.mapToDomainSubscription(mailInvalid));

        // Fail gender validation
        CreateSubscriptionRequest genderNull = new CreateSubscriptionRequest("name", "lastName", "email@mail.com", null, LocalDate.now(), true, "test");
        assertThrows(SubscriptionBadRequestException.class, () -> SubscriptionUtil.mapToDomainSubscription(genderNull));
    }

    @Test
    void mapToSubscriptionResponseList_OK() {
        List<SubscriptionResponse> subscriptionResponse = SubscriptionUtil
                .mapToSubscriptionResponseList(Collections.singletonList(subscription));

        assertNotNull(subscriptionResponse);
        assertEquals(1, subscriptionResponse.size());
    }

    @Test
    void mapToSubscriptionResponse_OK() {
        SubscriptionResponse subscriptionResponse = SubscriptionUtil
                .mapToSubscriptionResponse(subscription);

        assertNotNull(subscriptionResponse);
    }

    @Test
    void mapToSubscriptionMessage_OK() {
        SubscriptionMessage subscriptionMessage = SubscriptionUtil
                .mapToSubscriptionMessage(subscription);

        assertNotNull(subscriptionMessage);
    }
}
