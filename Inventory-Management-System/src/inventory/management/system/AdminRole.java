
package inventory.management.system;

import java.util.ArrayList;


public class AdminRole extends UserRole {
    
    private EmployeeUserDatabase database;

    public EmployeeUserDatabase getDatabase() {
        return database;
    }

    public AdminRole(){
        database = new EmployeeUserDatabase("Employees.txt");
        database.readFromFile();
    }
    
    
     public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
         if(database.contains(employeeId))
         {
             System.out.println(employeeId + " Already exsists.");
             return;
         }
         
         if(!Validation.isValidID(employeeId, 'E'))
         {
             System.out.println("Invalid Employee ID. (format:E####)");
             return;
         }
         
         if(!Validation.isValidEmail(email))
         {
             System.out.println("Invalid Email.");
             return;
         }
         
         if(!Validation.isValidName(name))
         {
             System.out.println("Invalid Name.");
             return;
         }
         
         if(!Validation.isValidNumber(phoneNumber))
         {
             System.out.println("Invalid phonenumber.");
             return;
         }
         
         
         name = name.trim();
         address = address.trim();
         email = email.trim();
         employeeId = employeeId.trim();
         phoneNumber = phoneNumber.trim();
         
         
         database.insertRecord(new EmployeeUser(employeeId,name,email,address,phoneNumber));
         System.out.println(employeeId + " successfully added.");
     }
    
     
     public EmployeeUser[] getListOfEmployees(){
         ArrayList<Record> r = database.returnAllRecords();
         int n = r.size();
         EmployeeUser[] arr = new EmployeeUser[n];
         for(int i=0;i<n;i++){
             arr[i] = (EmployeeUser) r.get(i);
         }
         return arr;
     }
    
     
     public void removeEmployee(String key){
         if(database.contains(key)){
         database.deleteRecord(key);
         System.out.println(key + " successfully removed.");
         }
         else
             System.out.println("Employee ID not found.");
     }
     

    @Override
    public void logout() {
        database.saveToFile();
    }
    
    
    
}
