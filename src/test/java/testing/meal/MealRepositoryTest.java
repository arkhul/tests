package testing.meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MealRepositoryTest {

    private final MealRepository mealRepository = new MealRepository();

    @BeforeEach
    void cleanUp() {
        mealRepository.getAllMeals().clear();
    }

    @Test
    void shouldBeAbleToAddMealToRepository() {
        // given
        Meal meal = new Meal(10, "Pizza");

        // when
        mealRepository.add(meal);

        // then
        assertThat(mealRepository.getAllMeals().get(0), is(meal));
    }

    @Test
    void shouldBeAbleToRemoveMealFromRepository() {
        // given
        Meal meal = new Meal(10, "Pizza");
        mealRepository.add(meal);

        // when
        mealRepository.remove(meal);

        // then
        assertThat(mealRepository.getAllMeals().size(), is(0));
        assertThat(mealRepository.getAllMeals(), not(contains(meal)));
    }

    @Test
    void shouldBeAbleToFindMealByName() {
        // given
        Meal meal = new Meal(10, "Pizza");
        mealRepository.add(meal);

        // when
        List<Meal> result = mealRepository.findByName("Pizza");

        // then
        assertThat(result.size(), is(1));
    }

    @Test
    void shouldBeAbleToFindMealByPrice() {
        // given
        Meal meal = new Meal(10, "Pizza");
        mealRepository.add(meal);

        // when
        List<Meal> result = mealRepository.findByPrice(10);

        // then
        assertThat(result.size(), is(1));
    }

    @Test
    void shouldBeAbleToFindMealByPieceOfMealName() {
        // given
        Meal meal = new Meal(10, "Pizza");
        mealRepository.add(meal);

        // when
        List<Meal> result = mealRepository.findByPieceOfMealName("Pi");

        // then
        assertThat(result.size(), is(1));
    }
}