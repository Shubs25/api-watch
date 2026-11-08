package com.apiwatch.analytics.config;

import com.apiwatch.analytics.constants.RabbitMQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange apiWatchExchange() {
        return new TopicExchange(
                RabbitMQConstants.APIWATCH_EXCHANGE,
                true,
                false
        );
    }

    @Bean
    public Queue healthCheckCompletedQueue() {
        return new Queue(
                RabbitMQConstants.APIWATCH_CHECK_COMPLETED_QUEUE_NAME,
                true
        );
    }

    @Bean
    public Binding healthCheckCompletedBinding(
            Queue healthCheckCompletedQueue,
            TopicExchange apiWatchExchange) {

        return BindingBuilder
                .bind(healthCheckCompletedQueue)
                .to(apiWatchExchange)
                .with(RabbitMQConstants.APIWATCH_HEALTH_CHECK_COMPLETED_QUEUE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jacksonJsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}