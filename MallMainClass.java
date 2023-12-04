import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;


class Customer {
    protected String name; 
    protected int balance;
    protected int age;

    public Customer(String name, int balance, int age, HashMap<String, Integer> foodsAvailable) {
        this.name = name;
        this.balance = balance;
        this.age = age;
    }

    
    public int processTransaction(HashMap<String, Integer> foodsAvailable, String order_name, int quantity) {
        int price_per_order = foodsAvailable.get(order_name);
        int total_price = price_per_order * quantity;
        
        return total_price;
    }
}




// Inheritance: The ReferralDiscount and SeniorDiscount classes inherit from the Customer class 
// to gain access to its properties and methods.

class ReferralDiscount extends Customer {
    private List<String> referrals; 

    // Inheritance: The ReferralDiscount class inherits the constructor from the Customer class.
    public ReferralDiscount(String name, int balance, int age, HashMap<String, Integer> foodsAvailable, List<String> referrals) {
        super(name, balance, age, foodsAvailable); 
        this.referrals = referrals;
    }

    @Override
    public int processTransaction(HashMap<String, Integer> foodsAvailable, String order_name, int quantity) {
        int price_per_order = foodsAvailable.get(order_name);
        int total_price = price_per_order * quantity;

        // Apply 50% discount if there are more than 4 referrals
        if (referrals.size() > 4) {
            total_price = total_price / 2;
        }

        return total_price;
    }
}



// Inheritance: The SeniorDiscount class inherits from the Customer class, gaining access to its properties and methods.
class SeniorDiscount extends Customer {
    // Constructor to initialize the senior discount
    public SeniorDiscount(String name, int balance, int age, HashMap<String, Integer> foodsAvailable) {
        super(name, balance, age, foodsAvailable);
    }

    // Polymorphism: The processTransaction method is overridden to provide a different implementation for senior discounts.
    @Override
    public int processTransaction(HashMap<String, Integer> foodsAvailable, String order_name, int quantity) {
        int price_per_order = foodsAvailable.get(order_name);
        int total_price = price_per_order * quantity;

        // Apply 60% discount for customers above 60 years old
        if (age > 60) {
            total_price = total_price * 40 / 100;
        }

        return total_price;
    }
}


// Main class for interacting with the user
class MallMainClass {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Apple", 10);
        hashMap.put("Banana", 5);
        hashMap.put("Orange", 8);
        hashMap.put("Grapes", 15);

        Set<Map.Entry<String, Integer>> entries = hashMap.entrySet();
        System.out.println("All products and their prices: " + entries);

        System.out.println("\nEnter your name: ");
        String name = scan.nextLine();

        System.out.println("Enter your balance: ");
        int balance = scan.nextInt();
        scan.nextLine(); // Consume the newline character

        System.out.println("Enter your age: ");
        int age = scan.nextInt();
        scan.nextLine(); // Consume the newline character

        System.out.println("Enter your chosen product: ");
        String chosenProduct = scan.nextLine();

        System.out.println("Enter the quantity: ");
        int quantity = scan.nextInt();
        scan.nextLine(); // Consume the newline character

        System.out.println("Enter the names of your referrals separated by spaces: ");
        String referralsInput = scan.nextLine();
        List<String> referrals = Arrays.asList(referralsInput.split(" "));

        Customer customer;
        if (age > 60) {
            customer = new SeniorDiscount(name, balance, age, hashMap);
        } else {
            customer = new Customer(name, balance, age, hashMap);
        }
        

		if (referrals.size() > 0) {
            customer = new ReferralDiscount(name, balance, age, hashMap, referrals);
        }
        
        System.out.println("Total Price: ");
        System.out.println(customer.processTransaction(hashMap, chosenProduct, quantity));
           
