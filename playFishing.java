import java.util.Scanner;

import java.util.Random;
public class playFishing {
    static Rare rareFish[] = {
        new Rare("Rare", "Coelacanth", 6.5, 500, 90),
        new Rare("Rare", "Blue Marlin", 12, 550, 93),
        new Rare("Rare", "Bluefin Tuna", 13, 600, 95),
        new Rare("Rare", "Yellowfin Tuna", 7, 525, 91),
        new Rare("Rare", "Dorado", 4, 485, 87)
    };
    static Common commonFish[] = {
        new Common("Common","Trout", 1.2, 50, 45),
        new Common("Common","Bass", 1.4, 55, 50),
        new Common("Common","Gold Fish", 0.2, 5, 5),
        new Common("Common","Carp", 1.5, 50, 10),
        new Common("Common","Salmon", 1.3, 40, 30),
        new Common("Common","Tuna", 3, 100, 45)
    };
    
    static Uncommon uncommonFish[] = {
        new Uncommon("Uncommon", "Clown Fish", 0.3, 350, 35),
        new Uncommon("Uncommon", "Garibaldi", 1, 400, 60),
        new Uncommon("Uncommon", "Koi Fish", 2.5, 200, 20),
        new Uncommon("Uncommon", "Cherry Salmon", 1.5, 250, 30),
        new Uncommon("Uncommon", "Puffer Fish", 2, 450, 65)
    
    };
    public static void main(String[] args){
        
        boolean playing = true;
               

        while (playing){
            System.out.println("Hi, Welcom to the fishing game!");
            String userChoice = menu();
            if (userChoice == "1"){ //go fishing
                String option = fishingMenu();
                boolean invalid = true;
                while (invalid){
                    if (option == "1"){ //apply bait
                        System.out.println("Good choice of bait!");
                        invalid = true;
                    }

                    else if (option == "2"){ //select rod
                        System.out.println("good choice of rod!");
                        invalid = true;
                    }

                    else if (option =="3"){ //cast
                        invalid = false;
                        System.out.println("Awesome, let's get that line out there!");
                        System.out.println("*casts line*");
                        String rarity = rarity();
                        //might have to do ifs and else statements when selecting a fish
                        Fish fish = pickFish(rarity);
                            
                    }
                    else{ //invalid option
                        System.out.println("Invalid option. Try again.");
                    }
                }
            }   
            
            else if (userChoice == "2"){ //go to inventory

            }

            else if (userChoice == "3"){ //exit
                playing = false;
            }

            else{ //invalid choice
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static String menu(){
        System.out.println("Select what you would like to do. (Enter 1, 2, or 3)");
        System.out.println("1. Go Fishing");
        System.out.println("2. Go to inventory");
        System.out.println("3. Exit game");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        return choice;
    }

    public static String fishingMenu(){
        System.out.println("Select an option (Enter 1, 2, or 3)");
        System.out.println("1. Apply bait");
        System.out.println("2. Select rod");
        System.out.println("3. Leave it alone");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        return choice;
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
}
