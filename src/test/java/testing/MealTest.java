package testing;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.sameInstance;
import static org.assertj.core.api.Assertions.assertThat;
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
}