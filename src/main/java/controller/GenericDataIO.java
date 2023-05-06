package controller;

import java.util.*;

public interface GenericDataIO<T> {
  public T getByID(int id);

  public List<T> getAll();

  public boolean insert(T data);

  public boolean update(T newData);

  public boolean delete(int id);
}
