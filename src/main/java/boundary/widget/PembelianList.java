package boundary.widget;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import boundary.constants.Colors;
import boundary.constants.ResourcePath;
import model.Barang;
import util.RupiahConverter;
import boundary.observer.pembelian.PembelianEvent;
import boundary.observer.pembelian.PembelianObserver;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.geom.RoundRectangle2D;

public class PembelianList extends RoundedPanel {
  public Barang barang;
  public int count;
  public double price;
  public JLabel titleText;
  private ActionListener closeListener;
  private JButton closeButton = new JButton();
  private PembelianObserver observer;

  JLabel countText = new JLabel(String.valueOf(count) + "x");
  JLabel priceText = new JLabel(RupiahConverter.convert(price));

  public PembelianList(Barang barang) {
    super(21, Colors.PEMBELIAN_BLUE, false, Color.WHITE, 0);
    this.barang = barang;

    String title = barang.getName();
    String subtitle = "";
    int count = 1;
    double price = barang.getHargaJual();
    String imagePath = barang.getGambar();
    int index = barang.getId();

    this.count = count;
    this.price = price;
    subtitle = "";

    this.setLayout(new BorderLayout());
    this.setBorder(new EmptyBorder(18, 15, 20, 15));

    RoundedPanel imageContainer = new RoundedPanel(20, Colors.PEMBELIAN_BLUE, false, Color.WHITE, 0);
    imageContainer.setPreferredSize(new Dimension(103, 64));
    imageContainer.setLayout(null);
    this.add(imageContainer, BorderLayout.WEST);

    try {
      int width = 124, height = 84;
      BufferedImage image = ImageIO.read(new File(imagePath));
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
      imageLabel.setBounds(0, 2, 103, 64);
      imageLabel.setOpaque(false);
      imageContainer.add(imageLabel);
    } catch (Exception e) {
      e.printStackTrace();
    }

    JPanel textPanel = new JPanel(null);
    textPanel.setBackground(Colors.PEMBELIAN_BLUE);
    this.add(textPanel, BorderLayout.CENTER);

    titleText = new JLabel(title);
    titleText.setBounds(15, 0, 105, 20);
    titleText.setForeground(Color.WHITE);
    titleText.setFont(new Font("Inter", Font.BOLD, 15));
    textPanel.add(titleText);

    JLabel subtitleText = new JLabel(subtitle);
    subtitleText.setBounds(15, 27, 160, 20);
    subtitleText.setForeground(Colors.SKY_BLUE);
    subtitleText.setFont(new Font("Inter", Font.PLAIN, 15));
    textPanel.add(subtitleText);

    countText.setBounds(15, 46, 160, 20);
    countText.setForeground(Colors.SKY_BLUE);
    countText.setFont(new Font("Inter", Font.PLAIN, 15));
    this.setCount(count);
    textPanel.add(countText);

    JPanel closePanel = new JPanel(null);
    closePanel.setPreferredSize(new Dimension(100, 100));
    closePanel.setBackground(Colors.PEMBELIAN_BLUE);
    closePanel.setOpaque(false);
    this.add(closePanel, BorderLayout.EAST);

    RoundedPanel buttonContainer = new RoundedPanel(20, Colors.LOMBOK_RED, false, Color.BLACK, 0);
    buttonContainer.setBounds(70, 0, 32, 30);
    buttonContainer.setOpaque(false);
    buttonContainer.setLayout(null);
    closePanel.add(buttonContainer);

    ImageIcon buttonImage = new ImageIcon(ResourcePath.ICON + "/cross.png");
    ImageIcon buttonImageScaled = new ImageIcon(
        buttonImage.getImage().getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH));
    closeButton.setIcon(buttonImageScaled);
    closeButton.setBounds(2, 2, 28, 26);
    closeButton.setBackground(Colors.PEMBELIAN_RED);
    closeButton.setBorder(null);
    closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    closeListener = e -> observer.newEvent(new PembelianEvent("REMOVE", barang));
    closeButton.addActionListener(closeListener);
    closeButton.setFocusPainted(false);
    closeButton.setOpaque(false);
    buttonContainer.add(closeButton);

    priceText.setForeground(Color.WHITE);
    priceText.setFont(new Font("Inter", Font.BOLD, 10));
    priceText.setBounds(0, 46, 100, 20);
    priceText.setHorizontalAlignment(SwingConstants.RIGHT);
    closePanel.add(priceText);
  }

  public void setObserver(PembelianObserver observer) {
    this.observer = observer;
  }

  public void setIndex(int index) {
    closeButton.removeActionListener(closeListener);
    closeListener = e -> observer.newEvent(new PembelianEvent("REMOVE", barang));
    closeButton.addActionListener(closeListener);
  }

  public void setCount(int count) {
    this.count = count;
    countText.setText(String.valueOf(count) + "x");
    priceText.setText(RupiahConverter.convert(count * price));
  }
}
