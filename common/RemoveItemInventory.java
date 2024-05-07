package common;

import java.util.List;

public class RemoveItemInventory implements ItemCommand {
    private Item item;
    private List<Item> itemList;

    public RemoveItemInventory(Item item, List<Item> itemList) {
        this.item = item;
        this.itemList = itemList;
    }
    
    public void itemOperation() {
        itemList.remove(item);
    }
}