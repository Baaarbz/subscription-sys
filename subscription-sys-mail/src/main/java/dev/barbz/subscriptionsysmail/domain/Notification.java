package dev.barbz.subscriptionsysmail.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "notifications")
@Data
@Accessors(chain = true)
public class Notification {
    @Id
    private String id;
    private String campaign;
    private String subject;
    private String text;
}
