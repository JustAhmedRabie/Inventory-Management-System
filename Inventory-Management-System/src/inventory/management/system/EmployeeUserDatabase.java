
package inventory.management.system;
import java.util.ArrayList;

public class EmployeeUserDatabase extends Database {

    public EmployeeUserDatabase(String filename) {
        super(filename);
    }
    


    @Override
    public Record createRecordFrom(String line) {
        String split[] = line.split(",");
        return new EmployeeUser(split[0],split[1],split[2],split[3],split[4]);
    }
    
}
