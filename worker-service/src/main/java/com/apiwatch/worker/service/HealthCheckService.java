package com.apiwatch.worker.service;

import com.apiwatch.contracts.constants.EventType;
import com.apiwatch.contracts.events.*;
import com.apiwatch.worker.client.EndpointHttpClient;
import com.apiwatch.worker.publisher.HealthCheckCompletedEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class HealthCheckService {

    private final EndpointHttpClient httpClient;
    private final HealthCheckCompletedEventPublisher publisher;

    public HealthCheckService(EndpointHttpClient httpClient, HealthCheckCompletedEventPublisher publisher) {
        this.httpClient = httpClient;
        this.publisher = publisher;
    }

    private HealthCheckCompleted constructHealthCheckCompleted(HealthCheckRequested request, EndpointHttpClient.RequestMetrics metrics) {
        HealthCheckCompletedPayload payload = constructHealthCheckCompletedPayload(request.getPayload(), metrics);
        EventMetadata metadata = constructEventMetadata(request.getMetadata());
        HealthCheckCompleted completed = new HealthCheckCompleted();

        completed.setMetadata(metadata);
        completed.setPayload(payload);

        return completed;
    }

    private HealthCheckCompletedPayload constructHealthCheckCompletedPayload(HealthCheckRequestedPayload requestPayload, EndpointHttpClient.RequestMetrics metrics) {
        HealthCheckCompletedPayload payload = new HealthCheckCompletedPayload();

        payload.setEndpointId(requestPayload.getEndpointId());
        payload.setUrl(requestPayload.getUrl());
        payload.setMethod(requestPayload.getMethod());
        payload.setStatus(metrics.statusCode() == null? CheckStatus.STATUS_DOWN: CheckStatus.STATUS_UP);
        payload.setHttpStatus(metrics.statusCode());
        payload.setResponseTimeMs(metrics.durationMillis());
        payload.setErrorMessage(metrics.errorMessage());
        payload.setCheckedAt(metrics.checkedAt());

        return payload;
    }

    private EventMetadata constructEventMetadata(EventMetadata requestMetadata) {
        EventMetadata metadata = new EventMetadata();

        metadata.setEventId(requestMetadata.getEventId());
        metadata.setEventType(EventType.HEALTH_CHECK_COMPLETED);
        metadata.setSchemaVersion(requestMetadata.getSchemaVersion());
        metadata.setOccurredAt(OffsetDateTime.now(ZoneOffset.UTC));
        metadata.setCorrelationId(requestMetadata.getCorrelationId());

        return metadata;
    }

    private void sendPayloadToPublisher(HealthCheckCompleted completedPayload) {
        publisher.publish(completedPayload);
    }

    public void performHealthCheck(HealthCheckRequested healthCheckRequest) {
        String url = healthCheckRequest.getPayload().getUrl();
        String method = healthCheckRequest.getPayload().getMethod();
        int timeoutMs = healthCheckRequest.getPayload().getTimeoutMs();

        EndpointHttpClient.RequestMetrics metrics = httpClient.invokeRequest(url, method,  timeoutMs);

        System.out.println(
                "Response Code: " + metrics.statusCode() + "\n" +
                "Time: " + metrics.durationMillis()
        );

        HealthCheckCompleted completedRequest = constructHealthCheckCompleted(healthCheckRequest, metrics);
        sendPayloadToPublisher(completedRequest);

    }
}
