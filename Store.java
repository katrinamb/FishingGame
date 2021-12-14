import java.util.ArrayList;
import java.util.Scanner;
public class Store extends Object{
    private int coins = 150;
    //public String[] rods = new String[]{"Surf Rod", "Spinning Rod", "Fly Rod", "Ice Rod", "Telescopic Rod"};
    //public String[] baits = new String[]{"Wacky Worm","Honeycomb Spoon","Glittering Spinner","Suspending Crankbait","Rooster Tail"};
    public static ArrayList<Rod> myRods = new ArrayList<Rod>();
    public static ArrayList<Bait> myBaits = new ArrayList<Bait>();
    // rods list
    public static Rod[] rods = {
        new Rod("Surf Rod", 100, 60, 85),
        new Rod("Spinning Rod", 150, 70, 90),
        new Rod("Fly Rod", 250, 75, 95),
        new Rod("Ice Rod", 400, 80, 100),
        new Rod("Telescopic Rod", 500, 90, 115)
    };
    // baits list
    public static Bait[] baits = {
        new Bait("Wacky Worm", 50, 10),
        new Bait("Honeycomb Spoon", 75, 20),
        new Bait("Glittering Spinner", 100, 35),
        new Bait("Suspending Crankbait", 125, 45),
        new Bait("Rooster Tail", 150, 60)
    };
    
    int getCoins(){
        return coins;
    }
    void setCoins(int coins){
        this.coins = coins;
    }
    Rod getRod(int index){
        return myRods.get(index);
    }
    Bait getBait(int index){
        return myBaits.get(index);
    }
    //adds coins to the total coins of the user
    void addCoins(int coins){
        this.coins += coins;
    }
    //removes coins from the total coins of the user
    void subCoins(int coins){
        this.coins -= coins;
    }
    //does the process of buying a rod, so subtracts money and adds rod to inventory
    void buyRod(String rodName, int rodCost){
        if(getCoins() >= rodCost){
            for (int i = 0; i < rods.length; i++){
                if(rods[i].getName() == rodName){
                    System.out.println("Nice choice! Added to your inventory.");
                    myRods.add(rods[i]);
                }
            }
            subCoins(rodCost);
        }
        else{
            System.out.println("You do not have enough coins to purchase this item.");
        } 
    }
    //does the process of buying bait, so subtracts money and adds bait to inventory
    void buyBait(String baitName, int baitCost){
        if(getCoins() >= baitCost){
            for (int i = 0; i < baits.length; i++){
                if(baits[i].getName() == baitName){
                    System.out.println("Nice choice! Added to your inventory.");
                    myBaits.add(baits[i]);
                }
            }
            subCoins(baitCost);
        }
        else{
            System.out.println("You do not have enough coins to purchase this item.");
        }    
    }

    //Displays all the names of the rods in the store
    public static void rodToString(){
        System.out.println("----Rods----");
        for(int i = 0; i < rods.length; i++){
            System.out.println(rods[i].getName()+ ": "+ rods[i].getPrice() + " coins");
        }
    }

    //Displays all the baits of the rods in the store
    public static void baitToString(){
        System.out.println("----Baits----");
        for(int i = 0; i < baits.length; i++){
            System.out.println(baits[i].getName()+ ": "+ baits[i].getPrice() + " coins");
        }
    }

    public static void choice(){
        Scanner scan = new Scanner(System.in);
        //While(true){// continue choice

        //}

    }
}