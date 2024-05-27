package se.kth.iv1350.pos.integration;

/**
 * Holds the details about a product in the store.
 * Represents the data of an item.
 */

public class ItemDTO {
    private String name;
    private double VATRate;
    private double price;
    private String description;
    private String itemId;
/**
 * 
 * @param name the name of the item
 * @param VATRate the VATRate of the item
 * @param price the price of the item
 * @param description the description of the item
 * @param itemId  the identifier of the item
 */
   public ItemDTO(String name, double VATRate, double price, String description, String itemId) {
      this.name = name;
      this.VATRate = VATRate;
      this.price = price;
      this.description = description;
      this.itemId = itemId; 
}

    /**
     * get the name of the item.
     * @return String name of tiem
     */
    public String getName(){
        return name;
    }
     /**
     * get the description of the item.
     * @return String description of tiem
     */
    public String getDescription(){
        return description;
    }
     /**
     * get the Identifer of the item.
     * @return String Identifer of tiem
     */
    public String getItemIdentifier(){
        return itemId;
    }
     /**
     * get the Price of the item.
     * @return Double price of tiem
     */
    public double getPrice(){
        return price;
    }
     /**
     * get the VAT Rate of the item.
     * @return double VAT rate of tiem
     */
    public double getVATRate(){
        return VATRate;
    }    
    @Override
public String toString() {
    return "ItemDTO{" +
           "name='" + name + '\'' +
           ", VATRate=" + VATRate +
           ", price=" + price +
           ", description='" + description + '\'' +
           ", itemId='" + itemId + '\'' +
           '}';
}
}
