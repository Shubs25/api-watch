package com.apiwatch.worker.config;


import com.apiwatch.worker.constants.RabbitMQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    @Bean
    public MessageConverter jacksonJsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(
                "apiwatch.events",
                true,
                false
        );
    }

    @Bean
    public Queue queue() {
        return new Queue(RabbitMQConstants.APIWATCH_CHECK_REQUESTED_QUEUE_NAME, true);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue)
                .to(topicExchange)
                .with(RabbitMQConstants.APIWATCH_HEALTH_CHECK_REQUESTED_QUEUE_ROUTING_KEY);
    }


}
