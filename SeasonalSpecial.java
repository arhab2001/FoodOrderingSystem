import java.util.ArrayList;
import java.util.List;

class SeasonalSpecial {
    private String offer;
    private double discount;

    private SeasonalSpecial(Builder builder) {
        this.offer = builder.offer;
        this.discount = builder.discount;
    }

    public String getOffer() {
        return offer;
    }

    public double getDiscount() {
        return discount;
    }

    public static class Builder {
        private String offer;
        private double discount;

        public Builder offer(String offer) {
            this.offer = offer;
            return this;
        }

        public Builder discount(double discount) {
            this.discount = discount;
            return this;
        }

        public SeasonalSpecial build() {
            return new SeasonalSpecial(this);
        }
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

class SeasonalSpecialObserver implements OrderObserver {
    @Override
    public void update(String status) {
        System.out.println("Seasonal Special: Order status changed to " + status);
    }
}


abstract class Pizza {
    public abstract String getDescription();
    public abstract double getCost();
}

class BasicPizza extends Pizza {
    @Override
    public String getDescription() {
        return "Basic Pizza";
    }

    @Override
    public double getCost() {
        return 10.0;
    }
}

abstract class PizzaDecorator extends Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

class SeasonalSpecialDecorator extends PizzaDecorator {
    public SeasonalSpecialDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Seasonal Special Discount";
    }

    @Override
    public double getCost() {
        return pizza.getCost() * 0.90; // applying 10% discount
    }
}

