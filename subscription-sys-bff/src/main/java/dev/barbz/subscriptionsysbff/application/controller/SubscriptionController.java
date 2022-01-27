package dev.barbz.subscriptionsysbff.application.controller;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionsResponse;
import dev.barbz.subscriptionsysbff.domain.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("v1/subscriptions")
@Slf4j
public record SubscriptionController(SubscriptionService subscriptionService) {

    @GetMapping
    public ResponseEntity<SubscriptionsResponse> retrieveAll() {
        log.info("GET\t- list of subscriptions");
        SubscriptionsResponse response = subscriptionService.retrieveAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{subscriptionId}")
    public ResponseEntity<SubscriptionResponse> retrieve(@PathVariable String subscriptionId) {
        log.info("GET\t- subscription {}", subscriptionId);
        SubscriptionResponse response = subscriptionService.retrieve(subscriptionId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody RegisterSubscriptionRequest subscriptionRequest) throws URISyntaxException {
        log.info("POST\t- register new subscription");
        String id = subscriptionService.registerSubscription(subscriptionRequest);
        // Relative resource location
        URI resource = new URI(String.format("/v1/subscriptions/%s", id));
        return ResponseEntity.created(resource).build();
    }

    @DeleteMapping("{subscriptionId}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable String subscriptionId) {
        log.info("DELETE\t- cancel subscription {}", subscriptionId);
        subscriptionService.cancel(subscriptionId);
        return ResponseEntity.ok().build();
    }
}
