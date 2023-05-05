package controller.customer;

import model.Customer;
import model.Member;

import java.util.List;

public interface MemberIO {
    public Member getByID(int id);

    public List<Member> getAllMember();

    public boolean insertMember(Member data);

    public boolean updateMember(Member newData);

    public boolean deleteMember(int id);
}
