package discount.plugin;

public class DiscountEntity {
  private int refId;
  private double discount;

  public DiscountEntity(int refId, double discount) {
    this.refId = refId;
    this.discount = discount;
  }
}
