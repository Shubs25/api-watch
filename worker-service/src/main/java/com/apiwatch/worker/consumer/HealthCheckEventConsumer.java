package com.apiwatch.worker.consumer;

import com.apiwatch.contracts.events.HealthCheckRequested;
import com.apiwatch.worker.constants.RabbitMQConstants;
import com.apiwatch.worker.service.HealthCheckService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class HealthCheckEventConsumer {

    private final HealthCheckService healthCheckService;

    public HealthCheckEventConsumer(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    @RabbitListener(queues = RabbitMQConstants.APIWATCH_CHECK_REQUESTED_QUEUE_NAME)
    public void listenOnCheckRequestsQueue(HealthCheckRequested healthCheckRequest) {
        System.out.println("received event Id " + healthCheckRequest.getMetadata().getEventId());
        healthCheckService.performHealthCheck(healthCheckRequest);
    }

}
