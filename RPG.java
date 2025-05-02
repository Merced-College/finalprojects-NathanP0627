import java.util.*;

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


// Where everything will run
public class RPG{
    public static void main(String[] args){

    }
}