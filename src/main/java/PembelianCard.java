import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PembelianCard {
  String title;
  String subtitle;
  String price;

  public PembelianCard(String title, String subtitle, String price) {
    this.title = title;
    this.subtitle = subtitle;
    this.price = price;
  }

  static class RoundedPanel extends JPanel {
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

  static class RoundedButton extends JButton {
    private int radius;
    private Color color;

    public RoundedButton(int radius, Color color, ImageIcon icon) {
      super(icon);
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

  private static void showUI() {
    JFrame mainFrame = new JFrame("Test Window");
    mainFrame.setSize(1280, 720);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLayout(null);
    mainFrame.getContentPane().setBackground(Color.LIGHT_GRAY);

    // ============ CARD STARTS HERE ==============
    RoundedPanel container = new RoundedPanel(20, Color.WHITE);
    container.setBounds(100, 100, 156, 186);
    container.setOpaque(false);
    container.setLayout(null);

    RoundedPanel imageContainer = new RoundedPanel(20, Color.WHITE);
    imageContainer.setBounds(0, 0, 156, 91);
    imageContainer.setLayout(null);
    imageContainer.setOpaque(false);
    container.add(imageContainer);

    try {
      BufferedImage image = ImageIO
          .read(new File("/home/rma1403/Documents/Programming/kuliah/Tubes-2-OOP-O08/src/main/java/beef-contoh.png"));
      int width = 184;
      int height = 111;
      Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

      BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = outputImage.createGraphics();
      g2d.setComposite(AlphaComposite.Src);
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      g2d.setColor(Color.WHITE);
      g2d.fill(new RoundRectangle2D.Float(0, 0, width, height, 50, 50));

      g2d.setComposite(AlphaComposite.Src);
      g2d.drawImage(scaledImage, 0, 0, null);
      g2d.dispose();

      JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
      imageLabel.setBackground(Color.RED);
      imageLabel.setBounds(0, 10, 156, 91);
      imageLabel.setOpaque(false);
      imageContainer.add(imageLabel);
    } catch (Exception e) {
      System.out.println(e);
    }

    JPanel bottomContainer = new JPanel();
    bottomContainer.setBounds(0, 96, 156, 78);
    bottomContainer.setBackground(Color.WHITE);
    bottomContainer.setLayout(null);
    container.add(bottomContainer);

    JLabel titleLabel = new JLabel("Salad Tuna");
    titleLabel.setFont(new Font("Rubik Light", Font.BOLD, 14));
    titleLabel.setBounds(10, 8, 136, 16);
    bottomContainer.add(titleLabel);

    JLabel subtitleLabel = new JLabel("(Must choose level)");
    subtitleLabel.setFont(new Font("Rubik Light", Font.PLAIN, 8));
    subtitleLabel.setForeground(new Color(42, 50, 86));
    subtitleLabel.setBounds(10, 27, 136, 10);
    bottomContainer.add(subtitleLabel);

    JLabel priceLabel = new JLabel("$10.99");
    priceLabel.setFont(new Font("Rubik Light", Font.BOLD, 14));
    priceLabel.setForeground(new Color(26, 114, 221));
    priceLabel.setBounds(10, 54, 58, 16);
    bottomContainer.add(priceLabel);

    RoundedPanel buttonContainer = new RoundedPanel(20, new Color(26, 114, 221));
    buttonContainer.setBounds(113, 48, 32, 30);
    buttonContainer.setOpaque(false);
    buttonContainer.setLayout(null);

    ImageIcon buttonImage = new ImageIcon(
        "/home/rma1403/Documents/Programming/kuliah/Tubes-2-OOP-O08/src/main/java/plus.png");
    JButton addButton = new JButton(buttonImage);
    addButton.setBounds(10, 8, 12, 14);
    addButton.setBackground(new Color(26, 114, 221));
    addButton.setBorder(null);
    addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    addButton.addActionListener(e -> System.out.println("DIPENCET"));

    buttonContainer.add(addButton);
    bottomContainer.add(buttonContainer);
    // ============ CARD ENDS HERE ================

    mainFrame.add(container);
    mainFrame.setVisible(true);
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        showUI();
      }
    });
  }
}