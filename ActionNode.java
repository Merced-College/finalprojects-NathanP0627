public class ActionNode {
    public String action;
    public ActionNode next;

    public ActionNode(String action) {
        this.action = action;
        this.next = null;
    }
}