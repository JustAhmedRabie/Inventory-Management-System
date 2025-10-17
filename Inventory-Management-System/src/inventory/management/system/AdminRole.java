
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
         database.insertRecord(new EmployeeUser(employeeId,name,email,address,phoneNumber));
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
         database.deleteRecord(key);
     }
     

    @Override
    public void logout() {
        database.saveToFile();
    }
    
    
    
}
