package nl.hu.bep3.kees.meals.core.application.command;

import java.util.Set;

public class AddNewMeal {
    private final String name;
    private final Integer price;
    private final Set<String> ingredients;

    public AddNewMeal(String name, Integer price, Set<String> ingredients) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }
}
