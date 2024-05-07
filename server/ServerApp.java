package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import common.PersonFactory;
import common.ItemManager;

public class ServerApp {
    public static void main(String[] args) throws RemoteException {
        try {
            Registry registry = LocateRegistry.createRegistry(2302);
            // Create a new instance of the PersonFactoryImpl and bind it to the RMI registry
            PersonFactory person = new PersonFactoryImpl();
	    System.out.println("New Person created from PersonFactory");
            registry.rebind("//in-csci-rrpc02.cs.iupui.edu/PersonFactory", person);
            System.out.println("Binding is complete.");
            System.out.println();
            
            ItemManager item = new ItemManagerImpl();
	    System.out.println("New Item Manager created");
            registry.rebind("//in-csci-rrpc02.cs.iupui.edu/ItemManager", item);
	    System.out.println("Binding is complete.");
            System.out.println("Service on server machine started. Ready for remote calls...");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
    }
}
