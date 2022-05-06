package testing.account;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.StringContains.containsString;

class AddressTest {

    @ParameterizedTest
    @CsvSource({"Fabryczna, 10", "Armii Krajowej, 56/77", "'Tytusa, Romka i Atomka', 40"})
    void givenAddressShouldNotBeEmotyAndHaveProperNames(String street, String number) {
        assertThat(street, notNullValue());
        assertThat(street, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(8));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")
    void givenAddressFromFileShouldNotBeEmotyAndHaveProperNames(String street, String number) {
        assertThat(street, notNullValue());
        assertThat(street, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(8));
    }
}