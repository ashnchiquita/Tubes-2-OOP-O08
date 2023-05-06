package boundary.observer.pembelian;

public class PembelianEvent {
  public String type, title, subtitle, imagePath;
  public double price;
  public int index, id;

  public PembelianEvent(String type) {
    this.type = type;
  }

  public PembelianEvent(String type, int id, String title, String subtitle, double price, String imagePath) {
    this.type = type;
    this.id = id;
    this.title = title;
    this.subtitle = subtitle;
    this.price = price;
    this.imagePath = imagePath;
  }

  public PembelianEvent(String type, int index) {
    this.type = type;
    this.index = index;
  }
}
