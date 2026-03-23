import java.util.*;

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App =====");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Single Room"));
        bookingQueue.addRequest(new Reservation("David", "Suite Room"));

        // Allocation service
        RoomAllocationService allocationService =
                new RoomAllocationService(inventory, bookingQueue);

        // Process queue
        allocationService.processBookings();

        // Show final allocations
        allocationService.displayAllocations();

        System.out.println("\n===== Booking Process Completed =====");
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

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return guestName + " (" + roomType + ")";
    }
}

// Booking Queue Class
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request Added: " + reservation);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// Room Inventory Class
class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Single Room", 2);
        rooms.put("Double Room", 1);
        rooms.put("Suite Room", 1);
    }

    public boolean allocateRoom(String roomType) {
        if (rooms.containsKey(roomType) && rooms.get(roomType) > 0) {
            rooms.put(roomType, rooms.get(roomType) - 1);
            return true;
        }
        return false;
    }
}

// Allocation Service Class
class RoomAllocationService {
    private RoomInventory inventory;
    private BookingRequestQueue queue;
    private List<String> confirmedBookings = new ArrayList<>();
    private List<String> failedBookings = new ArrayList<>();

    public RoomAllocationService(RoomInventory inventory, BookingRequestQueue queue) {
        this.inventory = inventory;
        this.queue = queue;
    }

    public void processBookings() {
        System.out.println("\n===== Processing Bookings =====");

        while (!queue.isEmpty()) {
            Reservation request = queue.getNextRequest();

            if (inventory.allocateRoom(request.getRoomType())) {
                String msg = "CONFIRMED: " + request;
                confirmedBookings.add(msg);
                System.out.println(msg);
            } else {
                String msg = "FAILED (No Room): " + request;
                failedBookings.add(msg);
                System.out.println(msg);
            }
        }
    }

    public void displayAllocations() {
        System.out.println("\n===== Confirmed Bookings =====");
        for (String s : confirmedBookings) {
            System.out.println(s);
        }

        System.out.println("\n===== Failed Bookings =====");
        for (String s : failedBookings) {
            System.out.println(s);
        }
    }
}