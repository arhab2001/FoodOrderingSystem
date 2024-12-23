import java.util.ArrayList;
import java.util.List;

class Pizza {
    private String pizzaType;
    private String pizzaSize;
    private String pizzaCrust;
    private String pizzaSauce;
    private String topping;
    private String cheese;
    private double pizzaFee;
    private double customizeFee;

    private Pizza(Builder builder) {
        this.pizzaType = builder.pizzaType;
        this.pizzaSize = builder.pizzaSize;
        this.pizzaCrust = builder.pizzaCrust;
        this.pizzaSauce = builder.pizzaSauce;
        this.topping = builder.topping;
        this.cheese = builder.cheese;
        this.pizzaFee = builder.pizzaFee;
        this.customizeFee = builder.customizeFee;
    }

    public static class Builder {
        private String pizzaType;
        private String pizzaSize;
        private String pizzaCrust;
        private String pizzaSauce;
        private String topping;
        private String cheese;
        private double pizzaFee;
        private double customizeFee;

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

        public Pizza build() {
            return new Pizza(this);
        }
    }

    public String getPizzaDetails() {
        return "Pizza Type: " + pizzaType + "\nSize: " + pizzaSize + "\nCrust: " + pizzaCrust +
               "\nSauce: " + pizzaSauce + "\nTopping: " + topping + "\nCheese: " + cheese +
               "\nPizza Fee: " + pizzaFee + "\nCustomize Fee: " + customizeFee;
    }

    public double calculateTotalFee() {
        return pizzaFee + customizeFee;
    }
}

interface OrderObserver {
    void update(String status);
}

class Order {
    private String status;
    private List<OrderObserver> observers = new ArrayList<>();

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

    public String getStatus() {
        return status;
    }
}

class PizzaOrderObserver implements OrderObserver {
    @Override
    public void update(String status) {
        System.out.println("Pizza Order Status: " + status);
    }
}

interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}
