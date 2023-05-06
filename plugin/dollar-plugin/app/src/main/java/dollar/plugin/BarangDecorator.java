package dollar.plugin;

import java.util.*;
import java.util.stream.*;

import controller.*;
import controller.barang.*;
import model.*;

public class BarangDecorator implements GenericDataIO<Barang> {
  private GenericDataIO<Barang> dataIO;
  private static final int exchangeRate = 14600;

  public void setDataIO(GenericDataIO<Barang> dataIO) {
    this.dataIO = dataIO;
  }

  public Barang getByID(int id) {
    Barang barangData = dataIO.getByID(id);

    if (barangData == null) {
      return null;
    }

    Barang modifiedData = Barang.builder().id().name(barangData.getName())
        .kategori(barangData.getKategori()).gambar(barangData.getGambar())
        .hargaJual((int) (barangData.getHargaJual() / exchangeRate))
        .hargaBeli((int) (barangData.getHargaBeli() / exchangeRate)).jumlah(barangData.getJumlah()).build();
    modifiedData.setId(barangData.getId());

    return modifiedData;
  }

  public List<Barang> getAll() {
    List<Barang> barangList = dataIO.getAll();

    List<Barang> newList = barangList.stream()
        .map(barangData -> {
          Barang temp = Barang.builder().id().name(barangData.getName())
              .kategori(barangData.getKategori()).gambar(barangData.getGambar())
              .hargaJual((int) (barangData.getHargaJual() / exchangeRate))
              .hargaBeli((int) (barangData.getHargaBeli() / exchangeRate)).jumlah(barangData.getJumlah()).build();
          temp.setId(barangData.getId());
          return temp;
        })
        .collect(Collectors.toList());

    return newList;
  }

  public boolean insert(Barang barangData) {
    Barang modifiedData = Barang.builder().id().name(barangData.getName())
        .kategori(barangData.getKategori()).gambar(barangData.getGambar())
        .hargaJual((int) (barangData.getHargaJual() * exchangeRate))
        .hargaBeli((int) (barangData.getHargaBeli() * exchangeRate)).jumlah(barangData.getJumlah()).build();
    modifiedData.setId(barangData.getId());

    return dataIO.insert(modifiedData);
  }

  public boolean update(Barang barangData) {
    Barang modifiedData = Barang.builder().id().name(barangData.getName())
        .kategori(barangData.getKategori()).gambar(barangData.getGambar())
        .hargaJual((int) (barangData.getHargaJual() * exchangeRate))
        .hargaBeli((int) (barangData.getHargaBeli() * exchangeRate)).jumlah(barangData.getJumlah()).build();
    modifiedData.setId(barangData.getId());

    return dataIO.update(modifiedData);
  }

  public boolean delete(int id) {
    return dataIO.delete(id);
  }
}
