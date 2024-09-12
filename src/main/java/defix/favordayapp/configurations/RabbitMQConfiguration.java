package defix.favordayapp.configurations;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration {
    public static final String POST_CREATED_QUEUE = "POST_CREATED";
    public static final String POST_CREATED_EXCHANGE = "POST_CREATED_EX";

    @Bean
    public Queue sqlDbQueue() {
        return new Queue(POST_CREATED_QUEUE, false);
    }

    @Bean
    public FanoutExchange sqlDbExchange() {
        return new FanoutExchange(POST_CREATED_EXCHANGE);
    }

    @Bean
    public Binding bindQueueWithEx() {
        return BindingBuilder.bind(sqlDbQueue()).to(sqlDbExchange());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
