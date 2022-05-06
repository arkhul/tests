package testing.account;

public class Account {

    private boolean active;

    private Address defaultDeliveryAddress;

    public Account(final Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
        if (defaultDeliveryAddress != null) {
            activate();
        } else {
            this.active = false;
        }
    }

    public Account() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public boolean isActive() {
        return this.active;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public void setDefaultDeliveryAddress(final Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
    }
}
