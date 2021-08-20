package domain.model;

import java.util.HashMap;


public class BPO {
    int ID;
    HashMap materials;
    HashMap products;
    int manufacteringTime;

    public BPO(int ID, HashMap materials, HashMap products, int manufacteringTime) {
        this.ID = ID;
        this.materials = materials;
        this.products = products;
        this.manufacteringTime = manufacteringTime;
    }

    public int getID() {
        return ID;
    }

    public HashMap getMaterials() {
        return materials;
    }

    public HashMap getProducts() {
        return products;
    }

    public int getManufacteringTime() {
        return manufacteringTime;
    }

}
