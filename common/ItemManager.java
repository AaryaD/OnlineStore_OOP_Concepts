package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ItemManager extends Remote {
    List<Item> browseItems() throws RemoteException;
    void viewCart(Item item, List<Item> cart) throws RemoteException;
    void addItemToCart(Item item, List<Item> cart) throws RemoteException;
    public void removeItemFromCart(Item item, List<Item> cart) throws RemoteException;
    double purchaseCartItem(List<Item> cart) throws RemoteException;
    void getItemDetailsList(Item item) throws RemoteException;
    Item getItemByName(String name) throws RemoteException;
    void addItem(Item item) throws RemoteException;
    void updateItem(Item item, double newPrice, String type, String des, int quantity) throws RemoteException;
    void removeItem(Item item) throws RemoteException;
}
