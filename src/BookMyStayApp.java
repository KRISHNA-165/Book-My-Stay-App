import java.util.*;

// Main Class
public class  BookMyStayApp{

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");
        Reservation r4 = new Reservation("David", "Single Room");

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);
        history.addReservation(r4);

        System.out.println("===== Booking History =====");
        history.displayHistory();

        BookingReportService reportService = new BookingReportService(history);

        reportService.generateReport();
    }
}

// Reservation Class
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return guestName + " - " + roomType;
    }
}

// Booking History Class
class BookingHistory {
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation r) {
        reservations.add(r);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void displayHistory() {
        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }
}

// Report Service Class
class BookingReportService {
    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    public void generateReport() {
        System.out.println("\n===== Booking Report =====");

        Map<String, Integer> roomCount = new HashMap<>();

        // Count room types
        for (Reservation r : history.getReservations()) {
            String type = r.getRoomType();
            roomCount.put(type, roomCount.getOrDefault(type, 0) + 1);
        }

        // Display report
        for (String type : roomCount.keySet()) {
            System.out.println(type + ": " + roomCount.get(type) + " bookings");
        }

        // Find most popular room type
        String popularRoom = null;
        int max = 0;

        for (Map.Entry<String, Integer> entry : roomCount.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                popularRoom = entry.getKey();
            }
        }

        System.out.println("\nMost Popular Room Type: " + popularRoom);
    }
}