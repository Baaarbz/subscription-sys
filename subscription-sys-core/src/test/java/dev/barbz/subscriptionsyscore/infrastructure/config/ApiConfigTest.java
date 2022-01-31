package dev.barbz.subscriptionsyscore.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class ApiConfigTest {

    private final WebApplicationContextRunner applicationContextRunner =
            new WebApplicationContextRunner()
                    .withConfiguration(AutoConfigurations.of(ApiConfig.class));

    @Test
    void apiConfig_OK() {
        this.applicationContextRunner.run(context -> assertThat(context).getBean(OpenAPI.class).isNotNull());
    }
}
