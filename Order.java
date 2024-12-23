import java.util.ArrayList;
import java.util.List;

class Order {
    private String username;
    private String pizzaType;
    private String pizzaSize;
    private String pizzaCrust;
    private String pizzaSauce;
    private String topping;
    private String cheese;
    private double pizzaFee;
    private double customizeFee;
    private double totalFee;
    private String deliveryType;
    private String paymentMethod;
    private String status;
    private List<OrderObserver> observers = new ArrayList<>();

    private Order(Builder builder) {
        this.username = builder.username;
        this.pizzaType = builder.pizzaType;
        this.pizzaSize = builder.pizzaSize;
        this.pizzaCrust = builder.pizzaCrust;
        this.pizzaSauce = builder.pizzaSauce;
        this.topping = builder.topping;
        this.cheese = builder.cheese;
        this.pizzaFee = builder.pizzaFee;
        this.customizeFee = builder.customizeFee;
        this.totalFee = builder.totalFee;
        this.deliveryType = builder.deliveryType;
        this.paymentMethod = builder.paymentMethod;
        this.status = "Order Placed";
    }

    public static class Builder {
        private String username;
        private String pizzaType;
        private String pizzaSize;
        private String pizzaCrust;
        private String pizzaSauce;
        private String topping;
        private String cheese;
        private double pizzaFee;
        private double customizeFee;
        private double totalFee;
        private String deliveryType;
        private String paymentMethod;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder pizzaType(String pizzaType) {
            this.pizzaType = pizzaType;
            return this;
        }

        public Builder pizzaSize(String pizzaSize) {
            this.pizzaSize = pizzaSize;
            return this;
        }

        public Builder pizzaCrust(String pizzaCrust) {
            this.pizzaCrust = pizzaCrust;
            return this;
        }

        public Builder pizzaSauce(String pizzaSauce) {
            this.pizzaSauce = pizzaSauce;
            return this;
        }

        public Builder topping(String topping) {
            this.topping = topping;
            return this;
        }

        public Builder cheese(String cheese) {
            this.cheese = cheese;
            return this;
        }

        public Builder pizzaFee(double pizzaFee) {
            this.pizzaFee = pizzaFee;
            return this;
        }

        public Builder customizeFee(double customizeFee) {
            this.customizeFee = customizeFee;
            return this;
        }

        public Builder totalFee(double totalFee) {
            this.totalFee = totalFee;
            return this;
        }

        public Builder deliveryType(String deliveryType) {
            this.deliveryType = deliveryType;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public void registerObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(status);
        }
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    public String getOrderDetails() {
        return "Username: " + username + "\nPizza Type: " + pizzaType + "\nSize: " + pizzaSize + "\nCrust: " + pizzaCrust +
               "\nSauce: " + pizzaSauce + "\nTopping: " + topping + "\nCheese: " + cheese + "\nPizza Fee: " + pizzaFee +
               "\nCustomize Fee: " + customizeFee + "\nTotal Fee: " + totalFee + "\nDelivery Type: " + deliveryType +
               "\nPayment Method: " + paymentMethod + "\nStatus: " + status;
    }
}

interface OrderObserver {
    void update(String status);
}

class PlacedState implements OrderState {
    @Override
    public void handle(Order order) {
        order.setStatus("Placed");
    }
}

class InPreparationState implements OrderState {
    @Override
    public void handle(Order order) {
        order.setStatus("In Preparation");
    }
}

class OutForDeliveryState implements OrderState {
    @Override
    public void handle(Order order) {
        order.setStatus("Out for Delivery");
    }
}

interface OrderState {
    void handle(Order order);
}
