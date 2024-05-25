// MemberLoader.java
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MemberLoader {
    private final String MEMBERS_FILE = "member.csv";

    public Map<String, Member> loadMembers() {
        Map<String, Member> members = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(MEMBERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals("NM")) continue;  // Header °Ç³Ê¶Ü
                String name = values[0];
                String id = values[1];
                String password = values[2];
                String birth = values[3];
                String phoneNumber = values[4];
                members.put(id, new Member(name, id, password, birth, phoneNumber));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return members;
    }

    public void saveMembers(Map<String, Member> members) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(MEMBERS_FILE))) {
            bw.write("NM,ID,PW,BIRTH,PN\n");
            for (Member member : members.values()) {
                bw.write(member.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}