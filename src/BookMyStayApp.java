import java.util.*;

// Booking class
class Booking {
    int bookingId;
    String guestName;
    int roomNumber;

    public Booking(int bookingId, String guestName, int roomNumber) {
        this.bookingId = bookingId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
    }

    public String toString() {
        return "BookingID: " + bookingId +
                ", Guest: " + guestName +
                ", Room: " + roomNumber;
    }
}

// Hotel System (Thread-Safe)
class HotelSystem {

    private Map<Integer, Booking> bookings = new HashMap<>();
    private Set<Integer> availableRooms = new HashSet<>();
    private int bookingCounter = 1;

    public HotelSystem(int totalRooms) {
        for (int i = 1; i <= totalRooms; i++) {
            availableRooms.add(i);
        }
    }

    // 🔒 Synchronized booking method (CRITICAL SECTION)
    public synchronized void bookRoom(String guestName) {

        if (availableRooms.isEmpty()) {
            System.out.println(Thread.currentThread().getName() +
                    ": No rooms available!");
            return;
        }

        // simulate delay (to show concurrency effect)
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int roomNumber = availableRooms.iterator().next();
        availableRooms.remove(roomNumber);

        Booking booking = new Booking(bookingCounter++, guestName, roomNumber);
        bookings.put(booking.bookingId, booking);

        System.out.println(Thread.currentThread().getName() +
                " booked -> " + booking);
    }

    public void showBookings() {
        System.out.println("\nFinal Bookings:");
        for (Booking b : bookings.values()) {
            System.out.println(b);
        }
    }
}

// Thread class (simulates a user)
class UserThread extends Thread {

    private HotelSystem hotel;
    private String guestName;

    public UserThread(HotelSystem hotel, String guestName) {
        this.hotel = hotel;
        this.guestName = guestName;
    }

    @Override
    public void run() {
        hotel.bookRoom(guestName);
    }
}

// Main Class
public class BookMyStayApp {
    public static void main(String[] args) {

        HotelSystem hotel = new HotelSystem(2);

        // Simulate concurrent users
        Thread user1 = new UserThread(hotel, "Alice");
        Thread user2 = new UserThread(hotel, "Bob");
        Thread user3 = new UserThread(hotel, "Charlie");

        user1.setName("User-1");
        user2.setName("User-2");
        user3.setName("User-3");

        // Start threads simultaneously
        user1.start();
        user2.start();
        user3.start();

        // Wait for all threads to finish
        try {
            user1.join();
            user2.join();
            user3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        hotel.showBookings();
    }
}

