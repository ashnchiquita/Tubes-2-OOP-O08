package PembelianUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import javax.swing.event.ChangeListener;

import PembelianObserver.PembelianListener;
import PembelianObserver.PembelianObserver;
import PembelianObserver.PembelianEvent;
import Util.RupiahConverter;

import javax.swing.event.ChangeEvent;

public class PembelianPanel extends JPanel implements PembelianListener {
  private int vw = 1280, vh = 720;
  private float sub = 0f, discount = 0f, tax = 0f, total = 0f;

  PembelianObserver observer = new PembelianObserver();
  ArrayList<Integer> buyIdList = new ArrayList<>();

  ArrayList<JPanel> gridItemList = new ArrayList<>();
  ArrayList<PembelianList> buyItemList = new ArrayList<>();

  // UI Components
  JScrollPane scrollGridPanel = new JScrollPane();
  JPanel buyPanel = new JPanel(new BorderLayout());
  JPanel gridPanel = new JPanel(new GridBagLayout());

  JPanel orderNumPanel = new JPanel();
  JLabel orderLabel = new JLabel("Order");
  JLabel orderNumLabel = new JLabel("");
  JPanel orderLine = new JPanel();

  JPanel orderSummaryPanel = new JPanel(new BorderLayout());
  JPanel summaryTextPanel = new JPanel(new GridBagLayout());

  JPanel subTextContainer = new JPanel(new BorderLayout());
  JLabel subText = new JLabel("Sub");

  JPanel subValueContainer = new JPanel(new BorderLayout());
  JLabel subValue = new JLabel(RupiahConverter.convert(sub));

  JPanel discountTextContainer = new JPanel(new BorderLayout());
  JLabel discountText = new JLabel("Discount");

  JPanel discountValueContainer = new JPanel(new BorderLayout());
  JLabel discountValue = new JLabel(RupiahConverter.convert(discount));

  JPanel taxTextContainer = new JPanel(new BorderLayout());
  JLabel taxText = new JLabel("Tax");

  JPanel taxValueContainer = new JPanel(new BorderLayout());
  JLabel taxValue = new JLabel(RupiahConverter.convert(tax));

  JPanel totalTextContainer = new JPanel(new BorderLayout());
  JLabel totalText = new JLabel("Total");

  JPanel totalValueContainer = new JPanel(new BorderLayout());
  JLabel totalValue = new JLabel(RupiahConverter.convert(total));

  JPanel buttonPanel = new JPanel(new BorderLayout());
  RoundedPanel cancelContainer = new RoundedPanel(9, new Color(36, 60, 148), true, new Color(236, 102, 102), 2);
  JButton cancelButton = new JButton("Cancel");
  RoundedPanel checkoutContainer = new RoundedPanel(42, new Color(74, 107, 222), false, Color.WHITE, 0);
  JButton checkoutButton = new JButton("Checkout");

  JPanel buyListPanel = new JPanel();
  JScrollPane buyListScroll = new JScrollPane(buyListPanel);

  public PembelianPanel(int orderNumber) {
    this.observer.addListener(this);
    this.orderNumLabel.setText("#" + String.valueOf(orderNumber));
    this.initializeUI();
  }

