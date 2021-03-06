package domain.utils;

import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;
import domain.db.ItemsDB;
import domain.model.Bpo;
import domain.model.Item;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Loader {

    static String BPOFile = "data/blueprints.yaml";
    static String ItemsFile = "data/typeIDs.yaml";

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
            inputStream = Loader.class.getClassLoader().getResourceAsStream(ItemsFile);
            HashMap yamlMap = (HashMap) yaml.load(inputStream);
            for (Object k :
                    yamlMap.keySet()) {
                HashMap item = (HashMap) yamlMap.get(k);
                Item i = generateItemFromYamlItem(Integer.parseInt(k.toString()), item);
                if (i != null) {
                    items.add(i);
                }
            }
        } catch (Error e) {
            e.printStackTrace();
        }
        return items;
    }

    private static Item generateItemFromYamlItem(int id, HashMap item) {
        double basePrice = 0;
        if (item.containsKey("basePrice")) {
            basePrice = Double.parseDouble(item.get("basePrice").toString());
        }
        int iconID = 0;
        if (item.containsKey("iconID")) {
            iconID = (int) item.get("iconID");
        }
        HashMap name = (HashMap) item.get("name");
        double volume = 0;
        if (item.containsKey("volume")) {
            volume = (double) item.get("volume");
        }
        return new Item(id, basePrice, iconID, (String) name.get("en"), volume);
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
    public static ArrayList<Bpo> getBPOs() {
        new ItemsDB();
        ArrayList<Bpo> bpos = new ArrayList<>();
        Yaml yaml = new Yaml();
        InputStream inputStream;
        try {
            inputStream = Loader.class.getClassLoader().getResourceAsStream(BPOFile);

            HashMap yamlMap = (HashMap) yaml.load(inputStream);
            for (Object o :
                    yamlMap.keySet()) {
                HashMap data = (HashMap) yamlMap.get(o);
                Bpo bpo = generateBPOFromYamlItem((int) o, data);
                if (bpo != null) {
                    bpos.add(bpo);
                }
            }
        } catch (Error e) {
            e.printStackTrace();
        }
        return bpos;
    }

    private static Bpo generateBPOFromYamlItem(int id, HashMap map) {
        if (map.containsKey("activities")) {
            HashMap activities = (HashMap) map.get("activities");
            if (activities.containsKey("manufacturing")) {
                HashMap menu = (HashMap) activities.get("manufacturing");
                if (menu.containsKey("materials") && menu.containsKey("products")) {
                    ArrayList materials = (ArrayList) menu.get("materials");
                    ArrayList product = (ArrayList) menu.get("products");
                    int time = (int) menu.get("time");
                    return new Bpo(id, formatListOfItemsToHasmap(materials), formatListOfItemsToHasmap(product), time);
                }
            }
        }
        return null;
    }

    private static HashMap formatListOfItemsToHasmap(ArrayList list) {
        HashMap map = new HashMap();
        for (Object o : list) {
            HashMap itemAndQuantity = (HashMap) o;
            int id = Integer.parseInt(itemAndQuantity.get("typeID").toString());
            map.put(ItemsDB.getInstance().getItemById(id), itemAndQuantity.get("quantity"));
        }
        return map;
    }

}
