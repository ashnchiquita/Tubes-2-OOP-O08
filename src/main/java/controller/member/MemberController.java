package controller.member;

import lombok.Getter;
import lombok.Setter;
import model.Member;

import java.util.List;

@Setter @Getter
public class MemberController {
    MemberIO dataIO;

    public Member getByID(int id) { return dataIO.getByID(id); }

    public List<Member> getAllMember()  { return dataIO.getAllMember(); }

    public boolean insertMember(Member data) { return dataIO.insertMember(data); }

    public boolean updateMember(Member newData) { return dataIO.updateMember(newData); }

    public boolean deleteMember(int id) { return dataIO.deleteMember(id); }

    // tambahin sesuai kebutuhan
}
