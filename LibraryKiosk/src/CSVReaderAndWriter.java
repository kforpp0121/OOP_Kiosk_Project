import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderAndWriter {

    public static List<String[]> readCSV(String csvFilePath) throws IOException {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
        }
        return records;
    }

    public static void writeCSV(String csvFilePath, List<String[]> data) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (String[] record : data) {
                bw.write(String.join(",", record));
                bw.newLine();
            }
        }
    }
}

