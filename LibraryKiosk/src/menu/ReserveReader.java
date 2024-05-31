package menu;

import java.io.*;
import java.util.*;

public class ReserveReader {
    
    public String getISBNById(String str) {
        Map<String, String> userInfo = getUserInfoById(str);
        return userInfo != null ? userInfo.get("RV_ISBN") : null;
    }

    public String getIDById(String str) {
        Map<String, String> userInfo = getUserInfoById(str);
        return userInfo != null ? userInfo.get("RV_ID") : null;
    }
    
    public String getDTById(String str) {
        Map<String, String> userInfo = getUserInfoById(str);
        return userInfo != null ? userInfo.get("RV_DT") : null;
    }
    
    public int countID(String id) {
        int count = 0;
        File csv = new File("rv_test.csv");
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[1].equals(id)) {
                    count++;
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
        return count;
    }
    
    private Map<String, String> getUserInfoById(String id) {
        Map<String, String> userInfo = null;
        File csv = new File("rv_test.csv");
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "UTF-8"));
            String[] headers = br.readLine().split(","); // 첫 번째 줄은 열 이름

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(id)) {
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