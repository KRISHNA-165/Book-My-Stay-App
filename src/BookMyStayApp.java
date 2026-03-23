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

    public int getBookingId() {
        return bookingId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String toString() {
        return "BookingID: " + bookingId +
                ", Guest: " + guestName +
                ", Room: " + roomNumber;
    }
}

// Hotel System
class HotelSystem {

    private Map<Integer, Booking> bookings = new HashMap<>();
    private Set<Integer> availableRooms = new HashSet<>();
    private int bookingCounter = 1;

    // Initialize rooms
    public HotelSystem(int totalRooms) {
        for (int i = 1; i <= totalRooms; i++) {
            availableRooms.add(i);
        }
    }

    // Book Room
    public void bookRoom(String guestName) {
        if (availableRooms.isEmpty()) {
            System.out.println("No rooms available!");
            return;
        }

        int roomNumber = availableRooms.iterator().next();
        availableRooms.remove(roomNumber);

        Booking booking = new Booking(bookingCounter++, guestName, roomNumber);
        bookings.put(booking.getBookingId(), booking);

        System.out.println("Booking Successful: " + booking);
    }

    // ✅ UC10: Cancel Booking + Inventory Rollback
    public void cancelBooking(int bookingId) {

        // Step 1: Check if booking exists
        if (!bookings.containsKey(bookingId)) {
            System.out.println("Invalid Booking ID!");
            return;
        }

        // Step 2: Remove booking
        Booking booking = bookings.remove(bookingId);

        // Step 3: Rollback inventory (make room available again)
        availableRooms.add(booking.getRoomNumber());

        System.out.println("Booking Cancelled Successfully!");
        System.out.println("Room " + booking.getRoomNumber() + " is now available.");
    }

    // Display Available Rooms
    public void showAvailableRooms() {
        System.out.println("Available Rooms: " + availableRooms);
    }

    // Display Bookings
    public void showBookings() {
        System.out.println("Current Bookings:");
        for (Booking b : bookings.values()) {
            System.out.println(b);
        }
    }
}

// Main Class
public class BookMyStayApp {
    public static void main(String[] args) {

        HotelSystem hotel = new HotelSystem(3);

        hotel.bookRoom("Alice");
        hotel.bookRoom("Bob");

        hotel.showBookings();
        hotel.showAvailableRooms();

        // UC10 Execution
        hotel.cancelBooking(1);

        hotel.showBookings();
        hotel.showAvailableRooms();
    }
}
