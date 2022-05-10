package testing.order;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import testing.meal.Meal;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;

    @BeforeEach
    void initializeOrder() {
        order = new Order();
    }

    @AfterEach
    void cleanUp() {
        order.cancel();
    }

    @Test
    void testAssertArrayEquals() {
        // given
        int[] ints1 = {1, 2, 3};
        int[] ints2 = {1, 2, 3};

        // then
        assertArrayEquals(ints1, ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder() {
        // then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        MatcherAssert.assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize() {
        // given
        Meal meal = new Meal(20, "Pizza");

        // when
        order.addMealToOrder(meal);

        // then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));
        assertThat(order.getMeals().get(0).getPrice(), equalTo(20));
    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize() {
        Meal meal = new Meal(20, "Pizza");

        // when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

        // then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(meal)));
        assertThat(order.getMeals(), not(hasItem(meal)));
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder() {
        // given
        Meal meal1 = new Meal(20, "Pizza");
        Meal meal2 = new Meal(15, "Burger");

        // when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        // then
        assertThat(order.getMeals(), contains(meal1, meal2));
        assertThat(order.getMeals(), containsInAnyOrder(meal2, meal1));  // kolejnoœæ nie jest wa¿na
    }

    @Test
    @DisplayName("Nasza nazwa")
    void testIfTwoMealListsAreTheSame() {
        // given
        Meal meal1 = new Meal(20, "Pizza");
        Meal meal2 = new Meal(15, "Burger");

        List<Meal> meals1 = Arrays.asList(meal1, meal2);
        List<Meal> meals2 = Arrays.asList(meal1, meal2);

        // then
        assertThat(meals1, is(meals2));       // czy zawieraj¹ te same elementy w tej samej kolejnoœci
    }
}