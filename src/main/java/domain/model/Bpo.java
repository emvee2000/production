package domain.model;

import java.util.HashMap;


public class Bpo {
    int ID;
    HashMap<Item, Integer> materials;
    HashMap<Item, String> products;
    int manufacteringTime;

    public Bpo(int ID, HashMap materials, HashMap products, int manufacteringTime) {
        this.ID = ID;
        this.materials = materials;
        this.products = products;
        this.manufacteringTime = manufacteringTime;
    }

    public int getID() {
        return ID;
    }

    public HashMap<Item, Integer> getMaterials() {
        return materials;
    }

    public HashMap getProductAndAmount() {
        return products;
    }

    public Item getProduct() {
        for (Item i :
                products.keySet()) {
            return i;
        }
        return null;
    }

    public String getProductName() {
        for (Item i :
                products.keySet()) {
            if (i != null) {
                return i.getName();
            }
        }
        return "Error";
    }

    public int getManufacteringTime() {
        return manufacteringTime;
    }

}
