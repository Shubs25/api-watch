package com.apiwatch.monitor.service;

import com.apiwatch.contracts.constants.EventType;
import com.apiwatch.contracts.events.EventMetadata;
import com.apiwatch.contracts.events.HealthCheckRequested;
import com.apiwatch.contracts.events.HealthCheckRequestedPayload;
import com.apiwatch.monitor.constants.RabbitMQConstants;
import com.apiwatch.monitor.entity.MonitoredEndpointEntity;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class HealthCheckEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public HealthCheckEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private EventMetadata generateEventMetadata(EventType eventType) {
        EventMetadata eventMetadata = new EventMetadata();

        eventMetadata.setEventId(UUID.randomUUID());
        eventMetadata.setEventType(eventType);
        eventMetadata.setSchemaVersion(1);
        eventMetadata.setOccurredAt(OffsetDateTime.now(ZoneOffset.UTC));
        eventMetadata.setCorrelationId(UUID.randomUUID());

        return eventMetadata;
    }

    private HealthCheckRequested generateHealthCheckRequestedEvent(MonitoredEndpointEntity entity) {
        HealthCheckRequestedPayload payload = new HealthCheckRequestedPayload();
        HealthCheckRequested healthCheckRequestedEvent = new HealthCheckRequested();
        EventMetadata eventMetadata = generateEventMetadata(EventType.HEALTH_CHECK_REQUESTED);

        payload.setMethod(entity.getMethod().toString());
        payload.setUrl(entity.getUrl());
        payload.setEndpointId(entity.getId());
        payload.setTimeoutMs(entity.getTimeoutMs());

        healthCheckRequestedEvent.setPayload(payload);
        healthCheckRequestedEvent.setMetadata(eventMetadata);

        return healthCheckRequestedEvent;
    }

    public void publishHealthCheckRequested(MonitoredEndpointEntity entity) {
        HealthCheckRequested healthCheckRequested = generateHealthCheckRequestedEvent(entity);
        CorrelationData correlationData = new CorrelationData(entity.getId().toString());

        rabbitTemplate.convertAndSend(
                RabbitMQConstants.APIWATCH_EXCHANGE,
                RabbitMQConstants.APIWATCH_HEALTH_CHECK_REQUESTED_QUEUE_ROUTING_KEY,
                healthCheckRequested,
                correlationData
        );

        correlationData.getFuture().whenComplete(((confirm, throwable) -> {
            if(confirm.ack()) {
                System.out.println("Acked");
            }
            if(correlationData.getReturned() != null) {
                System.out.println("Returned - routing failed");
            }
        }));

    }

}
