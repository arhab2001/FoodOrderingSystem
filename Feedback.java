import java.util.ArrayList;
import java.util.List;

class Feedback {
    private String username;
    private String pizzaType;
    private int rating; 
    private String comments;

    private Feedback(Builder builder) {
        this.username = builder.username;
        this.pizzaType = builder.pizzaType;
        this.rating = builder.rating;
        this.comments = builder.comments;
    }

    public static class Builder {
        private String username;
        private String pizzaType;
        private int rating; 
        private String comments;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder pizzaType(String pizzaType) {
            this.pizzaType = pizzaType;
            return this;
        }

        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder comments(String comments) {
            this.comments = comments;
            return this;
        }

        public Feedback build() {
            return new Feedback(this);
        }
    }

    public String getFeedbackDetails() {
        return "Username: " + username + "\nPizza Type: " + pizzaType + "\nRating: " + rating + "\nComments: " + comments;
    }
}

interface OrderObserver {
    void update(String status);
}

class FeedbackStatusNotifier implements OrderObserver {
    @Override
    public void update(String status) {
        System.out.println("Feedback status updated to: " + status);
    }
}
