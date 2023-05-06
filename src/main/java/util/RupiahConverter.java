package util;

import java.util.Locale;
import java.text.NumberFormat;

public class RupiahConverter {
  public static String convert(double value) {
    Locale locale = new Locale("in", "ID");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
    return formatter.format(value);
  }
}
