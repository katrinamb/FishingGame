/*
Derived class of Uncommon and thus an extension of all other fish types
The most rare type of fish with the most methods to fight back
*/
import java.util.Random;
public class Rare extends Uncommon{
    public Rare(String rarity, String name, double size, int value, int strength) {
        super(rarity, name, size, value, strength);
    }

    // calculates if the player's rod breaks or not while fishing
    public int calculateBreak(int durability){
        Random gen = new Random();
        int breakChance = gen.nextInt(10);
        if (breakChance/10 * strength > durability){  //if the rod's durability stat isn't high enough break the rod
            System.out.println("Oh snap! Your line...snapped.");
            return 1; //the rod breaks in main
        }
        else{  //the rod was durable enough not to break
            System.out.println("Woah the fish almost snapped your line there, be careful!");
            return 0;//the rod does not break in main
        }
    }
}
