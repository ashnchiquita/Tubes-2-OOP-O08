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
    return dataIO.getByID(int id);
  }

  public List<FixedBill> getAll() {
    return dataIO.getAll();
  }

  public boolean insert(FixedBill data) {

  }

  public boolean update(FixedBill newData) {
    return dataIO.update(newData)
  }

  public boolean delete(int id) {
    return dataIO.delete(id);
  }
}
