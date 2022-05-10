package testing.meal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MealRepository {

    private List<Meal> meals = new ArrayList<>();

    public void add(final Meal meal) {
        meals.add(meal);
    }

    public List<Meal> getAllMeals() {
        return meals;
    }

    public void remove(final Meal meal) {
        meals.remove(meal);
    }

    public List<Meal> findByName(final String mealName) {
        return meals.stream()
                .filter(meal -> meal.getName().equals(mealName))
                .collect(Collectors.toList());
    }

    public List<Meal> findByPrice(int price) {
        return meals.stream()
                .filter(meal -> meal.getPrice() == price)
                .collect(Collectors.toList());
    }

    public List<Meal> findByPieceOfMealName(final String str) {
        return meals.stream()
                .filter(meal -> meal.getName().contains(str))
                .collect(Collectors.toList());
    }
}
