package nl.hu.bep3.kees.meals.core.application.command;

public class RemoveMealsFromMenuByIngredient {

    private final String ingredient;

    public RemoveMealsFromMenuByIngredient(String ingredient){
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }
}
