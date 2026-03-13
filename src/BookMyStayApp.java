
import java.util.HashMap;
import java.util.Map;
public class BookMyStayApp {
    public static void main(String[] args) {

            System.out.println("=====================================");
            System.out.println("        BOOK MY STAY APP");
            System.out.println("            Version 3.0");
            System.out.println("=====================================");

            // Initialize Inventory
            RoomInventory inventory = new RoomInventory();

            // Display current inventory
            System.out.println("\nCurrent Room Inventory:");
            inventory.displayInventory();

            // Example update
            System.out.println("\nUpdating inventory...");
            inventory.updateAvailability("Single Room", 4);

            // Display updated inventory
            System.out.println("\nUpdated Room Inventory:");
            inventory.displayInventory();
        }
    }


    // Room Inventory Class
    class RoomInventory {

        private HashMap<String, Integer> availability;

        // Constructor initializes inventory
        public RoomInventory() {
            availability = new HashMap<>();

            availability.put("Single Room", 5);
            availability.put("Double Room", 3);
            availability.put("Suite Room", 2);
        }

        // Get availability of a room type
        public int getAvailability(String roomType) {
            return availability.getOrDefault(roomType, 0);
        }

        // Update availability
        public void updateAvailability(String roomType, int count) {
            availability.put(roomType, count);
        }

        // Display entire inventory
        public void displayInventory() {
            for (Map.Entry<String, Integer> entry : availability.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
            }
        }
    }




