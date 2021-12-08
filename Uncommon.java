import java.util.Random;
public class Uncommon extends Common{
    public Uncommon(String rarity, String name, double size, int value, int strength) {
        super(rarity, name, size, value, strength);
    }
    public int escape(int chanceStat){
        Random gen = new Random();
        int escapeChance = gen.nextInt(20);
        if (escapeChance-this.getSize()< 3){
            return 1; //escape in main
        }

        else{
            return 0; //didn't escape
        }
    }
}
