package inventory.management.system;

public class EmployeeRole extends UserRole
{
    private ProductDatabase productDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole()
    {
        productDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        productDatabase.readFromFile();
        customerProductDatabase.readFromFile();
    }

    @Override
    public void logout()
    {
        productDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}
