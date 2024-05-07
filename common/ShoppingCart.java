package common;

import java.util.List;

public class ShoppingCart {
    private List<Item> cart;
    private Item item;

    public ShoppingCart(List<Item> cart, Item item) {
        this.cart = cart;
        this.item = item;
    }

    public double purchaseCartItem(List<Item> shoppingCart) {
        double totalCost = 0.0;
        for (Item item : cart) {
            int quantity = item.getQuantity();
            if (quantity > 0) {
                item.setQuantity(quantity - 1);
                totalCost += item.getPrice();
            } else {
                System.out.println(item.getDescription() + " is not available at the moment!");
            }
        }
        shoppingCart.clear();
		System.out.println("The total cost is: $" + totalCost);
        return totalCost;
    }

    public void removeItemFromCart() {
        if (cart.contains(item)) {
            cart.remove(item);
        } else {
            System.out.println("Item is not found in cart.");
        }
    }
    
    public void addItemInCart() {
        cart.add(item);
    }
    
    public void browseItemsInCart() {
        if (cart.isEmpty()) {
            System.out.println("No item in cart!");
        } else {
            System.out.println("The items in your cart are:");
            for (Item item : cart) {
                System.out.println(item.getDescription());
            }
        }
    }   
}
