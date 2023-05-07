package dollar.plugin;

import java.util.*;
import java.util.stream.*;

import controller.*;
import controller.barang.*;
import model.*;

public class FixedBillDecorator implements GenericDataIO<FixedBill> {
  private GenericDataIO<FixedBill> dataIO;
  private static final int exchangeRate = 14600;

  public void setDataIO(GenericDataIO<FixedBill> dataIO) {
    this.dataIO = dataIO;
  }

  public FixedBill getByID(int id) {
    FixedBill billData = dataIO.getByID(id);

    if (billData == null) {
      return null;
    }

    FixedBill modifiedData = FixedBill.builder().id().cust(billData.getCust()).keranjang(new ArrayList<>())
        .date(billData.getDate()).time(billData.getTime()).billing(billData.getBilling() / exchangeRate).build();
    modifiedData.setId(billData.getId());

    for (Barang barangData : billData.getKeranjang()) {
      Barang newBarang = Barang.builder().id().name(barangData.getName())
          .kategori(barangData.getKategori()).gambar(barangData.getGambar())
          .hargaJual((int) (barangData.getHargaJual() / exchangeRate))
          .hargaBeli((int) (barangData.getHargaBeli() / exchangeRate)).jumlah(barangData.getJumlah()).build();
      newBarang.setId(barangData.getId());

      modifiedData.getKeranjang().add(barangData);
    }

    return modifiedData;
  }

  public List<FixedBill> getAll() {
    List<FixedBill> billList = dataIO.getAll();

    List<FixedBill> newList = billList.stream()
        .map(billData -> {
          FixedBill modifiedData = FixedBill.builder().id().cust(billData.getCust()).keranjang(new ArrayList<>())
              .date(billData.getDate()).time(billData.getTime()).billing(billData.getBilling() / exchangeRate).build();
          modifiedData.setId(billData.getId());

          for (Barang barangData : billData.getKeranjang()) {
            Barang newBarang = Barang.builder().id().name(barangData.getName())
                .kategori(barangData.getKategori()).gambar(barangData.getGambar())
                .hargaJual((int) (barangData.getHargaJual() / exchangeRate))
                .hargaBeli((int) (barangData.getHargaBeli() / exchangeRate)).jumlah(barangData.getJumlah()).build();
            newBarang.setId(barangData.getId());
            modifiedData.getKeranjang().add(barangData);
          }
          return modifiedData;
        })
        .collect(Collectors.toList());

    return newList;
  }

  public boolean insert(FixedBill billData) {
    FixedBill modifiedData = FixedBill.builder().id().cust(billData.getCust()).keranjang(new ArrayList<>())
        .date(billData.getDate()).time(billData.getTime()).billing(billData.getBilling() * exchangeRate).build();
    modifiedData.setId(billData.getId());

    return dataIO.insert(modifiedData);
  }

  public boolean update(FixedBill billData) {
    FixedBill modifiedData = FixedBill.builder().id().cust(billData.getCust()).keranjang(new ArrayList<>())
        .date(billData.getDate()).time(billData.getTime()).billing(billData.getBilling() * exchangeRate).build();
    modifiedData.setId(billData.getId());

    return dataIO.update(modifiedData);
  }

  public boolean delete(int id) {
    return dataIO.delete(id);
  }
}
