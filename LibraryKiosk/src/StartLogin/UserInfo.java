package StartLogin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class UserInfo {
    private String username;
    private String name;
    private String dob;
    private String phoneNumber;
    private String password;

    // 생성자와 getter/setter 메서드 추가

    public UserInfo(String username, String name, String dob, String phoneNumber) {
        this.username = username;
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
    }

    // Getter와 Setter 메서드
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public static UserInfo getUserInfo(String username, String password) {
        String csvFilePath = "LibraryKiosk/csv/userdata.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                String savedUsername = userData[0];
                String savedPassword = userData[1];
                String dob = userData[2];
                String phoneNumber = userData[3];
                String name = userData[4];
                if (username.equals(savedUsername)&&password.equals(savedPassword)) {
                    return new UserInfo(username, name, dob, phoneNumber);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // 해당하는 회원 정보가 없을 경우 null 반환
    }
}
