package controller;

import controller.barang.BarangController;
import controller.customer.CustomerController;
import controller.fixedbill.FixedBillController;
import controller.member.MemberController;
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
}
