package inventory.management.system;

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
                    employee(scanner);
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

    public static void adminLogin(){
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
                    do{
                    System.out.print("Enter Employee ID to remove: ");
                    rId = scanner.nextLine();
                    if(admin.getDatabase().contains(rId)){
                    admin.removeEmployee(rId);
                    System.out.println("Employee removed.");
                    break;}
                    else{
                        System.out.println("ID not found");
                    }
                    }while (!admin.getDatabase().contains(rId));
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

    public static void employee(Scanner scanner){}
    
}
