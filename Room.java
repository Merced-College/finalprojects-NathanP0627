// Represents a single dungeon room with a description.
// This is the core data stored inside each node of the linked list.
public class Room {
    private String description;
    public Enemy enemy; // ✅ Add this field

    // ✅ Constructor now accepts description and enemy
    public Room(String description, Enemy enemy) {
        this.description = description;
        this.enemy = enemy;
    }

    // Display the room's description
    public void displayRoom() {
        System.out.println(description);
    }

    public String getDescription() {
        return description;
    }
}