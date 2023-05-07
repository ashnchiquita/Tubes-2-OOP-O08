package testing;

import controller.MainController;
// import controller.customer.*;
import model.Customer;
import model.Member;
import model.VIP;

public class TesCust {
    public static void main(String[] args) {
        MainController controller = new MainController();
        Customer c = Customer.builder().id().build();
        Customer c2 = Customer.builder().id().build();
        controller.getCustomerDataIO().insert(c);
        controller.getCustomerDataIO().insert(c2);
        // Member c =
        // Member.builder().id().name("ayam").phone("123").point(0).transactions(0).active(true).build();
        // Member c2 =
        // Member.builder().id().name("ayam2").phone("1234").point(0).transactions(0).active(true).build();
        // VIP c =
        // VIP.builder().id().name("ayam").phone("123").point(0).transactions(0).active(true).build();
        // VIP c2 =
        // VIP.builder().id().name("ayam2").phone("1234").point(0).transactions(0).active(true).build();

        Customer t1 = Customer.builder().id().build();
        Member t2 = Member.builder().id().name("ayamaaa").phone("123").point(0).transactions(0).active(true).build();
        VIP t3 = VIP.builder().id().name("ayamadfasfa").phone("123").point(0).transactions(0).active(true).build();

        controller.getCustomerDataIO().insert(t1);
        controller.getMemberDataIO().insert(t2);
        controller.getVIPDataIO().insert(t3);
        // controller.setMemberDataIO(new
        // MemberAdapterJSON("src/main/resources/data/member.json"));
        // System.out.println("-------------------------");
        // System.out.println("Cust:" + Customer.count);
        // System.out.println("Member:" + Member.maxMemID);
        // System.out.println("VIP:" + VIP.maxVIPID);
        // controller.setVIPDataIO(new
        // VIPAdapterJSON("src/main/resources/data/vip.json"));
        // System.out.println("-------------------------");
        // System.out.println("Cust:" + Customer.count);
        // System.out.println("Member:" + Member.maxMemID);
        // System.out.println("VIP:" + VIP.maxVIPID);
        // controller.setCustomerDataIO(new
        // CustomerAdapterJSON("src/main/resources/data/customer.json"));
        // System.out.println("-------------------------");
        // System.out.println("Cust:" + Customer.count);
        // System.out.println("Member:" + Member.maxMemID);
        // System.out.println("VIP:" + VIP.maxVIPID);
    }

}
