package testing.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import testing.order.Order;
import testing.order.OrderStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartHandler cartHandler;    // zale¿noœæ (pole), która znajduje siê w klasie CartService

    @Test
    void processCartShouldSendToPrepare() {
        // given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

//  dziêki adnotacjom @InjectMock i Mock usuwamy poni¿sze ze wszystkich testów
//        CartHandler cartHandler = mock(CartHandler.class);
//        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        // when
        Cart resultCart = cartService.processCart(cart);

        // then
        verify(cartHandler).sendToPrepare(cart);
        then(cartHandler).should().sendToPrepare(cart);     // to samo co linijka wy¿ej

        verify(cartHandler, times(1)).sendToPrepare(cart);
        verify(cartHandler, atLeastOnce()).sendToPrepare(cart);

        InOrder inOrder = inOrder(cartHandler);     // weryfikujemy kolejoœæ wykonywanych metod w mocku
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);

//        assertThat(resultCart, hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void processCartShouldNotSendToPrepare() {
        // given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willReturn(false);

        // when
        Cart resultCart = cartService.processCart(cart);

        // then
        verify(cartHandler, never()).sendToPrepare(cart);
        then(cartHandler).should(never()).sendToPrepare(cart);  // to samo co linijka wy¿ej

        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }

    @Test
    void processCartShouldNotSendToPrepareWithArgumentMatchers() {
        // given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(false);

        // when
        Cart resultCart = cartService.processCart(cart);

        // then
        verify(cartHandler, never()).sendToPrepare(any(Cart.class));
        then(cartHandler).should(never()).sendToPrepare(any(Cart.class));  // to samo co linijka wy¿ej

        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }

    @Test
    void canHandleCartShouldReturnMultipleValues() {
        // given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willReturn(true, false, false, true);

        // then
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
    }

    @Test
    void processCartShouldSendToPrepareWithLambdas() {
        // given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(argThat(c -> c.getOrders().size() > 0))).willReturn(true);

        // when
        Cart resultCart = cartService.processCart(cart);

        // then
        then(cartHandler).should().sendToPrepare(cart);

        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void canHandleCartShouldThrowException() {
        // given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willThrow(IllegalStateException.class);

        // when & then
        assertThrows(IllegalStateException.class, () -> cartService.processCart(cart));
    }

    @Test
    void shouldDoNothingWhenProcessCart() {
        // given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        doNothing().when(cartHandler).sendToPrepare(cart);
//        willDoNothing().given(cartHandler).sendToPrepare(cart); // to samo co linijka wy¿ej

        // when
        Cart resultCart = cartService.processCart(cart);

        // then
        then(cartHandler).should().sendToPrepare(cart);

        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void deliveryShouldBeFree() {
        // given
        Cart cart = new Cart();
        cart.addOrderToCart(new Order());
        cart.addOrderToCart(new Order());
        cart.addOrderToCart(new Order());

//        given(cartHandler.isDeliveryFree(cart)).willCallRealMethod();   // to samo co ni¿ej
//        doCallRealMethod().when(cartHandler).isDeliveryFree(cart);

        // when
        boolean isDeliveryFree = cartHandler.isDeliveryFree(cart);

        // then
//        assertTrue(isDeliveryFree);
    }
}