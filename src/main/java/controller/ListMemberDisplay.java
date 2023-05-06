package controller;

import model.Member;
import model.VIP;

public class ListMemberDisplay {
   public static void main(String[] args) {
      ListMember list = new ListMember();

      Member member1 = Member.builder().point(1).transactions(1).active(true).id(1).build();
      VIP vip1 = VIP.builder().point(1).transactions(1).active(true).id(1).build();
      
      list.addMember(member1);
      list.addVip(vip1);
      
      list.printMemberList();
   }
}
