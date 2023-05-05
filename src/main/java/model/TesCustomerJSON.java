package model;

import controller.customer.CustomerAdapterJSON;
import controller.customer.CustomerIO;
import controller.fixedbill.FixedBillAdapterJSON;
import controller.fixedbill.FixedBillIO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class TesCustomerJSON {
    public static void main(String[] args) {
        CustomerIO dataIO = new CustomerAdapterJSON("tesCustJSON.json");
        Customer c = Customer.builder().id(1).build();
        // Member m = Member.builder().id(2).name("chigans").point(0).phone("12345").build();

        dataIO.insertCustomer(c);

        System.out.println(dataIO.getByID(1));
        //Objects.requireNonNull(dataIO.getAllCustomer()).forEach(el -> el.getCust().polymorphTest());
        Objects.requireNonNull(dataIO.getAllCustomer()).forEach(System.out::println);
    }
}
