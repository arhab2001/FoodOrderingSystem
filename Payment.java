import java.util.ArrayList;
import java.util.List;

class Payment {
    private String paymentMethod;
    private double amount;

    private Payment(Builder builder) {
        this.paymentMethod = builder.paymentMethod;
        this.amount = builder.amount;
    }

    public static class Builder {
        private String paymentMethod;
        private double amount;

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }

    public String processPayment() {
        return "Payment Method: " + paymentMethod + "\nAmount: " + amount + "\nPayment Successful!";
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

class PaymentObserver implements OrderObserver {
    @Override
    public void update(String status) {
        System.out.println("Payment status changed to: " + status);
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
