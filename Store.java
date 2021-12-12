public class Store extends Object{
    private int coins=150;
    // go back and add items to the arrays; I think we should add the objects in
    // the playFishing, but just put the names here in these arrays
    public String[] rods;
    public String[] baits;
    
    int getCoins(){
        return coins;
    }
    void setCoins(int coins){
        this.coins = coins;
    }
    //just an idea we could try because maybe we could just call methods to do the payments and paying
    //adds coins to the total coins of the user
    void addCoins(int coins){
        this.coins += coins;
    }
    //removes coins from the total coins of the user
    void subCoins(int coins){
        this.coins -= coins;
    }
    void buyRod(String rodName, int rodCost){
        if(getCoins() >= rodCost){
            for (int i = 0; i < rods.length; i++){
                if(rods[i] == rodName){
                    System.out.println("Nice choice! Added to your inventory.");
                    //have it add to the inventory list but we have not created inventory yet so for later
                }
            }
            subCoins(rodCost);
        }
        else{
            System.out.println("You do not have enough coins to purchase this item.");
        } 
    }
    void buyBait(String baitName, int baitCost){
        if(getCoins() >= baitCost){
            for (int i = 0; i < baits.length; i++){
                if(baits[i] == baitName){
                    System.out.println("Nice choice! Added to your inventory.");
                    //have it add to the inventory list but we have not created inventory yet so for later
                }
            }
            subCoins(baitCost);
        }
        else{
            System.out.println("You do not have enough coins to purchase this item.");
        }    
    }
}