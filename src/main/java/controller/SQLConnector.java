package controller;

import java.sql.*;

import lombok.RequiredArgsConstructor;

public abstract class SQLConnector<T> implements GenericDataIO<T> {
  protected Connection connection;

  public SQLConnector(String url, String user, String pass) {
    try {
      connection = DriverManager.getConnection(url, user, pass);
    } catch (SQLException e) {
      System.out.println("SQL ERROR : " + e);
    }
  }
}
