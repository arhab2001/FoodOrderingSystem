import java.util.ArrayList;
import java.util.List;

class Order {
    private String username;
    private String orderDetails;
    private String status;

    private Order(Builder builder) {
        this.username = builder.username;
        this.orderDetails = builder.orderDetails;
        this.status = builder.status;
    }

    public static class Builder {
        private String username;
        private String orderDetails;
        private String status;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder orderDetails(String orderDetails) {
            this.orderDetails = orderDetails;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public String getOrderDetails() {
        return "Username: " + username + "\nOrder Details: " + orderDetails + "\nStatus: " + status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
}

interface OrderObserver {
    void update(String status);
}

class OrderStatusNotifier implements OrderObserver {
    @Override
    public void update(String status) {
        System.out.println("Order status updated to: " + status);
    }
}
