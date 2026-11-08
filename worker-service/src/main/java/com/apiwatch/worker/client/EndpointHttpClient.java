package com.apiwatch.worker.client;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Component
public class EndpointHttpClient {

    private RestClient createRestClientWithCustomTimeout(int responseTimeoutMillis, int... otherTimeoutsMillis) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        if(otherTimeoutsMillis.length > 0) {
            factory.setConnectTimeout(Duration.ofMillis(otherTimeoutsMillis[0]));
        } else {
            factory.setConnectTimeout(Duration.ofSeconds(2));
        }

        factory.setReadTimeout(Duration.ofMillis(responseTimeoutMillis));

        return RestClient.builder()
                .requestFactory(factory)
                .build();

    }

    public RequestMetrics invokeRequest(String url, String method, int timeoutMillis) {
        RestClient restClient = createRestClientWithCustomTimeout(timeoutMillis);

        RestClient.RequestHeadersUriSpec<?> httpMethod = switch (method) {
            case "GET" -> restClient.get();
            case "POST" -> restClient.post();
            case "PUT" -> restClient.put();
            case "DELETE" -> restClient.delete();
            default -> null;
        };

        if(httpMethod == null) {
            throw new IllegalArgumentException("HTTP method can only be GET, POST, PUT, DELETE");
        }

        Long start = System.nanoTime();
        Long durationMillis = null;
        OffsetDateTime now = null;

        try {
            start = System.nanoTime();
            now = OffsetDateTime.now(ZoneOffset.UTC);

            // Fetch the response entity without throwing exceptions for 4xx/5xx errors
            ResponseEntity<Void> response = httpMethod
                    .uri(url)
                    .exchange((request, clientResponse) ->
                            ResponseEntity.status(clientResponse.getStatusCode()).build()
                    );

            durationMillis = (System.nanoTime() - start)/1_000_000;
            return new RequestMetrics(response.getStatusCode().value(), durationMillis, null, now);

        } catch (ResourceAccessException e) {
            if (durationMillis == null) {
                durationMillis = (System.nanoTime() - start)/1_000_000;
            }
            return new RequestMetrics(null, durationMillis, "Timed Out", now);
        } catch (Exception e) {
            // TODO: silently swallows all other errors, including programmatic bugs - need to change
            if (durationMillis == null) {
                durationMillis = (System.nanoTime() - start)/1_000_000;
            }
            return new RequestMetrics(null, durationMillis, e.getMessage(), now);
        }
    }

    public record RequestMetrics(Integer statusCode, Long durationMillis, String errorMessage, OffsetDateTime checkedAt) {}

}
