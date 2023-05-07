package discount.plugin;

import controller.*;
import java.util.*;

import controller.*;
import model.*;

public class FixedBillDecorator implements GenericDataIO<FixedBill> {
  private GenericDataIO<FixedBill> dataIO;
  private static final double discount = 0.5;

  public void setDataIO(GenericDataIO<FixedBill> dataIO) {
    this.dataIO = dataIO;
  }

  public FixedBill getByID(int id) {
    return dataIO.getByID(id);
  }

  public List<FixedBill> getAll() {
    return dataIO.getAll();
  }

  public boolean insert(FixedBill billData) {
    System.out.println(billData.getBilling());
    FixedBill modifiedData = FixedBill.builder().id().cust(billData.getCust()).keranjang(new ArrayList<>())
        .date(billData.getDate()).time(billData.getTime()).billing(billData.getBilling() * discount).build();
    modifiedData.setId(billData.getId());

    for (Barang b : billData.getKeranjang()) {
      modifiedData.getKeranjang().add(b);
    }

    return dataIO.insert(modifiedData);
  }

  public boolean update(FixedBill newData) {
    return dataIO.update(newData);
  }

  public boolean delete(int id) {
    return dataIO.delete(id);
  }
}
