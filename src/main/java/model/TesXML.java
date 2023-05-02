package model;

import controller.fixedbill.FixedBillAdapterXML;
import controller.fixedbill.FixedBillIO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class TesXML {
    public static void main(String[] args) {
        FixedBillIO dataIO = new FixedBillAdapterXML("tesXML.xml");
        Customer c = Customer.builder().id(1).build();
        Member m = Member.builder().id(2).name("chigans").point(0).phone("12345").build();

        FixedBill f = FixedBill.builder().cust(c).keranjang(new ArrayList<>()).id(1).date(LocalDate.now()).time(LocalTime.now()).build();
        Barang b = Barang.builder().id(1).gambar("a").jumlah(10).hargaBeli(1).name("anjay").hargaJual(5).kategori("ayam").build();
        f.addBarang(b);
        FixedBill f3 = FixedBill.builder().cust(m).keranjang(new ArrayList<>()).id(5).date(LocalDate.now()).time(LocalTime.now()).build();
        dataIO.insertFixedBill(f);
        dataIO.insertFixedBill(f3);
        VIP v = VIP.builder().id(3).name("chigansVIP").point(0).phone("12345").build();
        FixedBill f4 = FixedBill.builder().cust(v).keranjang(new ArrayList<>()).id(999).date(LocalDate.now()).time(LocalTime.now()).build();

        dataIO.updateFixedBill(f);
        FixedBill f2 = FixedBill.builder().cust(c).keranjang(new ArrayList<>()).id(3).date(LocalDate.now()).time(LocalTime.now()).build();
        dataIO.insertFixedBill(f2);
        dataIO.insertFixedBill(f4);
        System.out.println(f.getId());
        System.out.println(Objects.requireNonNull(dataIO.getAllFixedBill()).get(0));
        System.out.println(dataIO.getByID(3));

        Objects.requireNonNull(dataIO.getAllFixedBill()).forEach(el -> el.getCust().polymorphTest());
        Objects.requireNonNull(dataIO.getAllFixedBill()).forEach(el -> System.out.println(el.getDate()));
        Objects.requireNonNull(dataIO.getAllFixedBill()).forEach(el -> System.out.println(el.getTime()));
    }
}
