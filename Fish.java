package FishingGame;
import java.util.Random;

public class Fish{
    private String rarity;
    private String name;
    private float size;
    private int value;
    public Fish(String rarity, String name, float size, int value) {
        this.rarity = rarity;
        this.name = name;
        this.size = size;
        this.value = value;
    }
    public String getRarity() {
        return rarity;
    }
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getSize() {
        return size;
    }
    public void setSize(float size) {
        this.size = size;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public int fight(){
        Random gen = new Random();
        int fishFight = gen.nextInt(100);
      //  if (fishFight - "rodstat" > 5){
        //    System.out.println("The fish is fighting harder!");
          //  return 1;
       // } 
        //else{
          //  return 0;
        //}
        return 5;
    }

    
}
