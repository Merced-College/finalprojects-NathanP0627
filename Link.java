// Link object = single node in the linked list.
// It stores a Room object and a reference to the next Link.
public class Link {
    public Room room;   // The Room object stored in this node
    public Link next;   // Pointer to the next node in the list

    // Constructor initializes the link with a room
    public Link(Room room) {
        this.room = room;
        this.next = null;
    }

    // Display the room description through the Room class
    public void displayLink() {
        room.displayRoom();
    }
}