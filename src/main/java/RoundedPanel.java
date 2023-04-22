import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

class RoundedPanel extends JPanel {
  private int radius;
  private Color color;

  public RoundedPanel(int radius, Color color) {
    this.radius = radius;
    this.color = color;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g.create();
    RoundRectangle2D rect = new RoundRectangle2D.Float(0f, 0f, getWidth(), getHeight(), radius, radius);
    g2.setClip(rect);
    g2.setColor(color);
    g2.fillRect(0, 0, getWidth(), getHeight());
    g2.dispose();
  }
}
