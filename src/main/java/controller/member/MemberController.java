package controller.member;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;
import model.Member;

public class MemberController {
  @Setter
  MemberDataIO dataIO;

  public void showByID(int id) {
    System.out.println(dataIO.getByID(id));
  }

  public void showAllMember() {
    List<Member> memberList = new ArrayList<>();
    memberList = dataIO.getAllMembers();
    memberList.stream().forEach(member -> System.out.println(member));
  }

  public void addMember() {
    Member newMember = Member.builder().id(11).name("Kim Jisoo").phone("123").point(100).transactions(10).build();
    System.out.println(dataIO.insertMember(newMember));
  }

  public void updateMember(int id) {
    Member newDataMember = Member.builder().id(id).name("Kim Jisoo Unnieeeeeeeeeeeee").phone("123").point(100)
        .transactions(10)
        .build();
    System.out.println(dataIO.updateMember(11, newDataMember));
  }

  public void deleteMember(int id) {
    System.out.println(dataIO.deleteMember(id));
  }
}
