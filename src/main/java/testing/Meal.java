package testing;

public class Meal {

    private int price;
    private String name;

    public Meal(final int price, final String name) {
        this.price = price;
        this.name = name;
    }

    public Meal(final int price) {
        this.price = price;
    }

    public int getDiscountedPrice(int discount) {
        if (discount > price) {
            throw new IllegalArgumentException("Discount cannot be higher than the price!");
        }
        return this.price - discount;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;

        Meal meal = (Meal) o;

        if (getPrice() != meal.getPrice()) return false;
        return getName() != null ? getName().equals(meal.getName()) : meal.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getPrice();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
