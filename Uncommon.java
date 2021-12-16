/*
Derived class of common and thus also of Fish
The second most common type of fish in the game
*/
import java.util.Random;
import java.lang.Math;
public class Uncommon extends Common{
    public Uncommon(String rarity, String name, double size, int value, int strength) {
        super(rarity, name, size, value, strength);
    }

    //method that allows the fish to escape the line
    public int escape(int chanceStat){
        Random gen = new Random();
        int escapeChance = gen.nextInt(20);
        if (Math.abs(escapeChance-this.getSize())< 3){  //if the fish is big enough it escapes
            System.out.println("Oh no, the fish escaped! Better luck next time");
            return 1; //escape in main
        }

        else{  //the fish couldn't escape
            System.out.println("Be careful, it felt like the fish almost got off the line there!");
            return 0; //didn't escape
        }
    }
}
