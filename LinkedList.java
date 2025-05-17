// This class manages the linked list of rooms (the dungeon path).
// Rooms are stored in Link objects that point to the next room.
// You can append new rooms and traverse or display the dungeon.
public class LinkedList {
    private Link first;  // Head of the linked list

    // Constructor initializes an empty dungeon
    public LinkedList() {
        first = null;
    }

    // Append a new room to the end of the list (dungeon path)
    public void append(Room room) {
        Link newLink = new Link(room); // Create a new node for the room

        if (first == null) {
            // If list is empty, new link becomes the first node
            first = newLink;
        } else {
            // Traverse to the last node and append
            Link current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newLink;
        }
    }

    // Traverse and print all rooms in the dungeon
    public void displayList() {
        System.out.println("Dungeon Path:");
        Link current = first;

        // Move through each link and display its room
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
    }

    // Return the first link (starting point of the dungeon)
    public Link getFirst() {
        return first;
    }
}