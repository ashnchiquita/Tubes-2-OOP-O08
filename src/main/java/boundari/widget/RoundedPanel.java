package boundari.widget;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel {
  private int radius, borderThickness;
  private Color bgColor, borderColor;
  private boolean isBorder;

  public RoundedPanel(int radius, Color bgColor, boolean isBorder, Color borderColor, int borderThickness) {
    this.radius = radius;
    this.bgColor = bgColor;
    this.isBorder = isBorder;
    this.borderThickness = borderThickness;
    this.borderColor = borderColor;
    this.setOpaque(false);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g.create();
    if (isBorder) {
      g2.setColor(borderColor);
      g2.setStroke(new BasicStroke(borderThickness));
      RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
          0, 0,
          getWidth() - borderThickness, getHeight() - borderThickness,
          radius, radius);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.draw(roundedRectangle);
    }

    RoundRectangle2D rect = new RoundRectangle2D.Float(borderThickness, borderThickness,
        getWidth() - 2 * borderThickness,
        getHeight() - 2 * borderThickness, radius, radius);
    g2.setClip(rect);
    g2.setColor(bgColor);
    g2.fillRect(0, 0, getWidth(), getHeight());
    g2.dispose();
  }
}
