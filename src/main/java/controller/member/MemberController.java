package controller.member;

import java.util.*;

import lombok.Setter;
import model.Member;

public class MemberController {
  @Setter
  MemberDataIO dataIO;

  public List<Member> getAllMember() {
    List<Member> memberList = new ArrayList<>();
    memberList = dataIO.getAllMembers();
    return memberList;
  }

  public boolean addNewMember(String name, String phone) {
    List<Member> memberList = dataIO.getAllMembers();
    int id = memberList.size() + 1;

    Member newMember = Member.builder().id(id).name(name).phone(phone).point(0).transactions(1).build();
    return dataIO.insertMember(newMember);
  }

  public boolean addTransactions(int id) {
    Member member = dataIO.getByID(id);
    if (member == null) {
      return false;
    }

    member.setTransactions(member.getTransactions() + 1);
    return dataIO.updateMember(id, member);
  }

  public boolean addPoint(int id, int point) {
    Member member = dataIO.getByID(id);
    if (member == null) {
      return false;
    }

    member.setPoint(member.getPoint() + 1);
    return dataIO.updateMember(id, member);
  }

  public boolean updateMemberData(int id, String name, String phone) {
    Member member = dataIO.getByID(id);
    if (member == null) {
      return false;
    }

    member.setName(name);
    member.setPhone(phone);
    return dataIO.updateMember(id, member);
  }
}
