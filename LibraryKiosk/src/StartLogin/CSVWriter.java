package StartLogin;

//  0606
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class CSVWriter {

    public static void writeUserData(Vector<String> userData) {
        String csvFilePath = "userdata.csv";

        try (FileWriter writer = new FileWriter(csvFilePath, true)) {
            StringBuilder sb = new StringBuilder();
            for (String data : userData) {
                sb.append(data).append(",");
            }
            sb.deleteCharAt(sb.length() - 1); // 마지막 쉼표 제거
            sb.append("\n");
            writer.append(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Vector<String> userData = new Vector<>();
        userData.add("username");
        userData.add("password");
        userData.add("dob");
        userData.add("phoneNumber");
        userData.add("Name");

        writeUserData(userData);
    }
}
