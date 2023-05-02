package controller.fixedbill;

import model.FixedBill;

import java.io.IOException;
import java.util.List;

public interface FixedBillIO {
    public FixedBill getByID(int id);

    public List<FixedBill> getAllFixedBill();

    public boolean insertFixedBill(FixedBill data);

    public boolean updateFixedBill(FixedBill newData);

    public boolean deleteFixedBill(int id);
}
