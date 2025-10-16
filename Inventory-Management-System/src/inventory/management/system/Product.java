
package inventory.management.system;


public class Product extends Record{
    
    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    
    private int quantity;
    private float price;

    
    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }
    


    @Override
    public String lineRepresentation() {
        return productID + "," + productName + "," + manufacturerName + "," + supplierName + "," + quantity + "," + price;    }

    @Override
    public String getSearchKey() {
        return productID;
    }
    
}
