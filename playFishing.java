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
        new Common("Common","Trout", 1.2, 50, 87),
        new Common("Common","Bass", 1.4, 55, 85),
        new Common("Common","Gold Fish", 0.2, 5, 30),
        new Common("Common","Carp", 1.5, 50, 83),
        new Common("Common","Salmon", 1.3, 40, 89),
        new Common("Common","Tuna", 3, 100, 83)
    };
    
    // uncommon fish list
    static Uncommon uncommonFish[] = {
        new Uncommon("Uncommon", "Clown Fish", 0.3, 350, 78),
        new Uncommon("Uncommon", "Garibaldi", 1, 400, 72),
        new Uncommon("Uncommon", "Koi Fish", 2.5, 200, 70),
        new Uncommon("Uncommon", "Cherry Salmon", 1.5, 250, 79),
        new Uncommon("Uncommon", "Puffer Fish", 2, 450, 74)
    
    };
    // rods list
    static Rod[] rods = {
        new Rod("Surf Rod", 100, 60, 85),
        new Rod("Spinning Rod", 150, 70, 90),
        new Rod("Fly Rod", 250, 75, 95),
        new Rod("Ice Rod", 400, 80, 100),
        new Rod("Telescopic Rod", 500, 90, 115)
    };
    // baits list
    static Bait[] baits = {
        new Bait("Wacky Worm", 50, 5),
        new Bait("Honeycomb Spoon", 75, 10),
        new Bait("Glittering Spinner", 100, 15),
        new Bait("Suspending Crankbait", 125, 20),
        new Bait("Rooster Tail", 150, 25)
    };
    
    // main
    public static void main(String[] args){
        playFishing call = new playFishing();
        Rod starter = new Rod("Starter Rod", 0, 50, 55);
        Bait starterBait = new Bait("Hook", 0, 0);
        Store.myBaits.add(starterBait);
        Store.myRods.add(starter);
        Rod selectedRod = starter;
        Bait selectedBait = starterBait;

        int choice;
      
        System.out.println("Hi, Welcome to the fishing game!");
        while(true){  
            choice = menu();
            if (choice == 1){ //go fishing
                System.out.println();
                System.out.println("You currently have " + selectedRod.getName() + " applied and " + selectedBait.getName() + " applied");
                boolean casted = true;
                while (casted){
                    choice = fishingMenu();
                    if (choice == 1){ //apply bait
                        System.out.println();
                        selectedBait = Store.baitSelection();
                        break;
                    }

                    else if (choice == 2){ //change rod
                        System.out.println();
                        selectedRod = Store.selectedRod();
                        break;
                    }

                    else if (choice == 3){ //cast
                        System.out.println();
                        System.out.println("Awesome, let's get that line out there!");
                        System.out.println("*casts line*");
                        String rarity = rarity();
                        
                        Fish fish = pickFish(rarity); 
                        int ogStrength = fish.getStrength();     
                        boolean fishing = true;
                        
                        while(fishing){
                            int action = actionMenu();
                            fishing = call.resultAction(action, fish, rarity, selectedRod, selectedBait);
                            System.out.println("The fish's current strength: "+ fish.getStrength());
                        }
                        fish.setStrength(ogStrength);
                        selectedBait = starterBait;
                        selectedRod = starter;
                        casted = false;
                            
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
                    if(choice == 1){ //view inventory
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
                    else if(choice == 2){ //go to store
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

            else if (choice == 3){ //exit
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
            if (rod.getChanceStat()+ selectedBait.getEffect()>fish.getStrength()){
                //catch
                System.out.println("Great job, you just caught a real lunker!");
                System.out.println(fish.toString());
                Store.addCoins(fish.getValue());
                return false;
            }
          
            else{
                System.out.println("Oh no reeling didn't work, the fish is still too strong!");
                boolean fishing = reelAction(rarity, fish, selectedRod, selectedBait);
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
            boolean fishing = reelAction(rarity, fish, selectedRod, selectedBait);
            return fishing;
        }
        return true;
    }

    public boolean reelAction(String rarity, Object fishObject, Object rod, Object bait){
        Rod selectedRod = (Rod) rod;
        Bait selectedBait = (Bait) bait;
        if (rarity == "Rare"){
            Rare fish = (Rare) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(4);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(selectedRod.getChanceStat()); 
                if (strengthEffect == 0){ //fish getting tired
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }
                
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){
                    Store.myBaits.remove(selectedBait);
                    if (Store.myBaits.size() == 0){
                        Store.myBaits.add(new Bait("Hook", 0, 0));
                    }
                    return false;
                }
              

            }
            else if (fishAction == 2){ //escape method
                int escaped = fish.escape(selectedRod.getChanceStat());
                if (escaped == 1){  
                    return false;
                    //fish got away
                }
             
            }

            else if (fishAction == 3){ //break method
                int rodBreak = fish.calculateBreak(selectedRod.getDurability()); 
                if (rodBreak == 1){
                    //break rod and fish gets away
                    Store.myRods.remove(selectedRod);
                    if (Store.myRods.size() == 0){
                        Store.myRods.add(new Rod("Starter Rod", 0, 50, 55));
                    }
                    return false;
                }
             
            }
        }

        else if (rarity == "Uncommon"){
            Uncommon fish = (Uncommon) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(3);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(selectedRod.getChanceStat()); 
                if (strengthEffect == 0){ //fish getting tired
                    fish.setStrength(fish.getStrength()-10);
                    return true;
                }
              
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){
                    Store.myBaits.remove(selectedBait);
                    if (Store.myBaits.size() == 0){
                        Store.myBaits.add(new Bait("Hook", 0, 0));
                    }
                    return false;
                    //remove bait and fish gets away
                }
               

            }
            else if (fishAction == 2){
                int escaped = fish.escape(selectedRod.getChanceStat());
                if (escaped == 1){  //may need to change fishing variable in while loop to global to effect
                    return false;
                    //fish got away
                }
              
            }
        }

        else if (rarity == "Common"){
            Common fish = (Common) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(2);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(selectedRod.getChanceStat()); 
                if (strengthEffect == 0){ //fish getting tired
                    fish.setStrength(fish.getStrength()-10);
                    return true;
                }
             
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){
                    Store.myBaits.remove(selectedBait);
                    if (Store.myBaits.size() == 0){
                        Store.myBaits.add(new Bait("Hook", 0, 0));
                    }
                    return false;
                }
                          
            }

        }
        return true;

    }

    public boolean tugAction(String rarity, Object fishObject, Object rod, Object bait){
        Rod selectedRod = (Rod) rod;
        Bait selectedBait = (Bait) bait;
        if (rarity == "Rare"){
            Rare fish = (Rare) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(4);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(selectedRod.getChanceStat()); 
                if (strengthEffect == 0){ //fish getting tired
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
                if (baitStolen == 1){
                    Store.myBaits.remove(selectedBait);
                    if (Store.myBaits.size() == 0){
                        Store.myBaits.add(new Bait("Hook", 0, 0));
                    }
                    return false;
                    //remove bait and fish gets away
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }

            }
            else if (fishAction == 2){ //escape method
                int escaped = fish.escape(selectedRod.getChanceStat());
                if (escaped == 1){  
                    return false;
                    //fish got away
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }
            }

            else if (fishAction == 3){ //break method
                int rodBreak = fish.calculateBreak(selectedRod.getDurability()); 
                if (rodBreak == 1){
                    Store.myRods.remove(selectedRod);
                    if (Store.myRods.size() == 0){
                        Store.myRods.add(new Rod("Starter Rod", 0, 50, 55));
                    }
                    return false;
                    //break rod and fish gets away
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }
            }
        }

        else if (rarity == "Uncommon"){
            Uncommon fish = (Uncommon) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(3);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(selectedRod.getChanceStat());
                if (strengthEffect == 0){ //fish getting tired
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
                if (baitStolen == 1){
                    Store.myBaits.remove(selectedBait);
                    if (Store.myBaits.size() == 0){
                        Store.myBaits.add(new Bait("Hook", 0, 0));
                    }
                    return false;
                    //remove bait and fish gets away
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }

            }
            else if (fishAction == 2){
                int escaped = fish.escape(selectedRod.getChanceStat());
                if (escaped == 1){  
                    return false;
                    //fish got away
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }
            }
        }

        else if (rarity == "Common"){
            Common fish = (Common) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(2);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(selectedRod.getChanceStat()); 
                if (strengthEffect == 0){ //fish getting tired
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
                if (baitStolen == 1){
                    Store.myBaits.remove(selectedBait);
                    if (Store.myBaits.size() == 0){
                        Store.myBaits.add(new Bait("Hook", 0, 0));
                    }
                    return false;
                    //remove bait and fish gets away
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                    return true;
                }

            }

        }
        return true;
        
    }
}
