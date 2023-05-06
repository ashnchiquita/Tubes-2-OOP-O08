package controller;

import controller.barang.*;
import controller.customer.*;
import controller.fixedbill.*;
import controller.member.*;
import controller.vip.*;
import lombok.*;
import model.*;

@Getter
@Setter
public class MainController {
    GenericDataIO<Barang> barangDataIO;
    GenericDataIO<Customer> customerDataIO;
    GenericDataIO<FixedBill> fixedBillDataIO;
    GenericDataIO<Member> memberDataIO;
    GenericDataIO<VIP> VIPDataIO;

    public MainController() {
        barangDataIO = new BarangAdapterOBJ("src/main/resources/data/tes_barang");
        customerDataIO = new CustomerAdapterOBJ("src/main/resources/data/tes_customer");
        fixedBillDataIO = new FixedBillAdapterOBJ("src/main/resources/data/tes_fixed_bill");
        memberDataIO = new MemberAdapterOBJ("src/main/resources/data/tes_member");
        VIPDataIO = new VIPAdapterOBJ("src/main/resources/data/tes_vip");
    }
}
