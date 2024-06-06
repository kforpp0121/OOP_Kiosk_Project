import java.io.*;
import java.util.Vector;

public class CSVReader {

    public static Vector<String[]> readCSV(String csvFilePath) throws IOException {
        Vector<String[]> records = new Vector<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
        }
        return records;
    }
}


