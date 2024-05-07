package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import common.AddItemInventory;
import common.Item;
//import common.ItemInventory;
import common.ItemManager;
import common.RemoveItemInventory;
import common.ShoppingCart;
import common.UpdateItemInventory;

public class ItemManagerImpl extends UnicastRemoteObject implements ItemManager {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Item> defaultItemList = new ArrayList<>();
	
	public ItemManagerImpl() throws RemoteException {
		Item item1 = new Item("Stationery", "Notebook", 10, 3.50);
		Item item2 = new Item("Kithenware", "Coffee Mug", 20, 6.00);
		Item item3 = new Item("Fitness Accessory", "Yoga Mat", 25, 50.00);
		defaultItemList.add(item1);
		defaultItemList.add(item2);
		defaultItemList.add(item3);
	}
	
    public List<Item> browseItems() throws RemoteException {
    	List<Item> itemList = new ArrayList<>();
        for (Item item : defaultItemList) {
            itemList.add(item);
        }
        return itemList;
    }
    
	@Override
	public void viewCart(Item item, List<Item> cart) throws RemoteException {
		ShoppingCart cartOperations = new ShoppingCart(cart, item);
		cartOperations.browseItemsInCart();		
	}
	
	@Override
	public void addItemToCart(Item item, List<Item> cart) throws RemoteException {
		ShoppingCart cartOperations = new ShoppingCart(cart, item);
		cartOperations.addItemInCart();		
	}
	
	@Override
	public void removeItemFromCart(Item item, List<Item> cart) throws RemoteException {
		ShoppingCart cartOperations = new ShoppingCart(cart, item);
		cartOperations.removeItemFromCart();;				
	}
	
	@Override
	public double purchaseCartItem(List<Item> cart) throws RemoteException {
	    double totalCost = 0.0;
        for (Item item : cart) {
            int quantity = item.getQuantity();
            if (quantity > 0) {
                item.setQuantity(quantity - 1);
                totalCost += item.getPrice();
            } else {
                System.out.println(item.getName() + " is not available at the moment!");
            }
        }
        cart.clear();
        return totalCost;
	}
	
	@Override
	public void getItemDetailsList(Item item) throws RemoteException {
		System.out.printf("Name: %-10s | Description: %-20s | Price: $%.2f | Qty: %d%n",
			    item.getName(), item.getDescription(), item.getPrice(), item.getQuantity());
	}
	
	@Override
	public Item getItemByName(String name) throws RemoteException {
		for (Item item : defaultItemList) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        System.out.println("Item not found in store!");
		return null;
	}
	
	@Override
	public void addItem(Item item) throws RemoteException {
		AddItemInventory command = new AddItemInventory(item, defaultItemList);
		command.itemOperation();
	}
	
	@Override
	public void updateItem(Item item, double newPrice, String name, String des, int quantity) throws RemoteException {
		UpdateItemInventory command = new UpdateItemInventory(item, newPrice, name, des, quantity);
		command.itemOperation();
	}
	
	@Override
	public void removeItem(Item item) throws RemoteException {
		RemoveItemInventory command = new RemoveItemInventory(item, defaultItemList);
		command.itemOperation();
	}
	
}