package inventory.management.system;

import java.time.LocalDate;

public class CustomerProduct extends Record {
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public LocalDate getPurchaseDate(){
        return purchaseDate;
    }

    @Override
    public String lineRepresentation() {
        return customerSSN + "," + productID + "," + purchaseDate + "," + paid;
    }

    public boolean isPaid(){
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String getSearchKey() {
        return customerSSN + "," + productID + "," + purchaseDate;
    }
}
