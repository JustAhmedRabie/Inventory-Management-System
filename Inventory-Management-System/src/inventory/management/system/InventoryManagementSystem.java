package inventory.management.system;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InventoryManagementSystem {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== Inventory Management System =====");
            System.out.println("1- Admin");
            System.out.println("2- Employee");
            System.out.println("3- Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    adminLogin();
                    break;
                case "2":
                    employeeLogin();
                    break;
                case "3":
                    System.out.println("Exiting..");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    public static void adminLogin() {
        System.out.println("---------------------------------");
        String username;
        do {
            System.out.print("Enter your username: ");
            username = scanner.nextLine();
            if (!username.equals(AdminUser.getUsername())) {
                System.out.println("Invalid username.");
            }
        } while (!username.equals(AdminUser.getUsername()));
        do {
            System.out.print("Enter your password: ");
            username = scanner.nextLine();
            if (!username.equals(AdminUser.getPassword())) {
                System.out.println("Invalid password.");
            }
        } while (!username.equals(AdminUser.getPassword()));
        System.out.println("---------------------------------");
        admin();
    }

    public static void admin() {
        AdminRole admin = new AdminRole();

        while (true) {
            System.out.println("\n----------- Admin Menu ----------");
            System.out.println("1- Add Employee");
            System.out.println("2- Remove Employee");
            System.out.println("3- List Employees");
            System.out.println("4- Logout");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    AddEmployee(admin);
                    break;

                case "2":
                    RemoveEmployee(admin);
                    break;

                case "3":
                    GetEmployeeList(admin);
                    break;

                case "4":
                    admin.logout();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void GetEmployeeList(AdminRole admin) {
        EmployeeUser[] list = admin.getListOfEmployees();
        System.out.println("Employees:");
        for (EmployeeUser e : list) {
            System.out.println(e.lineRepresentation());
        }
    }

    private static void RemoveEmployee(AdminRole admin) {
        String id;
        System.out.print("Enter Employee ID to remove: ");
        id = scanner.nextLine();
        admin.removeEmployee(id);

    }

    private static void AddEmployee(AdminRole admin) {
        String id;
        System.out.print("Enter Employee ID: ");
        id = scanner.nextLine();
        id = id.trim();

        String name;
        System.out.print("Enter Employee Name: ");
        name = scanner.nextLine();

        String email;
        System.out.print("Enter Employee Email: ");
        email = scanner.nextLine();

        System.out.print("Enter Employee Address: ");
        String address = scanner.nextLine();

        String phone;
        System.out.print("Enter Employee Phone: ");
        phone = scanner.nextLine();

        admin.addEmployee(id, name, email, address, phone);

    }

    public static void employeeLogin() {
        System.out.println("---------------------------------");

        AdminRole admin = new AdminRole();
        EmployeeUser[] employees = admin.getListOfEmployees();

        if (employees == null || employees.length == 0) {
            System.out.println("No employees registered. Contact admin.");
            return;
        }

        String employeeId;
        EmployeeUser loggedInEmployee = null;

        do {
            System.out.print("Enter your Employee ID: ");
            employeeId = scanner.nextLine();

            for (EmployeeUser employee : employees) {
                if (employee.getSearchKey().equals(employeeId)) {
                    loggedInEmployee = employee;
                    break;
                }
            }

            if (loggedInEmployee == null) {
                System.out.println("Invalid ID. Try again.");
            }
        } while (loggedInEmployee == null);

        System.out.println("Login successful! Welcome " + loggedInEmployee.getName());
        employee();
    }

    public static void employee() {
        EmployeeRole employeeRole = new EmployeeRole();

        while (true) {
            System.out.println("\n----------- Employee Menu ----------");
            System.out.println("1- View Products");
            System.out.println("2- Add Product");
            System.out.println("3- View Purchasing Operations");
            System.out.println("4- Purchase Product");
            System.out.println("5- Return Product");
            System.out.println("6- Apply Payment");
            System.out.println("7- Logout");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewProducts(employeeRole);
                    break;

                case "2":
                    addProduct(employeeRole);
                    break;

                case "3":
                    viewPurchasingOperations(employeeRole);
                    break;

                case "4":
                    purchaseProduct(employeeRole);
                    break;

                case "5":
                    returnProduct(employeeRole);
                    break;
                case "6":
                    applyPayment(employeeRole);
                    break;

                case "7":
                    employeeRole.logout();
                    System.out.println("Logged out successfully.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewProducts(EmployeeRole employeeRole) {
        System.out.println("\n--- List of Products ---");
        Product[] products = employeeRole.getListOfProducts();
        if (products.length == 0) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                System.out.println(product.lineRepresentation());
            }
        }
    }

    private static void addProduct(EmployeeRole employeeRole) {

        ProductDatabase productsDatabase = new ProductDatabase("Products.txt");
        productsDatabase.readFromFile();
        System.out.println("\n--- Add New Product ---");
        System.out.print("Enter Product ID: ");
        String productID = scanner.nextLine();

        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter Manufacturer Name: ");
        String manufacturerName = scanner.nextLine();

        System.out.print("Enter Supplier Name: ");
        String supplierName = scanner.nextLine();

        int quantity = 0;

        System.out.print("Enter Quantity: ");
        quantity = Integer.parseInt(scanner.nextLine());

        float price = 0;

        System.out.print("Enter Price: ");
        price = Float.parseFloat(scanner.nextLine());

        employeeRole.addProduct(productID, productName, manufacturerName, supplierName, quantity, price);
    }

    private static void viewPurchasingOperations(EmployeeRole employeeRole) {
        System.out.println("\n--- Purchasing Operations ---");

        Record[] operations = employeeRole.getListOfPurchasingOperations();
        if (operations == null || operations.length == 0) {
            System.out.println("No purchasing operations found.");
        } else {
            for (Record operation : operations) {
                System.out.println(operation.lineRepresentation());
            }
        }
    }

    private static void purchaseProduct(EmployeeRole employeeRole) {
        System.out.println("\n--- Purchase Product ---");

        System.out.print("Enter Customer SSN: ");
        String customerSSN = scanner.nextLine();

        System.out.print("Enter Product ID: ");
        String productID = scanner.nextLine();

        LocalDate purchaseDate = null;
        boolean validDate = false;
        while (!validDate) {
            try {
                System.out.print("Enter Purchase Date (DD-MM-YYYY): ");
                String dateStr = scanner.nextLine();
                purchaseDate = LocalDate.parse(dateStr, DATE_FORMATTER);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use DD-MM-YYYY format.");
            }
        }

        boolean success = employeeRole.purchaseProduct(customerSSN, productID, purchaseDate);
        if (success) {
            System.out.println("Product purchased successfully!");
        } else {
            System.out.println("Failed to purchase product. Check if product exists and has sufficient quantity.");
        }
    }

    private static void returnProduct(EmployeeRole employeeRole) {
        System.out.println("\n--- Return Product ---");

        System.out.print("Enter Customer SSN: ");
        String customerSSN = scanner.nextLine();

        System.out.print("Enter Product ID: ");
        String productID = scanner.nextLine();

        LocalDate purchaseDate = null;
        boolean validPurchaseDate = false;
        while (!validPurchaseDate) {
            try {
                System.out.print("Enter Purchase Date (DD-MM-YYYY): ");
                String purchaseDateStr = scanner.nextLine();
                purchaseDate = LocalDate.parse(purchaseDateStr, DATE_FORMATTER);
                validPurchaseDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use DD-MM-YYYY format.");
            }
        }

        LocalDate returnDate = null;
        boolean validReturnDate = false;
        while (!validReturnDate) {
            try {
                System.out.print("Enter Return Date (DD-MM-YYYY): ");
                String returnDateStr = scanner.nextLine();
                returnDate = LocalDate.parse(returnDateStr, DATE_FORMATTER);
                validReturnDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use DD-MM-YYYY format.");
            }
        }

        double refundAmount = employeeRole.returnProduct(customerSSN, productID, purchaseDate, returnDate);
        if (refundAmount >= 0) {
            System.out.println("Product returned successfully! Refund amount: $" + refundAmount);
        } else {
            System.out.println("Failed to return product. Check dates and product availability.");
        }
    }

    private static void applyPayment(EmployeeRole employeeRole) {
        System.out.println("\n--- Apply Payment ---");

        System.out.print("Enter Customer SSN: ");
        String customerSSN = scanner.nextLine();

        LocalDate purchaseDate = null;
        boolean validDate = false;
        while (!validDate) {
            try {
                System.out.print("Enter Purchase Date (DD-MM-YYYY): ");
                String dateStr = scanner.nextLine();
                purchaseDate = LocalDate.parse(dateStr, DATE_FORMATTER);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use DD-MM-YYYY format.");
            }
        }

        boolean success = employeeRole.applyPayment(customerSSN, purchaseDate);
        if (success) {
            System.out.println("Payment applied successfully!");
        } else {
            System.out.println("Failed to apply payment. Check customer SSN and purchase date.");
        }
    }
}
