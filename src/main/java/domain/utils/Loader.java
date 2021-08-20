package domain.utils;

import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;
import domain.db.ItemsDB;
import domain.model.BPO;
import domain.model.Item;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Loader {

    static String BPOFile = "src/main/webapp/files/domain/blueprints.yaml";
    static String ItemsFile = "src/main/webapp/files/domain/typeIDs.yaml";

    //load items
    /*
            yamlMap looks like:
            266={
                basePrice= 240.0,
                description={
                    de= Eine kleine Rakete mit Durchschlagskopf.
                    en= A small rocket with a piercing warhead.
                    fr= Petite roquette dotée d'une ogive perforante.
                    ja= 徹甲弾頭を装備した小型ロケット。
                    ru= Маленькая ракета ближнего боя с бронебойной боеголовкой.
                    zh= 一种装备了穿甲弹头的小型火箭。
                    },
                graphicID= 20060,
                groupID= 387,
                iconID= 1350,
                marketGroupID= 922,
                mass= 100.0,
                metaGroupID= 1,
                name={
                    de= Scourge Rocket
                    en= Scourge Rocket
                    fr= Roquette Scourge
                    ja= スコージロケット
                    ru= Scourge Rocket
                    zh= 鞭挞火箭
                    },
                portionSize= 100,
                published= true,
                radius= 300.0,
                volume= 0.005}
             */
    public static ArrayList<Item> getItems() {

        ArrayList<Item> items = new ArrayList<>();
        Yaml yaml = new Yaml();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(ItemsFile);

            HashMap yamlMap = (HashMap) yaml.load(inputStream);
            for (Object k :
                    yamlMap.keySet()) {
                HashMap item = (HashMap) yamlMap.get(k);
                Item i = generateItemFromYamlItem((int) k, item);
                if (i != null) {
                    items.add(i);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }

    private static Item generateItemFromYamlItem(int id, HashMap item) {
        if (item.containsKey("basePrice") && item.containsKey("iconID") && item.containsKey("name") && item.containsKey("volume")) {
            double basePrice = Double.parseDouble(item.get("basePrice").toString());
            int iconID = (int) item.get("iconID");
            HashMap name = (HashMap) item.get("name");
            double volume = (double) item.get("volume");
            return new Item(id, basePrice, iconID, (String) name.get("en"), volume);
        }
        return null;
    }


    //load BPO's
            /*
            the yamlMap looks like this:
            683={
                activities={
                            copying={
                                time= 4800
                                },
                            invention={

                                materials=[
                                {quantity= 2, typeID= 20416}
                                {quantity= 2, typeID= 25887}
                                            ],
                                products=[
                                {probability= 0.3,
                                    quantity= 1,
                                    typeID= 39581}
                                    ],

                                skills=[
                                {level= 1,
                                    typeID:= 11442}
                                {level= 1,
                                    typeID= 11454}
                                {level= 1
                                    typeID= 21790}
                                    ],

                                time= 63900
                                },
                            manufacturing={
                                materials=[
                                {quantity= 24000, typeID= 34},
                                {quantity= 4500, typeID= 35}
                                {quantity= 1875; typeID= 36
                                {quantity= 375, typeID= 37
                                    ],
                                products=[
                                {quantity= 1,
                                    typeID= 582}
                                    ]
                                skills=[
                                {level= 1, typeID= 3380}
                                    ]
                                time= 6000
                                }
                            research_material={
                                {time= 2100}
                                }
                            research_time={{
                                time= 2100}}
                                }
                        blueprintTypeID= 683
                        maxProductionLimit= 30
            }
        }
             */
    public static ArrayList<BPO> getBPOs() {

        ArrayList<BPO> bpos = new ArrayList<>();
        Yaml yaml = new Yaml();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(BPOFile);

            HashMap yamlMap = (HashMap) yaml.load(inputStream);
            for (Object o :
                    yamlMap.keySet()) {
                HashMap data = (HashMap) yamlMap.get(o);
                BPO bpo = generateBPOFromYamlItem((int) o, data);
                if (bpo != null) {
                    bpos.add(bpo);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bpos;
    }

    private static BPO generateBPOFromYamlItem(int id, HashMap map) {
        if (map.containsKey("activities")) {
            HashMap activities = (HashMap) map.get("activities");
            if (activities.containsKey("manufacturing")) {
                HashMap menu = (HashMap) activities.get("manufacturing");
                if (menu.containsKey("materials") && menu.containsKey("products")) {
                    ArrayList materials = (ArrayList) menu.get("materials");
                    ArrayList product = (ArrayList) menu.get("products");
                    int time = (int) menu.get("time");
                    return new BPO(id, formatListOfItemsToHasmap(materials), formatListOfItemsToHasmap(product), time);
                }
            }
        }
        return null;
    }

    private static HashMap formatListOfItemsToHasmap(ArrayList list) {
        HashMap map = new HashMap();
        for (Object o : list) {
            HashMap itemAndQuantity = (HashMap) o;
            map.put(ItemsDB.getInstance().getItemById((int) itemAndQuantity.get("typeID")), itemAndQuantity.get("quantity"));
        }
        return map;
    }

}
