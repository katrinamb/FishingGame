public class Bait extends Object{
    private String name;
    private int price;
    private int effect;

    public Bait(String name, int price, int effect) {
        this.name = name;
        this.price = price;
        this.effect = effect;
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
    public int getEffect() {
        return effect;
    }
    public void setEffect(int effect) {
        this.effect = effect;
    }
    
}
