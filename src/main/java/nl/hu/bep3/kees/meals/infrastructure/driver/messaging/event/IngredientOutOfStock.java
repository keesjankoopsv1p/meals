package nl.hu.bep3.kees.meals.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.UUID;

public class IngredientOutOfStock {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;
    public String ingredient;
}
