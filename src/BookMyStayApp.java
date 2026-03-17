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

// Booking History Class (UC8)
class BookingHistory {
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}

// Report Service Class (UC8)
class BookingReportService {

    public void showAllBookings(List<Reservation> reservations) {
        System.out.println("\n===== BOOKING HISTORY =====");
        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    public void generateSummary(List<Reservation> reservations) {
        System.out.println("\n===== SUMMARY REPORT =====");
        System.out.println("Total Bookings: " + reservations.size());

        Map<String, Integer> roomCount = new HashMap<>();

        for (Reservation r : reservations) {
            roomCount.put(r.roomType,
                    roomCount.getOrDefault(r.roomType, 0) + 1);
        }

        for (String type : roomCount.keySet()) {
            System.out.println(type + " : " + roomCount.get(type));
        }
    }
}

// Custom Exception (UC9)
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Validator Class (UC9)
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

// MAIN CLASS
public class BookMyStayApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        int roomsAvailable = 2;

        System.out.println("Enter Room Type (Deluxe/Suite/Standard): ");
        String roomType = sc.nextLine();

        try {
            // UC9 Validation
            InvalidBookingValidator.validate(roomType, roomsAvailable);

            // Booking Success
            System.out.println("Booking Successful for " + roomType);

            // Add to history (UC8)
            history.addReservation(new Reservation("R101", "Guest", roomType));

            roomsAvailable--;

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        // Show History & Report (UC8)
        reportService.showAllBookings(history.getReservations());
        reportService.generateSummary(history.getReservations());

        System.out.println("\nSystem continues safely...");
    }
}