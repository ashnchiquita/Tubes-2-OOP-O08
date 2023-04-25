package PembelianUI;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import PembelianObserver.PembelianObserver;
import PembelianObserver.PembelianEvent;
import Util.RupiahConverter;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import java.awt.geom.RoundRectangle2D;

public class PembelianList extends RoundedPanel {
  public String title;

  private ActionListener closeListener;
  private JButton closeButton = new JButton();
  private PembelianObserver observer;

  public PembelianList(String title, String subtitle, int count, float price, String imagePath, int index) {
    super(21, new Color(16, 37, 109), false, Color.WHITE, 0);
    this.title = title;

    this.setLayout(new BorderLayout());
    this.setBorder(new EmptyBorder(18, 15, 20, 15));

    RoundedPanel imageContainer = new RoundedPanel(20, new Color(16, 37, 109), false, Color.WHITE, 0);
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
    textPanel.setBackground(new Color(16, 37, 109));
    this.add(textPanel, BorderLayout.CENTER);

    JLabel titleText = new JLabel(title);
    titleText.setBounds(15, 0, 160, 20);
    titleText.setForeground(Color.WHITE);
    titleText.setFont(new Font("Inter", Font.BOLD, 15));
    textPanel.add(titleText);

    JLabel subtitleText = new JLabel(subtitle);
    subtitleText.setBounds(15, 27, 160, 20);
    subtitleText.setForeground(new Color(149, 172, 255));
    subtitleText.setFont(new Font("Inter", Font.PLAIN, 15));
    textPanel.add(subtitleText);

    JLabel countText = new JLabel(String.valueOf(count) + "x");
    countText.setBounds(15, 46, 160, 20);
    countText.setForeground(new Color(149, 172, 255));
    countText.setFont(new Font("Inter", Font.PLAIN, 15));
    textPanel.add(countText);

    JPanel closePanel = new JPanel(null);
    closePanel.setPreferredSize(new Dimension(55, 100));
    closePanel.setBackground(new Color(16, 37, 109));
    this.add(closePanel, BorderLayout.EAST);

    RoundedPanel buttonContainer = new RoundedPanel(20, new Color(244, 38, 26), false, Color.BLACK, 0);
    buttonContainer.setBounds(10, 0, 32, 30);
    buttonContainer.setOpaque(false);
    buttonContainer.setLayout(null);
    closePanel.add(buttonContainer);

    ImageIcon buttonImage = new ImageIcon(
        "/home/rma1403/Documents/Programming/kuliah/Tubes-2-OOP-O08/src/main/java/img/cross.png");
    ImageIcon buttonImageScaled = new ImageIcon(
        buttonImage.getImage().getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH));
    closeButton.setIcon(buttonImageScaled);
    closeButton.setBounds(2, 2, 28, 26);
    closeButton.setBackground(new Color(244, 114, 221));
    closeButton.setBorder(null);
    closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    closeListener = e -> observer.newEvent(new PembelianEvent("REMOVE", index));
    closeButton.addActionListener(closeListener);
    closeButton.setFocusPainted(false);
    closeButton.setOpaque(false);
    buttonContainer.add(closeButton);

    JLabel priceText = new JLabel(RupiahConverter.convert(price));
    priceText.setForeground(Color.WHITE);
    priceText.setFont(new Font("Inter", Font.BOLD, 10));
    priceText.setBounds(0, 46, 55, 20);
    closePanel.add(priceText);
  }

  public void setObserver(PembelianObserver observer) {
    this.observer = observer;
  }

  public void setIndex(int index) {
    closeButton.removeActionListener(closeListener);
    closeListener = e -> observer.newEvent(new PembelianEvent("REMOVE", index));
    closeButton.addActionListener(closeListener);
  }
}
