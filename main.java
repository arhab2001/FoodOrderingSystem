import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {
    private static Scanner scanner = new Scanner(System.in);
    private static int userIdCounter = 1;
    private static Map<String, User> userProfiles = new HashMap<>();

    public static void main(String[] args) {
        main system = new main();
        system.start();
    }

    public void start() {
        while (true) {
            System.out.println("--------------- Pizza Ordering System ---------------");
            System.out.println("1. Create User Profile");
            System.out.println("2. Customize Pizza");
            System.out.println("3. Order");
            System.out.println("4. Track Order");
            System.out.println("5. View Seasonal Specials");
            System.out.println("6. Give Feedback");
            System.out.println("7. View Details");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: createUserProfile(); break;
                    case 2: customizePizza(); break;
                    case 3: placeOrder(); break;
                    case 4: trackOrder(); break;
                    case 5: viewSeasonalSpecials(); break;
                    case 6: giveFeedback(); break;
                    case 7: viewDetails(); break;
                    case 8: exitSystem(); return;
                    default: System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                scanner.nextLine();
            }
        }
    }

    public void createUserProfile() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your mobile number: ");
        String mobile = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();

        String userId = generateUserId();

        User newUser = new User(username, email, mobile, address);
        userProfiles.put(userId, newUser); 

        System.out.println("--------------------------------------------------------------");
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Mobile number: " + mobile);
        System.out.println("Address: " + address);
        System.out.println("ID: " + userId);
        System.out.println("------------- User profile created successfully -------------");
        System.out.println("--------------------------------------------------------------");
    }

    private String generateUserId() {
        return String.format("%03d", userIdCounter++);
    }

    public void customizePizza() {
        System.out.print("Enter Your User Id: ");
        String userId = scanner.nextLine();


        if (!userProfiles.containsKey(userId)) {
            System.out.println("User not found! Please create a profile first.");
            return;
        }
        System.out.println("--------------------------------------------------------------");
        System.out.println("1. Margherita\n2. Pepperoni\n3. Hawaiian\n4. BBQ Chicken\n5. Veggie");
        System.out.print("Choose Pizza Type: ");
        String pizzaType = scanner.nextLine();
        System.out.println("--------------------------------------------------------------");
        System.out.println("1. Mini\n2. Small\n3. Medium\n4. Large\n5. Jumbo Large");
        System.out.print("Choose Pizza Size: ");
        String pizzaSize = scanner.nextLine();
        System.out.println("--------------------------------------------------------------");
        System.out.println("1. Thin Crust\n2. Thick Crust\n3. Stuffed Crust\n4. Pan Crust\n5. Gluten-Free Crust");
        System.out.print("Choose Pizza Crust: ");
        String pizzaCrust = scanner.nextLine();
        System.out.println("--------------------------------------------------------------");
        System.out.println("1. Tomato Sauce\n2. BBQ Sauce\n3. Alfredo Sauce\n4. Pesto Sauce\n5. Buffalo Sauce");
        System.out.print("Choose Pizza Sauce: ");
        String pizzaSauce = scanner.nextLine();
        System.out.println("--------------------------------------------------------------");
        System.out.print("Enter topping: ");
        String topping = scanner.nextLine();
        System.out.print("Enter cheese: ");
        String cheese = scanner.nextLine();
        

        double pizzaFee = 12.0; 
        double customizeFee = 2.0;
        double seasonalDiscount = 0.0 * pizzaFee; 
        double totalFee = pizzaFee + customizeFee - seasonalDiscount;

        User user = userProfiles.get(userId);
        user.setLastPizzaOrder(pizzaType, pizzaSize,pizzaCrust, pizzaSauce,topping, cheese, pizzaFee, pizzaFee, seasonalDiscount, totalFee);

        System.out.println("--------------------------------------------------------------");
        System.out.println("User Id: " + userId);
        System.out.println("Pizza Type: " + pizzaType);
        System.out.println("Pizza Size: " + pizzaSize);
        System.out.println("Pizza Crust: " + pizzaCrust);
        System.out.println("Pizza Sauce: " + pizzaSauce);
        System.out.println("Topping: " + topping);
        System.out.println("Cheese: " + cheese);
        System.out.println("Customize Fee: $" + customizeFee);
        System.out.println("Pizza Fee: $" + pizzaFee);
        System.out.println("Total Fee: $" + totalFee);
        System.out.println("------------- Pizza customization successful -------------");
        System.out.println("--------------------------------------------------------------");
    }

    public void placeOrder() {

        System.out.println("--------------------------------------------------------------");
        System.out.println("1. Place Order\n2. New Order");
        System.out.print("Choose an option: ");
        int orderChoice = scanner.nextInt();
        scanner.nextLine(); 

        if (orderChoice == 1) {
            System.out.print("Enter Your User Id: ");
            String userId = scanner.nextLine();
        
            if (!userProfiles.containsKey(userId)) {
                System.out.println("User not found! Please create a profile first.");
                return;
            }
        
            User user = userProfiles.get(userId);
        
            System.out.println("--------------------------------------------------------------");
            System.out.println("1. Pickup\n2. Delivery");
            System.out.print("Enter delivery type (1 for Pickup, 2 for Delivery): ");
            int deliveryType = scanner.nextInt();
            scanner.nextLine();
        
            String deliveryMethod = (deliveryType == 1) ? "Pickup" : "Delivery";
        
            double pizzaFee = 12.0;
            double customizeFee = 2.0;
            double deliveryFee = (deliveryType == 2) ? 3.0 : 0.0;
            double totalFee = pizzaFee + customizeFee + deliveryFee;
        
            String pizzaType = user.getLastPizzaType();
            String pizzaSize = user.getLastPizzaSize();
            String pizzaCrust = user.getLastPizzaCrust();
            String pizzaSauce = user.getLastPizzaSauce();
            String topping = user.getLastTopping();
            String cheese = user.getLastCheese();
        
            System.out.println("--------------------------------------------------------------");
            System.out.println("User Id: " + userId);
            System.out.println("Email: " + user.getEmail());
            System.out.println("Mobile number: " + user.getMobile());
            System.out.println("Address: " + user.getAddress());
            System.out.println("Pizza Type: " + pizzaType);
            System.out.println("Pizza Size: " + pizzaSize);
            System.out.println("Pizza Crust: " + pizzaCrust);
            System.out.println("Pizza Sauce: " + pizzaSauce);
            System.out.println("Topping: " + topping);
            System.out.println("Cheese: " + cheese);
            System.out.println("Pizza Fee: $" + pizzaFee);
            System.out.println("Customize Fee: $" + customizeFee);
            System.out.println("Delivery Fee: $" + deliveryFee);
            System.out.println("Total Fee: $" + totalFee);
            System.out.println("--------------------------------------------------------------");
        
            System.out.println("1. Card Payment\n2. Cash on Delivery");
            System.out.print("Choose payment method: ");
            int paymentChoice = scanner.nextInt();
            scanner.nextLine();
        
            if (paymentChoice == 1) {
                System.out.print("Enter card number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter card expiry date: ");
                String cardDate = scanner.nextLine();
                System.out.print("Enter card CVV: ");
                String cardCvv = scanner.nextLine();
                System.out.println("---------------- Payment successful ----------------");
            } else {
                System.out.println("---------------- Payment successful ----------------");
            }
        
            System.out.println("------------- " + deliveryMethod + " successful -------------");
            System.out.println("--------------------------------------------------------------");
        }
        
        if (orderChoice == 2) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your mobile number: ");
            String mobile = scanner.nextLine();
            System.out.print("Enter your address: ");
            String address = scanner.nextLine();
            
            System.out.println("1. Margherita\n2. Pepperoni\n3. Hawaiian\n4. BBQ Chicken\n5. Veggie");
            System.out.print("Choose Pizza Type: ");
            String pizzaType = scanner.nextLine();
            System.out.println("--------------------------------------------------------------");

            System.out.println("1. Mini\n2. Small\n3. Medium\n4. Large\n5. Jumbo Large");
            System.out.print("Choose Pizza Size: ");
            String pizzaSize = scanner.nextLine();
            System.out.println("--------------------------------------------------------------");

            System.out.println("1. Thin Crust\n2. Thick Crust\n3. Stuffed Crust\n4. Pan Crust\n5. Gluten-Free Crust");
            System.out.print("Choose Pizza Crust: ");
            String pizzaCrust = scanner.nextLine();
            System.out.println("--------------------------------------------------------------");

            System.out.println("1. Tomato Sauce\n2. BBQ Sauce\n3. Alfredo Sauce\n4. Pesto Sauce\n5. Buffalo Sauce");
            System.out.print("Choose Pizza Sauce: ");
            String pizzaSauce = scanner.nextLine();
            System.out.println("--------------------------------------------------------------");

            System.out.print("Enter topping: ");
            String topping = scanner.nextLine();
            System.out.print("Enter cheese: ");
            String cheese = scanner.nextLine();
        

            System.out.println("1. Pickup\n2. Delivery");
            System.out.print("Enter delivery type: ");
            int deliveryType = scanner.nextInt();
            scanner.nextLine();  
        
            String deliveryMethod = (deliveryType == 1) ? "Pickup" : "Delivery";
            double pizzaFee = 12.0;
            double customizeFee = 2.0;
            double deliveryFee = (deliveryType == 2) ? 3.0 : 0.0; 
            double totalFee = pizzaFee + customizeFee + deliveryFee;
        

            String userId = generateUserId();
            

            User newUser = new User(username, email, mobile, address);
            newUser.setLastPizzaOrder(pizzaType, pizzaSize, pizzaCrust, pizzaSauce, topping, cheese, pizzaFee, deliveryFee, 0.0, totalFee);
            userProfiles.put(userId, newUser);
        

            System.out.println("--------------------------------------------------------------");
            System.out.println("User Id: " + userId);
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Mobile number: " + mobile);
            System.out.println("Address: " + address);
            System.out.println("Pizza Type: " + pizzaType);
            System.out.println("Pizza Size: " + pizzaSize);
            System.out.println("Pizza Crust: " + pizzaCrust);
            System.out.println("Pizza Sauce: " + pizzaSauce);
            System.out.println("Topping: " + topping);
            System.out.println("Cheese: " + cheese);
            System.out.println("Pizza Fee: $" + pizzaFee);
            System.out.println("Customize Fee: $" + customizeFee);
            System.out.println("Delivery Fee: $" + deliveryFee);
            System.out.println("Total Fee: $" + totalFee);
            System.out.println("--------------------------------------------------------------");
        

            System.out.println("1. Card Payment\n2. Cash on Delivery");
            System.out.print("Choose payment method: ");
            int paymentChoice = scanner.nextInt();
            scanner.nextLine();
        
            if (paymentChoice == 1) {
                System.out.print("Enter card number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter card expiry date: ");
                String cardDate = scanner.nextLine();
                System.out.print("Enter card CVV: ");
                String cardCvv = scanner.nextLine();
                System.out.println("---------------- Payment successful ----------------");
            } else {
                System.out.println("---------------- Payment successful ----------------");
            }
        

            System.out.println("------------- " + deliveryMethod + " successful -------------");
            System.out.println("--------------------------------------------------------------");
        }
        

    }

    public void trackOrder() {
        System.out.print("Enter Your User Id: ");
        String userId = scanner.nextLine();

        System.out.println("--------------------------------------------------------------");
        System.out.println("Order Status: Preparing your pizza");
        System.out.println("--------------------------------------------------------------");
    }

    public void viewSeasonalSpecials() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("1. Place Order\n2. New Order");
        System.out.print("Choose an option: ");
        int orderChoice = scanner.nextInt();
        scanner.nextLine(); 

        if (orderChoice == 1) {
            System.out.print("Enter Your User Id: ");
            String userId = scanner.nextLine();
        
            if (!userProfiles.containsKey(userId)) {
                System.out.println("User not found! Please create a profile first.");
                return;
            }
        
            User user = userProfiles.get(userId);
        
            System.out.println("--------------------------------------------------------------");
            System.out.println("1. Pickup\n2. Delivery");
            System.out.print("Enter delivery type (1 for Pickup, 2 for Delivery): ");
            int deliveryType = scanner.nextInt();
            scanner.nextLine(); 
        
            String deliveryMethod = (deliveryType == 1) ? "Pickup" : "Delivery";
        
            double pizzaFee = 12.0;
            double customizeFee = 2.0;
            double deliveryFee = (deliveryType == 2) ? 3.0 : 0.0; 
            double seasonalDiscount = 0.10 * (pizzaFee + customizeFee); 
            double totalFee = pizzaFee + customizeFee + deliveryFee - seasonalDiscount;
        
            String pizzaType = user.getLastPizzaType();
            String pizzaSize = user.getLastPizzaSize();
            String pizzaCrust = user.getLastPizzaCrust();
            String pizzaSauce = user.getLastPizzaSauce();
            String topping = user.getLastTopping();
            String cheese = user.getLastCheese();
        
            System.out.println("--------------------------------------------------------------");
            System.out.println("User Id: " + userId);
            System.out.println("Email: " + user.getEmail());
            System.out.println("Mobile number: " + user.getMobile());
            System.out.println("Address: " + user.getAddress());
            System.out.println("Pizza Type: " + pizzaType);
            System.out.println("Pizza Size: " + pizzaSize);
            System.out.println("Pizza Crust: " + pizzaCrust);
            System.out.println("Pizza Sauce: " + pizzaSauce);
            System.out.println("Topping: " + topping);
            System.out.println("Cheese: " + cheese);
            System.out.println("Pizza Fee: $" + pizzaFee);
            System.out.println("Customize Fee: $" + customizeFee);
            System.out.println("Delivery Fee: $" + deliveryFee);
            System.out.println("Seasonal Discount: -$" + seasonalDiscount);
            System.out.println("Total Fee: $" + totalFee);
            System.out.println("--------------------------------------------------------------");
        
            System.out.println("1. Card Payment\n2. Cash on Delivery");
            System.out.print("Choose payment method: ");
            int paymentChoice = scanner.nextInt();
            scanner.nextLine();
        
            if (paymentChoice == 1) {
                System.out.print("Enter card number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter card expiry date: ");
                String cardDate = scanner.nextLine();
                System.out.print("Enter card CVV: ");
                String cardCvv = scanner.nextLine();
                System.out.println("---------------- Payment successful ----------------");
            } else {
                System.out.println("---------------- Payment successful ----------------");
            }
        
            System.out.println("------------- " + deliveryMethod + " successful -------------");
            System.out.println("--------------------------------------------------------------");
        }

        if (orderChoice == 2) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your mobile number: ");
            String mobile = scanner.nextLine();
            System.out.print("Enter your address: ");
            String address = scanner.nextLine();
            
            System.out.println("1. Margherita\n2. Pepperoni\n3. Hawaiian\n4. BBQ Chicken\n5. Veggie");
            System.out.print("Choose Pizza Type: ");
            String pizzaType = scanner.nextLine();
            System.out.println("--------------------------------------------------------------");

            System.out.println("1. Mini\n2. Small\n3. Medium\n4. Large\n5. Jumbo Large");
            System.out.print("Choose Pizza Size: ");
            String pizzaSize = scanner.nextLine();
            System.out.println("--------------------------------------------------------------");

            System.out.println("1. Thin Crust\n2. Thick Crust\n3. Stuffed Crust\n4. Pan Crust\n5. Gluten-Free Crust");
            System.out.print("Choose Pizza Crust: ");
            String pizzaCrust = scanner.nextLine();
            System.out.println("--------------------------------------------------------------");

            System.out.println("1. Tomato Sauce\n2. BBQ Sauce\n3. Alfredo Sauce\n4. Pesto Sauce\n5. Buffalo Sauce");
            System.out.print("Choose Pizza Sauce: ");
            String pizzaSauce = scanner.nextLine();
            System.out.println("--------------------------------------------------------------");

            System.out.print("Enter topping: ");
            String topping = scanner.nextLine();
            System.out.print("Enter cheese: ");
            String cheese = scanner.nextLine();
        

            System.out.println("1. Pickup\n2. Delivery");
            System.out.print("Enter delivery type: ");
            int deliveryType = scanner.nextInt();
            scanner.nextLine(); 
        
            String deliveryMethod = (deliveryType == 1) ? "Pickup" : "Delivery";
            double pizzaFee = 12.0;
            double customizeFee = 2.0;
            double deliveryFee = (deliveryType == 2) ? 3.0 : 0.0;
            double seasonalDiscount = 0.10 * pizzaFee; 
            double totalFee = pizzaFee + deliveryFee - seasonalDiscount;
        
   
            String userId = generateUserId();
            
       
            User newUser = new User(username, email, mobile, address);
            newUser.setLastPizzaOrder(pizzaType, pizzaSize, pizzaCrust, pizzaSauce, topping, cheese, pizzaFee, deliveryFee, 0.0, totalFee);
            userProfiles.put(userId, newUser);
        
      
            System.out.println("--------------------------------------------------------------");
            System.out.println("User Id: " + userId);
            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Mobile number: " + mobile);
            System.out.println("Address: " + address);
            System.out.println("Pizza Type: " + pizzaType);
            System.out.println("Pizza Size: " + pizzaSize);
            System.out.println("Pizza Crust: " + pizzaCrust);
            System.out.println("Pizza Sauce: " + pizzaSauce);
            System.out.println("Topping: " + topping);
            System.out.println("Cheese: " + cheese);
            System.out.println("Pizza Fee: $" + pizzaFee);
            System.out.println("Customize Fee: $" + customizeFee);
            System.out.println("Delivery Fee: $" + deliveryFee);
            System.out.println("Total Fee: $" + totalFee);
            System.out.println("--------------------------------------------------------------");

            System.out.println("1. Card Payment\n2. Cash on Delivery");
            System.out.print("Choose payment method: ");
            int paymentChoice = scanner.nextInt();
            scanner.nextLine();
        
            if (paymentChoice == 1) {
                System.out.print("Enter card number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter card expiry date: ");
                String cardDate = scanner.nextLine();
                System.out.print("Enter card CVV: ");
                String cardCvv = scanner.nextLine();
                System.out.println("---------------- Payment successful ----------------");
            } else {
                System.out.println("---------------- Payment successful ----------------");
            }
            System.out.println("------------- " + deliveryMethod + " successful -------------");
            System.out.println("--------------------------------------------------------------");
        }
        
    }

    public void giveFeedback() {
        System.out.print("Enter Your User Id: ");
        String userId = scanner.nextLine();
        
        if (!userProfiles.containsKey(userId)) {
            System.out.println("User not found! Please create a profile first.");
            return;
        }
    
        System.out.print("Enter rating (1 to 5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); 
    
        System.out.print("Enter comments: ");
        String comments = scanner.nextLine();
    
        User user = userProfiles.get(userId);
        user.setRating(rating);
        user.setComments(comments);
    
        System.out.println("--------------------------------------------------------------");
        System.out.println("Thank you for your feedback!");
        System.out.println("--------------------------------------------------------------");
    }
    
    public void viewDetails() {
        System.out.print("Enter Your User Id: ");
        String userId = scanner.nextLine();
    
        if (!userProfiles.containsKey(userId)) {
            System.out.println("User not found! Please create a profile first.");
            return;
        }
    
        User user = userProfiles.get(userId);
    
        String pizzaType = user.getLastPizzaType();
        String pizzaSize = user.getLastPizzaSize();
        double pizzaFee = user.getLastPizzaFee();
        double deliveryFee = user.getLastDeliveryFee();
        double seasonalDiscount = user.getLastSeasonalDiscount();
        double totalFee = user.getLastTotalFee();
        double customizeFee = user.getLastPizzaFee();
        String pizzaCrust = user.getLastPizzaCrust();
        String pizzaSauce = user.getLastPizzaSauce();
        String topping = user.getLastTopping();
        String cheese = user.getLastCheese();
        int rating = user.getRating();
        String comments = user.getComments();
    
        // Display user and pizza order details
        System.out.println("--------------------------------------------------------------");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Mobile: " + user.getMobile());
        System.out.println("Address: " + user.getAddress());
        System.out.println("Pizza Type: " + pizzaType);
        System.out.println("Pizza Size: " + pizzaSize);
        System.out.println("Pizza Crust: " + pizzaCrust);
        System.out.println("Pizza Sauce: " + pizzaSauce);
        System.out.println("Topping: " + topping);
        System.out.println("Cheese: " + cheese);
        System.out.println("Pizza Fee: $" + pizzaFee);
        System.out.println("Customize Fee: $" + customizeFee);
        System.out.println("Delivery Fee: $" + deliveryFee);
        System.out.println("Seasonal Discount: -$" + seasonalDiscount);
        System.out.println("Total Fee: $" + totalFee);
        System.out.println("--------------------------------------------------------------");
        System.out.println("User Feedback:");
        System.out.println("Rating: " + rating + "/5");
        System.out.println("Comments: " + comments);
        System.out.println("--------------------------------------------------------------");
        System.out.println("Order Status: Delivered");
    }

    public void exitSystem() {
        System.out.println("Thank you for using the Pizza Ordering System!");
    }
}
