package com.apiwatch.monitor.config;

import com.apiwatch.monitor.constants.RabbitMQConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(
                "apiwatch.events",
                true,
                false
        );
    }

    @Bean
    public MessageConverter jacksonJsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

//    @Bean
//    public Queue queue() {
//        return new Queue(RabbitMQConstants.APIWATCH_CHECK_REQUESTED_QUEUE_NAME, true);
//    }
//
//    @Bean
//    public Binding binding(Queue queue, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queue)
//                .to(topicExchange)
//                .with(RabbitMQConstants.APIWATCH_HEALTH_CHECK_REQUESTED_QUEUE_ROUTING_KEY);
//    }

}
