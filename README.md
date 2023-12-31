# Object Oriented Design Review Lecture

```java
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

class ReferralDiscount extends Customer {
    private List<String> referrals;

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

class SeniorDiscount extends Customer {
    public SeniorDiscount(String name, int balance, int age, HashMap<String, Integer> foodsAvailable) {
        super(name, balance, age, foodsAvailable);
    }

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
    }
}

```

# Java HashMap Sample Operations (Additional)

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

public class HashMapExample {
    public static void main(String[] args) {
        // Creating a HashMap with keys of type String and values of type Integer
        HashMap<String, Integer> hashMap = new HashMap<>();

        // Adding key-value pairs to the HashMap
        hashMap.put("Apple", 10);
        hashMap.put("Banana", 5);
        hashMap.put("Orange", 8);
        hashMap.put("Grapes", 15);

        // keys() - Getting all keys from the HashMap
        Set<String> keys = hashMap.keySet();
        System.out.println("Keys: " + keys);

        // values() - Getting all values from the HashMap
        Collection<Integer> values = hashMap.values();
        System.out.println("Values: " + values);

        // items() - Getting all key-value pairs from the HashMap
        Set<Map.Entry<String, Integer>> entries = hashMap.entrySet();
        System.out.println("Key-Value Pairs: " + entries);

        // get() - Getting value associated with a specific key
        Integer value = hashMap.get("Apple");
        System.out.println("Value for key 'Apple': " + value);

        // pop() - Removing and returning value associated with a specific key
        Integer poppedValue = hashMap.remove("Banana");
        System.out.println("Popped value for key 'Banana': " + poppedValue);

        // update() - Updating the HashMap with key-value pairs from another HashMap
        HashMap<String, Integer> anotherHashMap = new HashMap<>();
        anotherHashMap.put("Pineapple", 12);
        anotherHashMap.put("Mango", 20);
        hashMap.putAll(anotherHashMap);
        System.out.println("Updated HashMap: " + hashMap);

        // clear() - Removing all key-value pairs from the HashMap
        hashMap.clear();
        System.out.println("Cleared HashMap: " + hashMap);
    }
}
```