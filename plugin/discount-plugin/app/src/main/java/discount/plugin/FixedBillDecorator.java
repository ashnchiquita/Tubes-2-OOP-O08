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

  }

  public List<T> getAll();

  public boolean insert(T data);

  public boolean update(T newData);

  public boolean delete(int id);
}
