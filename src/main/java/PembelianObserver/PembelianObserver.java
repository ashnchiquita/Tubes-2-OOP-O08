package PembelianObserver;

import java.util.ArrayList;

public class PembelianObserver {
  ArrayList<PembelianHandler> listeners = new ArrayList<>();

  public void addListener(PembelianHandler newListener) {
    listeners.add(newListener);
  }

  public void newEvent(PembelianEvent e) {
    if (e.type == "ADD") {
      for (PembelianHandler listener : listeners) {
        listener.handleAddItem(e);
      }
    } else if (e.type == "REMOVE") {
      for (PembelianHandler listener : listeners) {
        listener.handleRemoveItem(e);
      }

    }
  }
}
