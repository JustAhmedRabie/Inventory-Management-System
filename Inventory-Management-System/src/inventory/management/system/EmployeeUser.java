
package inventory.management.system;


public class EmployeeUser extends Record {
    //employeeId, Name, Email, Address, PhoneNumber 
    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    

    @Override
    public String lineRepresentation() {
        return employeeId + "," + name + "," + email + "," + address + "," + phoneNumber;
    }

    @Override
    public String getSearchKey() {
        return employeeId; 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
