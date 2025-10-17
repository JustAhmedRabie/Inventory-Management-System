package inventory.management.system;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InventoryManagementSystem {

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
                    System.out.println("Goodbye!");
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
        String password;
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
                    String id;
                    do {
                        System.out.print("Enter Employee ID: ");
                        id = scanner.nextLine();
                        if (!Validation.isValidID(id, admin.getDatabase())) {
                            System.out.println("Invalid or duplicate ID.");
                        }
                    } while (!Validation.isValidID(id, admin.getDatabase()));

                    String name;
                    do {
                        System.out.print("Enter Employee Name: ");
                        name = scanner.nextLine();
                        if (!Validation.isValidName(name)) {
                            System.out.println("Invalid name.");
                        }
                    } while (!Validation.isValidName(name));

                    String email;
                    do {
                        System.out.print("Enter Employee Email: ");
                        email = scanner.nextLine();
                        if (!Validation.isValidEmail(email)) {
                            System.out.println("Invalid email.");
                        }
                    } while (!Validation.isValidEmail(email));

                    System.out.print("Enter Employee Address: ");
                    String address = scanner.nextLine();

                    String phone;
                    do {
                        System.out.print("Enter Employee Phone: ");
                        phone = scanner.nextLine();
                        if (!Validation.isValidNumber(phone)) {
                            System.out.println("Invalid phone number.");
                        }
                    } while (!Validation.isValidNumber(phone));

                    admin.addEmployee(id, name, email, address, phone);
                    System.out.println("Employee added.");
                    break;

                case "2":
                    String rId;
                    do {
                        System.out.print("Enter Employee ID to remove: ");
                        rId = scanner.nextLine();
                        if (admin.getDatabase().contains(rId)) {
                            admin.removeEmployee(rId);
                            System.out.println("Employee removed.");
                            break;
                        } else {
                            System.out.println("ID not found");
                        }
                    } while (!admin.getDatabase().contains(rId));
                    break;

                case "3":
                    EmployeeUser[] list = admin.getListOfEmployees();
                    System.out.println("Employees:");
                    for (EmployeeUser e : list) {
                        System.out.println(e.lineRepresentation());
                    }
                    break;

                case "4":
                    admin.logout();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
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

    System.out.println("Login successful! Welcome " + loggedInEmployee.getSearchKey());
    employee(loggedInEmployee);
}


    public static void employee(EmployeeUser loggedInEmployee) {
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

//            String choice = scanner.nextLine();
//
//            switch (choice) {
//                case "1":
//                    viewProducts(employeeRole);
//                    break;
//
//                case "2":
//                    addProduct(scanner, employeeRole);
//                    break;
//
//                case "3":
//                    viewPurchasingOperations(employeeRole);
//                    break;
//
//                case "4":
//                    purchaseProduct(scanner, employeeRole);
//                    break;
//
//                case "5":
//                    returnProduct(scanner, employeeRole);
//                    break;
//
//                case "6":
//                    applyPayment(scanner, employeeRole);
//                    break;
//
//                case "7":
//                    employeeRole.logout();
//                    System.out.println("Logged out successfully.");
//                    return;
//
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
        }
    }

