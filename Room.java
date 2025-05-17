// Represents a single dungeon room with a description.
// This is the core data stored inside each node of the linked list.
public class Room {
    private String description;

    // Constructor assigns the room description
    public Room(String description) {
        this.description = description;
    }

    // Print the room's description
    public void displayRoom() {
        System.out.println(description);
    }

    // Optional getter if we want to access the description
    public String getDescription() {
        return description;
    }
}