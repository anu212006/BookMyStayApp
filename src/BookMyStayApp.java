import java.util.*;

/**
 * Book My Stay Application
 * Use Case 6 - Room Allocation & Confirmation
 *
 * @version 6.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Book My Stay - Room Allocation");

        // Inventory setup
        HashMap<String, Integer> inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);

        // Booking request queue
        Queue<String> bookingQueue = new LinkedList<>();
        bookingQueue.add("Guest1 - Single Room");
        bookingQueue.add("Guest2 - Double Room");
        bookingQueue.add("Guest3 - Single Room");

        // Set to store allocated room IDs
        Set<String> allocatedRooms = new HashSet<>();

        int roomId = 101;

        while (!bookingQueue.isEmpty()) {

            String request = bookingQueue.poll();

            String[] parts = request.split(" - ");
            String guest = parts[0];
            String roomType = parts[1];

            if (inventory.containsKey(roomType) && inventory.get(roomType) > 0) {

                String roomNumber = roomType.substring(0,1) + roomId;

                allocatedRooms.add(roomNumber);

                inventory.put(roomType, inventory.get(roomType) - 1);

                System.out.println("Reservation Confirmed for " + guest +
                        " | Room ID: " + roomNumber);

                roomId++;

            } else {

                System.out.println("No rooms available for " + guest);

            }

        }

    }

}