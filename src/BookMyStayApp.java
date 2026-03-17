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

// Booking History (Storage)
class BookingHistory {

    // List to maintain order
    private List<Reservation> reservations = new ArrayList<>();

    // Add confirmed booking
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    // Get all reservations
    public List<Reservation> getReservations() {
        return reservations;
    }
}

// Report Service
class BookingReportService {

    // Display all bookings
    public void showAllBookings(List<Reservation> reservations) {

        System.out.println("\n===== BOOKING HISTORY =====");

        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    // Generate summary
    public void generateSummary(List<Reservation> reservations) {

        System.out.println("\n===== SUMMARY REPORT =====");
        System.out.println("Total Bookings: " + reservations.size());

        Map<String, Integer> roomCount = new HashMap<>();

        for (Reservation r : reservations) {
            roomCount.put(r.roomType,
                    roomCount.getOrDefault(r.roomType, 0) + 1);
        }

        System.out.println("Room Type Distribution:");
        for (String type : roomCount.keySet()) {
            System.out.println(type + " : " + roomCount.get(type));
        }
    }
}

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulating confirmed bookings
        history.addReservation(new Reservation("R101", "Anu", "Deluxe"));
        history.addReservation(new Reservation("R102", "Raj", "Suite"));
        history.addReservation(new Reservation("R103", "Priya", "Deluxe"));

        // Admin views history
        reportService.showAllBookings(history.getReservations());

        // Admin views report
        reportService.generateSummary(history.getReservations());
    }
}