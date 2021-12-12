public class Rod extends Object{
    private String name;
    private int price;
    private int durability;
    private int chanceStat;

    public Rod(String name,int price, int durability,int chanceStat) {
        this.name = name;
        this.price = price;
        this.durability = durability;
        this.chanceStat = chanceStat;
    }
    
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
