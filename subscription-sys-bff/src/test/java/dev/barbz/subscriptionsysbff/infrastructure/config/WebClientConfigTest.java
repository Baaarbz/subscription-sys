package dev.barbz.subscriptionsysbff.infrastructure.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;

class WebClientConfigTest {

    private final WebApplicationContextRunner applicationContextRunner =
            new WebApplicationContextRunner()
                    .withConfiguration(AutoConfigurations.of(WebClientConfig.class));

    @Test
    void subscriptionCoreClient_OK() {
        this.applicationContextRunner.run(context -> assertThat(context).getBean(WebClient.class).isNotNull());
    }
}
