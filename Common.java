import java.lang.Math;
import java.util.Random;
public class Common extends Fish {
  public int strength;
  public Common(String rarity, String name,double size, int value, int strength) {
      super(rarity, name, size, value, strength);
  }

    public int stealBait(int chanceStat){
      Random gen = new Random();
      int stealChance = gen.nextInt(100);
      if (Math.abs(stealChance - chanceStat) < 10){
        System.out.println("Oh no, the fish stole your bait!");
        return 1; //remove bait in main
      }

      else{
        return 0; //keep bait in main
      }

    }
}
