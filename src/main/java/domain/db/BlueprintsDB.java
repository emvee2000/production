package domain.db;

import domain.model.BPO;
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

    ArrayList<BPO> BPOs = new ArrayList<>();

    public BlueprintsDB() {
        BPOs = Loader.getBPOs();
    }

    public HashMap getMaterialsForBPOID(int id) {
        for (BPO bpo :
                this.BPOs) {
            if (bpo.getID() == id) {
                return bpo.getMaterials();
            }
        }
        return null;
    }

}
