package dev.barbz.subscriptionsysbff.infrastructure.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.barbz.subscriptionsysbff.domain.Subscription;
import dev.barbz.subscriptionsysbff.domain.enums.Gender;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class SubscriptionClientImplTest {

    @SpyBean
    SubscriptionClientImpl subscriptionClient;

    @MockBean
    WebClient subscriptionCoreClient;

    public static MockWebServer mockBackEnd;
    private static Subscription subscription;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
        subscription = new Subscription(
                null,
                "name",
                "lastName",
                "email@mail.com",
                Gender.NA,
                null,
                true,
                "test");
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        subscriptionCoreClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();

        subscriptionClient = new SubscriptionClientImpl(subscriptionCoreClient);
        ReflectionTestUtils.setField(subscriptionClient, "retrieveByIdPath", "test");
        ReflectionTestUtils.setField(subscriptionClient, "deletePath", "test");
    }

    @Test
    void createSubscription_OK() throws JsonProcessingException {
        mockBackEnd.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(subscription))
                .addHeader("Content-Type", "application/json"));

        assertNotNull(subscriptionClient.createSubscription(subscription));
    }

    @Test
    void getAllSubscriptions_OK() throws JsonProcessingException {
        mockBackEnd.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(Collections.singletonList(subscription)))
                .addHeader("Content-Type", "application/json"));

        assertNotNull(subscriptionClient.getAllSubscriptions());
    }

    @Test
    void getSubscriptionById_OK() throws JsonProcessingException {
        mockBackEnd.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(subscription))
                .addHeader("Content-Type", "application/json"));

        assertNotNull(subscriptionClient.getSubscriptionById("test"));
    }

    @Test
    void deleteSubscription_OK() {
        mockBackEnd.enqueue(new MockResponse()
                .setResponseCode(200)
                .addHeader("Content-Type", "application/json"));

        assertDoesNotThrow(() -> subscriptionClient.deleteSubscription("test"));
    }
}
