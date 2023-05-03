package controller.datastore;

import java.io.Serializable;
import java.util.*;

public interface DataIO<T> {

  public T findById(int id);

  public List<T> findAll();

  public boolean insert(T data);

  public boolean update(int id, T newData);

  public boolean delete(int id);

}
