package sprint_7_classes;

import java.util.List;

public class OrderList {
    private List<Order> orders;

    public OrderList(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public boolean isNotEmpty() {
        if (orders.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
