package dev.barbz.subscriptionsyscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "dev.barbz.subscriptionsyscore.infrastructure.repository")
@EnableScheduling
public class SubscriptionSysCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionSysCoreApplication.class, args);
    }

}
