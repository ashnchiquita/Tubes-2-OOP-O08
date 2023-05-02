package controller.barang;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;
import model.Barang;

public class BarangController {
  @Setter
  BarangDataIO dataIO;

  public void showByID(int id) {
    System.out.println(dataIO.getByID(id));
  }

  public List<Barang> getAllBarang() {
    List<Barang> memberList = new ArrayList<>();
    memberList = dataIO.getAllBarang();
    return memberList;
  }

  public void showAllBarang() {
    List<Barang> barangList = new ArrayList<>();
    barangList = dataIO.getAllBarang();
    barangList.stream().forEach(barang -> System.out.println(barang));
  }

  public boolean addNewBarang(String name, String kategori) {
    List<Barang> barangList = dataIO.getAllBarang();
    int id = barangList.size() + 1;

    Barang newBarang = Barang.builder().id(id).name(name).kategori(kategori).hargaJual(0).hargaBeli(1).jumlah(0).gambar("").build();
    return dataIO.insertBarang(newBarang);
  }

  public boolean addMoreBarang(int id) {
    Barang barang = dataIO.getByID(id);
    if (barang == null) {
      return false;
    }

    barang.setJumlah(barang.getJumlah() + 1);
    return dataIO.updateBarang(id, barang);
  }

  public boolean updateMemberData(int id, String name, String kategori) {
    Barang barang  = dataIO.getByID(id);
    if (barang == null) {
      return false;
    }

    barang.setName(name);
    barang.setKategori(kategori);
    return dataIO.updateBarang(id, barang);
  }

  public void deleteBarang(int id) {
    System.out.println(dataIO.deleteBarang(id));
  }
}
