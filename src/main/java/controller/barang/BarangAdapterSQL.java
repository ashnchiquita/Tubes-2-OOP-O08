package controller.barang;

import java.util.*;
import java.sql.*;

import controller.*;
import model.*;

public class BarangAdapterSQL extends SQLConnector<Barang> {
  public BarangAdapterSQL(String url, String user, String pass) {
    super(url, user, pass);
  }

  @Override
  public Barang getByID(int id) {
    List<Barang> barangList = getAll();

    for (Barang b : barangList) {
      if (b.getId() == id) {
        return b;
      }
    }
    return null;
  }

  @Override
  public List<Barang> getAll() {
    try {
      Statement stmt = connection.createStatement();
      ResultSet resultSet = stmt.executeQuery("SELECT * FROM barang");

      List<Barang> barangList = new ArrayList<>();
      while (resultSet.next()) {
        Barang temp = Barang.builder().id().name(resultSet.getString("name")).kategori(resultSet.getString("kategori"))
            .gambar(resultSet.getString("gambar")).hargaJual(resultSet.getLong("harga_jual"))
            .hargaBeli(resultSet.getLong("harga_beli")).jumlah(resultSet.getInt("jumlah")).build();

        temp.setId(resultSet.getInt("id"));
        barangList.add(temp);
      }
      return barangList;
    } catch (SQLException e) {
      System.out.println("SQL error on method getAll : " + e);
      return new ArrayList<Barang>();
    }
  }

  @Override
  public boolean insert(Barang data) {
    try {
      Statement stmt = connection.createStatement();
      String insertStmt = "INSERT INTO barang VALUES (" + String.valueOf(data.getId()) + ", '" + data.getName()
          + "', '" + data.getKategori() + "', '" + data.getGambar() + "', " + String.valueOf(data.getHargaJual()) + ", "
          + String.valueOf(data.getHargaBeli()) + ", " + String.valueOf(data.getJumlah()) + ")";

      stmt.executeUpdate(insertStmt);
      return true;
    } catch (SQLException e) {
      System.out.println("SQL error on method insert : " + e);
      return false;
    }
  }

  @Override
  public boolean update(Barang newData) {
    try {
      Statement stmt = connection.createStatement();
      String insertStmt = "UPDATE barang SET name = '" + newData.getName() + "', kategori = '" + newData.getKategori()
          + "', gambar = '" + newData.getGambar() + "', harga_jual = " + String.valueOf(newData.getHargaJual())
          + ", harga_beli = " + String.valueOf(newData.getHargaBeli()) + ", jumlah = "
          + String.valueOf(newData.getJumlah()) + " WHERE id = " + String.valueOf(newData.getId());

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
      String insertStmt = "DELETE FROM barang WHERE id = " + String.valueOf(id);

      stmt.executeUpdate(insertStmt);
      return true;
    } catch (SQLException e) {
      System.out.println("SQL error on method delete : " + e);
      return false;
    }
  }

}
