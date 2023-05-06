package controller;

import boundary.constants.ResourcePath;
import controller.barang.BarangAdapterJSON;
import controller.barang.BarangAdapterOBJ;
import controller.barang.BarangAdapterXML;
import controller.barang.BarangController;
import controller.customer.CustomerAdapterJSON;
import controller.customer.CustomerAdapterOBJ;
import controller.customer.CustomerAdapterXML;
import controller.customer.CustomerController;
import controller.fixedbill.FixedBillAdapterJSON;
import controller.fixedbill.FixedBillAdapterOBJ;
import controller.fixedbill.FixedBillAdapterXML;
import controller.fixedbill.FixedBillController;
import controller.member.MemberAdapterJSON;
import controller.member.MemberAdapterOBJ;
import controller.member.MemberAdapterXML;
import controller.member.MemberController;
import controller.vip.VIPAdapterJSON;
import controller.vip.VIPAdapterOBJ;
import controller.vip.VIPAdapterXML;
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
