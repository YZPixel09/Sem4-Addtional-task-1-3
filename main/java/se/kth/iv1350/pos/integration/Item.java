package se.kth.iv1350.pos.integration;

/**
 * Constructs an Item with a given ItemDTO and default quantity of 1.
 * @param itemDTO The data transfer object representing item details.
 */
public class Item {
    private ItemDTO itemDTO;
    private int quantity;

    
   /**
     * Constructs an Item with a given ItemDTO and specified quantity.
     * @param itemDTO The data transfer object representing item details.
     * @param quantity The initial quantity of this item.
     */
    public Item(ItemDTO itemDTO, int quantity) {
        this.itemDTO = itemDTO;
        this.quantity = quantity;
    }

     
    /**
     * get the name of the item.
     * @return string name of item
     */
    public String getName(){
        return this.itemDTO.getName(); 
    }
    /**
     * get the items description
     * @return the items description
     */
    public String getDescription(){
        return this.itemDTO.getDescription();
    }
    /**
     * get the items identifer
     * @return a string of the items identifer
     */
    public String getItemIdentifier(){
        return this.itemDTO.getItemIdentifier();
    }
    /**
     * get the items quantity
     * @return the quantity of the item
     */
    public int getQuantity() {
        return this.quantity;
    }
    /**
     * get the price of the item
     * @return the price of the item
     */
    public double getPrice(){
        return this.itemDTO.getPrice();
    }
    /**
     * get the vat rate of the item
     * @return vat rate of the item
     */
    public double getVATRate(){
        return this.itemDTO.getVATRate();
    }

   /**
     * Increases the quantity of the item by a specified amount.
     * This method is used when the same item is added multiple times to increase the total count.
     * @param addition The amount to increase the quantity by.
     */

    public void increaseQuantity(int addition){
        this.quantity += addition;
    }  
}
