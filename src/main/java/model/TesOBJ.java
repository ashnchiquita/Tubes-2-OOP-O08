package model;
import controller.fixedbill.FixedBillAdapterOBJ;
import controller.fixedbill.FixedBillIO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class TesOBJ {
    public static void main(String[] args) {
        FixedBillIO dataIO = new FixedBillAdapterOBJ("tesOBJ");
        Customer c = Customer.builder().id(1).build();
        FixedBill f = FixedBill.builder().cust(c).keranjang(new ArrayList<>()).id(1).date(LocalDate.now()).time(LocalTime.now()).build();

        dataIO.insertFixedBill(f);
        Barang b = Barang.builder().id(1).gambar("a").jumlah(10).hargaBeli(1).name("anjay").hargaJual(5).kategori("ayam").build();
        f.addBarang(b);
        dataIO.updateFixedBill(f);
        // dataIO.getAllFixedBill().forEach(System.out::println);
        FixedBill f2 = FixedBill.builder().cust(c).keranjang(new ArrayList<>()).id(3).date(LocalDate.now()).time(LocalTime.now()).build();
        dataIO.insertFixedBill(f2);
        System.out.println(f.getId());
        System.out.println(Objects.requireNonNull(dataIO.getAllFixedBill()).get(0));
        System.out.println(dataIO.getByID(3));
    }
}
