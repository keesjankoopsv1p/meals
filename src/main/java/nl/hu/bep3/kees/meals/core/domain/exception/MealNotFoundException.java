package nl.hu.bep3.kees.meals.core.domain.exception;

public class MealNotFoundException extends RuntimeException{
    public MealNotFoundException(String message) { super(message); }
}
