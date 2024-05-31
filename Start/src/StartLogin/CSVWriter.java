package StartLogin;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    public static void writeUserData(String username, String password, String dob, String phoneNumber, String Name) {
        String csvFilePath = "userdata.csv";

        try (FileWriter writer = new FileWriter(csvFilePath, true)) {
            writer.append(username)
                    .append(",")
                    .append(password)
                    .append(",")
                    .append(dob)
                    .append(",")
                    .append(phoneNumber)
                    .append(",")
                    .append(Name)
                    .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
	
