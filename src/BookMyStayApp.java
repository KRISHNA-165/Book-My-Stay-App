import java.util.*;
// Domain Model: Room
        class Room {
            private String roomType;
            private double price;
            private String amenities;

            public Room(String roomType, double price, String amenities) {
                this.roomType = roomType;
                this.price = price;
                this.amenities = amenities;
            }

            public String getRoomType() {
                return roomType;
            }

            public double getPrice() {
                return price;
            }

            public String getAmenities() {
                return amenities;
            }
        }

// Inventory (State Holder)
        class Inventory {
            private Map<String, Integer> roomAvailability = new HashMap<>();

            public void addRoom(String roomType, int count) {
                roomAvailability.put(roomType, count);
            }

            public int getAvailability(String roomType) {
                return roomAvailability.getOrDefault(roomType, 0);
            }

            public Set<String> getRoomTypes() {
                return roomAvailability.keySet();
            }
        }

// Search Service (Read-only operations)
        class SearchService {

            public void searchAvailableRooms(Inventory inventory, Map<String, Room> rooms) {

                System.out.println("\nAvailable Rooms:\n");

                for (String type : inventory.getRoomTypes()) {

                    int available = inventory.getAvailability(type);

                    // Validation logic
                    if (available > 0) {
                        Room room = rooms.get(type);

                        System.out.println("Room Type : " + room.getRoomType());
                        System.out.println("Price     : $" + room.getPrice());
                        System.out.println("Amenities : " + room.getAmenities());
                        System.out.println("Available : " + available);
                        System.out.println("---------------------------");
                    }
                }
            }
        }

// Main Class

public class BookMyStayApp {

            public static void main(String[] args) {

                // Create room objects (Domain Model)
                Room standard = new Room("Standard", 100, "WiFi, TV");
                Room deluxe = new Room("Deluxe", 180, "WiFi, TV, Mini Bar");
                Room suite = new Room("Suite", 250, "WiFi, TV, Mini Bar, Jacuzzi");

                // Store rooms in a map
                Map<String, Room> rooms = new HashMap<>();
                rooms.put("Standard", standard);
                rooms.put("Deluxe", deluxe);
                rooms.put("Suite", suite);

                // Inventory setup
                Inventory inventory = new Inventory();
                inventory.addRoom("Standard", 5);
                inventory.addRoom("Deluxe", 2);
                inventory.addRoom("Suite", 0); // Suite not available

                // Guest searches for rooms
                SearchService searchService = new SearchService();
                searchService.searchAvailableRooms(inventory, rooms);
            }
        }




