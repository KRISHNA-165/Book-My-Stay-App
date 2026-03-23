import java.util.*;

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App =====");

        // Example reservation ID
        String reservationId = "SINGLEROOM-1";

        // Create services
        AddOnService breakfast = new AddOnService("Breakfast", 20);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 40);
        AddOnService spaAccess = new AddOnService("Spa Access", 50);

        // Service manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Guest selects services
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, airportPickup);
        manager.addService(reservationId, spaAccess);

        // Display selected services
        manager.displayServices(reservationId);

        // Calculate total add-on cost
        double totalCost = manager.calculateTotalCost(reservationId);

        System.out.println("\nTotal Add-On Cost: $" + totalCost);

        System.out.println("\n===== Add-On Service Selection Completed =====");
    }
}

// Add-On Service Class
class AddOnService {
    private String serviceName;
    private double price;

    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return serviceName + " ($" + price + ")";
    }
}

// Service Manager Class
class AddOnServiceManager {
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to a reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
        System.out.println("Added Service: " + service + " to " + reservationId);
    }

    // Display services for a reservation
    public void displayServices(String reservationId) {
        System.out.println("\n===== Services for Reservation: " + reservationId + " =====");

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (AddOnService s : services) {
            System.out.println(s);
        }
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null) return 0;

        double total = 0;
        for (AddOnService s : services) {
            total += s.getPrice();
        }
        return total;
    }
}