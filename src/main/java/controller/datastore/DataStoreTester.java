package controller.datastore;

import model.Member;
import model.FixedBill;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class DataStoreTester {
  public static void main(String[] args) {
    Member memberData = Member.builder().id(40).name("Kim Jisoo").phone("08123").point(10).transactions(14)
        .build();

    XMLAdapter<Member> memberAdapter = new XMLAdapter<>("test.xml",
        Member.class);
    memberAdapter.insert(memberData);
    List<Member> memberList = memberAdapter.findAll();
    memberList.stream().forEach(member -> System.out.println(member));
    // Member m =
    // Member.builder().id(2).name("chi").point(0).phone("12345").build();

    // FixedBill f = FixedBill.builder().cust(m).keranjang(new
    // ArrayList<>()).id(2).date(LocalDate.now())
    // .time(LocalTime.now()).build();
    // XMLAdapter<FixedBill> billAdapter = new XMLAdapter<>("test.xml",
    // FixedBill.class);
    // billAdapter.insert(f);
    // billAdapter.findAll().stream().forEach(bill -> System.out.println(bill));
  }
}
