package testing.cart;

import testing.meal.Meal;
import testing.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Order> orders = new ArrayList<>();

    public void addOrderToCart(final Order order) {
        orders.add(order);
    }

    public void clearCart() {
        orders.clear();
    }

    public void simulateLargeOrder() {
        for (int i = 0; i < 10; i++) {
            Meal meal = new Meal(i % 10, "Hamburger no " + i);
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrderToCart(order);
        }
        System.out.println("Cart size " + orders.size());
        clearCart();
    }

    public List<Order> getOrders() {
        return orders;
    }
}
