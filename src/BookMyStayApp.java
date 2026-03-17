import java.util.*;

// Reservation Class
class Reservation {
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

// Booking History (UC8)
class BookingHistory {
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public boolean removeReservation(String id) {
        return reservations.removeIf(r -> r.reservationId.equals(id));
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}

// Report Service (UC8)
class BookingReportService {

    public void showAllBookings(List<Reservation> reservations) {
        System.out.println("\n===== BOOKING HISTORY =====");
        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }
}

// Custom Exception (UC9)
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Validator (UC9)
class InvalidBookingValidator {

    public static void validate(String roomType, int roomsAvailable)
            throws InvalidBookingException {

        if (!(roomType.equals("Deluxe") ||
                roomType.equals("Suite") ||
                roomType.equals("Standard"))) {
            throw new InvalidBookingException("Invalid room type!");
        }

        if (roomsAvailable <= 0) {
            throw new InvalidBookingException("No rooms available!");
        }
    }
}

// Cancellation Service (UC10)
class CancellationService {

    private Stack<String> rollbackStack = new Stack<>();

    public boolean cancelBooking(String reservationId,
                                 BookingHistory history,
                                 Map<String, Integer> inventory) {

        List<Reservation> list = history.getReservations();

        for (Reservation r : list) {

            if (r.reservationId.equals(reservationId)) {

                // Push to stack (LIFO rollback)
                rollbackStack.push(reservationId);

                // Restore inventory
                inventory.put(r.roomType,
                        inventory.getOrDefault(r.roomType, 0) + 1);

                // Remove from history
                history.removeReservation(reservationId);

                System.out.println("Cancellation successful for ID: " + reservationId);
                return true;
            }
        }

        System.out.println("Cancellation failed: Reservation not found!");
        return false;
    }

    public void showRollbackStack() {
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}

// MAIN CLASS
public class BookMyStayApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();
        CancellationService cancelService = new CancellationService();

        // Inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 2);
        inventory.put("Standard", 2);

        System.out.println("Enter Room Type (Deluxe/Suite/Standard): ");
        String roomType = sc.nextLine();

        try {
            // UC9 Validation
            InvalidBookingValidator.validate(roomType, inventory.get(roomType));

            // Booking success
            System.out.println("Booking successful!");

            // Reduce inventory
            inventory.put(roomType, inventory.get(roomType) - 1);

            // Add booking (UC8)
            history.addReservation(new Reservation("R101", "Guest", roomType));

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        // Show bookings
        reportService.showAllBookings(history.getReservations());

        // UC10: Cancellation
        System.out.println("\nEnter Reservation ID to cancel:");
        String cancelId = sc.nextLine();

        cancelService.cancelBooking(cancelId, history, inventory);

        // Show updated data
        reportService.showAllBookings(history.getReservations());
        cancelService.showRollbackStack();

        System.out.println("Updated Inventory: " + inventory);

        System.out.println("\nSystem remains consistent...");
    }
}