/*
File to create objects of rod class that can help the user catch bigger fish
the user can apply these objects when going fishing and can buy them from the store
*/

public class Rod extends Object{
    private String name;
    private int price;  //how much it costs
    private int durability;   //helps the rod in not breaking against rare fish
    private int chanceStat;  //helps the user catch fish easier and quicker

    public Rod(String name,int price, int durability,int chanceStat) {
        this.name = name;
        this.price = price;
        this.durability = durability;
        this.chanceStat = chanceStat;
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
    public int getDurability() {
        return durability;
    }
    public void setDurability(int durability) {
        this.durability = durability;
    }
    public int getChanceStat() {
        return chanceStat;
    }
    public void setChanceStat(int chanceStat) {
        this.chanceStat = chanceStat;
    }
}
