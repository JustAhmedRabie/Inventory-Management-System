package inventory.management.system;

import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeRole extends UserRole
{
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole()
    {
        productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        productsDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }
    
    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity)
    {

        Product product = new Product(productID, productName, manufacturerName, supplierName, quantity, 0);
        productsDatabase.insertRecord(product);
    }
    
    
    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price)
    {

        Product product = new Product(productID, productName, manufacturerName, supplierName, quantity, price);
        productsDatabase.insertRecord(product);
    }

    public Product[] getListOfProducts()
    {
        ArrayList<Record> records = productsDatabase.returnAllRecords();
        Product[] products = new Product[records.size()];
        for (int i = 0; i < records.size(); i++)
        {
            products[i] = (Product) records.get(i);
        }
        return products;
    }

    public CustomerProduct[] getListOfPurchasingOperations()
    {
        ArrayList<Record> records = customerProductDatabase.returnAllRecords();
        CustomerProduct[] list = new CustomerProduct[records.size()];
        for (int i = 0; i < records.size(); i++)
        {
            list[i] = (CustomerProduct) records.get(i);
        }
        return list;
    }


    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate)
    {
        if (!productsDatabase.contains(productID))
        {
            return false;
        }

        Product product = (Product)productsDatabase.getRecord(productID);

        if (product.getQuantity() == 0)
        {
            return false;
        }

        product.setQuantity(product.getQuantity() - 1);

        CustomerProduct customerProduct = new CustomerProduct(customerSSN, productID, purchaseDate);
        customerProductDatabase.insertRecord(customerProduct);

        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();

        return true;
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate)
    {

        if (returnDate.isBefore(purchaseDate))
        {
            return -1;
        }

        if (!productsDatabase.contains(productID))
        {
            return -1;
        }

        String key = customerSSN + "," + productID + "," + purchaseDate;
        if (!customerProductDatabase.contains(key))
        {
            return -1;
        }
        if (returnDate.isAfter(purchaseDate.plusDays(14))) return -1;

        Product product = (Product)productsDatabase.getRecord(productID);
        product.setQuantity(product.getQuantity() + 1);
        customerProductDatabase.deleteRecord(key);

        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();

        return product.getPrice();
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate)
    {
        ArrayList<Record> list = customerProductDatabase.returnAllRecords();
        boolean updated = false;

        for (Record record : list)
        {
            CustomerProduct cp = (CustomerProduct) record;

            if (cp.getCustomerSSN().equals(customerSSN) &&
                    cp.getPurchaseDate().equals(purchaseDate))
            {

                if (!cp.isPaid())
                {
                    cp.setPaid(true);
                    updated = true;
                }
            }
        }

        if (updated)
        {
            customerProductDatabase.saveToFile();
            return true;
        }

        return false;
    }

    @Override
    public void logout()
    {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}
