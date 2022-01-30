package dev.barbz.subscriptionsyscore.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Open API Configuration class
 */
@Configuration
public class ApiConfig {

    /**
     * Define basic information about the API.
     *
     * @return open API bean.
     */
    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setUrl("https://github.com/Baaarbz");
        contact.setName("Eduardo 'Barbz'");

        Info info = new Info()
                .version("1.0")
                .contact(contact)
                .title("Subscription System Core");

        return new OpenAPI()
                .info(info);
    }
}
