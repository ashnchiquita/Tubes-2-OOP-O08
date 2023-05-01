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

  public void showAllBarang() {
    List<Barang> barangList = new ArrayList<>();
    barangList = dataIO.getAllBarang();
    barangList.stream().forEach(barang -> System.out.println(barang));
  }

  public void addBarang() {
    Barang newBarang = Barang.builder().id(11).name("keyboard").kategori("hardware").hargaJual(900).hargaBeli(1000).jumlah(1).gambar("").build();
    System.out.println(dataIO.insertBarang(newBarang));
  }

  public void updateBarang(int id) {
    Barang newDataBarang = Barang.builder().id(id).name("new_keyboard").kategori("hardware").hargaJual(950).hargaBeli(1050).jumlah(1).gambar("").build();
    System.out.println(dataIO.updateBarang(11, newDataBarang));
  }

  public void deleteBarang(int id) {
    System.out.println(dataIO.deleteBarang(id));
  }
}
