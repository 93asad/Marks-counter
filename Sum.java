import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class Sum {
    private static final String KEY_VALUE_DELIMITER = ",";
    private static final String MESSAGE = "The total for %s is %.0f.";
    private static final String ARGUMENT_NULL_ERROR = 
        "Please provide a path of a file to be processed as an argument";
    private static final String FILE_NOT_FOUND_ERROR = "Invalid filename/filepath";
    private static HashTable hashMap;

    public static void main(String[] args) {
        try 
        {
            calculate(args[0]); //filepath as first argument
            printToScreen();
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println(ARGUMENT_NULL_ERROR);
        }
    }

    private static void calculate(String filepath) {
        try {    
            FileReader in = new FileReader(filepath);
            BufferedReader br = new BufferedReader(in);
            String keyValuePair; //complete line in file (key, value)
            String key;
            double value;
            hashMap = new HashTable();
            while ((keyValuePair = br.readLine()) != null) {
                String[] keyAndValue = keyValuePair.split(KEY_VALUE_DELIMITER);

                key = keyAndValue[0].trim();
                value = Double.parseDouble(keyAndValue[1].trim());

                hashMap.put(key, value);
            }
            in.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND_ERROR);
            System.exit(1);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void printToScreen() {
        for (Object key : hashMap.keySet().toArray()){
            System.out.println(String.format(MESSAGE, key, hashMap.get(key)));
        }
    }

    @SuppressWarnings("serial")
    private static class HashTable extends HashMap<String, Double> {
        public void put(String key, double value) {
            double newVal = (this.get(key) == null) ? value : this.get(key) + value;
            super.put(key, newVal);
        }
    }
}