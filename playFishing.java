/*
Authors: Brady Fischer, Katrina Baha, Dillan Lopez
Professor Nuzen
Comp 305
12/16/21
Program to create a fishing game where the user can catch various types of fish and buy new rods and baits!
*/
import java.util.Scanner;
import java.util.Random;
// the runner program
public class playFishing {
    private static final Scanner scan = new Scanner(System.in);
    // rare fish list
    static Rare rareFish[] = {
        new Rare("Rare", "Coelacanth", 6.5, 500, 90),
        new Rare("Rare", "Blue Marlin", 12, 550, 93),
        new Rare("Rare", "Bluefin Tuna", 13, 600, 95),
        new Rare("Rare", "Yellowfin Tuna", 7, 525, 91),
        new Rare("Rare", "Dorado", 4, 485, 87)
    };
    // common fish list
    static Common commonFish[] = {
        new Common("Common","Trout", 1.2, 50, 68),
        new Common("Common","Bass", 1.4, 55, 65),
        new Common("Common","Gold Fish", 0.2, 5, 52),
        new Common("Common","Carp", 1.5, 50, 67),
        new Common("Common","Salmon", 1.3, 40, 71),
        new Common("Common","Tuna", 3, 100, 72)
    };
    
    // uncommon fish list
    static Uncommon uncommonFish[] = {
        new Uncommon("Uncommon", "Clown Fish", 0.3, 350, 78),
        new Uncommon("Uncommon", "Garibaldi", 1, 400, 75),
        new Uncommon("Uncommon", "Koi Fish", 2.5, 200, 73),
        new Uncommon("Uncommon", "Cherry Salmon", 1.5, 250, 82),
        new Uncommon("Uncommon", "Puffer Fish", 2, 450, 80)
    
    };

    
    // main
    public static void main(String[] args){
        playFishing call = new playFishing();
        Rod starter = new Rod("Starter Rod", 0, 50, 55);  //free rod the player can start with
        Bait starterBait = new Bait("Hook", 0, 0);  //free bait the player can start with
        Store.myBaits.add(starterBait);
        Store.myRods.add(starter);
        Rod selectedRod = starter;  
        Bait selectedBait = starterBait;

        int choice;
      
        System.out.println("Hi, Welcome to the fishing game!");
        while(true){  
            choice = menu();    //get what the player wants to do
            if (choice == 1){ //go fishing
                System.out.println();
                System.out.println("You currently have " + selectedRod.getName() + " applied and " + selectedBait.getName() + " applied");
                boolean casted = true;
                while (casted){ 
                    choice = fishingMenu();
                    if (choice == 1){ //apply bait
                        System.out.println();
                        selectedBait = Store.baitSelection();  //get the player's bait selection
                        
                    }

                    else if (choice == 2){ //change rod
                        System.out.println();
                        selectedRod = Store.selectedRod();  //get the player's rod selection
                      
                    }

                    else if (choice == 3){ //cast
                        System.out.println();
                        System.out.println("Awesome, let's get that line out there!");
                        System.out.println("*casts line*");
                        String rarity = rarity();  //figure out a rarity of fish to be caught
                        
                        Fish fish = pickFish(rarity);  //select which fish is to be caught
                        int ogStrength = fish.getStrength();     
                        boolean fishing = true;
                        
                        while(fishing){ //while the player is fighting the fish
                            int action = actionMenu();  //give the player actions to fight the fish
                            fishing = call.resultAction(action, fish, rarity, selectedRod, selectedBait);
                            System.out.println("The fish's current strength: "+ fish.getStrength());
                        }
                        fish.setStrength(ogStrength);   //reset the fish' strength
                        selectedBait = starterBait; 
                        selectedRod = starter;
                        casted = false; //stop fishing process
                            
                    }
                    else{ //invalid option
                        System.out.println();
                        System.out.println("Invalid option. Try again.");
                    }
                }
            }   
            
            else if (choice == 2){ //go to inventory
                Boolean invalid = true;
                while(invalid){
                    choice = Store.inventoryMenu();
                    if(choice == 1){ //view inventory (prints everything the player owns)
                        System.out.println();
                        System.out.println("----Your Inventory----");
                        System.out.println("\nCoins: " + Store.getCoins());
                        System.out.println("\nRods: ");
                        for (int i = 0; i < Store.myRods.size(); i++){
                            System.out.println(Store.myRods.get(i).getName());
                        }
                        System.out.println("\nBaits: ");
                        for (int i = 0; i < Store.myBaits.size(); i++){
                            System.out.println(Store.myBaits.get(i).getName());
                        }
                        invalid = false;
                    }
                    else if(choice == 2){ //go to store and allow the player to buy new items
                        System.out.println();
                        Store.storeMenu();
                        Store.choice();
                        invalid = false;
                    }
                    else{ //invalid option
                        System.out.println();
                        System.out.println("Invalid option. Try again.");
                    } 
                }  

            }

            else if (choice == 3){ //exit the game
                break;
            }

            else{ //invalid choice
                System.out.println();
                System.out.println("Invalid choice. Try again.");
            }
        }
        scan.close();
    }
    //the menu displayed before the user starts fishing
    public static int menu(){
        System.out.println("\nSelect what you would like to do. (Enter 1, 2, or 3)");
        System.out.println("1. Go Fishing");
        System.out.println("2. Go to Inventory");
        System.out.println("3. Exit Game");
        return scan.nextInt();
    }
    //the menu displayed after "Go Fishing" is selected
    public static int fishingMenu(){
        System.out.println("\nSelect an option (Enter 1, 2, or 3)");
        System.out.println("1. Apply Bait");
        System.out.println("2. Select Rod");
        System.out.println("3. Cast");
        return scan.nextInt();
    }

