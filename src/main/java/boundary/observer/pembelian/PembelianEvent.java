package boundary.observer.pembelian;

import model.Barang;

public class PembelianEvent {
  public String type;
  public Barang barang;

  public PembelianEvent(String type) {
    this.type = type;
  }

  public PembelianEvent(String type, Barang barang) {
    this.type = type;
    this.barang = barang;
  }
}