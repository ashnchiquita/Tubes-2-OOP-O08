package boundari.observer.pembelian;

import java.util.ArrayList;

public class PembelianObserver {
  ArrayList<PembelianListener> listeners = new ArrayList<>();

  public void addListener(PembelianListener newListener) {
    listeners.add(newListener);
  }

  public void newEvent(PembelianEvent e) {
    if (e.type == "ADD") {
      for (PembelianListener listener : listeners) {
        listener.handleAddItem(e);
      }
    } else if (e.type == "REMOVE") {
      for (PembelianListener listener : listeners) {
        listener.handleRemoveItem(e);
      }
    }
  }
}
