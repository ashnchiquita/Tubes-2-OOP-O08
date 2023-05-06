package controller.member;

import lombok.Getter;
import lombok.Setter;
import model.Member;
import controller.*;

import java.util.List;

@Setter
@Getter
public class MemberController implements GenericController<Member> {
    MemberIO dataIO;

    public Member getByID(int id) {
        return dataIO.getByID(id);
    }

    public List<Member> getAll() {
        return dataIO.getAllMember();
    }

    public boolean insert(Member data) {
        return dataIO.insertMember(data);
    }

    public boolean update(Member newData) {
        return dataIO.updateMember(newData);
    }

    public boolean delete(int id) {
        return dataIO.deleteMember(id);
    }

    // tambahin sesuai kebutuhan
}
