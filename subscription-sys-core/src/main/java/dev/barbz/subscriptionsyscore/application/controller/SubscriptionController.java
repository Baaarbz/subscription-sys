package dev.barbz.subscriptionsyscore.application.controller;

import dev.barbz.subscriptionsyscore.application.request.CreateSubscriptionRequest;
import dev.barbz.subscriptionsyscore.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsyscore.domain.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/subscriptions")
@Slf4j
public record SubscriptionController(SubscriptionService subscriptionService) {

    @GetMapping
    public ResponseEntity<List<SubscriptionResponse>> retrieveAll() {
        log.info("GET\t- list of subscriptions");
        List<SubscriptionResponse> response = subscriptionService.retrieveAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{subscriptionId}")
    public ResponseEntity<SubscriptionResponse> retrieve(@PathVariable String subscriptionId) {
        log.info("GET\t- subscription {}", subscriptionId);
        SubscriptionResponse response = subscriptionService.retrieveById(subscriptionId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("create")
    public ResponseEntity<SubscriptionResponse> register(@RequestBody CreateSubscriptionRequest subscriptionRequest) {
        log.info("POST\t- create new subscription");
        SubscriptionResponse response = subscriptionService.create(subscriptionRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{subscriptionId}/delete")
    public ResponseEntity<Void> cancel(@PathVariable String subscriptionId) {
        log.info("DELETE\t- delete subscription {}", subscriptionId);
        subscriptionService.delete(subscriptionId);
        return ResponseEntity.ok().build();
    }
}
