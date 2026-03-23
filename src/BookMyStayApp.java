import java.util.*;

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Luxury Room"); // Invalid type

        processBooking(r1, inventory);
        processBooking(r2, inventory);
    }

    public static void processBooking(Reservation reservation, RoomInventory inventory) {

        try {
            String roomType = reservation.getRoomType();

            // Validation checks
            InvalidBookingValidator.validateRoomType(roomType, inventory);
            InvalidBookingValidator.validateAvailability(roomType, inventory);

            int available = inventory.getAvailability(roomType);

            inventory.updateAvailability(roomType, available - 1);

            System.out.println("Booking successful for "
                    + reservation.getGuestName() + " - " + roomType);

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed for "
                    + reservation.getGuestName() + ": " + e.getMessage());
        }
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
}

// Room Inventory Class
class RoomInventory {
    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Single Room", 1);
        rooms.put("Double Room", 1);
        rooms.put("Suite Room", 0); // No availability
    }

    public boolean containsRoomType(String roomType) {
        return rooms.containsKey(roomType);
    }

    public int getAvailability(String roomType) {
        return rooms.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        rooms.put(roomType, count);
    }
}

// Custom Exception Class
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Validator Class
class InvalidBookingValidator {

    public static void validateRoomType(String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        if (!inventory.containsRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }

    public static void validateAvailability(String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        if (inventory.getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("Room not available.");
        }
    }
}