    //randomly decide the rarity of the fish 
    public static String rarity(){
        Random gen = new Random();
        int fishRarity = gen.nextInt(100);
        if (fishRarity < 50){
            return "Common";
        }
        else if (fishRarity <90){
            return "Uncommon";
        }

        else{
            return "Rare";
        }
    }

    // picks the fish that the player will have to try to fish
    public static Fish pickFish(String rarity){
        Random gen = new Random();
        int fishNum = gen.nextInt(4);
        if (rarity == "Rare"){
            return rareFish[fishNum];
        }

        else if(rarity == "Uncommon"){
            return uncommonFish[fishNum];
        }

        else{
            return commonFish[fishNum];
        }
    }

   

    //the menu displayed while the user is fishing
    public static int actionMenu(){
        System.out.println("Select an option (Enter 1, 2, or 3)");
        System.out.println("1. Reel In");
        System.out.println("2. Tug On Line");
        System.out.println("3. Leave It Alone");
        return scan.nextInt();
    }

    //computes the result after the action the user chooses to do
    //returns true to continue fishing, returns false to end fishing
    public boolean resultAction(int action, Object fishObject, String rarity, Object selectedRod, Object bait){
        Fish fish = (Fish) fishObject;
        Rod rod = (Rod) selectedRod;
        Bait selectedBait = (Bait) bait;
        if (action == 1){ //reel in
            if (rod.getChanceStat()+ selectedBait.getEffect()>fish.getStrength()){  //if the fish is weak enough th eplayer catches it
                //catch
                System.out.println("Great job, you just caught a real lunker!");
                System.out.println(fish.toString());
                Store.addCoins(fish.getValue());
                return false;
            }
          
            else{  //the fish is still to strong to catch
                System.out.println("Oh no reeling didn't work, the fish is still too strong!");
                boolean fishing = reelAction(rarity, fish, selectedRod, selectedBait);  //the fish calls a method to fight back
                return fishing;
            }       
        }

        else if (action == 2){ //tug on line
            System.out.println("Great idea to tug on the line and tire the fish out!");
            boolean fishing = tugAction(rarity, fish, selectedRod, selectedBait);
            return fishing;
        }

        else if (action ==3){ //leave it alone
            System.out.println("Interesting idea to leave it alone, let's see if that works!");
            boolean fishing = reelAction(rarity, fish, selectedRod, selectedBait);  //the fish calls a method to fight back
            return fishing;
        }
        return true;
    }

    //method that allows the fish to use one of its methods if the player tried to reel in or left it alone
    public boolean reelAction(String rarity, Object fishObject, Object rod, Object bait){
        Rod selectedRod = (Rod) rod;
        Bait selectedBait = (Bait) bait;
        if (rarity == "Rare"){  //all the methods a rare fish can call
            Rare fish = (Rare) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(4);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(); 
                if (strengthEffect == 0){ //fish getting tired so decrease its strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }
                
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){  //bait is stolennso remove from inventory
                    Store.myBaits.remove(selectedBait);
                    if (Store.myBaits.size() == 0){  //if the player has nothing left give them the free hook
                        Store.myBaits.add(new Bait("Hook", 0, 0));
                    }
                    return false;
                }
              

            }
            else if (fishAction == 2){ //escape method
                int escaped = fish.escape();
                if (escaped == 1){   //the fish got away
                    return false;
                }
             
            }

            else if (fishAction == 3){ //break method
                int rodBreak = fish.calculateBreak(selectedRod.getDurability()); 
                if (rodBreak == 1){ //break rod and fish gets away
                    Store.myRods.remove(selectedRod);
                    if (Store.myRods.size() == 0){ //if the player has no rods left give them the free rod
                        Store.myRods.add(new Rod("Starter Rod", 0, 50, 55));
                    }
                    return false;
                }
             
            }
        }

