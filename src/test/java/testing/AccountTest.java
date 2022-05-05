package testing;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void newAcountShouldNotBeActiveAfterCreation() {
        // given & when
        Account newAccount = new Account();

        // then
        assertFalse(newAccount.isActive(), "Some message if false");
        assertThat(newAccount.isActive(), Matchers.equalTo(false));
        assertThat(newAccount.isActive(), Matchers.is(false));
    }

    @Test
    void accountShouldBeActiveAfterActivation() {
        // givem
        Account newAccount = new Account();

        // when
        newAccount.activate();

        // then
        assertTrue(newAccount.isActive());
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDelliveryAddressSet() {
        // given
        Account account = new Account();

        // when
        Address address = account.getDefaultDeliveryAddress();

        // then
        assertNull(address);
        assertThat(address, Matchers.nullValue());
    }

    @Test
    void DefaultDelliveryAddressShouldNotBeNullAfterBeingSet() {
        // given
        Address address = new Address("Krakowska", "36c");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);

        // when
        Address defaultAddress = account.getDefaultDeliveryAddress();

        // then
        assertNotNull(defaultAddress);
        assertThat(defaultAddress, Matchers.is(notNullValue()));
    }
}