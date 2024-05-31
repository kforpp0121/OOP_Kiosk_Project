import java.io.*;
import java.util.*;

public class ProfileReader {
	
    public String getNumById(String id) {
        Map<String, String> userInfo = getUserInfoById(id);
        return userInfo != null ? userInfo.get("PN") : null;
    }

    public String getBirthdateById(String id) {
        Map<String, String> userInfo = getUserInfoById(id);
        return userInfo != null ? userInfo.get("BIRTH") : null;
    }
    
    public String getNameById(String id) {
        Map<String, String> userInfo = getUserInfoById(id);
        return userInfo != null ? userInfo.get("NM") : null;
    }

    private Map<String, String> getUserInfoById(String id) {
        Map<String, String> userInfo = null;
        File csv = new File("mem_test.csv");
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "UTF-8"));
            String[] headers = br.readLine().split(",");

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(id)) {
                    userInfo = new HashMap<>();
                    for (int i = 0; i < headers.length; i++) {
                        userInfo.put(headers[i], values[i]);
                    }
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userInfo;
    }
}
