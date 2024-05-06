package client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import common.*;

public class CustomerActions implements UserActions {
	public final ItemManager remoteItem;
	
	public CustomerActions(ItemManager remoteItem) {
        this.remoteItem = remoteItem;
    }
	
	List<Item> cart = new ArrayList<>();

	@Override
    public void executeAction(MenuOptions option, Scanner sc) throws RemoteException {
        ItemManager remoteItem = ClientApp.remoteItem;
		switch (option) {
        case ADD_TO_CART:
            addItemToCart(sc, remoteItem, cart);
            break;
        case REMOVE_FROM_CART:
            removeItemFromCart(sc, remoteItem, cart);
            break;
        case VIEW_CART:
            viewCart(remoteItem, cart);
            break;
        case PURCHASE_ITEMS:
            purchaseItems(remoteItem, cart);
            break;
        case VIEW_ITEMS:
            viewItems(remoteItem);
            break;
            default:
                System.out.println("Invalid choice for customer");        }
    }
	

	private void addItemToCart(Scanner sc, ItemManager remoteItem ,List<Item> cart) throws RemoteException {
		List<Item> itemList = remoteItem.browseItems();
		System.out.println("The list of available items is: ");
		for (Item item : itemList) {
    		System.out.printf("Name: %-10s | Description: %-20s | Price: $%.2f | Qty: %d%n",
    			    item.getName(), item.getDescription(), item.getPrice(), item.getQuantity());
    	}
		// view available items in store.
		
		Item addItem = new Item();
	    System.out.println("Enter item name to add in cart or type STOP to finish:");
	    do {
	    	String itemName = sc.nextLine();
	    	
	    	if (itemName.equals("STOP")) {
	    	    double totalCost = remoteItem.purchaseCartItem(cart);
	    	    System.out.println("The total cost is: $" + totalCost);
	    	    break;
	    	}
	    	addItem = remoteItem.getItemByName(itemName);
		    if (addItem != null) {
		    	remoteItem.addItemToCart(addItem, cart);
		        cart.add(addItem);
		        System.out.println(addItem.getName() + " is added to your cart.");
		    }
	    }while(true); 
	    
	}

    private void removeItemFromCart(Scanner sc, ItemManager remoteItem, List<Item> cart) throws RemoteException {
    	System.out.println("Enter the name of the item you want to remove or type STOP to finish: ");
    	do {
        	String itemName = sc.nextLine();
        	if (itemName.equals("STOP")) {
        		double totalCost = remoteItem.purchaseCartItem(cart);
        		System.out.println("The total cost is: $" + totalCost);
        		break;
        	   }
            Item removeItem = null;
            for (Item item : cart) {
         	   if (item.getName().equals(itemName)) {
                removeItem = item;
                break;
         	   }
            }
            if (removeItem != null) {
         	   remoteItem.removeItem(removeItem);
         	   cart.remove(removeItem);
         	   System.out.println("Item is removed from cart.");
            }
            else {
             System.out.println("Item is not found in cart.");
            }
    	}while (true);
    }

    private void viewCart(ItemManager remoteItem, List<Item> cart) {
    	System.out.println("Items in your cart are:");
    	if (cart.isEmpty()) {
    		System.out.println("Your cart is empty!");
    	} else {
    		for (Item item: cart) {
    			System.out.println(item.getName()+" - "+item.getDescription()+" - "+item.getPrice());
    		}
    	}
    }

    private void purchaseItems(ItemManager remoteItem, List<Item> cart) throws RemoteException {
    	if (cart.isEmpty()) {
     	   System.out.println("Your cart is empty.");
     	   } 
     	   else {
     		   double totalCost = remoteItem.purchaseCartItem(cart);
     		   System.out.println("The total cost is: $" + totalCost);
     		   cart.clear();
     	   }
       
    }

    private void viewItems(ItemManager remoteItem) throws RemoteException {
    	List<Item> itemList = remoteItem.browseItems();
    	System.out.println("Items are:");
    	for (Item item : itemList) {
    		System.out.printf("Name: %-10s | Description: %-20s | Price: $%.2f | Qty: %d%n",
    			    item.getName(), item.getDescription(), item.getPrice(), item.getQuantity());
    	}
        
    }
	
	@Override
    public MenuOptions showMenu(Scanner sc) {
        System.out.println("Customer Menu:");
        System.out.println("1. Add Item to Cart\n2. Remove Item from Cart\n3. View Cart Items\n4. Purchase Items from Cart\n5. View Items in Inventory\n6. Logout");
	
        int option = sc.nextInt();
        switch (option) {
            case 1: return MenuOptions.ADD_TO_CART;
            case 2: return MenuOptions.REMOVE_FROM_CART;
            case 3: return MenuOptions.VIEW_CART;
            case 4: return MenuOptions.PURCHASE_ITEMS;
            case 5: return MenuOptions.VIEW_ITEMS;
            case 6: System.out.println("You are logged out!");
					System.exit(0);
            default:
                System.out.println("Invalid choice for customer menu, please try again.");
                return showMenu(sc); // Recursion for invalid option
        }
    }
}
