package domain.db;

import domain.model.Bpo;
import domain.model.Item;
import domain.utils.Loader;

import java.util.ArrayList;
import java.util.HashMap;

public class BlueprintsDB {

    static BlueprintsDB instance = null;

    public static BlueprintsDB getInstance() {
        if (instance == null) {
            instance = new BlueprintsDB();
        }
        return instance;
    }

    ArrayList<Bpo> BPOs = new ArrayList<>();

    public BlueprintsDB() {
        BPOs = Loader.getBPOs();
    }

    public ArrayList<Bpo> getBPOs() {
        return BPOs;
    }

    public Bpo getBPObyID(int id) {
        for (Bpo bpo :
                this.BPOs) {
            if (bpo.getID() == id) {
                return bpo;
            }
        }
        return null;
    }

    public HashMap getMaterialsForBPOID(int id) {
        for (Bpo bpo :
                this.BPOs) {
            if (bpo.getID() == id) {
                return bpo.getMaterials();
            }
        }
        return null;
    }

    public Item getProductOfBPOForBPO(int id) {
        for (Bpo bpo :
                this.BPOs) {
            if (bpo.getID() == id) {
                return bpo.getProduct();
            }
        }
        return null;
    }

    public Bpo getBPOforItem(Item item) {
        for (Bpo bpo :
                this.BPOs) {
            Item product = bpo.getProduct();
            if (product == item) {
                return bpo;
            }
        }
        return null;
    }

}
