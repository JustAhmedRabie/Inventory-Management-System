
package inventory.management.system;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ofPattern;


public class CustomerProductDatabase extends Database{

    public CustomerProductDatabase(String filename) {
        super(filename);
    }
    
    

    @Override
    public Record createRecordFrom(String line) {
        String split[] = line.split(line);
        LocalDate d = LocalDate.parse(split[2], DateTimeFormatter.ofPattern("dd-MM-yyyy")); //check
        CustomerProduct p = new CustomerProduct(split[0],split[1],d);
        p.setPaid(Boolean.parseBoolean(split[3]));
        return p;
    }
    
}
