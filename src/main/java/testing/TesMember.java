package testing;

import controller.member.MemberAdapterJSON;
import controller.member.MemberAdapterXML;
import controller.member.MemberAdapterOBJ;
import controller.member.MemberController;
import model.Member;

import java.util.Objects;

public class TesMember {
    public static void main(String[] args) {
        MemberController dataIO = new MemberController();
        // dataIO.setDataIO(new MemberAdapterJSON("src/main/resources/data/tes_member.json"));
        // dataIO.setDataIO(new MemberAdapterXML("src/main/resources/data/tes_member.xml"));
        dataIO.setDataIO(new MemberAdapterOBJ("src/main/resources/data/tes_member"));

        Member c = Member.builder().id(1).point(0).transactions(0).name("chi").phone("123").active(true).build();
        Member c2 = Member.builder().id(3).point(1).transactions(2).name("chu").phone("123").active(false).build();

        dataIO.insertMember(c);
        dataIO.insertMember(c2);

        c.setTransactions(10);
        dataIO.updateMember(c);

        System.out.println(dataIO.getByID(1));
        Objects.requireNonNull(dataIO.getAllMember()).forEach(Member::polymorphTest);

        Objects.requireNonNull(dataIO.getAllMember()).forEach(System.out::println);
        dataIO.deleteMember(1);
    }
}
