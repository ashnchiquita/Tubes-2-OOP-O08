package PembelianUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import javax.swing.event.ChangeListener;

import PembelianObserver.PembelianHandler;
import PembelianObserver.PembelianObserver;
import PembelianObserver.PembelianEvent;
import Util.RupiahConverter;

import javax.swing.event.ChangeEvent;

public class PembelianPanel extends JPanel implements PembelianHandler {
  private int vw = 1280, vh = 720;
  private float sub = 0f, discount = 0f, tax = 0f, total = 0f;

  ArrayList<PembelianList> buyItemList = new ArrayList<>();
  JPanel buyListPanel = new JPanel();

  PembelianObserver observer = new PembelianObserver();

  public PembelianPanel() {
    this.observer.addListener(this);
    this.setLayout(new BorderLayout());

    JScrollPane scrollGridPanel = new JScrollPane();
    scrollGridPanel.setBackground(Color.LIGHT_GRAY);
    this.add(scrollGridPanel, BorderLayout.CENTER);

    JPanel buyPanel = new JPanel(new BorderLayout());
    buyPanel.setPreferredSize(new Dimension((int) (0.31 * vw), vh));
    buyPanel.setBackground(new Color(36, 60, 148));
    this.add(buyPanel, BorderLayout.EAST);

    JPanel gridPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    gridPanel.setBorder(new EmptyBorder(78, 0, 78, 0));
    gridPanel.setBackground(Color.WHITE);
    scrollGridPanel.setViewportView(gridPanel);

    ArrayList<JPanel> gridItemList = new ArrayList<>();

    for (int i = 0; i < data.length; i++) {
      JPanel container = new JPanel(new BorderLayout());
      container.setBorder(new EmptyBorder(16, 16, 16, 16));
      container.setBackground(Color.WHITE);
      container.setDoubleBuffered(true);
      c.gridx = i % 3;
      c.gridy = i / 3;

      PembelianCard card = new PembelianCard(data[i].title, data[i].subtitle, data[i].price, data[i].imagePath);
      card.setObserver(observer);
      card.setPreferredSize(new Dimension(155, 185));
      container.add(card, BorderLayout.CENTER);
      gridPanel.add(container, c);

      gridItemList.add(container);
    }

    scrollGridPanel.getViewport().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        gridItemList.forEach(gridItemList -> gridItemList.repaint());
      }
    });

    JPanel orderNumPanel = new JPanel();
    orderNumPanel.setBackground(new Color(36, 60, 148));
    orderNumPanel.setPreferredSize(new Dimension(400, 69));
    orderNumPanel.setLayout(null);
    buyPanel.add(orderNumPanel, BorderLayout.NORTH);

    JLabel orderLabel = new JLabel("Order");
    orderLabel.setFont(new Font("Inter", Font.BOLD, 24));
    orderLabel.setForeground(Color.WHITE);
    orderLabel.setBounds(14, 0, 79, 32);
    orderNumPanel.add(orderLabel);

    JLabel orderNumLabel = new JLabel("#240");
    orderNumLabel.setFont(new Font("Inter", Font.PLAIN, 24));
    orderNumLabel.setForeground(Color.WHITE);
    orderNumLabel.setBounds(96, 0, 79, 32);
    orderNumPanel.add(orderNumLabel);

    JPanel orderLine = new JPanel();
    orderLine.setBounds(14, 45, (int) (0.28 * vw), 1);
    orderNumPanel.add(orderLine);

    JPanel orderSummaryPanel = new JPanel(new BorderLayout());
    orderSummaryPanel.setBackground(new Color(36, 60, 148));
    orderSummaryPanel.setPreferredSize(new Dimension(400, 262));
    buyPanel.add(orderSummaryPanel, BorderLayout.SOUTH);

    JPanel summaryTextPanel = new JPanel(new GridBagLayout());
    summaryTextPanel.setBackground(new Color(36, 60, 148));
    Font summaryFont = new Font("Inter", Font.BOLD, 17);

    c.gridx = 0;
    c.gridy = 0;
    JPanel subTextContainer = new JPanel(new BorderLayout());
    subTextContainer.setBackground(new Color(36, 60, 148));
    subTextContainer.setBorder(new EmptyBorder(15, 0, 10, 0));
    JLabel subText = new JLabel("Sub");
    subText.setPreferredSize(new Dimension(80, 18));
    subText.setForeground(Color.WHITE);
    subText.setFont(summaryFont);
    subTextContainer.add(subText, BorderLayout.CENTER);
    summaryTextPanel.add(subTextContainer, c);

    c.gridx = 1;
    c.gridy = 0;
    JPanel subValueContainer = new JPanel(new BorderLayout());
    subValueContainer.setBackground(new Color(36, 60, 148));
    orderSummaryPanel.setBackground(new Color(36, 60, 148));
    subValueContainer.setBorder(new EmptyBorder(15, 0, 10, 0));
    JLabel subValue = new JLabel(RupiahConverter.convert(sub));
    subValue.setPreferredSize(new Dimension(266, 18));
    subValue.setForeground(Color.WHITE);
    subValue.setFont(summaryFont);
    subValue.setHorizontalAlignment(SwingConstants.RIGHT);
    subValueContainer.add(subValue, BorderLayout.CENTER);
    summaryTextPanel.add(subValueContainer, c);

    c.gridx = 0;
    c.gridy = 1;
    JPanel discountTextContainer = new JPanel(new BorderLayout());
    discountTextContainer.setBackground(new Color(36, 60, 148));
    discountTextContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
    JLabel discountText = new JLabel("Discount");
    discountText.setPreferredSize(new Dimension(80, 18));
    discountText.setForeground(Color.WHITE);
    discountText.setFont(summaryFont);
    discountTextContainer.add(discountText, BorderLayout.CENTER);
    summaryTextPanel.add(discountTextContainer, c);

    c.gridx = 1;
    c.gridy = 1;
    JPanel discountValueContainer = new JPanel(new BorderLayout());
    discountValueContainer.setBackground(new Color(36, 60, 148));
    discountValueContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
    JLabel discountValue = new JLabel(RupiahConverter.convert(discount));
    discountValue.setPreferredSize(new Dimension(266, 18));
    discountValue.setForeground(Color.WHITE);
    discountValue.setFont(summaryFont);
    discountValue.setHorizontalAlignment(SwingConstants.RIGHT);
    discountValueContainer.add(discountValue, BorderLayout.CENTER);
    summaryTextPanel.add(discountValueContainer, c);

    c.gridx = 0;
    c.gridy = 2;
    JPanel taxTextContainer = new JPanel(new BorderLayout());
    taxTextContainer.setBackground(new Color(36, 60, 148));
    taxTextContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
    JLabel taxText = new JLabel("Tax");
    taxText.setPreferredSize(new Dimension(80, 18));
    taxText.setForeground(Color.WHITE);
    taxText.setFont(summaryFont);
    taxTextContainer.add(taxText, BorderLayout.CENTER);
    summaryTextPanel.add(taxTextContainer, c);

    c.gridx = 1;
    c.gridy = 2;
    JPanel taxValueContainer = new JPanel(new BorderLayout());
    taxValueContainer.setBackground(new Color(36, 60, 148));
    taxValueContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
    JLabel taxValue = new JLabel(RupiahConverter.convert(tax));
    taxValue.setPreferredSize(new Dimension(266, 18));
    taxValue.setForeground(Color.WHITE);
    taxValue.setFont(summaryFont);
    taxValue.setHorizontalAlignment(SwingConstants.RIGHT);
    taxValueContainer.add(taxValue, BorderLayout.CENTER);
    summaryTextPanel.add(taxValueContainer, c);

    c.gridx = 0;
    c.gridy = 3;
    JPanel totalTextContainer = new JPanel(new BorderLayout());
    totalTextContainer.setBackground(new Color(36, 60, 148));
    JLabel totalText = new JLabel("Total");
    totalText.setPreferredSize(new Dimension(80, 18));
    totalText.setForeground(Color.WHITE);
    totalText.setFont(summaryFont);
    totalTextContainer.add(totalText, BorderLayout.CENTER);
    summaryTextPanel.add(totalTextContainer, c);

    c.gridx = 1;
    c.gridy = 3;
    JPanel totalValueContainer = new JPanel(new BorderLayout());
    totalValueContainer.setBackground(new Color(36, 60, 148));
    JLabel totalValue = new JLabel(RupiahConverter.convert(total));
    totalValue.setPreferredSize(new Dimension(266, 18));
    totalValue.setForeground(Color.WHITE);
    totalValue.setFont(summaryFont);
    totalValue.setHorizontalAlignment(SwingConstants.RIGHT);
    totalValueContainer.add(totalValue, BorderLayout.CENTER);
    summaryTextPanel.add(totalValueContainer, c);

    orderSummaryPanel.add(summaryTextPanel, BorderLayout.NORTH);

    JPanel buttonPanel = new JPanel(new BorderLayout());
    buttonPanel.setBorder(new EmptyBorder(51, 27, 43, 22));
    buttonPanel.setBackground(new Color(36, 60, 148));
    orderSummaryPanel.add(buttonPanel, BorderLayout.CENTER);

    RoundedPanel cancelContainer = new RoundedPanel(9, new Color(36, 60, 148), true, new Color(236, 102, 102), 2);
    cancelContainer.setOpaque(false);
    cancelContainer.setLayout(null);
    cancelContainer.setPreferredSize(new Dimension(134, 49));
    JButton cancelButton = new JButton("Cancel");
    cancelButton.setBackground(new Color(36, 60, 148));
    cancelButton.setForeground(new Color(236, 102, 102));
    cancelButton.setFont(new Font("Inter", Font.BOLD, 17));
    cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    cancelButton.setBounds(4, 4, 126, 41);
    cancelButton.setBorder(null);
    cancelButton.setFocusPainted(false);
    cancelContainer.add(cancelButton);
    buttonPanel.add(cancelContainer, BorderLayout.WEST);

    RoundedPanel checkoutContainer = new RoundedPanel(42, new Color(74, 107, 222), false, Color.WHITE, 0);
    checkoutContainer.setOpaque(false);
    checkoutContainer.setLayout(null);
    checkoutContainer.setPreferredSize(new Dimension(205, 49));
    JButton checkoutButton = new JButton("Checkout");
    checkoutButton.setFont(new Font("Inter", Font.BOLD, 17));
    checkoutButton.setBackground(new Color(74, 107, 222));
    checkoutButton.setForeground(Color.WHITE);
    checkoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    checkoutButton.setBorder(null);
    checkoutButton.setFocusPainted(false);
    checkoutButton.setBounds(10, 4, 185, 41);
    checkoutContainer.add(checkoutButton);
    buttonPanel.add(checkoutContainer, BorderLayout.EAST);

    buyListPanel.setBackground(new Color(36, 60, 148));
    buyListPanel.setLayout(new BoxLayout(buyListPanel, BoxLayout.Y_AXIS));

    JScrollPane buyListScroll = new JScrollPane(buyListPanel);
    buyListScroll.getViewport().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        buyItemList.forEach(item -> item.repaint());
      }
    });
    buyListScroll.setBorder(null);
    buyPanel.add(buyListScroll, BorderLayout.CENTER);
  }

  public void handleAddItem(PembelianEvent e) {
    PembelianList temp = new PembelianList(e.title, e.subtitle, 1, e.price,
        e.imagePath, buyItemList.size());
    temp.setPreferredSize(new Dimension(0, 103));
    temp.setMaximumSize(new Dimension(346, 103));
    temp.setObserver(observer);
    buyItemList.add(temp);
    buyListPanel.add(buyItemList.get(buyItemList.size() - 1));
    buyListPanel.add(Box.createRigidArea(new Dimension(0, 15)));

    this.revalidate();
  }

  public void handleRemoveItem(PembelianEvent e) {
    buyListPanel.removeAll();
    buyItemList.remove(e.index);
    for (int i = 0; i < buyItemList.size(); i++) {
      buyItemList.get(i).setIndex(i);
      buyListPanel.add(buyItemList.get(i));
      buyListPanel.add(Box.createRigidArea(new Dimension(0, 15)));
    }
    buyListPanel.revalidate();
    buyListPanel.repaint();
    this.revalidate();
  }

  private static class DataObj {
    public String title, subtitle, imagePath;
    public float price;

    public DataObj(String title, String subtitle, float price, String imagePath) {
      this.title = title;
      this.subtitle = subtitle;
      this.price = price;
      this.imagePath = imagePath;
    }
  }

  private static DataObj[] data = {
      new DataObj("Salad Tuna", "(Must choose level)", 10.99f,
          "/home/rma1403/Documents/Programming/kuliah/Tubes-2-OOP-O08/src/main/java/img/salad-tuna.png"),
      new DataObj("Beef Contoh", "", 10.99f,
          "/home/rma1403/Documents/Programming/kuliah/Tubes-2-OOP-O08/src/main/java/img/beef-contoh.png"),
      new DataObj("Iga Bakar", "(Must choose level)", 10.99f,
          "/home/rma1403/Documents/Programming/kuliah/Tubes-2-OOP-O08/src/main/java/img/iga-bakar.png"),
      new DataObj("Salad Egg", "", 10.99f,
          "/home/rma1403/Documents/Programming/kuliah/Tubes-2-OOP-O08/src/main/java/img/salad-egg.png"),
      new DataObj("Salad Tuna", "(Must choose level)", 10.99f,
          "/home/rma1403/Documents/Programming/kuliah/Tubes-2-OOP-O08/src/main/java/img/salad-tuna.png"),
      new DataObj("Beef Contoh", "", 10.99f,
          "/home/rma1403/Documents/Programming/kuliah/Tubes-2-OOP-O08/src/main/java/img/beef-contoh.png"),
      new DataObj("Iga Bakar", "(Must choose level)", 10.99f,
          "/home/rma1403/Documents/Programming/kuliah/Tubes-2-OOP-O08/src/main/java/img/iga-bakar.png"),
      new DataObj("Salad Egg", "", 10.99f,
          "/home/rma1403/Documents/Programming/kuliah/Tubes-2-OOP-O08/src/main/java/img/salad-egg.png"),

  };

}
