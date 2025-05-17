public class ActionQueue {
    private ActionNode front;
    private ActionNode rear;

    // Constructor creates empty queue
    public ActionQueue() {
        front = null;
        rear = null;
    }

    // Add an action to the back of the queue
    public void enqueue(String action) {
        ActionNode newNode = new ActionNode(action);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    // Remove and return the front action
    public String dequeue() {
        if (front == null) return null;
        String action = front.action;
        front = front.next;
        if (front == null) rear = null; // reset if empty
        return action;
    }

    // Add action to the end again (cycle it)
    public void cycleAction(String action) {
        enqueue(action);
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return front == null;
    }
}