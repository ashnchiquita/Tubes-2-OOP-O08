package controller.member;

import java.util.*;
import java.sql.*;

import controller.*;
import model.*;

public class MemberAdapterSQL extends SQLConnector<Member> {
  public MemberAdapterSQL(String url, String user, String pass) {
    super(url, user, pass);
  }

  @Override
  public Member getByID(int id) {
    List<Member> memberList = getAll();

    for (Member m : memberList) {
      if (m.getId() == id) {
        return m;
      }
    }
    return null;
  }

  @Override
  public List<Member> getAll() {
    try {
      Statement stmt = connection.createStatement();
      ResultSet resultSet = stmt.executeQuery("SELECT * FROM member");

      List<Member> memberList = new ArrayList<>();
      while (resultSet.next()) {
        Member temp = Member.builder().id().name(resultSet.getString("name")).phone(resultSet.getString("phone"))
            .point(resultSet.getInt("point")).transactions(resultSet.getInt("transactions"))
            .active(resultSet.getInt("active") != 0).build();

        temp.setId(resultSet.getInt("id"));
        memberList.add(temp);
      }
      return memberList;
    } catch (SQLException e) {
      System.out.println("SQL error on method getAll : " + e);
      return new ArrayList<Member>();
    }
  }

  @Override
  public boolean insert(Member data) {
    try {
      Statement stmt = connection.createStatement();
      String insertStmt = "INSERT INTO member VALUES (" + String.valueOf(data.getId()) + ", '" + data.getName()
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
  public boolean update(Member newData) {
    try {
      Statement stmt = connection.createStatement();
      String insertStmt = "UPDATE member SET name = '" + newData.getName() + "', phone = '" + newData.getPhone()
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
      String insertStmt = "DELETE FROM member WHERE id = " + String.valueOf(id);

      stmt.executeUpdate(insertStmt);
      return true;
    } catch (SQLException e) {
      System.out.println("SQL error on method delete : " + e);
      return false;
    }
  }

}
