package controller.fixedbill;

import lombok.Getter;
import lombok.Setter;
import model.FixedBill;

import java.util.List;

@Setter @Getter
public class FixedBillController {
    FixedBillIO dataIO;

    public FixedBill getByID(int id) { return dataIO.getByID(id); }

    public List<FixedBill> getAllFixedBill()  { return dataIO.getAllFixedBill(); }

    public boolean insertFixedBill(FixedBill data) { return dataIO.insertFixedBill(data); }

    public boolean updateFixedBill(FixedBill newData) { return dataIO.updateFixedBill(newData); }

    public boolean deleteFixedBill(int id) { return dataIO.deleteFixedBill(id); }

    // tambahin sesuai kebutuhan
}
