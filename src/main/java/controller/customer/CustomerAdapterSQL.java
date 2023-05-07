package controller.customer;

import java.util.*;
import java.sql.*;

import controller.*;
import model.*;

public class CustomerAdapterSQL extends SQLConnector<Customer> {
  public CustomerAdapterSQL(String url, String user, String pass) {
    super(url, user, pass);
  }

  @Override
  public Customer getByID(int id) {
    List<Customer> customerList = getAll();

    for (Customer c : customerList) {
      if (c.getId() == id) {
        return c;
      }
    }
    return null;
  }

  @Override
  public List<Customer> getAll() {
    try {
      Statement stmt = connection.createStatement();
      ResultSet resultSet = stmt.executeQuery("SELECT * FROM customer");

      List<Customer> customerList = new ArrayList<>();
      while (resultSet.next()) {
        Customer temp = Customer.builder().id().build();

        temp.setId(resultSet.getInt("id"));
        customerList.add(temp);
      }
      return customerList;
    } catch (SQLException e) {
      System.out.println("SQL error on method getAll : " + e);
      return new ArrayList<Customer>();
    }
  }

  @Override
  public boolean insert(Customer data) {
    try {
      Statement stmt = connection.createStatement();
      String insertStmt = "INSERT INTO customer VALUES (" + String.valueOf(data.getId()) + ")";

      stmt.executeUpdate(insertStmt);
      return true;
    } catch (SQLException e) {
      System.out.println("SQL error on method insert : " + e);
      return false;
    }
  }

  @Override
  public boolean update(Customer newData) {
    return true;
  }

  @Override
  public boolean delete(int id) {
    try {
      Statement stmt = connection.createStatement();
      String insertStmt = "DELETE FROM customer WHERE id = " + String.valueOf(id);

      stmt.executeUpdate(insertStmt);
      return true;
    } catch (SQLException e) {
      System.out.println("SQL error on method delete : " + e);
      return false;
    }
  }

}
