package controller;

import controller.barang.BarangAdapterOBJ;
import controller.barang.BarangController;
import controller.customer.CustomerAdapterOBJ;
import controller.customer.CustomerController;
import controller.fixedbill.FixedBillAdapterOBJ;
import controller.fixedbill.FixedBillController;
import controller.member.MemberAdapterOBJ;
import controller.member.MemberController;
import controller.vip.VIPAdapterOBJ;
import controller.vip.VIPController;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainController {
    BarangController barangCon;
    CustomerController customerCon;
    FixedBillController fixedBillCon;
    MemberController memberCon;
    VIPController vipCon;

    public MainController(){
        barangCon = new BarangController();
        customerCon = new CustomerController();
        fixedBillCon = new FixedBillController();
        memberCon = new MemberController();
        vipCon = new VIPController();

        barangCon.setDataIO(new BarangAdapterOBJ("src/main/resources/data/tes_barang"));
        customerCon.setDataIO(new CustomerAdapterOBJ("src/main/resources/data/tes_customer"));
        fixedBillCon.setDataIO(new FixedBillAdapterOBJ("src/main/resources/data/tes_fixed_bill"));
        memberCon.setDataIO(new MemberAdapterOBJ("src/main/resources/data/tes_member"));
        vipCon.setDataIO(new VIPAdapterOBJ("src/main/resources/data/tes_vip"));
    }
}
