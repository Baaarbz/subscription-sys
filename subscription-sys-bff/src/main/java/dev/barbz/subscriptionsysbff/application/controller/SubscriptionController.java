package dev.barbz.subscriptionsysbff.application.controller;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/subscriptions")
public record SubscriptionController() {

    private static final Logger LOG = LoggerFactory.getLogger(SubscriptionController.class);

    @GetMapping
    public ResponseEntity<SubscriptionsResponse> retrieveAll() {
        // TODO Add service call - ALL SUBSCRIPTIONS
        LOG.info("GET\t- list of subscriptions");
        return ResponseEntity.ok().build();
    }

    @GetMapping("{subscriptionId}")
    public ResponseEntity<SubscriptionResponse> retrieve(@PathVariable String subscriptionId) {
        // TODO Add service call - SUBSCRIPTION BY ID
        LOG.info("GET\t- subscription {}", subscriptionId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody RegisterSubscriptionRequest subscriptionRequest) throws URISyntaxException {
        // TODO Add service call - CREATE SUBSCRIPTION
        LOG.info("POST\t- register new subscription");
        // TODO CREATE HEADER REAL ID
        String id = UUID.randomUUID().toString();
        // Relative resource location
        URI resource = new URI(String.format("/v1/subscriptions/%s", id));
        return ResponseEntity.created(resource).build();
    }

    @DeleteMapping("{subscriptionId}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable String subscriptionId) {
        // TODO Add service call - CREATE SUBSCRIPTION
        LOG.info("DELETE\t- cancel subscription {}", subscriptionId);
        return ResponseEntity.ok().build();
    }
}
