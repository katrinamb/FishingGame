/*
File to house all the methods and objects involved with viewing 
the player's inventory and buying new items
*/
import java.util.ArrayList;
import java.util.Scanner;
public class Store extends Object{
    static Scanner scan = new Scanner(System.in);
    private static int coins = 150;  //base amount of coins to start with
    
    //will house items the player has bought
    public static ArrayList<Rod> myRods = new ArrayList<Rod>();
    public static ArrayList<Bait> myBaits = new ArrayList<Bait>();
    
    // rods available to buy
    public static Rod[] rods = {
        new Rod("Surf Rod", 100, 60, 65),
        new Rod("Spinning Rod", 150, 70, 70),
        new Rod("Fly Rod", 250, 75, 60),
        new Rod("Ice Rod", 400, 80, 80),
        new Rod("Telescopic Rod", 500, 90, 75)
    };
    // baits available to buy
    public static Bait[] baits = {
        new Bait("Wacky Worm", 50, 2),
        new Bait("Honeycomb Spoon", 75, 3),
        new Bait("Glittering Spinner", 100, 4),
        new Bait("Suspending Crankbait", 125, 6),
        new Bait("Rooster Tail", 150, 7)
    };
    
    //getters and setters
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
    static void addCoins(int coins){
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
                if(rods[i].getName() == rod.getName()){  //finds the rod the player wants to buy
                    System.out.println("Nice choice! Added to your inventory.");
                    myRods.add(rods[i]);  //adds rod to inventory
                }
            }
            subCoins(rod.getPrice());
        }
        else{  //doesn't let the player buy if they don't have enough money
            System.out.println("You do not have enough coins to purchase this item.");
        } 
    }
    //does the process of buying bait, so subtracts money and adds bait to inventory
    static void buyBait(Bait bait){
        if(getCoins() >= bait.getPrice()){
            for (int i = 0; i < baits.length; i++){
                if(baits[i].getName() == bait.getName()){  //finds the bait the player wants to buy
                    System.out.println("Nice choice! Added to your inventory.");
                    myBaits.add(baits[i]);  //adds bait to inventory
                }
            }
            subCoins(bait.getPrice());
        }
        else{  //doesn't let the player buy bait if they don't have the money
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
        Store.rodToString();  //all the rods you can buy
        System.out.println();
        Store.baitToString();  //all the baits you can buy
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

    // get the users choice to purchase items and then buys that item
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
        else if(choice == 11){ //exit
            
        }
        else{ //invalid choice 
            System.out.println("Invalid choice. Try again.");
        }
    }

    //get users input to return the bait the user selects in main
    public static Bait baitSelection(){
        while (true){  
            if(myBaits.size() != 0){  //makes sure their is bait for the user to select
                System.out.println();
                System.out.println("Player 1's Bait: ");
                for(int i = 0; i < myBaits.size(); i++){  //prints all the baits the user can choose from
                    System.out.println(myBaits.get(i).getName() + " [Select: "+ i + "]");
                }
            }
            else{  //if they don't have any baits
                System.out.println();
                System.out.println("No bait available to equip.");
            }
            System.out.println();
            System.out.println("Select an option:");
            int choice = scan.nextInt();
            if (choice <= myBaits.size()){ //lets the user select the bait they want to use and applies it
                Bait selectedBait = myBaits.get(choice);
                System.out.println();
                System.out.println(selectedBait.getName()+", good choice of bait!");
              
                return selectedBait;
            }
            else{
                System.out.println();
                System.out.println("Invalid Choice.");
            }
        }
    } 

    //get users input to return the rod the user selects in main
    public static Rod selectedRod(){
        while (true){  
            if(myRods.size() != 0){  //makes sure the player has rods to choose from
                System.out.println();
                System.out.println("Player 1's Rods: ");
                for(int i = 0; i < myRods.size(); i++){  //loops through all the player's rods
                    System.out.println(myRods.get(i).getName() + " [Select: "+ i + "]");
                }
            }
            else{  //if they don't have any rods
                System.out.println();
                System.out.println("No Rod available to equip.");
            }
            System.out.println();
            System.out.println("Select an option:");
            int choice = scan.nextInt();
            if (choice <= myRods.size()){ //lets the user select the rod they want and applies it
                Rod selectedRod = myRods.get(choice);
                System.out.println();
                System.out.println(selectedRod.getName()+" equipped.");
         
                return selectedRod;
            }
            else{
                System.out.println();
                System.out.println("Invalid Choice.");
            }
        }
    } 
}