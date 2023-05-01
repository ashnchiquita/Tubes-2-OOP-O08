package controller.member;

import java.util.List;

import model.Member;

public interface MemberDataIO {
  public Member getByID(int id);

  public List<Member> getAllMembers();

  public boolean insertMember(Member data);

  public boolean updateMember(int id, Member newData);

  public boolean deleteMember(int id);
}
