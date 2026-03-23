import java.util.LinkedList;
import java.util.Queue;

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay App =====");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Guests submit booking requests
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");

        // Add requests to queue (FIFO order)
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Display queued booking requests
        bookingQueue.displayRequests();

        System.out.println("\n===== Processing Requests =====");

        // Process requests one by one
        while (!bookingQueue.isEmpty()) {
            Reservation processed = bookingQueue.processRequest();
            System.out.println("Processed: " + processed);
        }
    }
}

// Reservation class
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room: " + roomType;
    }
}

// BookingRequestQueue class
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Added: " + reservation);
    }

    // Display all requests
    public void displayRequests() {
        System.out.println("\n===== Booking Requests Queue =====");
        if (queue.isEmpty()) {
            System.out.println("No booking requests.");
            return;
        }

        for (Reservation r : queue) {
            System.out.println(r);
        }
    }

    // Process (remove) request
    public Reservation processRequest() {
        return queue.poll();
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}