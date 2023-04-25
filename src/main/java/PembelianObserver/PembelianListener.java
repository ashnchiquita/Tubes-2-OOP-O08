package PembelianObserver;

public interface PembelianListener {
  void handleAddItem(PembelianEvent e);

  void handleRemoveItem(PembelianEvent e);
}
