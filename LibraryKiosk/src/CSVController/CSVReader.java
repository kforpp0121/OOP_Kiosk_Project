package CSVController;

import java.io.*;
import java.util.Vector;

public class CSVReader {

    public static Vector<String[]> readCSV(String csvFilePath) throws IOException {
        Vector<String[]> records = new Vector<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line = br.readLine(); // 첫째줄 건너 뛰는 코드 추가 -> 다시 write할 때 첫째줄 별도로 써줌
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
        }
        return records;
    }
}


