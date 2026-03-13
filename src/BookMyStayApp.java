public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("        BOOK MY STAY APP");
        System.out.println("            Version 2.0");
        System.out.println("=====================================");

        // Polymorphism
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability variables
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("\n--- Single Room Details ---");
        single.displayRoomDetails();
        System.out.println("Available Rooms: " + singleAvailable);

        System.out.println("\n--- Double Room Details ---");
        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleAvailable);

        System.out.println("\n--- Suite Room Details ---");
        suite.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteAvailable);

        System.out.println("\nApplication Terminated.");
    }
}


// Abstract Class
abstract class Room {

    protected String roomType;
    protected int numberOfBeds;
    protected int roomSize;
    protected double price;

    public Room(String roomType, int numberOfBeds, int roomSize, double price) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.roomSize = roomSize;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Room Size: " + roomSize + " sq.ft");
        System.out.println("Price per night: ₹" + price);
    }
}


// Single Room Class
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 2500);
    }
}


// Double Room Class
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 4000);
    }
}


// Suite Room Class
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 500, 7000);
    }
}


