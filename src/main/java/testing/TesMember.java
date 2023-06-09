package testing;

import controller.MainController;
import controller.member.*;
import model.Member;

import java.util.Objects;

public class TesMember {
  public static void main(String[] args) {
    MainController controller = new MainController();
     controller.setMemberDataIO(new
     MemberAdapterJSON("src/main/resources/data/member.json"));
    // controller.setMemberDataIO(new
    // MemberAdapterXML("src/main/resources/data/tes_member.xml"));
    // controller.setMemberDataIO(new
    // MemberAdapterOBJ("src/main/resources/data/tes_member"));
//    controller.setMemberDataIO(
//        new MemberAdapterSQL("jdbc:mysql://localhost:3306/testing_oop", "rma1403", ""));

    Member c = Member.builder().id().point(0).transactions(0).name("chi").phone("123").active(true).build();
    Member c2 = Member.builder().id().point(1).transactions(2).name("chu").phone("123").active(false).build();

     controller.getMemberDataIO().insert(c);
     controller.getMemberDataIO().insert(c2);

    // c.setTransactions(10);
    // controller.getMemberDataIO().update(c);

    System.out.println(controller.getMemberDataIO().getByID(4));
    Objects.requireNonNull(controller.getMemberDataIO().getAll()).forEach(Member::polymorphTest);

    Objects.requireNonNull(controller.getMemberDataIO().getAll()).forEach(System.out::println);
    controller.getMemberDataIO().delete(4);
  }
}
