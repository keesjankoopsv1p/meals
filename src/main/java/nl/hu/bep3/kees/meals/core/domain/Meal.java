package nl.hu.bep3.kees.meals.core.domain;

import nl.hu.bep3.kees.meals.core.domain.event.MealAddedEvent;
import nl.hu.bep3.kees.meals.core.domain.event.MealEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Document
public class Meal {
    @Id
    private UUID id;
    @Indexed(unique = true)
    private String name;
    @Indexed
    private Integer price;
    @Indexed
    private Set<String> ingredients;
    @Indexed
    private boolean onMenu;
    @Transient
    private List<MealEvent> events = new ArrayList<>();

    public Meal(String name, Integer price, Set<String> ingredients){
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    // methods
    public void registerMeal(){
        this.events.add(new MealAddedEvent(this.id));
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setOnMenu() {
        this.onMenu = true;
    }

    public void takeOffMenu() { this.onMenu = false; }

    public void clearEvents() {
        this.events.clear();
    }

    public List<MealEvent> listEvents() { return events; }
}
