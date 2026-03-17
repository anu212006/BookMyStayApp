import java.util.*;

// Booking Processor (handles shared resource safely)
class BookingProcessor {

    private Map<String, Integer> inventory;

    BookingProcessor(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    // Critical Section (Thread-safe)
    public synchronized void bookRoom(String guestName, String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            System.out.println(Thread.currentThread().getName()
                    + " processing booking for " + guestName);

            // Simulate delay
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            inventory.put(roomType, available - 1);

            System.out.println("Booking SUCCESS for " + guestName);
        } else {
            System.out.println("Booking FAILED for " + guestName + " (No rooms)");
        }
    }
}

// MAIN CLASS
public class BookMyStayApp {

    public static void main(String[] args) {

        // Shared inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Deluxe", 2);

        BookingProcessor processor = new BookingProcessor(inventory);

        // Multiple booking requests (threads)
        Thread t1 = new Thread(() -> processor.bookRoom("Anu", "Deluxe"));
        Thread t2 = new Thread(() -> processor.bookRoom("Raj", "Deluxe"));
        Thread t3 = new Thread(() -> processor.bookRoom("Priya", "Deluxe"));

        // Start threads
        t1.start();
        t2.start();
        t3.start();

        // Wait for completion
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final inventory
        System.out.println("Final Inventory: " + inventory);
    }
}