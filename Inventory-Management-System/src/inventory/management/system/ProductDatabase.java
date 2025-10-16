
package inventory.management.system;


public class ProductDatabase extends Database {

    public ProductDatabase(String filename) {
        super(filename);
    }

    
    
    @Override
    public Record createRecordFrom(String line) {
        String split[] = line.split(",");
        return new Product(split[0],split[1],split[2],split[3]
                ,Integer.parseInt(split[4]),Float.parseFloat(split[5]));
        
    }
    
}
