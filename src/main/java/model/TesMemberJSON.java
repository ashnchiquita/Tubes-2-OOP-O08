package model;

import controller.customer.CustomerAdapterJSON;
import controller.customer.CustomerIO;
import controller.customer.MemberAdapterJSON;
import controller.customer.MemberIO;

import java.util.Objects;

public class TesMemberJSON {
    public static void main(String[] args) {
        MemberIO dataIO = new MemberAdapterJSON("tesMemberJSON.json");
        // Customer c = Customer.builder().id(1).build();
        Member m = Member.builder().id(2).name("chigans").point(0).phone("12345").build();

        dataIO.insertMember(m);

        System.out.println(dataIO.getByID(2));
        Objects.requireNonNull(dataIO.getAllMember()).forEach(Member::polymorphTest);
        Objects.requireNonNull(dataIO.getAllMember()).forEach(System.out::println);
    }
}
