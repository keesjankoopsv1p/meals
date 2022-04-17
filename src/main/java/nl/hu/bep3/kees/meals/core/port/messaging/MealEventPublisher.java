package nl.hu.bep3.kees.meals.core.port.messaging;

import nl.hu.bep3.kees.meals.core.domain.event.MealEvent;

public interface MealEventPublisher {
    void publish(MealEvent event);
}
