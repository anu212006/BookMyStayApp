import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay Application
 * Use Case 5 - Booking Request Queue
 *
 * @version 5.0
 */

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Book My Stay - Booking Requests");

        Queue<String> bookingQueue = new LinkedList<>();

        // Guests submitting booking requests
        bookingQueue.add("Guest1 - Single Room");
        bookingQueue.add("Guest2 - Double Room");
        bookingQueue.add("Guest3 - Suite Room");

        System.out.println("\nBooking Queue:");

        for (String request : bookingQueue) {

            System.out.println(request);

        }

    }

}