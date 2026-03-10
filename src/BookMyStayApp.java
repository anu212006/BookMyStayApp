import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay Application
 * Use Case 4 - Room Search
 *
 * @version 4.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Book My Stay - Room Search");

        HashMap<String, Integer> inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0);
        inventory.put("Suite Room", 2);

        System.out.println("\nAvailable Rooms:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {

            if (entry.getValue() > 0) {

                System.out.println(entry.getKey() + " Available: " + entry.getValue());

            }

        }

    }

}
