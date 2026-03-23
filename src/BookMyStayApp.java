import java.io.*;
import java.util.*;

// Booking class (Serializable)
class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

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

// Wrapper class for persistence
class HotelData implements Serializable {
    private static final long serialVersionUID = 1L;

    Map<Integer, Booking> bookings;
    Set<Integer> availableRooms;
    int bookingCounter;

    public HotelData(Map<Integer, Booking> bookings,
                     Set<Integer> availableRooms,
                     int bookingCounter) {
        this.bookings = bookings;
        this.availableRooms = availableRooms;
        this.bookingCounter = bookingCounter;
    }
}

// Hotel System
class HotelSystem {

    private Map<Integer, Booking> bookings = new HashMap<>();
    private Set<Integer> availableRooms = new HashSet<>();
    private int bookingCounter = 1;

    private final String FILE_NAME = "hotel_data.ser";

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

    // Show bookings
    public void showBookings() {
        System.out.println("Current Bookings:");
        for (Booking b : bookings.values()) {
            System.out.println(b);
        }
    }

    // Show available rooms
    public void showAvailableRooms() {
        System.out.println("Available Rooms: " + availableRooms);
    }

    // ✅ UC12: SAVE DATA (Persistence)
    public void saveData() {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            HotelData data = new HotelData(bookings, availableRooms, bookingCounter);
            oos.writeObject(data);

            System.out.println("Data saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving data!");
            e.printStackTrace();
        }
    }

    // ✅ UC12: LOAD DATA (Recovery)
    public void loadData() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            HotelData data = (HotelData) ois.readObject();

            this.bookings = data.bookings;
            this.availableRooms = data.availableRooms;
            this.bookingCounter = data.bookingCounter;

            System.out.println("System recovered successfully!");

        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data!");
            e.printStackTrace();
        }
    }
}

// Main Class
public class BookMyStayApp {
    public static void main(String[] args) {

        HotelSystem hotel = new HotelSystem(3);

        // Try recovering previous data
        hotel.loadData();

        hotel.bookRoom("Alice");
        hotel.bookRoom("Bob");

        hotel.showBookings();
        hotel.showAvailableRooms();

        // Save before exit
        hotel.saveData();
    }
}
