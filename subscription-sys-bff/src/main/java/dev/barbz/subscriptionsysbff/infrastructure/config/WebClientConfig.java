package dev.barbz.subscriptionsysbff.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Web client configuration class.
 * Define the web clients used along the application.
 */
@Configuration
@Slf4j
public class WebClientConfig {

    @Value("${services.subscription-sys-core.base-url}")
    private String subscriptionCoreUrl;

    /**
     * Subscription core client configured with:
     * <ul>
     *     <li>Accept header: JSON</li>
     *     <li>Content-Type header: JSON</li>
     *     <li>Logging request and responses in DEBUG level</li>
     * </ul>
     *
     * @return {@link WebClient} with the base configuration to realize different request to the
     * subscription core service.
     */
    @Bean
    public WebClient subscriptionCoreClient() {
        return clientConfigurer()
                .baseUrl(subscriptionCoreUrl)
                .build();
    }

    private WebClient.Builder clientConfigurer() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filters(exchangeFilterFunctions -> {
                    exchangeFilterFunctions.add(logRequest());
                    exchangeFilterFunctions.add(logResponse());
                });
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            if (log.isDebugEnabled()) {
                log.debug("Request {}\t: {}", clientRequest.method(), clientRequest.url());
                clientRequest
                        .headers()
                        .forEach((name, values) -> values.forEach(value -> log.debug("{}\t: {}", name, value)));

            }
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.debug("RECEIVED: {} - {}", clientResponse.rawStatusCode(), clientResponse.statusCode().getReasonPhrase());
            return Mono.just(clientResponse);
        });
    }
}