//    private static void viewProducts(EmployeeRole employeeRole) {
//        System.out.println("\n--- List of Products ---");
//        Product[] products = employeeRole.getListOfProducts();
//        if (products.length == 0) {
//            System.out.println("No products available.");
//        } else {
//            for (Product product : products) {
//                System.out.println(product.lineRepresentation());
//            }
//        }
//    }
//
//    private static void addProduct(Scanner scanner, EmployeeRole employeeRole) {
//        System.out.println("\n--- Add New Product ---");
//
//        System.out.print("Enter Product ID: ");
//        String productID = scanner.nextLine();
//
//        System.out.print("Enter Product Name: ");
//        String productName = scanner.nextLine();
//
//        System.out.print("Enter Manufacturer Name: ");
//        String manufacturerName = scanner.nextLine();
//
//        System.out.print("Enter Supplier Name: ");
//        String supplierName = scanner.nextLine();
//
//        int quantity = 0;
//        boolean validQuantity = false;
//        while (!validQuantity) {
//            try {
//                System.out.print("Enter Quantity: ");
//                quantity = Integer.parseInt(scanner.nextLine());
//                validQuantity = true;
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid quantity. Please enter a valid number.");
//            }
//        }
//
//        float price = 0;
//        boolean validPrice = false;
//        while (!validPrice) {
//            try {
//                System.out.print("Enter Price: ");
//                price = Float.parseFloat(scanner.nextLine());
//                validPrice = true;
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid price. Please enter a valid number.");
//            }
//        }
//
//        employeeRole.addProduct(productID, productName, manufacturerName, supplierName, quantity, price);
//        System.out.println("Product added successfully!");
//    }
//
//    private static void viewPurchasingOperations(EmployeeRole employeeRole) {
//        System.out.println("\n--- Purchasing Operations ---");
//        CustomerProduct[] operations = employeeRole.getListOfPurchasingOperations();
//        if (operations.length == 0) {
//            System.out.println("No purchasing operations found.");
//        } else {
//            for (CustomerProduct operation : operations) {
//                System.out.println(operation.lineRepresentation());
//            }
//        }
//    }
//
//    private static void purchaseProduct(Scanner scanner, EmployeeRole employeeRole) {
//        System.out.println("\n--- Purchase Product ---");
//
//        System.out.print("Enter Customer SSN: ");
//        String customerSSN = scanner.nextLine();
//
//        System.out.print("Enter Product ID: ");
//        String productID = scanner.nextLine();
//
//        LocalDate purchaseDate = null;
//        boolean validDate = false;
//        while (!validDate) {
//            try {
//                System.out.print("Enter Purchase Date (YYYY-MM-DD): ");
//                String dateStr = scanner.nextLine();
//                purchaseDate = LocalDate.parse(dateStr);
//                validDate = true;
//            } catch (DateTimeParseException e) {
//                System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
//            }
//        }
//
//        boolean success = employeeRole.purchaseProduct(customerSSN, productID, purchaseDate);
//        if (success) {
//            System.out.println("Product purchased successfully!");
//        } else {
//            System.out.println("Failed to purchase product. Check if product exists and has sufficient quantity.");
//        }
//    }
//
//    private static void returnProduct(Scanner scanner, EmployeeRole employeeRole) {
//        System.out.println("\n--- Return Product ---");
//
//        System.out.print("Enter Customer SSN: ");
//        String customerSSN = scanner.nextLine();
//
//        System.out.print("Enter Product ID: ");
//        String productID = scanner.nextLine();
//
//        LocalDate purchaseDate = null;
//        boolean validPurchaseDate = false;
//        while (!validPurchaseDate) {
//            try {
//                System.out.print("Enter Purchase Date (YYYY-MM-DD): ");
//                String purchaseDateStr = scanner.nextLine();
//                purchaseDate = LocalDate.parse(purchaseDateStr);
//                validPurchaseDate = true;
//            } catch (DateTimeParseException e) {
//                System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
//            }
//        }
//
//        LocalDate returnDate = null;
//        boolean validReturnDate = false;
//        while (!validReturnDate) {
//            try {
//                System.out.print("Enter Return Date (YYYY-MM-DD): ");
//                String returnDateStr = scanner.nextLine();
//                returnDate = LocalDate.parse(returnDateStr);
//                validReturnDate = true;
//            } catch (DateTimeParseException e) {
//                System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
//            }
//        }
//
//        double refundAmount = employeeRole.returnProduct(customerSSN, productID, purchaseDate, returnDate);
//        if (refundAmount >= 0) {
//            System.out.println("Product returned successfully! Refund amount: $" + refundAmount);
//        } else {
//            System.out.println("Failed to return product. Check dates and product availability.");
//        }
//    }
//
//    private static void applyPayment(Scanner scanner, EmployeeRole employeeRole) {
//        System.out.println("\n--- Apply Payment ---");
//
//        System.out.print("Enter Customer SSN: ");
//        String customerSSN = scanner.nextLine();
//
//        LocalDate purchaseDate = null;
//        boolean validDate = false;
//        while (!validDate) {
//            try {
//                System.out.print("Enter Purchase Date (YYYY-MM-DD): ");
//                String dateStr = scanner.nextLine();
//                purchaseDate = LocalDate.parse(dateStr);
//                validDate = true;
//            } catch (DateTimeParseException e) {
//                System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
//            }
//        }
//
//        boolean success = employeeRole.applyPayment(customerSSN, purchaseDate);
//        if (success) {
//            System.out.println("Payment applied successfully!");
//        } else {
//            System.out.println("Failed to apply payment. Check customer SSN and purchase date.");
//        }
//
//    }

}
