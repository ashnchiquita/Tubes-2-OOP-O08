package model;

import controller.customer.MemberAdapterJSON;
import controller.customer.MemberIO;
import controller.customer.VIPAdapterJSON;
import controller.customer.VIPIO;

import java.util.Objects;

public class TesVIPJSON {
    public static void main(String[] args) {
        VIPIO dataIO = new VIPAdapterJSON("tesVIPJSON.json");
        // Customer c = Customer.builder().id(1).build();
        VIP m = VIP.builder().id(2).name("chigans").point(0).phone("12345").build();

        dataIO.insertVIP(m);

        System.out.println(dataIO.getByID(2));
        Objects.requireNonNull(dataIO.getAllVIP()).forEach(Member::polymorphTest);
        Objects.requireNonNull(dataIO.getAllVIP()).forEach(System.out::println);
    }
}
