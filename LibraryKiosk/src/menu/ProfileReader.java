package menu;

import java.io.*;
import java.util.*;

public class ProfileReader {
	
    public String getNumById(String id) {
    	String num = null;
        Vector<Map<String, String>> userInfo = getUserInfoById(id);
        
        for (Map<String, String> profInfo : userInfo) {
        	num = profInfo.get("PN");
            break;
        }
        
        return num;
    }

    public String getBirthdateById(String id) {
    	String birth = null;
        Vector<Map<String, String>> userInfo = getUserInfoById(id);
        
        for (Map<String, String> profInfo : userInfo) {
        	birth = profInfo.get("BIRTH");
        	break;
             }
        
        return birth;
    }
    
    public String getNameById(String id) {
    	String name = null;
        Vector<Map<String, String>> userInfo = getUserInfoById(id);
        
        for (Map<String, String> profInfo : userInfo) {
        	name = profInfo.get("NM");
        	break;
             }
        
        return name;
    }

    private Vector<Map<String, String>> getUserInfoById(String id) {
        Vector<Map<String, String>> userInfo = null;
        File csv = new File("mem_test.csv");
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "UTF-8"));
            String[] headers = br.readLine().split(",");

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> profInfo = new HashMap<>();
                if (values[1].equals(id)) {
                    userInfo = new Vector<>();
                    for (int i = 0; i < headers.length; i++) {
                    	profInfo.put(headers[i], values[i]);
                        userInfo.add(profInfo);
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
