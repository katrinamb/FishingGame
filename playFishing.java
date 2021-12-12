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

    // main
    public static void main(String[] args){
        playFishing call = new playFishing();
        int choice;
      
        System.out.println("Hi, Welcome to the fishing game!");
        while(true){  
            choice = menu();
            if (choice == 1){ //go fishing
                System.out.println("You currently have your starter rod selected");
                Rod selectedRod = Rod.myRods[0];
                boolean casted = true;
                while (casted){
                    choice = fishingMenu();
                    
                    if (choice == 1){ //apply bait
                        System.out.println("Good choice of bait!");
                        break;
                    }

                    else if (choice == 2){ //change rod
                        System.out.println("good choice of rod!");
                        break;
                    }

                    else if (choice == 3){ //cast
                        System.out.println("Awesome, let's get that line out there!");
                        System.out.println("*casts line*");
                        String rarity = rarity();
                        //might have to do ifs and else statements when selecting a fish
                        Fish fish = pickFish(rarity); 
                        int ogStrength = fish.getStrength();         
                        boolean fishing = true;
                        
                        while(fishing){
                            int action = actionMenu();
                            fishing = call.resultAction(action, fish, rarity, selectedRod);
                            System.out.println("The fish's current strength:"+ fish.getStrength());
                        }
                        fish.setStrength(ogStrength);
                        casted = false;
                            
                    }
                    else{ //invalid option
                        System.out.println("Invalid option. Try again.");
                    }
                }
            }   
            
            else if (choice == 2){ //go to inventory
                break;
            }

            else if (choice == 3){ //exit
                break;
            }

            else{ //invalid choice
                System.out.println("Invalid choice. Try again.");
            }
        }
        scan.close();
    }

    public static int menu(){
    
        System.out.println("Select what you would like to do. (Enter 1, 2, or 3)");
        System.out.println("1. Go Fishing");
        System.out.println("2. Go to inventory");
        System.out.println("3. Exit game");
        return scan.nextInt();
    }

    public static int fishingMenu(){
        System.out.println("Select an option (Enter 1, 2, or 3)");
        System.out.println("1. Apply bait");
        System.out.println("2. Select rod");
        System.out.println("3. Cast");
        return scan.nextInt();
    }

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

    // casts the rod of the player
  //  public static void castRod(){
    //    System.out.println("Awesome, let's get that line out there!");
      //  System.out.println("*casts line*");
        //String rarity = rarity();
        //Fish fish = pickFish(rarity);
    //}

    public static int actionMenu(){
        System.out.println("Select an option (Enter 1, 2, or 3)");
        System.out.println("1. Reel in");
        System.out.println("2. Tug on line");
        System.out.println("3. Leave it alone");
        return scan.nextInt();
    }

    public boolean resultAction(int action, Object fishObject, String rarity, Object selectedRod){
        Fish fish = (Fish) fishObject;
        Rod rod = (Rod) selectedRod;
        if (action == 1){ //reel in
            //Random gen = new Random();
            //int reelChance = gen.nextInt(100);  //replace with chance stat when implemented
            if (rod.getChanceStat()>fish.getStrength()){//(Math.abs(reelChance - fish.getStrength()) > 40){
                //catch
                System.out.println("Great job, you just caught a real lunker!");
                System.out.println(fish.toString());
                return false;
            }
            else{
                System.out.println("Oh no reeling didn't work, the fish is still too strong!");
                reelAction(rarity, fish, selectedRod);
                return true;
            }       
        }

        else if (action == 2){ //tug on line
            System.out.println("Great idea to tug on the line and tire the fish out!");
            tugAction(rarity, fish, selectedRod);
            return true;
        }

        else if (action ==3){ //leave it alone
            System.out.println("Interesting idea to leave it alone, let's see if that works!");
            reelAction(rarity, fish, selectedRod);
            return true;
        }
        return false;
    }

    public void reelAction(String rarity, Object fishObject, Object rod){
        Rod selectedRod = (Rod) rod;
        if (rarity == "Rare"){
            Rare fish = (Rare) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(4);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(selectedRod.getChanceStat()); //10 is placeholder for rod stat
                if (strengthEffect == 0){ //fish getting tired
                    fish.setStrength(fish.getStrength()-5);
                }
                //else{ //fish fighting hard but should still lose a little strength
                  //  fish.setStrength(fish.getStrength()-5);
                //}
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){
                    //remove bait and fish gets away
                }
                //else{ //fish fighting hard but should still lose a little strength
                  //  fish.setStrength(fish.getStrength()-5);
                //}

            }
            else if (fishAction == 2){ //escape method
                int escaped = fish.escape(selectedRod.getChanceStat());
                if (escaped == 1){  //may need to change fishing variable in while loop to global to effect
                    //fish got away
                }
               // else{ //fish fighting hard but should still lose a little strength
                 //   fish.setStrength(fish.getStrength()-5);
                //}
            }

            else if (fishAction == 3){ //break method
                int rodBreak = fish.calculateBreak(selectedRod.getDurability()); //85 placeholder for durability
                if (rodBreak == 1){
                    //break rod and fish gets away
                }
               // else{ //fish fighting hard but should still lose a little strength
                 //   fish.setStrength(fish.getStrength()-5);
                //}
            }
        }

        else if (rarity == "Uncommon"){
            Uncommon fish = (Uncommon) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(3);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(selectedRod.getChanceStat()); //10 is placeholder for rod stat
                if (strengthEffect == 0){ //fish getting tired
                    fish.setStrength(fish.getStrength()-10);
                }
               // else{ //fish fighting hard but should still lose a little strength
                 //   fish.setStrength(fish.getStrength()-5);
                //}
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){
                    //remove bait and fish gets away
                }
               // else{ //fish fighting hard but should still lose a little strength
                 //   fish.setStrength(fish.getStrength()-5);
                //}

            }
            else if (fishAction == 2){
                int escaped = fish.escape(selectedRod.getChanceStat());
                if (escaped == 1){  //may need to change fishing variable in while loop to global to effect
                    //fish got away
                }
              //  else{ //fish fighting hard but should still lose a little strength
                //    fish.setStrength(fish.getStrength()-5);
                //}
            }
        }

        else if (rarity == "Common"){
            Common fish = (Common) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(2);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(selectedRod.getChanceStat()); //10 is placeholder for rod stat
                if (strengthEffect == 0){ //fish getting tired
                    fish.setStrength(fish.getStrength()-10);
                }
              //  else{ //fish fighting hard but should still lose a little strength
                //    fish.setStrength(fish.getStrength()-5);
                //}
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){
                    //remove bait and fish gets away
                }
               // else{ //fish fighting hard but should still lose a little strength
                 //   fish.setStrength(fish.getStrength()-5);
                //}

            }

        }
    }

    public void tugAction(String rarity, Object fishObject, Object rod){
        Rod selectedRod = (Rod) rod;
        if (rarity == "Rare"){
            Rare fish = (Rare) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(4);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(selectedRod.getChanceStat()); //10 is placeholder for rod stat
                if (strengthEffect == 0){ //fish getting tired
                    fish.setStrength(fish.getStrength()-10);
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                }
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){
                    //remove bait and fish gets away
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                }

            }
            else if (fishAction == 2){ //escape method
                int escaped = fish.escape(selectedRod.getChanceStat());
                if (escaped == 1){  //may need to change fishing variable in while loop to global to effect
                    //fish got away
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                }
            }

            else if (fishAction == 3){ //break method
                int rodBreak = fish.calculateBreak(selectedRod.getDurability()); //85 placeholder for durability
                if (rodBreak == 1){
                    //break rod and fish gets away
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                }
            }
        }

        else if (rarity == "Uncommon"){
            Uncommon fish = (Uncommon) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(3);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(selectedRod.getChanceStat()); //10 is placeholder for rod stat
                if (strengthEffect == 0){ //fish getting tired
                    fish.setStrength(fish.getStrength()-10);
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                }
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){
                    //remove bait and fish gets away
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                }

            }
            else if (fishAction == 2){
                int escaped = fish.escape(selectedRod.getChanceStat());
                if (escaped == 1){  //may need to change fishing variable in while loop to global to effect
                    //fish got away
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                }
            }
        }

        else if (rarity == "Common"){
            Common fish = (Common) fishObject;
            Random gen = new Random();
            int fishAction = gen.nextInt(2);
            if (fishAction == 0){ //fight method
                int strengthEffect = fish.fight(selectedRod.getChanceStat()); //10 is placeholder for rod stat
                if (strengthEffect == 0){ //fish getting tired
                    fish.setStrength(fish.getStrength()-10);
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                }
            }
            else if (fishAction == 1){ //steal bait method
                int baitStolen = fish.stealBait(selectedRod.getChanceStat());
                if (baitStolen == 1){
                    //remove bait and fish gets away
                }
                else{ //fish fighting hard but should still lose a little strength
                    fish.setStrength(fish.getStrength()-5);
                }

            }

        }
    }
}
