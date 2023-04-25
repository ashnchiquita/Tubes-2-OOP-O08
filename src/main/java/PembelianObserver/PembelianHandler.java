package PembelianObserver;

public interface PembelianHandler {
  void handleAddItem(PembelianEvent e);

  void handleRemoveItem(PembelianEvent e);
}
