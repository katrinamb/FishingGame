import java.util.Random;

public abstract class Fish extends Object{
    private String rarity;
    private String name;
    private double size;
    private int value;
    public int strength;
    public Fish(String rarity, String name, double size, int value, int strength) {
        this.rarity = rarity;
        this.name = name;
        this.size = size;
        this.value = value;
        this.strength = strength;
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
    public double getSize() {
        return size;
    }
    public void setSize(double size) {
        this.size = size;
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
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
