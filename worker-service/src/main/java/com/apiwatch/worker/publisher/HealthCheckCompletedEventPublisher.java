package com.apiwatch.worker.publisher;

import com.apiwatch.contracts.events.HealthCheckCompleted;
import com.apiwatch.worker.constants.RabbitMQConstants;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckCompletedEventPublisher {
    private RabbitTemplate rabbitTemplate;

    public HealthCheckCompletedEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(HealthCheckCompleted completedPayload) {
        CorrelationData correlationData = new CorrelationData(
                completedPayload.getPayload()
                .getEndpointId()
                .toString()
        );

        rabbitTemplate.convertAndSend(
                RabbitMQConstants.APIWATCH_EXCHANGE,
                RabbitMQConstants.APIWATCH_HEALTH_CHECK_COMPLETED_QUEUE_ROUTING_KEY,
                completedPayload,
                correlationData
        );

        correlationData.getFuture().whenComplete(((confirm, throwable) -> {
            if (confirm.ack()) {
                System.out.println("Acked");
            }
            if (throwable != null) {
                System.out.println("Routing failed");
            }
        }));


    }

}
