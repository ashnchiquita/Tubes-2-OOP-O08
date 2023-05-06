package controller;

import java.util.*;

public interface GenericController<T> {
  public T getByID(int id);

  public List<T> getAll();

  public boolean insert(T data);

  public boolean update(T data);

  public boolean delete(int id);
}
