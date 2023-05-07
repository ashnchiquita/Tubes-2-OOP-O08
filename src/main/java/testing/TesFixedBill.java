package testing;

import controller.MainController;
// import controller.barang.BarangAdapterOBJ;
// import controller.fixedbill.*;
// import controller.customer.*;
// import controller.member.*;
// import controller.vip.*;
// import controller.barang.*;
import model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class TesFixedBill {
  public static void main(String[] args) {
    MainController controller = new MainController();
    // dataIO.setDataIO(new
    // FixedBillAdapterJSON("src/main/resources/data/tes_fixed_bill.json"));
    // controller.setFixedBillDataIO(new
    // FixedBillAdapterXML("src/main/resources/data/tes_fixed_bill.xml"));
    // controller.setFixedBillDataIO(new
    // FixedBillAdapterOBJ("src/main/resources/data/tes_fixed_bill"));
    // controller.setFixedBillDataIO(
    // new FixedBillAdapterSQL("jdbc:mysql://localhost:3306/testing_oop", "rma1403",
    // ""));
    // controller
    // .setCustomerDataIO(new
    // CustomerAdapterSQL("jdbc:mysql://localhost:3306/testing_oop",
    // "rma1403", ""));
    // controller.setMemberDataIO(
    // new MemberAdapterSQL("jdbc:mysql://localhost:3306/testing_oop", "rma1403",
    // ""));
    // controller.setVIPDataIO(
    // new VIPAdapterSQL("jdbc:mysql://localhost:3306/testing_oop", "rma1403", ""));
    // controller.setBarangDataIO(
    // new BarangAdapterSQL("jdbc:mysql://localhost:3306/testing_oop", "rma1403",
    // ""));

    Customer c = Customer.builder().id().build();
    Member m = Member.builder().id().name("chigans").point(0).phone("12345").transactions(0).active(true)
        .build();
    // controller.getCustomerDataIO().insert(c);
    // controller.getMemberDataIO().insert(m);

    FixedBill f = FixedBill.builder().cust(c).keranjang(new ArrayList<>()).id().date(LocalDate.now())
        .time(LocalTime.now()).billing(0).build();
    FixedBill f3 = FixedBill.builder().cust(m).keranjang(new ArrayList<>()).id().date(LocalDate.now())
        .time(LocalTime.now()).billing(0).build();
    controller.getFixedBillDataIO().insert(f);
    controller.getFixedBillDataIO().insert(f3);
    VIP v = VIP.builder().id().name("chigansVIP").point(0).phone("12345").transactions(1).active(false)
        .build();
    // controller.getVIPDataIO().insert(v);
    FixedBill f4 = FixedBill.builder().cust(v).keranjang(new ArrayList<>()).id().date(LocalDate.now())
        .time(LocalTime.now()).billing(0).build();
    Barang b = Barang.builder().id().gambar("a").jumlah(10).hargaBeli(1).name("anjay").hargaJual(5)
        .kategori("ayam").build();
    // controller.getBarangDataIO().insert(b);
    f4.addBarang(b);
    f.setBilling(200);
    controller.getFixedBillDataIO().update(f);
    FixedBill f2 = FixedBill.builder().cust(c).keranjang(new ArrayList<>()).id().date(LocalDate.now())
        .time(LocalTime.now()).billing(0).build();
    controller.getFixedBillDataIO().insert(f4);
    controller.getFixedBillDataIO().insert(f2);
    // System.out.println(f.getId());
    // System.out.println(Objects.requireNonNull(controller.getFixedBillDataIO().getAll()));
    controller.getFixedBillDataIO().getAll().stream().forEach(System.out::println);
    // System.out.println(controller.getFixedBillDataIO().getByID(10));
    // Objects.requireNonNull(controller.getFixedBillDataIO().getAll())
    // .forEach(el -> el.getCust().polymorphTest());
    // controller.getFixedBillDataIO().delete(999);
  }
}
