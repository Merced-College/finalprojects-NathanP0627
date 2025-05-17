import java.util.Scanner;
import java.util.HashMap;
import java.util.Stack;


// Represents the player class
class Player{
    String name;
    int hp;
    String job;
    int level;
    int xp;
    public HashMap<String, Integer> inventory = new HashMap<>();

    public Player(String name){
        this.name = name;
        this.hp = 100;
        this.job = "Jobless";
        this.level = 1;
        this.xp = 0;
    }
    // How the player character will attack enemies
    public void attack(Enemy enemy){
        int damage = (int)(Math.random() * 10 + 5);
        System.out.println(name + " attacks " + enemy.name + " for " + damage + " damage!");
        enemy.hp -= damage;
}

// XP system
    public void gainXP(int xpGained){
        xp += xpGained;
        if(xp >= level * 10){
        level++;
        hp += 10;
        xp = 0; //Might add a way so that if xp overflows, it goes back into this xp
        System.out.println("You leveled up! You are now level " + level + ".");
    }
}
// Add an item to inventory
    public void addItem(String item) {
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
        System.out.println("You picked up: " + item);
    }

    public void printInventory(){
        System.out.println("\n Inventory");
        if (inventory.isEmpty()){ 
            System.out.println(" - (empty)");
        } else {
            for (String item : inventory.keySet()){
                System.out.println(" - " + item + " x" + inventory.get(item));
            }
        }
    }
}


// Represents the enemies
class Enemy {
    String name;
    int level;
    int hp;

    public Enemy(String name, int level, int hp){
        this.name = name;
        this.level = level;
        this.hp = hp;
    }
// How the enemies attack the player chharacter
    public void enemyattack(Player player){
    int baseDamage = 3;
    int damage = (int)(Math.random() * 5 + baseDamage + level * 2);
    System.out.println(name + " (Lv." + level + ") attacks " + player.name + " for " + damage + " damage!");
    player.hp -= damage;
    }
}


// Where everything will run
public class RPG{
    public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    // Stores previously visited rooms
    Stack<Link> roomHistory = new Stack<>(); 


        // Create player
        System.out.print("Enter your hero's name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);

        // Build dungeon using LinkedList
        LinkedList dungeon = new LinkedList();
        dungeon.append(new Room("You enter a dark and cold cave.", new Enemy("Goblin", 1, 25), "Potion"));
        dungeon.append(new Room("You hear distant bones clattering.", new Enemy("Skeleton", 2, 35), "Key"));
        dungeon.append(new Room("A glowing chest lies in the corner.", null, "Gold"));  // No enemy in last room



        // Traverse dungeon via LinkedList
        Link current = dungeon.getFirst();
        while (current != null) {
            Room currentRoom = current.room;
            System.out.println("\n>>> " + currentRoom.getDescription());

            // ✅ NEW: Collect item if the room has one
            if (currentRoom.item != null) {
                player.addItem(currentRoom.item);    // add item to inventory
                currentRoom.item = null;             // prevent re-collecting
            }
            
            // If there's an enemy in the room, trigger battle
            if (currentRoom.enemy != null && currentRoom.enemy.hp > 0) {
                Enemy enemy = currentRoom.enemy;
                System.out.println("A wild " + enemy.name + " appears!");

                // Queue for enemy actions
                ActionQueue enemyActions = new ActionQueue();
                enemyActions.enqueue("attack");
                enemyActions.enqueue("growl");
                enemyActions.enqueue("wait");

                while (enemy.hp > 0 && player.hp > 0) {
                    System.out.print("Do you want to (a)ttack, (r)un, or view (i)nventory? ");
                    String action = scanner.nextLine();

                    // ✅ NEW: Inventory command in combat
                    if (action.equalsIgnoreCase("i")) {
                        player.printInventory();
                        continue;
                    }

                    if (action.equalsIgnoreCase("a")) {
                        player.attack(enemy);

                        if (currentRoom.enemy.hp > 0) {
                            String enemyAction = enemyActions.dequeue();
                            if (enemyAction.equals("attack")) {
                                enemy.enemyattack(player);
                            } else if (enemyAction.equals("growl")) {
                                System.out.println(enemy.name + " growls fiercely!");
                            } else if (enemyAction.equals("wait")) {
                                System.out.println(enemy.name + " hesitates...");
                            }

                            enemyActions.cycleAction(enemyAction); // rotate action
                            
                        } else {
                            System.out.println("You defeated the " + enemy.name + "!");
                            player.gainXP(10);
                        }
                    } else {
                        System.out.println("You try to run... but the way is blocked!");
                    }
                }

                if (player.hp <= 0) {
                    System.out.println("You have fallen in battle. Game Over.");
                    break;
                }
            }

            // Move to the next room in the dungeon
            if (current.next != null) {
                roomHistory.push(current);
                current = current.next;
            } else {
                current = null;
            }

            if (current != null) {
                System.out.print("Press Enter to continue to the next room... or type 'i' to view inventory or u to undo and go back to the previous room: ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("i")) {
                    player.printInventory();
                } else if (choice.equalsIgnoreCase("u")){
                    if (!roomHistory.isEmpty()) {
                        // Go back to last room
                    current = roomHistory.pop();
                    System.out.println("You retraced your steps to the previous room.");
                    continue;
                } else{
                    System.out.println("\nYou turn around and run out of the dungeon!");
                    System.out.println("Cowardice has saved your life... for now.");
                    return; // Exit the game early
                }
            }
        }
    }
        if (player.hp > 0) {
            System.out.println("\nYou've cleared the dungeon. Congratulations, " + player.name + "!");
        
    }
}
}