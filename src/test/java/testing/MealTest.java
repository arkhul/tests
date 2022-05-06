package testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.sameInstance;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.lessThan;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {
        // given
        Meal soup = new Meal(10);

        // when
        int result = soup.getDiscountedPrice(2);

        // then
        assertEquals(8, result);

//        assertThat(result, Matchers.is(8));

        assertThat(result).isEqualTo(8);
    }

    @Test
    void referencesToTheSameObjectsShouldBeEqual() {
        // given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

        // when & then
        assertSame(meal1, meal2);

//        assertThat(meal1, sameInstance(meal2));

        assertThat(meal1).isSameAs(meal2);
    }

    @Test
    void referencesToDifferentSameObjectsShouldBeEqual() {
        // given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(10);

        // when & then
        assertNotSame(meal1, meal2);

//        assertThat(meal1, Matchers.not(sameInstance(meal2)));

        assertThat(meal1).isNotSameAs(meal2);
    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame() {
        // given
        Meal meal1 = new Meal(10, "Pizza");
        Meal meal2 = new Meal(10, "Pizza");

        // when & then
        assertEquals(meal1, meal2);

        assertThat(meal1).isEqualTo(meal2);
    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice() {
        // given
        Meal meal = new Meal(8, "Soup");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountedPrice(12));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 18})
    void mealPriceShouldBeLowerThan20(int price) {
//        assertThat(price, lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgersShouldHaveCorrectNameAndPrice(String name, int price) {
//        assertThat(name, containsString("burger"));
//        assertThat(price, greaterThanOrEqualTo(10));

    }

    private static Stream<Arguments> createMealsWithNameAndPrice() {
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheeseburger", 12)
        );
    }

    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNamesShouldEndWithCake(String name) {
//        assertThat(name, notNullValue());
//        assertThat(name, endsWith("cake"));
    }

    private static Stream<String> createCakeNames() {
        List<String> cakeNames = Arrays.asList("cheesecake", "fruitcake", "cupcake");
        return cakeNames.stream();
    }
}