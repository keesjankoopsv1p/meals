package nl.hu.bep3.kees.meals.infrastructure.driven.messaging;

import nl.hu.bep3.kees.meals.core.domain.event.MealEvent;
import nl.hu.bep3.kees.meals.core.port.messaging.MealEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements MealEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String restaurantExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String restaurantExchange
    ){
        this.rabbitTemplate = rabbitTemplate;
        this.restaurantExchange = restaurantExchange;
    }

    public void publish(MealEvent event){
        this.rabbitTemplate.convertAndSend(restaurantExchange, event.getEventKey(), event);
    }
}
