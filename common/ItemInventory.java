package common;

import java.util.List;

public class ItemInventory {
	private List<Item> itemList;
	private Item item;
	
	public ItemInventory(List<Item> itemList, Item item) {
		this.itemList = itemList;
		this.item = item;
	}
	
	public void addItem() {
		itemList.add(item);			
	}

    public void updateItem(String newName, String newDescription, double newPrice, int newQuantity) {
        if (item != null) {
            item.setPrice(newPrice);
            item.setName(newName);
            item.setDescription(newDescription);
            item.setQuantity(newQuantity);
        } else {
            System.out.println("No item retrieved hence cannot update");
        }
    }
    
    public void removeItem() {
        itemList.remove(item);   		
    }
}