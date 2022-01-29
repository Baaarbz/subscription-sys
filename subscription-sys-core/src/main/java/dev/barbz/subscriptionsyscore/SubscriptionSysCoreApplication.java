package dev.barbz.subscriptionsyscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "dev.barbz.subscriptionsyscore.infrastructure.repository")
public class SubscriptionSysCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubscriptionSysCoreApplication.class, args);
    }

}
