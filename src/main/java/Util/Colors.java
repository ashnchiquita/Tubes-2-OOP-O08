package Util;

import java.awt.*;

public enum Colors {
  LIGHT_BLUE(new Color(56, 100, 194)),
  DARK_BLUE(new Color(36, 60, 148)),
  WHITE(new Color(255, 255, 255)),
  ORANGE(new Color(229, 151, 0)),
  LIGHT_GRAY(new Color(217, 217, 217)),
  RED(new Color(236, 102, 102)),
  MED_GRAY(new Color(151, 151, 151)),
  BUTTON_BLUE(new Color(74, 104, 222)),
  PEMBELIAN_RED(new Color(244, 114, 221)),
  PEMBELIAN_BLUE(new Color(16, 37, 109)),
  PEMBELIAN_BLUE_LIGHT(new Color(26, 114, 221));

  private final Color rgb;

  public Color getColor() {
    return rgb;
  }

  private Colors(Color color) {
    rgb = color;
  }
}