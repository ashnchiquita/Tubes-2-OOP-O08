package boundary.widget;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

import boundary.constants.ResourcePath;
import model.Barang;
import util.RupiahConverter;
import boundary.observer.pembelian.PembelianEvent;
import boundary.observer.pembelian.PembelianObserver;
import boundary.constants.Colors;

import java.awt.geom.RoundRectangle2D;

public class PembelianCard extends RoundedPanel {
  private PembelianObserver observer;

  public PembelianCard(Barang barang) {
    super(20, Colors.WHITE, true, Colors.MED_GRAY, 1);

    this.setLayout(null);

    RoundedPanel imageContainer = new RoundedPanel(20, Colors.WHITE, false, Colors.WHITE, 0);
    imageContainer.setBounds(1, 1, 153, 91);
    imageContainer.setLayout(null);
    imageContainer.setOpaque(false);
    this.add(imageContainer);

    try {
      Integer width = 184, height = 111;
      BufferedImage image = ImageIO.read(new File(barang.getGambar()));
      Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

      BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = outputImage.createGraphics();
      g2d.setComposite(AlphaComposite.Src);
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      g2d.setColor(Colors.WHITE);
      g2d.fill(new RoundRectangle2D.Float(0, 0, width, height, 50, 50));

      g2d.setComposite(AlphaComposite.Src);
      g2d.drawImage(scaledImage, 0, 0, null);
      g2d.dispose();

      JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
      imageLabel.setBackground(Colors.RED);
      imageLabel.setBounds(0, 10, 153, 91);
      imageLabel.setOpaque(false);
      imageContainer.add(imageLabel);
    } catch (Exception e) {
      e.printStackTrace();
    }

    JPanel bottomContainer = new JPanel();
    bottomContainer.setBounds(1, 96, 150, 78);
    bottomContainer.setPreferredSize(new Dimension(154, 78));
    bottomContainer.setBackground(Colors.WHITE);
    bottomContainer.setLayout(null);
    this.add(bottomContainer);

    JLabel titleLabel = new JLabel(barang.getName());
    titleLabel.setFont(new Font("Rubik Light", Font.BOLD, 14));
    titleLabel.setBounds(10, 8, 136, 16);
    bottomContainer.add(titleLabel);

    JLabel subtitleLabel = new JLabel(barang.getKategori());
    subtitleLabel.setFont(new Font("Rubik Light", Font.PLAIN, 8));
    subtitleLabel.setForeground(new Color(42, 50, 86));
    subtitleLabel.setBounds(10, 27, 136, 10);
    bottomContainer.add(subtitleLabel);

    JLabel priceLabel = new JLabel(RupiahConverter.convert(barang.getHargaJual()));
    priceLabel.setFont(new Font("Rubik Light", Font.BOLD, 12));
    priceLabel.setForeground(Colors.PEMBELIAN_BLUE_LIGHT);
    priceLabel.setBounds(10, 54, 90, 16);
    bottomContainer.add(priceLabel);

    RoundedPanel buttonContainer = new RoundedPanel(20, Colors.PEMBELIAN_BLUE_LIGHT, false, Colors.BLACK, 0);
    buttonContainer.setBounds(113, 48, 32, 30);
    buttonContainer.setOpaque(false);
    buttonContainer.setLayout(null);

    ImageIcon buttonImage = new ImageIcon(ResourcePath.ICON+ "/plus.png");
    ImageIcon buttonImageScaled = new ImageIcon(
        buttonImage.getImage().getScaledInstance(16, 18, java.awt.Image.SCALE_SMOOTH));
    JButton addButton = new JButton(buttonImageScaled);
    addButton.setBounds(2, 2, 28, 26);
    addButton.setBackground(Colors.PEMBELIAN_BLUE_LIGHT);
    addButton.setBorder(null);
    addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    addButton
        .addActionListener(e -> observer.newEvent(new PembelianEvent("ADD", barang)));
    addButton.setFocusPainted(false);
    addButton.setOpaque(false);

    buttonContainer.add(addButton);
    bottomContainer.add(buttonContainer);
  }

  public void setObserver(PembelianObserver observer) {
    this.observer = observer;
  }
}