package member;

public class ListMemberDisplay {
   public static void main(String[] args) {
      ListMember list = new ListMember();
      
      Member member1 = new Member("McDonald's", "14045");
      Vip vip1 = new Vip("PHD", "500 600");
      
      list.addMember(member1);
      list.addVip(vip1);
      
      list.printMemberList();
   }
}
