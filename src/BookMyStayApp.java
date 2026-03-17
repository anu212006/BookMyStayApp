<<<<<<< HEAD
import java.util.*;

// Add-On Service Class
class AddOnService {
    String serviceName;
    double cost;

    AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map: ReservationID -> List of Services
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {

        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {

        double total = 0;

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.cost;
            }
        }

        return total;
    }

    // Display services
    public void displayServices(String reservationId) {

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null) {
            System.out.println("No services added.");
            return;
        }

        System.out.println("Services for Reservation: " + reservationId);

        for (AddOnService s : services) {
            System.out.println(s.serviceName + " - ₹" + s.cost);
        }
    }
}
=======
/**
 * Book My Stay Application
 * Entry point for Hotel Booking System
 *
 * @author Student
 * @version 1.0
 */
>>>>>>> 7a1e7622b18b5ffd76f7f05df811d99988e45289

public class BookMyStayApp {

    public static void main(String[] args) {

<<<<<<< HEAD
        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "R101";

        // Guest selects services
        manager.addService(reservationId, new AddOnService("Breakfast", 200));
        manager.addService(reservationId, new AddOnService("Spa", 1000));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 500));

        // Display services
        manager.displayServices(reservationId);

        // Total cost
        double total = manager.calculateTotalCost(reservationId);

        System.out.println("Total Add-On Cost: ₹" + total);
    }
=======
        System.out.println("Welcome to Book My Stay");
        System.out.println("Hotel Booking Management System");
        System.out.println("Version 1.0");

    }

>>>>>>> 7a1e7622b18b5ffd76f7f05df811d99988e45289
}