package dev.barbz.subscriptionsyscore.application.controller;

import dev.barbz.subscriptionsyscore.application.request.CreateSubscriptionRequest;
import dev.barbz.subscriptionsyscore.application.response.SubscriptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("v1/subscriptions")
@Slf4j
public record SubscriptionsCoreController() {

    @GetMapping
    public ResponseEntity<List<SubscriptionResponse>> retrieveAll() {
        log.info("GET\t- list of subscriptions");
//        List<SubscriptionResponse> response = subscriptionService.retrieveAllSubscriptions();
        return ResponseEntity.ok(null);
    }

    @GetMapping("{subscriptionId}")
    public ResponseEntity<SubscriptionResponse> retrieve(@PathVariable String subscriptionId) {
        log.info("GET\t- subscription {}", subscriptionId);
//        SubscriptionResponse response = subscriptionService.retrieveSubscription(subscriptionId);
        return ResponseEntity.ok(null);
    }

    @PostMapping("create")
    public ResponseEntity<SubscriptionResponse> register(@RequestBody CreateSubscriptionRequest subscriptionRequest) throws URISyntaxException {
        log.info("POST\t- create new subscription");
//        String id = subscriptionService.registerSubscription(subscriptionRequest);
        // Relative resource location
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("{subscriptionId}/delete")
    public ResponseEntity<Void> cancel(@PathVariable String subscriptionId) {
        log.info("DELETE\t- delete subscription {}", subscriptionId);
//        subscriptionService.cancelSubscription(subscriptionId);
        return ResponseEntity.ok().build();
    }
}
