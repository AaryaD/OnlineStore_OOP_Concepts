package common;

import java.util.List;

public class AddItemInventory implements ItemCommand {
    private Item item;
    private List<Item> itemList;

    public AddItemInventory(Item item, List<Item> itemList) {
        this.item = item;
        this.itemList = itemList;
    }
    
    public void itemOperation() {
        itemList.add(item);
    }
}