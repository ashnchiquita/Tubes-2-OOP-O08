package PembelianUI;

import javax.swing.*;
import java.awt.*;

public class HintTextField extends JTextField {
  public HintTextField(String hint, int col) {
    super(col);
    _hint = hint;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    if (getText().length() == 0) {
      int h = getHeight();
      ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      Insets ins = getInsets();
      FontMetrics fm = g.getFontMetrics();
      // TODO : Setup color
      // g.setColor(Colors.LIGHT_GRAY.getColor());
      g.setColor(new Color(217, 217, 217));
      g.drawString(_hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
    }
  }

  private final String _hint;
}