        else if (rarity == "Uncommon"){ //all the methods an Uncommon fish can call
            Uncommon fish = (Uncommon) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(3);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(); 
                if (strengthEffect == 0){ //fish getting tired so decrease its strength
                    fish.setStrength(fish.getStrength()-10);
                    return true;
                }
              
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){ //bait is stolen so remove it from inventory
                    Store.myBaits.remove(selectedBait);
                    if (Store.myBaits.size() == 0){  //if the player has no baits left give them a free one
                        Store.myBaits.add(new Bait("Hook", 0, 0));
                    }
                    return false;
                }
               
            }
            else if (fishAction == 2){  //escape method
                int escaped = fish.escape();
                if (escaped == 1){  //the fish escaped
                    return false;
                }
              
            }
        }

        else if (rarity == "Common"){ //all the methods a Common fish can call
            Common fish = (Common) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(2);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(); 
                if (strengthEffect == 0){ //fish getting tired so decrease its strength
                    fish.setStrength(fish.getStrength()-10);
                    return true;
                }
             
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){  //bait is stolen so remove it from inventory
                    Store.myBaits.remove(selectedBait);
                    if (Store.myBaits.size() == 0){  //if the player has no baits left give them a free one
                        Store.myBaits.add(new Bait("Hook", 0, 0));
                    }
                    return false;
                }
                          
            }

        }
        return true;

    }

    //method for if the player tugs on the line to tire the fish out
    public boolean tugAction(String rarity, Object fishObject, Object rod, Object bait){
        Rod selectedRod = (Rod) rod;
        Bait selectedBait = (Bait) bait;
        if (rarity == "Rare"){  //all the methods a rare fish can call
            Rare fish = (Rare) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(4);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(); 
                if (strengthEffect == 0){ //fish getting tired so decrease its strength
                    fish.setStrength(fish.getStrength()-10);
                    return true;
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){  //bait is stolen so remove it from inventory
                    Store.myBaits.remove(selectedBait);
                    if (Store.myBaits.size() == 0){  //give player free bait if they have none left
                        Store.myBaits.add(new Bait("Hook", 0, 0));
                    }
                    return false;
                }
                else{ //fish couldn't steal bait so lose some strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }

            }
            else if (fishAction == 2){ //escape method
                int escaped = fish.escape();
                if (escaped == 1){   //the fish escaped
                    return false;
                }
                else{ //fish couldn't escape so lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }
            }

            else if (fishAction == 3){ //break method
                int rodBreak = fish.calculateBreak(selectedRod.getDurability()); 
                if (rodBreak == 1){  //the player's rod breaks so remove it from inventory
                    Store.myRods.remove(selectedRod);
                    if (Store.myRods.size() == 0){  //if the player has no rods left give them a free one
                        Store.myRods.add(new Rod("Starter Rod", 0, 50, 55));
                    }
                    return false;
                }
                else{ //fish couldn't break rod so it loses a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }
            }
        }

        else if (rarity == "Uncommon"){  //all the methods an uncommon fish can call
            Uncommon fish = (Uncommon) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(3);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight();
                if (strengthEffect == 0){ //fish getting tired so lose a lot of strength
                    fish.setStrength(fish.getStrength()-10);
                    return true;
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){  //bait is stolen so remove it from inventory
                    Store.myBaits.remove(selectedBait);
                    if (Store.myBaits.size() == 0){  //give the player a free bait if they don't have any left
                        Store.myBaits.add(new Bait("Hook", 0, 0));
                    }
                    return false;
                    //remove bait and fish gets away
                }
                else{ //fish couldn't steal bait so it should lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }

            }
            else if (fishAction == 2){  //escape method
                int escaped = fish.escape();
                if (escaped == 1){    //fish escaped
                    return false;
                }
                else{ //fish couldn't escape so it should lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }
            }
        }

        else if (rarity == "Common"){  //all the methods a common fish can call
            Common fish = (Common) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(2);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(); 
                if (strengthEffect == 0){ //fish getting tired so it loses a lot of strength
                    fish.setStrength(fish.getStrength()-10);
                    return true;
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){  //bait is stolen so remove it from inventory
                    Store.myBaits.remove(selectedBait);
                    if (Store.myBaits.size() == 0){  //give the player  free bait if they don't have any left
                        Store.myBaits.add(new Bait("Hook", 0, 0));
                    }
                    return false;
                    //remove bait and fish gets away
                }
                else{ //fish couldn't steal bait so it should lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }

            }

        }
        return true;
        
    }
}
