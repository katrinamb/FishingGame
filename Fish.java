/*
Base class file for Fish objects
Outlines the attributes of Fish the user will catch and gives them a basic method
*/

import java.lang.Math;
import java.util.Random;
public class Fish extends Object{
    private String rarity;  //will be rare, common, and uncommon
    private String name;
    private double size;
    private int value;  //how many coins it gives the user for catching
    public int strength;   //factors into how hard it is to catch
    public Fish(String rarity, String name, double size, int value, int strength) {
        this.rarity = rarity;
        this.name = name;
        this.size = size;
        this.value = value;
        this.strength = strength;
    }

    //getters and setters
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

    //method that allows the fish to fight against the user while it's on the line
    public int fight(int chanceStat){
        Random gen = new Random();
        int fightChance = gen.nextInt(100);
        if (Math.abs(fightChance - this.strength) < 10){     //the fish will not lose as much strength
            System.out.println("The fish is fighting harder and not slowing down!");
            return 1;  //dont decrease fish strength
        } 
        else{   //the fish will lose strength because it's tiring its self out
            System.out.println("The fish is fighting hard but losing steam");
            return 0; //decrease fish strength
        }
    }
    
    //basic toString to print out the fish' attributes after catching it
    public String toString() {
        return "\nFish name: " + name + "\nRarity: " + rarity + "\nSize: " + size + "\nValue: "
                + value;
    }

    

    
}
