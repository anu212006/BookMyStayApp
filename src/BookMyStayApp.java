import java.io.*;
import java.util.*;

// Reservation Class (Serializable)
class Reservation implements Serializable {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String toString() {
        return reservationId + " | " + guestName + " | " + roomType;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "data.ser";

    // Save data
    public void saveData(List<Reservation> bookings,
                         Map<String, Integer> inventory) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(bookings);
            oos.writeObject(inventory);

            System.out.println("Data saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving data!");
        }
    }

    // Load data
    public void loadData(List<Reservation> bookings,
                         Map<String, Integer> inventory) {

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            List<Reservation> savedBookings =
                    (List<Reservation>) ois.readObject();

            Map<String, Integer> savedInventory =
                    (Map<String, Integer>) ois.readObject();

            bookings.addAll(savedBookings);
            inventory.putAll(savedInventory);

            System.out.println("Data loaded successfully!");

        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (Exception e) {
            System.out.println("Error loading data!");
        }
    }
}

// MAIN CLASS
public class BookMyStayApp {

    public static void main(String[] args) {

        List<Reservation> bookings = new ArrayList<>();
        Map<String, Integer> inventory = new HashMap<>();

        inventory.put("Deluxe", 2);

        PersistenceService service = new PersistenceService();

        // Load previous state
        service.loadData(bookings, inventory);

        // Simulate new booking
        bookings.add(new Reservation("R101", "Anu", "Deluxe"));
        inventory.put("Deluxe", inventory.get("Deluxe") - 1);

        System.out.println("Current Bookings:");
        for (Reservation r : bookings) {
            System.out.println(r);
        }

        System.out.println("Current Inventory: " + inventory);

        // Save state before exit
        service.saveData(bookings, inventory);
    }
}