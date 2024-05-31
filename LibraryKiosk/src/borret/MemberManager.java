package borret;// MemberManager.java
import java.util.HashMap;
import java.util.Map;

public class MemberManager {
    private Map<String, Member> members = new HashMap<>();
    private MemberLoader memberLoader;

    public MemberManager(MemberLoader memberLoader) {
        this.memberLoader = memberLoader;
        this.members = memberLoader.loadMembers();
    }

    public void saveMembers() {
        memberLoader.saveMembers(members);
    }
}