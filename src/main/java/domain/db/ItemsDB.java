package domain.db;

import domain.model.Item;
import domain.utils.Loader;

import java.util.ArrayList;

public class ItemsDB {

    static ItemsDB instance = null;

    public static ItemsDB getInstance() {
        if (instance == null) {
            instance = new ItemsDB();
        }
        return instance;
    }

    ArrayList<Item> items = new ArrayList<>();

    public ItemsDB() {
        items = Loader.getItems();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getNameOfItemById(int id) {
        for (Item item :
                this.items) {
            if (item.getId() == id) {
                return item.getName();
            }
        }
        return "";
    }

    public Item getItemById(int id) {
        for (Item item :
                this.items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

}
