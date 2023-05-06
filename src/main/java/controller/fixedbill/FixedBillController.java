package controller.fixedbill;

import lombok.Getter;
import lombok.Setter;
import model.FixedBill;
import controller.*;

import java.util.List;

@Setter
@Getter
public class FixedBillController implements GenericController<FixedBill> {
    FixedBillIO dataIO;

    public FixedBill getByID(int id) {
        return dataIO.getByID(id);
    }

    public List<FixedBill> getAll() {
        return dataIO.getAllFixedBill();
    }

    public boolean insert(FixedBill data) {
        return dataIO.insertFixedBill(data);
    }

    public boolean update(FixedBill newData) {
        return dataIO.updateFixedBill(newData);
    }

    public boolean delete(int id) {
        return dataIO.deleteFixedBill(id);
    }

    // tambahin sesuai kebutuhan
}
