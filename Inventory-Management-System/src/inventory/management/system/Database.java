package inventory.management.system;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;




public abstract class Database {
    protected ArrayList<Record> records;
    protected String filename;

    public Database(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }
    
    public abstract Record createRecordFrom(String line);
    
    public void readFromFile() {
        records.clear();
        try (BufferedReader f = new BufferedReader(new FileReader(filename))){
            
                String line;
                while((line = f.readLine()) != null){
                    records.add(createRecordFrom(line));
                }
                } catch (IOException ex) {
                 System.out.println("Error in reading file" + ex.getMessage());
            }
    }
    
    
    public boolean contains(String key ){
        int n = records.size();
        for(int i = 0;i<n;i++){
            if(records.get(i).getSearchKey().equals(key))
                return true;
        }
        return false;
    }
    
    public Record getRecord(String key){
        int n = records.size();
        for(int i = 0;i<n;i++){
            if(records.get(i).getSearchKey().equals(key))
                return records.get(i);
        }
        return null;
    }
    
    public void insertRecord(Record record){
        records.add(record);
    }
    
    public void deleteRecord(String key){
        int n = records.size();
        for (int i = 0; i < n; i++) {
            if (records.get(i).getSearchKey().equals(key)) {
                records.remove(i);
                break; 
            }
        }
    }
    
    
    public ArrayList<Record> returnAllRecords(){
        return records;
    }
    
    
    public void saveToFile(){
    
            try (BufferedWriter f = new BufferedWriter(new FileWriter(filename))) {
                int n = records.size();
                for(int i = 0;i<n;i++){
                    f.write(records.get(i).lineRepresentation() + "\n");
                }
            }catch (IOException ex) {
            System.out.println("Error in saving file" + ex.getMessage());
        }
            
  
    }
    

        
    }
    
    



