package nl.hu.bep3.kees.meals.infrastructure.driver.messaging;

import nl.hu.bep3.kees.meals.core.application.MealCommandHandler;
import nl.hu.bep3.kees.meals.core.application.command.RemoveMealsFromMenuByIngredient;
import nl.hu.bep3.kees.meals.infrastructure.driver.messaging.event.IngredientOutOfStock;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqEventListener {
    private final MealCommandHandler commandHandler;

    public RabbitMqEventListener(MealCommandHandler commandHandler) {

        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.stock-meals}'}")
    public void listen (IngredientOutOfStock event) {
        switch (event.eventKey) {
            case "stock.meals.out.of.stock":
                this.handle(event);
                break;
        }
    }

    private void handle(IngredientOutOfStock event) {
        this.commandHandler.handle(new RemoveMealsFromMenuByIngredient(event.ingredient));
    }
}
