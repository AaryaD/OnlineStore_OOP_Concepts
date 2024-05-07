package client;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;
import common.*;

public class AdminActions implements UserActions {
	public final ItemManager remoteItem;

    public AdminActions(ItemManager remoteItem) {
        this.remoteItem = remoteItem;
    }

	@Override
    public void executeAction(MenuOptions option, Scanner sc) throws RemoteException {
		ItemManager remoteItem = ClientApp.remoteItem;
		//List<Item> items = new ArrayList<>();
		
		switch (option) {
        case VIEW_ITEMS_ADMIN:
            viewItemsAdmin(remoteItem);
            break;
        case ADD_ITEM:
            addItem(sc, remoteItem);
            break;
        case REMOVE_ITEM:
            removeItem(sc, remoteItem);
            break;
        case UPDATE_ITEM:
            updateItem(sc, remoteItem);
            break;
        case ADD_ADMIN:
            addAdmin(sc);
            break;
        case ADD_CUSTOMER:
            addCustomer(sc);
            break;
        case REMOVE_CUSTOMER:
            removeCustomer(sc);
            break;
        
            default:
                System.out.println("Invalid choice for admin!");
        }
    }
    
    @Override
    public MenuOptions showMenu(Scanner sc) {
        System.out.println("Admin Menu:");
        System.out.println("1. View Items\n2. Add Item\n3. Remove Item\n4. Update Item\n5. Add Admin\n6. Add Customer\n7. Remove Customer\n8. Logout");
		
        int choice = sc.nextInt();
        switch (choice) {
            case 1: return MenuOptions.VIEW_ITEMS_ADMIN;
            case 2: return MenuOptions.ADD_ITEM;
            case 3: return MenuOptions.REMOVE_ITEM;
            case 4: return MenuOptions.UPDATE_ITEM; 
            case 5: return MenuOptions.ADD_ADMIN;
            case 6: return MenuOptions.ADD_CUSTOMER;
            case 7: return MenuOptions.REMOVE_CUSTOMER;
            case 8: System.out.println("You are logged out!");
					System.exit(0);
            default:
                System.out.println("Invalid choice for admin menu, please try again.");
                return showMenu(sc);
        }
    }
    
    private void viewItemsAdmin(ItemManager remoteItem) throws RemoteException {
    	List<Item> itemsList = remoteItem.browseItems();
    	System.out.println("Items are:");
    	for (Item item : itemsList) {
    		System.out.printf("Name: %-10s | Description: %-20s | Price: $%.2f | Qty: %d%n",
    			    item.getName(), item.getDescription(), item.getPrice(), item.getQuantity());
    	}
    }

    private void addItem(Scanner sc, ItemManager remoteItem) throws RemoteException {
    	List<Item> itemList = remoteItem.browseItems();
    	sc.nextLine();
    	System.out.println("Enter the name of item to be added:");
    	String name=sc.nextLine();
    	System.out.println("Enter the Description:");
    	String desc=sc.nextLine();
    	System.out.println("Enter the price:");
    	Double price=sc.nextDouble();
    	System.out.println("Enter the quantity:");
    	int quant=sc.nextInt();
    	Item newItem=new Item(desc, name, quant, price);
    	remoteItem.addItem(newItem);
    	itemList.add(newItem);
    	System.out.println("The new item is added");
        System.out.println("List of products:");
        for (Item item: itemList) {
    		System.out.printf("Name: %-10s | Description: %-20s | Price: $%.2f | Qty: %d%n",
    			    item.getName(), item.getDescription(), item.getPrice(), item.getQuantity());
    	}
    }

    private void removeItem(Scanner sc, ItemManager remoteItem) throws RemoteException {
    	List<Item> itemList = remoteItem.browseItems();
    	System.out.println("Enter the item name to be removed from inventory:");
    	String removeItem = sc.nextLine();
    	boolean removed=false;
        for(Item item: itemList) {
            if(item.getName().equalsIgnoreCase(removeItem)){
                removed = itemList.remove(item);
                break;
            }
        }
        if (removed) {
            System.out.println("The item is successfully removed!" );
        } else {
            System.out.println("The item was not found in inventory!");
        }
        
    }

    private void updateItem(Scanner sc, ItemManager remoteItem) throws RemoteException {
    	System.out.println("Enter the item to be updated in the inventory: ");
    	String updateItem = sc.nextLine();
    	Item itemToUpdate = remoteItem.getItemByName(updateItem);
    	if (itemToUpdate != null) {
    		System.out.println("Enter the new price: ");
    		double newPrice = sc.nextDouble();
    		System.out.println("Enter the new name: ");
    		String newName = sc.nextLine();
    		System.out.println("Enter the new description: ");
    		String newDesc = sc.nextLine();
    		System.out.println("Enter the new quantity: ");
    		int newQuantity = sc.nextInt();
    		System.out.println("The updated item details are:");
    		remoteItem.updateItem(itemToUpdate, newPrice, newName, newDesc, newQuantity);
    		
        	List<Item> itemsList = remoteItem.browseItems();
    		for(Item item: itemsList) {
                if(item.getName().equalsIgnoreCase(updateItem)){
                    item.setDescription(newDesc);
                    item.setPrice(newPrice);
                    item.setQuantity(newQuantity);
                    item.setName(newName);
                    System.out.println("Item is successfully updated!");
                    break;
                }
    		}
    	}
    	
    }
    	

    private void addAdmin(Scanner sc) throws RemoteException {
    	Admin newAdmin = new Admin();
    	System.out.println("Enter username for new admin:");
    	String newadmin = sc.nextLine();
    	System.out.println("Enter the password for new admin:");
    	String pass = sc.nextLine();
    	Admin n = new Admin(newadmin, pass);
    	newAdmin.addAdmin(n);
    	System.out.println("The new admin is successfully added!");
    }

    private void addCustomer(Scanner sc) throws RemoteException {
    	System.out.println("Enter username for new customer:");
    	String newcust = sc.nextLine();
    	System.out.println("Enter the password for new customer:");
    	String pass = sc.nextLine();
    	Customer newCust = new Customer(newcust, pass);
    	Admin a2 = new Admin();
    	a2.addCustomer(newCust);
    	System.out.println("The new customer is successfully added!");	
    }

    private void removeCustomer(Scanner sc) throws RemoteException {
    	Admin a3 = new Admin();
    	System.out.println("Enter customer username you want to remove:");
    	String oldcust = sc.nextLine();
    	a3.removeCustomer(oldcust);
    	System.out.println("The customer " + oldcust + " is successfully removed!");
    }

}
