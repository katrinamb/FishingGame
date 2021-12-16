/*
Derived class of Fish
The most common type of fish in the game, adds a new method
*/
import java.lang.Math;
import java.util.Random;
public class Common extends Fish {
  public int strength;
  public Common(String rarity, String name,double size, int value, int strength) {
      super(rarity, name, size, value, strength);
  }

    //method that allows the fish to steal a players bait
    public int stealBait(int chanceStat){
      Random gen = new Random();
      int stealChance = gen.nextInt(100);
      if (Math.abs(stealChance - chanceStat) < 10){  //if the player's rod stat isn't high enough then the fish steals the bait
        System.out.println("Oh no, the fish stole your bait!");
        return 1; //remove bait in main
      }

      else{  //the fish wasn't able to steal the bait
        System.out.println("Wow this fish is really nibbling at your bait!");
        return 0; //keep bait in main
      }

    }
}
