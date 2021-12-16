/*
File to create objects of bait class that can help the user catch bigger fish
the user can apply these objects when going fishing and can buy them from the store
*/


public class Bait extends Object{
    private String name;
    private int price;  //how much the bait costs
    private int effect;  //how much the bait helps the player

    public Bait(String name, int price, int effect) {
        this.name = name;
        this.price = price;
        this.effect = effect;
    }
    
    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getEffect() {
        return effect;
    }
    public void setEffect(int effect) {
        this.effect = effect;
    }
    
}
