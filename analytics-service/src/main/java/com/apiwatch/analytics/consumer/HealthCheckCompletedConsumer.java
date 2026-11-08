package com.apiwatch.analytics.consumer;


import com.apiwatch.analytics.constants.RabbitMQConstants;
import com.apiwatch.analytics.service.HealthCheckService;
import com.apiwatch.contracts.events.HealthCheckCompleted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckCompletedConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(HealthCheckCompletedConsumer.class);

    private final HealthCheckService healthCheckService;

    public HealthCheckCompletedConsumer(
            HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    @RabbitListener(
            queues = RabbitMQConstants.APIWATCH_CHECK_COMPLETED_QUEUE_NAME
    )
    public void consume(HealthCheckCompleted event) {

        log.info("Received completed health check event: {}",
                event.getMetadata().getEventId());

        healthCheckService.recordHealthCheck(event);
    }
}
