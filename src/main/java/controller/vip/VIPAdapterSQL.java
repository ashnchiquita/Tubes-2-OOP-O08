package controller.vip;

import java.util.*;
import java.sql.*;

import controller.*;
import model.*;

public class VIPAdapterSQL extends SQLConnector<VIP> {
  public VIPAdapterSQL(String url, String user, String pass) {
    super(url, user, pass);
  }

  @Override
  public VIP getByID(int id) {
    List<VIP> vipList = getAll();

    for (VIP v : vipList) {
      if (v.getId() == id) {
        return v;
      }
    }
    return null;
  }

  @Override
  public List<VIP> getAll() {
    try {
      Statement stmt = connection.createStatement();
      ResultSet resultSet = stmt.executeQuery("SELECT * FROM vip");

      List<VIP> vipList = new ArrayList<>();
      while (resultSet.next()) {
        VIP temp = VIP.builder().id().name(resultSet.getString("name")).phone(resultSet.getString("phone"))
            .point(resultSet.getInt("point")).transactions(resultSet.getInt("transactions"))
            .active(resultSet.getInt("active") != 0).build();

        temp.setId(resultSet.getInt("id"));
        vipList.add(temp);
      }
      return vipList;
    } catch (SQLException e) {
      System.out.println("SQL error on method getAll : " + e);
      return new ArrayList<VIP>();
    }
  }

  @Override
  public boolean insert(VIP data) {
    try {
      Statement stmt = connection.createStatement();
      String insertStmt = "INSERT INTO vip VALUES (" + String.valueOf(data.getId()) + ", '" + data.getName()
          + "', '" + data.getPhone() + "', " + String.valueOf(data.getPoint()) + ", "
          + String.valueOf(data.getTransactions()) + ", " + (data.isActive() ? 1 : 0) + ")";

      stmt.executeUpdate(insertStmt);
      return true;
    } catch (SQLException e) {
      System.out.println("SQL error on method insert : " + e);
      return false;
    }
  }

  @Override
  public boolean update(VIP newData) {
    try {
      Statement stmt = connection.createStatement();
      String insertStmt = "UPDATE vip SET name = '" + newData.getName() + "', phone = '" + newData.getPhone()
          + "', point = " + String.valueOf(newData.getPoint())
          + ", transactions = " + String.valueOf(newData.getTransactions()) + ", active = "
          + (newData.isActive() ? 1 : 0) + " WHERE id = " + String.valueOf(newData.getId());

      stmt.executeUpdate(insertStmt);
      return true;
    } catch (SQLException e) {
      System.out.println("SQL error on method update : " + e);
      return false;
    }
  }

  @Override
  public boolean delete(int id) {
    try {
      Statement stmt = connection.createStatement();
      String insertStmt = "DELETE FROM vip WHERE id = " + String.valueOf(id);

      stmt.executeUpdate(insertStmt);
      return true;
    } catch (SQLException e) {
      System.out.println("SQL error on method delete : " + e);
      return false;
    }
  }

}
