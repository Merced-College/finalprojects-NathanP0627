import java.util.Scanner;

// This class represents rooms in each dungeon using a doubly linked list, will be used to for descriptions
class Room{
    String descriptions;
    Enemy enemy;
    Room next;
    Room prev;
    
    public Room(String descriptions, Enemy enemy){
        this.descriptions = descriptions;
        this.enemy = enemy;
    }
}

// Represents the player class
class Player{
    String name:
    int hp;
    String job;
    int level;
    int xp;
    
    public Player(String name){
        this.name = name;
        this.hp = 100;
        this.job = "Jobless";
        this.level = 1;
        this.xp = 0;
    }
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
}

// How the enemies attack the player chharacter
public void enemyattack(Player player){
    int baseDamage = 3;
    int damage = (int)(Math.random() * 5 + baseDamage + level * 2);
    System.out.println(name + " (Lv." + level + ") attacks " + player.name + " for " + damage + " damage!");
    player.hp -= damage;
}


// Where everything will run
public class RPG{
    public static void main(String[] args){
      Scanner scanner = new Scanner(System.in);

        // Create player
        System.out.print("Enter your hero's name: ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName);

        // Build dungeon using LinkedList
        LinkedList dungeon = new LinkedList();
        dungeon.append(new Room("You enter a dark and cold cave.", new Enemy("Goblin", 1, 25)));
        dungeon.append(new Room("You hear distant bones clattering.", new Enemy("Skeleton", 2, 35)));
        dungeon.append(new Room("A glowing chest lies in the corner.", null));  // No enemy in last room

        // Traverse dungeon via LinkedList
        Link current = dungeon.getFirst();
        while (current != null) {
            Room currentRoom = current.room;
            System.out.println("\n>>> " + currentRoom.getDescription());

            // If there's an enemy in the room, trigger battle
            if (currentRoom.enemy != null && currentRoom.enemy.hp > 0) {
                System.out.println("A wild " + currentRoom.enemy.name + " appears!");

                while (currentRoom.enemy.hp > 0 && player.hp > 0) {
                    System.out.print("Do you want to (a)ttack or (r)un? ");
                    String action = scanner.nextLine();

                    if (action.equalsIgnoreCase("a")) {
                        player.attack(currentRoom.enemy);

                        if (currentRoom.enemy.hp > 0) {
                            currentRoom.enemy.enemyattack(player);
                        } else {
                            System.out.println("You defeated the " + currentRoom.enemy.name + "!");
                            player.gainXP(10);
                        }
                    } else if (action.equalsIgnoreCase("r")) {
                        System.out.println("You run to the previous room... but it's blocked. You must fight!");
                    }
                }

                if (player.hp <= 0) {
                    System.out.println("You have fallen in battle. Game Over.");
                    break;
                }
            }

            // Move to the next room in the dungeon
            current = current.next;

            if (current != null) {
                System.out.print("Press Enter to continue to the next room...");
                scanner.nextLine();
            }
        }

        if (player.hp > 0) {
            System.out.println("\nYou've cleared the dungeon. Congratulations, " + player.name + "!");
        }

        scanner.close();
    }
}