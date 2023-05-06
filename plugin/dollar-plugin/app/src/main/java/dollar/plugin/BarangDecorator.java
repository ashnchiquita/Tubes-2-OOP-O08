package dollar.plugin;

import java.util.*;
import java.util.stream.*;

import controller.*;
import controller.barang.*;
import model.*;

public class BarangDecorator implements GenericController<Barang> {
  private BarangIO dataIO;
  private static final int exchangeRate = 14600;

  public void setDataIO(BarangIO dataIO) {
    this.dataIO = dataIO;
  }

  public Barang getByID(int id) {
    Barang barangData = dataIO.getByID(id);

    Barang modifiedData = Barang.builder().id(barangData.getId()).name(barangData.getName())
        .kategori(barangData.getKategori()).gambar(barangData.getGambar())
        .hargaJual((int) (barangData.getHargaJual() / exchangeRate))
        .hargaBeli((int) (barangData.getHargaBeli() / exchangeRate)).jumlah(barangData.getJumlah()).build();

    return modifiedData;
  }

  public List<Barang> getAll() {
    List<Barang> barangList = dataIO.getAllBarang();

    List<Barang> newList = barangList.stream()
        .map(barangData -> Barang.builder().id(barangData.getId()).name(barangData.getName())
            .kategori(barangData.getKategori()).gambar(barangData.getGambar())
            .hargaJual((int) (barangData.getHargaJual() / exchangeRate))
            .hargaBeli((int) (barangData.getHargaBeli() / exchangeRate)).jumlah(barangData.getJumlah()).build())
        .collect(Collectors.toList());

    return newList;
  }

  public boolean insert(Barang barangData) {
    Barang modifiedData = Barang.builder().id(barangData.getId()).name(barangData.getName())
        .kategori(barangData.getKategori()).gambar(barangData.getGambar())
        .hargaJual((int) (barangData.getHargaJual() * exchangeRate))
        .hargaBeli((int) (barangData.getHargaBeli() * exchangeRate)).jumlah(barangData.getJumlah()).build();

    return dataIO.insertBarang(modifiedData);
  }

  public boolean update(Barang barangData) {
    Barang modifiedData = Barang.builder().id(barangData.getId()).name(barangData.getName())
        .kategori(barangData.getKategori()).gambar(barangData.getGambar())
        .hargaJual((int) (barangData.getHargaJual() * exchangeRate))
        .hargaBeli((int) (barangData.getHargaBeli() * exchangeRate)).jumlah(barangData.getJumlah()).build();

    return dataIO.updateBarang(modifiedData);
  }

  public boolean delete(int id) {
    return dataIO.deleteBarang(id);
  }
}
