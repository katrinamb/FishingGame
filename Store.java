import java.util.ArrayList;
import java.util.Scanner;
public class Store extends Object{
    static Scanner scan = new Scanner(System.in);
    private static int coins = 150;
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
    
    static int getCoins(){
        return coins;
    }
    void setCoins(int coins){
        Store.coins = coins;
    }
    Rod getRod(int index){
        return myRods.get(index);
    }
    Bait getBait(int index){
        return myBaits.get(index);
    }
    //adds coins to the total coins of the user
    void addCoins(int coins){
        Store.coins += coins;
    }
    //removes coins from the total coins of the user
    static void subCoins(int coins){
        Store.coins -= coins;
    }
    //does the process of buying a rod, so subtracts money and adds rod to inventory
    static void buyRod(Rod rod){
        if(getCoins() >= rod.getPrice()){
            for (int i = 0; i < rods.length; i++){
                if(rods[i].getName() == rod.getName()){
                    System.out.println("Nice choice! Added to your inventory.");
                    myRods.add(rods[i]);
                }
            }
            subCoins(rod.getPrice());
        }
        else{
            System.out.println("You do not have enough coins to purchase this item.");
        } 
    }
    //does the process of buying bait, so subtracts money and adds bait to inventory
    static void buyBait(Bait bait){
        if(getCoins() >= bait.getPrice()){
            for (int i = 0; i < baits.length; i++){
                if(baits[i].getName() == bait.getName()){
                    System.out.println("Nice choice! Added to your inventory.");
                    myBaits.add(baits[i]);
                }
            }
            subCoins(bait.getPrice());
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

    //the store menu
    public static void storeMenu(){
        System.out.println("\nWelcome to the Store!");
        Store.rodToString();
        System.out.println();
        Store.baitToString();
    }

    // the invetory menu 
    public static int inventoryMenu(){
        System.out.println("\nSelect what you would like to do. (Enter 1 or 2)");
        System.out.println("1. View Inventory");
        System.out.println("2. View Store");
        return scan.nextInt();
    }

    //displays menu of baits and rods the player can buy
    public static void buyMenu(){
        System.out.println("\nWhat would you like to buy? (Enter the corresponding number)");
        System.out.println("1. Surf Rod");
        System.out.println("2. Spinning Rod");
        System.out.println("3. Fly Rod");
        System.out.println("4. Ice Rod");
        System.out.println("5. Telescopic Rod");
        System.out.println("6. Wacky Worm");
        System.out.println("7. Honeycomb Spoon");
        System.out.println("8. Glittering Spinner");
        System.out.println("9. Suspending Crankbait");
        System.out.println("10. Rooster Tail");
        System.out.println("11. Exit");
    }

    // get the users choice to purchase items
    public static void choice(){
        buyMenu();
        int choice = scan.nextInt();
        if(choice == 1){
           buyRod(rods[0]);
        }
        else if(choice==2){
            buyRod(rods[1]);
        }
        else if(choice==3){
            buyRod(rods[2]);
        }
        else if(choice==4){
            buyRod(rods[3]);
        }
        else if(choice==5){
            buyRod(rods[4]);
        }
        else if(choice==6){
            buyBait(baits[0]);
        }
        else if(choice==7){
            buyBait(baits[1]);
        }
        else if(choice==8){
            buyBait(baits[2]);
        }
        else if(choice==9){
            buyBait(baits[3]);
        }
        else if(choice==10){
            buyBait(baits[4]);
        }
        else{ //invalid choice
            System.out.println("Invalid choice. Try again.");
        }
        scan.close();
    }
}