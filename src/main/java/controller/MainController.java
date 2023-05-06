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
}
