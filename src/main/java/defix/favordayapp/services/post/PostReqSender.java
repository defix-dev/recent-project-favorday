package defix.favordayapp.services.post;

import defix.favordayapp.configurations.RabbitMQConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostReqSender {
    private final RabbitTemplate template;

    @Autowired
    public PostReqSender(RabbitTemplate template) {
        this.template = template;

    }

    public void notify(PostSettings settings) {
        template.convertAndSend(RabbitMQConfiguration.POST_CREATED_QUEUE, settings);
    }
}
