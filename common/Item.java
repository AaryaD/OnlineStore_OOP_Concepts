package common;

import java.io.Serializable;

public class Item implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private String name;
    private int quantity;
    private double price;
    
    public Item(String desc, String name, int quantity, double price) {
        this.name = name;
        this.description = desc;
        this.price = price;
        this.quantity = quantity;
    }

    public Item() {
    	this.name = " ";
        this.description = " ";
        this.price = 0;
        this.quantity = 0;
	}
    
    public String getDescription() {
    	return description;
    }
    
    public void setDescription(String description) {
		this.description = description;
	}
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
		this.name = name;
	}
    
    public int getQuantity() {
    	return quantity;
    }
    
    public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    public double getPrice() {
    	return price;
    }
    
    public void setPrice(double price) {
		this.price = price;
	}
}