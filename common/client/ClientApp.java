package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import common.*;

public class ClientApp {
	
    public static PersonFactory remotePerson;
    public static ItemManager remoteItem;
    
    public ClientApp(ItemManager remoteItem, PersonFactory remotePerson) {
        ClientApp.remoteItem = remoteItem;
        ClientApp.remotePerson = remotePerson;
    }

    public static void main(String[] args) {
        setupDependencies();

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("Choose a role: 1. Customer, 2. Admin, 3. Exit");
                int role = sc.nextInt();
                sc.nextLine();

				//List<Item> itemList = remoteItem.browseItems();
                switch (role) {
                    case 1:
                    	Person customer = remotePerson.factorPerson("CUSTOMER");
                    	System.out.println("Press 1 for Login and 2 for Register");
    					String i=sc.nextLine();
    					System.out.println("Enter your Username:");
    					String uname=sc.nextLine();
    					System.out.println("Enter your Password:");
    					String pass=sc.nextLine();
    					if(i.equalsIgnoreCase("1")) {
    						if(customer.login(uname,pass)) {
    							System.out.println("You are logged in!");
    						}
    						else {
    							System.out.println("Your login is unsuccessful!");
    							System.exit(0);
    						}
    					} else if(i.equalsIgnoreCase("2")) {
    						if(customer.register(uname,pass)) {
    							System.out.println("You are successfully registered!");
    						}
    						else {
    							System.out.println("Your registration is unsuccessful!");
    							System.exit(0);
    						}
    					}
                        handleUser(new CustomerActions(remoteItem), sc);
                        break;
                    case 2:
                        Person admin = remotePerson.factorPerson("ADMIN");
                        System.out.println("Press 1 for Login and 2 for Register");
    					String j=sc.nextLine();
    					System.out.println("Enter your Username:");
    					String user=sc.nextLine();
    					System.out.println("Enter your Password:");
    					String password=sc.nextLine();
    					if(j.equalsIgnoreCase("1")) {
    						if(admin.login(user,password)) {
    							System.out.println("You are logged in!");
    						}
    						else {
    							System.out.println("Your login is unsuccessful!");
    							System.exit(0);
    						}
    					} else if(j.equalsIgnoreCase("2")) {
    						if(admin.register(user,password)) {
    							System.out.println("You are successfully registered!");
    						}
    						else {
    							System.out.println("Your registration is unsuccessful!");
    							System.exit(0);
    						}
    					}
                        handleUser(new AdminActions(remoteItem), sc);
                        break;
                    case 3:
                        System.out.println("Thank you for visiting!");
                        return;
                    default:
                        System.out.println("Invalid Input, please choose again.");
                }
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static void setupDependencies() {
        try {
            Registry registry = LocateRegistry.getRegistry("in-csci-rrpc02.cs.iupui.edu", 2302);
            remotePerson = (PersonFactory) registry.lookup("//in-csci-rrpc02.cs.iupui.edu/PersonFactory");
            remoteItem = (ItemManager) registry.lookup("//in-csci-rrpc02.cs.iupui.edu/ItemManager");        } catch (Exception e) {
            System.err.println("Could not establish connection with the RMI server:  " + e.getMessage());
            System.exit(1);
        }
    }

    private static void handleUser(UserActions userActions, Scanner sc) throws RemoteException {
        while (true) {
            MenuOptions option = userActions.showMenu(sc);
            if (option == MenuOptions.LOGOUT || option == MenuOptions.LOGOUT_ADMIN) {
                System.out.println("Logged out successfully.");
                break;
            }
            userActions.executeAction(option, sc);
        }
    }
}
