// Member.java
public class Member {
    String name;
    String id;
    String password;
    String birth;
    String phoneNumber;

    public Member(String name, String id, String password, String birth, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.join(",", name, id, password, birth, phoneNumber);
    }
}