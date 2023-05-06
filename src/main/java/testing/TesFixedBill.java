package testing;

import controller.barang.BarangAdapterOBJ;
import controller.fixedbill.FixedBillAdapterJSON;
import controller.fixedbill.FixedBillAdapterOBJ;
import controller.fixedbill.FixedBillAdapterXML;
import controller.fixedbill.FixedBillController;
import model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

public class TesFixedBill {
    public static void main(String[] args) {
        FixedBillController dataIO = new FixedBillController();
        // dataIO.setDataIO(new FixedBillAdapterJSON("src/main/resources/data/tes_fixed_bill.json"));
        dataIO.setDataIO(new FixedBillAdapterXML("src/main/resources/data/tes_fixed_bill.xml"));
        // dataIO.setDataIO(new FixedBillAdapterOBJ("src/main/resources/data/tes_fixed_bill"));

        Customer c = Customer.builder().id().build();
        Member m = Member.builder().id().name("chigans").point(0).phone("12345").transactions(0).active(true).build();

        FixedBill f = FixedBill.builder().cust(c).keranjang(new ArrayList<>()).id().date(LocalDate.now()).time(LocalTime.now()).billing(0).build();
        FixedBill f3 = FixedBill.builder().cust(m).keranjang(new ArrayList<>()).id().date(LocalDate.now()).time(LocalTime.now()).billing(0).build();
        dataIO.insertFixedBill(f);
        dataIO.insertFixedBill(f3);
        VIP v = VIP.builder().id().name("chigansVIP").point(0).phone("12345").transactions(1).active(false).build();
        FixedBill f4 = FixedBill.builder().cust(v).keranjang(new ArrayList<>()).id().date(LocalDate.now()).time(LocalTime.now()).billing(0).build();
        Barang b = Barang.builder().id().gambar("a").jumlah(10).hargaBeli(1).name("anjay").hargaJual(5).kategori("ayam").build();
        f.addBarang(b);
        dataIO.updateFixedBill(f);
        FixedBill f2 = FixedBill.builder().cust(c).keranjang(new ArrayList<>()).id().date(LocalDate.now()).time(LocalTime.now()).billing(0).build();
        dataIO.insertFixedBill(f2);
        dataIO.insertFixedBill(f4);
        System.out.println(f.getId());
        System.out.println(Objects.requireNonNull(dataIO.getAllFixedBill()).get(0));
        System.out.println(dataIO.getByID(3));
        Objects.requireNonNull(dataIO.getAllFixedBill()).forEach(el -> el.getCust().polymorphTest());
        dataIO.deleteFixedBill(999);
    }
}
