package controller.fixedbill;

import lombok.Setter;
import model.FixedBill;

import java.util.List;

@Setter
public class FixedBillController {
    FixedBillIO dataIO;
    public List<FixedBill> getAllFixedBill() {
        return dataIO.getAllFixedBill();
    }

    // tambahin sesuai kebutuhan
}
