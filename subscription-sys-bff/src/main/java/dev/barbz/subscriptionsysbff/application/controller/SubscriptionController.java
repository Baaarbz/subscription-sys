package dev.barbz.subscriptionsysbff.application.controller;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsysbff.domain.service.SubscriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("v1/subscriptions")
@Slf4j
public record SubscriptionController(SubscriptionService subscriptionService) {

    @GetMapping
    public ResponseEntity<List<SubscriptionResponse>> retrieveAll() {
        log.info("GET\t- list of subscriptions");
        List<SubscriptionResponse> response = subscriptionService.retrieveAllSubscriptions();
        return ResponseEntity.ok(response);
    }

    @GetMapping("{subscriptionId}")
    public ResponseEntity<SubscriptionResponse> retrieve(@PathVariable String subscriptionId) {
        log.info("GET\t- subscription {}", subscriptionId);
        SubscriptionResponse response = subscriptionService.retrieveSubscription(subscriptionId);
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
        subscriptionService.cancelSubscription(subscriptionId);
        return ResponseEntity.ok().build();
    }
}
