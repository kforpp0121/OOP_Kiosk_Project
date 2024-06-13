package borret;

import java.util.Vector;

public class MemberManager {
    private Vector<Member> members;
    private BBB memberLoader;

    public MemberManager(BBB memberLoader) {
        this.memberLoader = memberLoader;
        this.members = memberLoader.getMembers();
    }

    public Member getMemberById(String id) {
        return memberLoader.getMemberById(id);
    }
}