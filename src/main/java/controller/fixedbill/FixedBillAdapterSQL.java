package controller.fixedbill;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import controller.*;
import model.*;

public class FixedBillAdapterSQL extends SQLConnector<FixedBill> {
  public FixedBillAdapterSQL(String url, String user, String pass) {
    super(url, user, pass);
  }

  @Override
  public FixedBill getByID(int id) {
    List<FixedBill> fixedBillList = getAll();

    for (FixedBill f : fixedBillList) {
      if (f.getId() == id) {
        return f;
      }
    }
    return null;
  }

  @Override
  public List<FixedBill> getAll() {
    try {
      Statement stmt = connection.createStatement();
      ResultSet resultSet = stmt.executeQuery("SELECT * FROM fixed_bill");

      List<FixedBill> fixedBillList = new ArrayList<>();
      while (resultSet.next()) {
        FixedBill temp = FixedBill.builder().id().cust(null).keranjang(new ArrayList<>())
            .date(resultSet.getDate("date").toLocalDate()).time(resultSet.getTime("time").toLocalTime())
            .billing(resultSet.getLong("billing")).build();

        temp.setId(resultSet.getInt("id"));

        Boolean found = false;

        Statement vipStmt = connection.createStatement();
        ResultSet vipResultSet = vipStmt
            .executeQuery("SELECT * FROM vip WHERE id = " + String.valueOf(resultSet.getInt("cust_id")));

        while (!found && vipResultSet.next()) {
          VIP tempVIP = VIP.builder().id().name(vipResultSet.getString("name")).phone(vipResultSet.getString("phone"))
              .point(vipResultSet.getInt("point")).transactions(vipResultSet.getInt("transactions"))
              .active(vipResultSet.getInt("active") != 0).build();
          tempVIP.setId(vipResultSet.getInt("id"));
          temp.setCust(tempVIP);
          found = true;
        }

        Statement memberStmt = connection.createStatement();
        ResultSet memberResultSet = memberStmt
            .executeQuery("SELECT * FROM member WHERE id = " + String.valueOf(resultSet.getInt("cust_id")));

        while (!found && memberResultSet.next()) {
          Member tempMember = Member.builder().id().name(memberResultSet.getString("name"))
              .phone(memberResultSet.getString("phone")).point(memberResultSet.getInt("point"))
              .transactions(memberResultSet.getInt("transactions")).active(memberResultSet.getInt("active") != 0)
              .build();
          tempMember.setId(memberResultSet.getInt("id"));
          temp.setCust(tempMember);
          found = true;
        }

        Statement custStmt = connection.createStatement();
        ResultSet custResultSet = custStmt
            .executeQuery("SELECT * FROM customer WHERE id = " + String.valueOf(resultSet.getInt("cust_id")));

        while (!found && custResultSet.next()) {
          Customer tempCust = Customer.builder().id().build();
          tempCust.setId(custResultSet.getInt("id"));
          temp.setCust(tempCust);
          found = true;
        }

        Statement keranjangStmt = connection.createStatement();
        ResultSet keranjangResultSet = keranjangStmt
            .executeQuery("SELECT * FROM bill_barang WHERE bill_id = " + String.valueOf(temp.getId()));

        while (keranjangResultSet.next()) {
          Statement barangStmt = connection.createStatement();
          ResultSet barangResultSet = barangStmt.executeQuery(
              "SELECT * FROM barang WHERE id = " + String.valueOf(keranjangResultSet.getInt("barang_id")));

          while (barangResultSet.next()) {
            Barang tempBarang = Barang.builder().id().name(barangResultSet.getString("name"))
                .kategori(barangResultSet.getString("kategori"))
                .gambar(barangResultSet.getString("gambar")).hargaJual(barangResultSet.getLong("harga_jual"))
                .hargaBeli(barangResultSet.getLong("harga_beli")).jumlah(barangResultSet.getInt("jumlah")).build();
            tempBarang.setId(barangResultSet.getInt("id"));
            temp.getKeranjang().add(tempBarang);
            break;
          }
        }

        fixedBillList.add(temp);
      }
      return fixedBillList;
    } catch (SQLException e) {
      System.out.println("SQL error on method getAll : " + e);
      return new ArrayList<FixedBill>();
    }
  }

  @Override
  public boolean insert(FixedBill data) {
    try {
      Statement stmt = connection.createStatement();
      String insertStmt = "INSERT INTO fixed_bill VALUES (" + String.valueOf(data.getId()) + ", '"
          + String.valueOf(Date.valueOf(data.getDate())) + "', '" + String.valueOf(Time.valueOf(data.getTime())) + "', "
          + String.valueOf(data.getBilling()) + ", " + String.valueOf(data.getCust().getId()) + ")";
      stmt.executeUpdate(insertStmt);

      for (Barang barang : data.getKeranjang()) {
        Statement keranjangStmt = connection.createStatement();
        keranjangStmt.executeUpdate("INSERT INTO bill_barang VALUES (" + String.valueOf(data.getId()) + ", "
            + String.valueOf(barang.getId()) + ")");
      }

      return true;
    } catch (SQLException e) {
      System.out.println("SQL error on method insert : " + e);
      return false;
    }
  }

  @Override
  public boolean update(FixedBill newData) {
    try {
      Statement stmt = connection.createStatement();
      String insertStmt = "UPDATE fixed_bill SET date = '" + String.valueOf(Date.valueOf(newData.getDate()))
          + "', time = '" + String.valueOf(Time.valueOf(newData.getTime())) + "', billing = "
          + String.valueOf(newData.getBilling()) + ", cust_id = " + String.valueOf(newData.getCust().getId())
          + " WHERE id = " + String.valueOf(newData.getId());
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
      String insertStmt = "DELETE FROM fixed_bill WHERE id = " + String.valueOf(id);

      stmt.executeUpdate(insertStmt);

      Statement keranjangStmt = connection.createStatement();
      keranjangStmt.executeUpdate("DELETE FROM bill_barang WHERE bill_id = " + String.valueOf(id));

      return true;
    } catch (SQLException e) {
      System.out.println("SQL error on method delete : " + e);
      return false;
    }
  }

}
