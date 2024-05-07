package common;

public class UpdateItemInventory implements ItemCommand {
    private Item item;
    private String newDescription;
	private String newName;
    private int newQuantity;
    private double newPrice;

    public UpdateItemInventory(Item item, double newPrice, String newName, String newDescription, int newQuantity) {
        this.item = item;
        this.newDescription = newDescription;
        this.newName = newName;
        this.newPrice = newPrice;
        this.newQuantity = newQuantity;
    }
    
    public void itemOperation() {
        item.setDescription(newDescription);
        item.setName(newName);
        item.setPrice(newPrice);
        item.setQuantity(newQuantity);
    }
}