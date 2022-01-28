package dev.barbz.subscriptionsyscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SubscriptionSysCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionSysCoreApplication.class, args);
    }

}
