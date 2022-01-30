package dev.barbz.subscriptionsysbff.application.controller;

import dev.barbz.subscriptionsysbff.application.request.RegisterSubscriptionRequest;
import dev.barbz.subscriptionsysbff.application.response.ErrorResponse;
import dev.barbz.subscriptionsysbff.application.response.SubscriptionResponse;
import dev.barbz.subscriptionsysbff.domain.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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

/**
 * Subscription controller.
 * Definition of the API.
 */
@Slf4j
@RestController
@RequestMapping(path = "v1/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Subscription", description = "Subscription API with the available operations.")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    /**
     * Subscription controller constructor.
     *
     * @param subscriptionService subscription service
     */
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    /**
     * Retrieve all the subscriptions registered.
     *
     * @return list of subscriptions.
     */
    @GetMapping
    @Operation(summary = "Get all subscriptions.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found subscriptions."),
            @ApiResponse(responseCode = "204", description = "There are not subscriptions.", content = @Content),
    })
    public ResponseEntity<List<SubscriptionResponse>> retrieveAll() {
        log.info("GET\t- list of subscriptions");
        List<SubscriptionResponse> response = subscriptionService.retrieveAllSubscriptions();
        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieve subscription by its id.
     *
     * @param subscriptionId subscription id.
     * @return requested subscription.
     */
    @Operation(summary = "Get subscription by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found subscription by id."),
            @ApiResponse(responseCode = "404", description = "Subscription not found.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("{subscriptionId}")
    public ResponseEntity<SubscriptionResponse> retrieve(
            @Parameter(description = "ID of the requested subscription.", example = "61f4588813a91001bf1bf486")
            @PathVariable String subscriptionId) {
        log.info("GET\t- subscription {}", subscriptionId);
        SubscriptionResponse response = subscriptionService.retrieveSubscription(subscriptionId);
        return ResponseEntity.ok(response);
    }

    /**
     * Register a new subscription in the system, once the process is finished the user will receive a notification
     * in his mail inbox.
     *
     * @param subscriptionRequest subscription request body.
     * @return location header with the relative path of the registered subscription.
     * @throws URISyntaxException URI syntax exception.
     */
    @Operation(summary = "Register a new subscription in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subscription registered successfully ", content = @Content,
                    headers = {@Header(name = "Location",
                            description = "Indicates the relative path to retrieve the create subscription")}),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class)),
                    description = "Invalid body, can be caused by fields with empty/nulls values, wrong formats"),
    })
    @PostMapping(path = "register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody RegisterSubscriptionRequest subscriptionRequest) throws URISyntaxException {
        log.info("POST\t- register new subscription");
        String id = subscriptionService.registerSubscription(subscriptionRequest);
        // Relative resource location
        URI resource = new URI(String.format("/v1/subscriptions/%s", id));
        return ResponseEntity.created(resource).build();
    }

    /**
     * Remove requested subscription of the system.
     *
     * @param subscriptionId subscription id.
     * @return void.
     */
    @Operation(summary = "Remove requested subscription of the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Removed subscription", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class)),
                    description = "Subscription to remove not found."),
    })
    @DeleteMapping("{subscriptionId}/cancel")
    public ResponseEntity<Void> cancel(
            @Parameter(description = "ID of the subscription to delete", example = "61f4588813a91001bf1bf486")
            @PathVariable String subscriptionId) {
        log.info("DELETE\t- cancel subscription {}", subscriptionId);
        subscriptionService.cancelSubscription(subscriptionId);
        return ResponseEntity.ok().build();
    }
}
