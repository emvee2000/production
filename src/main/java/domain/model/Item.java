package domain.model;

public class Item {
    private int id;
    private double basePrice;
    private int iconID;
    private String name;
    private double volume;

    public Item(int id, double basePrice, int iconID, String name, double volume) {
        this.id = id;
        this.basePrice = basePrice;
        this.iconID = iconID;
        this.name = name;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public int getIconID() {
        return iconID;
    }

    public String getName() {
        return name;
    }

    public double getVolume() {
        return volume;
    }
}
