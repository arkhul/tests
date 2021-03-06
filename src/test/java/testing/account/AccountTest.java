package testing.account;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import testing.account.Account;
import testing.account.Address;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.notNullValue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AccountTest {

    @Test
    void newAcountShouldNotBeActiveAfterCreation() {
        // given & when
        Account newAccount = new Account();

        // then
        assertFalse(newAccount.isActive(), "Some message if false");

//        assertThat(newAccount.isActive(), Matchers.equalTo(false));
//        assertThat(newAccount.isActive(), Matchers.is(false));

        assertThat(newAccount.isActive()).isFalse();
    }

    @Test
    void accountShouldBeActiveAfterActivation() {
        // givem
        Account newAccount = new Account();

        // when
        newAccount.activate();

        // then
        assertTrue(newAccount.isActive());

        assertThat(newAccount.isActive()).isTrue();
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDelliveryAddressSet() {
        // given
        Account account = new Account();

        // when
        Address address = account.getDefaultDeliveryAddress();

        // then
        assertNull(address);

//        assertThat(address, Matchers.nullValue());

        assertThat(address).isNull();
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

//        assertThat(defaultAddress, Matchers.is(notNullValue()));

        assertThat(defaultAddress).isNotNull();
    }

    @RepeatedTest(2)
    void newAccountWithNotNullAddressShouldBeActive() {
        // given
        Address address = new Address("Nowa", "16");

        // when
        Account account = new Account(address);

        // then
        assumingThat(address != null, () -> {
            assertTrue(account.isActive());         // wykona si? tylko wtedy, gdy assumption jest true
        });
    }
}