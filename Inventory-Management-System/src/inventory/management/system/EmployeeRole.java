package inventory.management.system;

import static inventory.management.system.InventoryManagementSystem.scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class EmployeeRole extends UserRole {

    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity) {

        Product product = new Product(productID, productName, manufacturerName, supplierName, quantity, 0);
        productsDatabase.insertRecord(product);
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        if (!Validation.isNonEmpty(productID) || !Validation.isValidID(productID, 'P')) {
            System.out.println("Invalid Product ID.");
            return;
        }
        if (productsDatabase.contains(productID)) {
            System.out.println("Product ID already exists.");
            return;
        }

        if (!Validation.isValidName(productName)) {
            System.out.println("Invalid product name. Use letters, spaces, or apostrophes only.");
            return;
        }
        if (!Validation.isValidName(manufacturerName)) {
            System.out.println("Invalid manufacturer name. Use letters, spaces, or apostrophes only.");
            return;
        }
        if (!Validation.isValidName(supplierName)) {
            System.out.println("Invalid supplier name. Use letters, spaces, or apostrophes only.");
            return;
        }
        if (quantity < 0) {
            System.out.println("Invalid quantity. It cannot be negative.");
            return;
        }
        if (price < 0) {
            System.out.println("Invalid price. It cannot be negative.");
            return;
        }
        productID = productID.trim();
        productName = productName.trim();
        manufacturerName = manufacturerName.trim();
        supplierName = supplierName.trim();
        Product product = new Product(productID, productName, manufacturerName, supplierName, quantity, price);
        productsDatabase.insertRecord(product);
        System.out.println("Product added successfully!");

    }

    public Product[] getListOfProducts() {
        ArrayList<Record> records = productsDatabase.returnAllRecords();
        Product[] products = new Product[records.size()];
        for (int i = 0; i < records.size(); i++) {
            products[i] = (Product) records.get(i);
        }
        return products;
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        ArrayList<Record> records = customerProductDatabase.returnAllRecords();
        CustomerProduct[] list = new CustomerProduct[records.size()];
        for (int i = 0; i < records.size(); i++) {
            list[i] = (CustomerProduct) records.get(i);
        }
        return list;
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        if (!productsDatabase.contains(productID)) {
            return false;
        }

        Product product = (Product) productsDatabase.getRecord(productID);

        if (product.getQuantity() == 0) {
            return false;
        }

        product.setQuantity(product.getQuantity() - 1);

        CustomerProduct customerProduct = new CustomerProduct(customerSSN, productID, purchaseDate);
        customerProductDatabase.insertRecord(customerProduct);

        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();

        return true;
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {

        if (returnDate.isBefore(purchaseDate)) {
            return -1;
        }

        if (!productsDatabase.contains(productID)) {
            return -1;
        }

        String key = customerSSN + "," + productID + "," + purchaseDate;
        if (!customerProductDatabase.contains(key)) {
            return -1;
        }
        if (returnDate.isAfter(purchaseDate.plusDays(14))) {
            return -1;
        }

        Product product = (Product) productsDatabase.getRecord(productID);
        product.setQuantity(product.getQuantity() + 1);
        customerProductDatabase.deleteRecord(key);

        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();

        return product.getPrice();
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        ArrayList<Record> list = customerProductDatabase.returnAllRecords();
        boolean updated = false;

        for (Record record : list) {
            CustomerProduct cp = (CustomerProduct) record;

            if (cp.getCustomerSSN().equals(customerSSN)
                    && cp.getPurchaseDate().equals(purchaseDate)) {

                if (!cp.isPaid()) {
                    cp.setPaid(true);
                    updated = true;
                }
            }
        }

        if (updated) {
            customerProductDatabase.saveToFile();
            return true;
        }

        return false;
    }

    @Override
    public void logout() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}
