import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay Application
 * Use Case 3 - Centralized Room Inventory
 *
 * @version 3.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Book My Stay - Room Inventory");

        // Centralized Inventory using HashMap
        HashMap<String, Integer> inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);

        System.out.println("\nCurrent Room Availability:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {

            System.out.println(entry.getKey() + " : " + entry.getValue());

        }

    }

}