  private void initializeUI() {
    this.setLayout(new BorderLayout());

    scrollGridPanel.setBackground(Color.LIGHT_GRAY);
    this.add(scrollGridPanel, BorderLayout.CENTER);

    buyPanel.setPreferredSize(new Dimension((int) (0.31 * vw), vh));
    buyPanel.setBackground(new Color(36, 60, 148));
    this.add(buyPanel, BorderLayout.EAST);

    GridBagConstraints c = new GridBagConstraints();
    gridPanel.setBorder(new EmptyBorder(78, 0, 78, 0));
    gridPanel.setBackground(Color.WHITE);
    scrollGridPanel.setViewportView(gridPanel);
    scrollGridPanel.getVerticalScrollBar().setUnitIncrement(10);

    for (int i = 0; i < data.length; i++) {
      JPanel container = new JPanel(new BorderLayout());
      container.setBorder(new EmptyBorder(16, 16, 16, 16));
      container.setBackground(Color.WHITE);
      container.setDoubleBuffered(true);
      c.gridx = i % 3;
      c.gridy = i / 3;

      PembelianCard card = new PembelianCard(i, data[i].title, data[i].subtitle, data[i].price, data[i].imagePath);
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

    orderNumPanel.setBackground(new Color(36, 60, 148));
    orderNumPanel.setPreferredSize(new Dimension(400, 69));
    orderNumPanel.setLayout(null);
    buyPanel.add(orderNumPanel, BorderLayout.NORTH);

    orderLabel.setFont(new Font("Inter", Font.BOLD, 24));
    orderLabel.setForeground(Color.WHITE);
    orderLabel.setBounds(14, 0, 79, 32);
    orderNumPanel.add(orderLabel);

    orderNumLabel.setFont(new Font("Inter", Font.PLAIN, 24));
    orderNumLabel.setForeground(Color.WHITE);
    orderNumLabel.setBounds(96, 0, 79, 32);
    orderNumPanel.add(orderNumLabel);

    orderLine.setBounds(14, 45, (int) (0.28 * vw), 1);
    orderNumPanel.add(orderLine);

    orderSummaryPanel.setBackground(new Color(36, 60, 148));
    orderSummaryPanel.setPreferredSize(new Dimension(400, 262));
    buyPanel.add(orderSummaryPanel, BorderLayout.SOUTH);

    summaryTextPanel.setBackground(new Color(36, 60, 148));
    Font summaryFont = new Font("Inter", Font.BOLD, 17);

    c.gridx = 0;
    c.gridy = 0;
    subTextContainer.setBackground(new Color(36, 60, 148));
    subTextContainer.setBorder(new EmptyBorder(15, 0, 10, 0));
    subText.setPreferredSize(new Dimension(80, 18));
    subText.setForeground(Color.WHITE);
    subText.setFont(summaryFont);
    subTextContainer.add(subText, BorderLayout.CENTER);
    summaryTextPanel.add(subTextContainer, c);

    c.gridx = 1;
    c.gridy = 0;
    subValueContainer.setBackground(new Color(36, 60, 148));
    orderSummaryPanel.setBackground(new Color(36, 60, 148));
    subValueContainer.setBorder(new EmptyBorder(15, 0, 10, 0));
    subValue.setPreferredSize(new Dimension(266, 18));
    subValue.setForeground(Color.WHITE);
    subValue.setFont(summaryFont);
    subValue.setHorizontalAlignment(SwingConstants.RIGHT);
    subValueContainer.add(subValue, BorderLayout.CENTER);
    summaryTextPanel.add(subValueContainer, c);

    c.gridx = 0;
    c.gridy = 1;
    discountTextContainer.setBackground(new Color(36, 60, 148));
    discountTextContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
    discountText.setPreferredSize(new Dimension(80, 18));
    discountText.setForeground(Color.WHITE);
    discountText.setFont(summaryFont);
    discountTextContainer.add(discountText, BorderLayout.CENTER);
    summaryTextPanel.add(discountTextContainer, c);

    c.gridx = 1;
    c.gridy = 1;
    discountValueContainer.setBackground(new Color(36, 60, 148));
    discountValueContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
    discountValue.setPreferredSize(new Dimension(266, 18));
    discountValue.setForeground(Color.WHITE);
    discountValue.setFont(summaryFont);
    discountValue.setHorizontalAlignment(SwingConstants.RIGHT);
    discountValueContainer.add(discountValue, BorderLayout.CENTER);
    summaryTextPanel.add(discountValueContainer, c);

    c.gridx = 0;
    c.gridy = 2;
    taxTextContainer.setBackground(new Color(36, 60, 148));
    taxTextContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
    taxText.setPreferredSize(new Dimension(80, 18));
    taxText.setForeground(Color.WHITE);
    taxText.setFont(summaryFont);
    taxTextContainer.add(taxText, BorderLayout.CENTER);
    summaryTextPanel.add(taxTextContainer, c);

    c.gridx = 1;
    c.gridy = 2;
    taxValueContainer.setBackground(new Color(36, 60, 148));
    taxValueContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
    taxValue.setPreferredSize(new Dimension(266, 18));
    taxValue.setForeground(Color.WHITE);
    taxValue.setFont(summaryFont);
    taxValue.setHorizontalAlignment(SwingConstants.RIGHT);
    taxValueContainer.add(taxValue, BorderLayout.CENTER);
    summaryTextPanel.add(taxValueContainer, c);

    c.gridx = 0;
    c.gridy = 3;
    totalTextContainer.setBackground(new Color(36, 60, 148));
    totalText.setPreferredSize(new Dimension(80, 18));
    totalText.setForeground(Color.WHITE);
    totalText.setFont(summaryFont);
    totalTextContainer.add(totalText, BorderLayout.CENTER);
    summaryTextPanel.add(totalTextContainer, c);

    c.gridx = 1;
    c.gridy = 3;
    totalValueContainer.setBackground(new Color(36, 60, 148));
    totalValue.setPreferredSize(new Dimension(266, 18));
    totalValue.setForeground(Color.WHITE);
    totalValue.setFont(summaryFont);
    totalValue.setHorizontalAlignment(SwingConstants.RIGHT);
    totalValueContainer.add(totalValue, BorderLayout.CENTER);
    summaryTextPanel.add(totalValueContainer, c);

    orderSummaryPanel.add(summaryTextPanel, BorderLayout.NORTH);

    buttonPanel.setBorder(new EmptyBorder(51, 27, 43, 22));
    buttonPanel.setBackground(new Color(36, 60, 148));
    orderSummaryPanel.add(buttonPanel, BorderLayout.CENTER);

    cancelContainer.setOpaque(false);
    cancelContainer.setLayout(null);
    cancelContainer.setPreferredSize(new Dimension(134, 49));
    cancelButton.setBackground(new Color(36, 60, 148));
    cancelButton.setForeground(new Color(236, 102, 102));
    cancelButton.setFont(new Font("Inter", Font.BOLD, 17));
    cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    cancelButton.setBounds(4, 4, 126, 41);
    cancelButton.setBorder(null);
    cancelButton.setFocusPainted(false);
    cancelContainer.add(cancelButton);
    buttonPanel.add(cancelContainer, BorderLayout.WEST);

    checkoutContainer.setOpaque(false);
    checkoutContainer.setLayout(null);
    checkoutContainer.setPreferredSize(new Dimension(205, 49));
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

    buyListScroll.getViewport().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        buyItemList.forEach(item -> item.repaint());
      }
    });
    buyListScroll.setBorder(null);
    buyListScroll.getVerticalScrollBar().setUnitIncrement(10);
    buyPanel.add(buyListScroll, BorderLayout.CENTER);
  }

  public void handleAddItem(PembelianEvent e) {
    if (buyIdList.contains(e.id)) {
      int index = buyIdList.indexOf(e.id);
      int prevCount = buyItemList.get(index).count;
      buyItemList.get(index).setCount(prevCount + 1);
    } else {
      PembelianList temp = new PembelianList(e.title, e.subtitle, 1, e.price,
          e.imagePath, buyItemList.size());
      temp.setPreferredSize(new Dimension(0, 103));
      temp.setMaximumSize(new Dimension(346, 103));
      temp.setObserver(observer);
      buyItemList.add(temp);
      buyListPanel.add(buyItemList.get(buyItemList.size() - 1));
      buyListPanel.add(Box.createRigidArea(new Dimension(0, 15)));

      buyIdList.add(e.id);
    }
    buyListPanel.revalidate();
    buyListPanel.repaint();

    sub += e.price;
    total += e.price;

    subValue.setText(RupiahConverter.convert(sub));
    totalValue.setText(RupiahConverter.convert(total));
  }

  public void handleRemoveItem(PembelianEvent e) {
    sub = 0;
    total = discount + tax;

    buyListPanel.removeAll();
    buyItemList.remove(e.index);
    buyIdList.remove(e.index);
    for (int i = 0; i < buyItemList.size(); i++) {
      buyItemList.get(i).setIndex(i);
      sub += buyItemList.get(i).price * buyItemList.get(i).count;
      buyListPanel.add(buyItemList.get(i));
      buyListPanel.add(Box.createRigidArea(new Dimension(0, 15)));
    }
    buyListPanel.revalidate();
    buyListPanel.repaint();

    total += sub;
    subValue.setText(RupiahConverter.convert(sub));
    totalValue.setText(RupiahConverter.convert(total));
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
