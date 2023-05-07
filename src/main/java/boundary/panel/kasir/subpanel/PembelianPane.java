package boundary.panel.kasir.subpanel;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeListener;

import boundary.constants.Colors;
import boundary.constants.ResourcePath;
import boundary.observer.panelflow.PanelFlowEvent;
import boundary.observer.tab.TabEvent;
import boundary.widget.*;

import model.*;
import controller.*;
import util.RupiahConverter;
import boundary.observer.pembelian.PembelianEvent;
import boundary.observer.pembelian.PembelianListener;
import boundary.observer.pembelian.PembelianObserver;

import javax.swing.event.ChangeEvent;

public class PembelianPane extends TabPane implements PembelianListener {
  private int vw = 1280, vh = 720;
  private float sub = 0f, discount = 0f, tax = 0f, total = 0f;
  private GenericDataIO<Barang> barangDataIO;
  private GenericDataIO<FixedBill> fixedBillDataIO;
  private GenericDataIO<Member> memberDataIO;
  private GenericDataIO<VIP> VIPDataIO;
  private GenericDataIO<Customer> customerDataIO;
  PembelianObserver pembelianObserver = new PembelianObserver();
  ArrayList<Integer> buyIdList = new ArrayList<>();

  ArrayList<JPanel> gridItemList = new ArrayList<>();
  ArrayList<PembelianList> buyItemList = new ArrayList<>();
  ArrayList<Barang> barangList = new ArrayList<>();

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
  RoundedPanel cancelContainer = new RoundedPanel(9, Colors.DARK_BLUE, true, Colors.RED, 2);
  JButton cancelButton = new JButton("Cancel");
  RoundedPanel checkoutContainer = new RoundedPanel(42, Colors.BUTTON_BLUE, false, Color.WHITE, 0);
  JButton checkoutButton = new JButton("Checkout");
  JPanel buyListPanel = new JPanel();
  JScrollPane buyListScroll = new JScrollPane(buyListPanel);

  public PembelianPane(GenericDataIO<Barang> barangDataIO, GenericDataIO<FixedBill> fixedBillDataIO,
      GenericDataIO<Member> memberDataIO, GenericDataIO<VIP> VIPDataIO, GenericDataIO<Customer> customerDataIO) {
    this.customerDataIO = customerDataIO;
    this.VIPDataIO = VIPDataIO;
    this.barangDataIO = barangDataIO;
    this.fixedBillDataIO = fixedBillDataIO;
    this.memberDataIO = memberDataIO;
    this.pembelianObserver.addListener(this);
    this.orderNumLabel.setText("#" + String.valueOf(fixedBillDataIO.getAll().size() + 1));
    this.initializeUI();
  }

  private void initializeUI() {
    this.setLayout(new BorderLayout());

    scrollGridPanel.setBackground(Color.LIGHT_GRAY);
    this.add(scrollGridPanel, BorderLayout.CENTER);

    buyPanel.setPreferredSize(new Dimension((int) (0.31 * vw), vh));
    buyPanel.setBackground(Colors.DARK_BLUE);
    this.add(buyPanel, BorderLayout.EAST);

    GridBagConstraints c = new GridBagConstraints();
    gridPanel.setBorder(new EmptyBorder(10, 0, 78, 0));
    gridPanel.setBackground(Color.WHITE);
    scrollGridPanel.setBorder(null);
    scrollGridPanel.setViewportView(gridPanel);
    scrollGridPanel.getVerticalScrollBar().setUnitIncrement(10);
    scrollGridPanel.getVerticalScrollBar().setUI(new PlainScrollBar(Colors.WHITE, Colors.SIDE_SLIDER_BLUE));

    List<Barang> listBarang = barangDataIO.getAll();
    for(int i = 0; i < listBarang.size(); i++){
      JPanel container = new JPanel(new BorderLayout());
      container.setBorder(new EmptyBorder(16, 16, 16, 16));
      container.setBackground(Color.WHITE);
      container.setDoubleBuffered(true);
      c.gridx = i % 3;
      c.gridy = i / 3;

      PembelianCard card = new PembelianCard(listBarang.get(i));
      card.setObserver(pembelianObserver);
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

    orderNumPanel.setBackground(Colors.DARK_BLUE);
    orderNumPanel.setPreferredSize(new Dimension(400, 69));
    orderNumPanel.setLayout(null);
    buyPanel.add(orderNumPanel, BorderLayout.NORTH);

    orderLabel.setFont(new Font("Inter", Font.BOLD, 24));
    orderLabel.setForeground(Color.WHITE);
    orderLabel.setBounds(14, 20, 79, 32);
    orderNumPanel.add(orderLabel);

    orderNumLabel.setFont(new Font("Inter", Font.PLAIN, 24));
    orderNumLabel.setForeground(Color.WHITE);
    orderNumLabel.setBounds(96, 20, 79, 32);
    orderNumPanel.add(orderNumLabel);

    orderLine.setBounds(14, 60, (int) (0.28 * vw), 1);
    orderNumPanel.add(orderLine);

    orderSummaryPanel.setBackground(Colors.DARK_BLUE);
    orderSummaryPanel.setPreferredSize(new Dimension(400, 262));
    buyPanel.add(orderSummaryPanel, BorderLayout.SOUTH);

    summaryTextPanel.setBackground(Colors.DARK_BLUE);
    Font summaryFont = new Font("Inter", Font.BOLD, 17);

    c.gridx = 0;
    c.gridy = 0;
    subTextContainer.setBackground(Colors.DARK_BLUE);
    subTextContainer.setBorder(new EmptyBorder(15, 0, 10, 0));
    subText.setPreferredSize(new Dimension(80, 18));
    subText.setForeground(Color.WHITE);
    subText.setFont(summaryFont);
    subTextContainer.add(subText, BorderLayout.CENTER);
    summaryTextPanel.add(subTextContainer, c);

    c.gridx = 1;
    c.gridy = 0;
    subValueContainer.setBackground(Colors.DARK_BLUE);
    orderSummaryPanel.setBackground(Colors.DARK_BLUE);
    subValueContainer.setBorder(new EmptyBorder(15, 0, 10, 0));
    subValue.setPreferredSize(new Dimension(266, 18));
    subValue.setForeground(Color.WHITE);
    subValue.setFont(summaryFont);
    subValue.setHorizontalAlignment(SwingConstants.RIGHT);
    subValueContainer.add(subValue, BorderLayout.CENTER);
    summaryTextPanel.add(subValueContainer, c);

    c.gridx = 0;
    c.gridy = 1;
    discountTextContainer.setBackground(Colors.DARK_BLUE);
    discountTextContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
    discountText.setPreferredSize(new Dimension(80, 18));
    discountText.setForeground(Color.WHITE);
    discountText.setFont(summaryFont);
    discountTextContainer.add(discountText, BorderLayout.CENTER);
    summaryTextPanel.add(discountTextContainer, c);

    c.gridx = 1;
    c.gridy = 1;
    discountValueContainer.setBackground(Colors.DARK_BLUE);
    discountValueContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
    discountValue.setPreferredSize(new Dimension(266, 18));
    discountValue.setForeground(Color.WHITE);
    discountValue.setFont(summaryFont);
    discountValue.setHorizontalAlignment(SwingConstants.RIGHT);
    discountValueContainer.add(discountValue, BorderLayout.CENTER);
    summaryTextPanel.add(discountValueContainer, c);

    c.gridx = 0;
    c.gridy = 2;
    taxTextContainer.setBackground(Colors.DARK_BLUE);
    taxTextContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
    taxText.setPreferredSize(new Dimension(80, 18));
    taxText.setForeground(Color.WHITE);
    taxText.setFont(summaryFont);
    taxTextContainer.add(taxText, BorderLayout.CENTER);
    summaryTextPanel.add(taxTextContainer, c);

    c.gridx = 1;
    c.gridy = 2;
    taxValueContainer.setBackground(Colors.DARK_BLUE);
    taxValueContainer.setBorder(new EmptyBorder(0, 0, 10, 0));
    taxValue.setPreferredSize(new Dimension(266, 18));
    taxValue.setForeground(Color.WHITE);
    taxValue.setFont(summaryFont);
    taxValue.setHorizontalAlignment(SwingConstants.RIGHT);
    taxValueContainer.add(taxValue, BorderLayout.CENTER);
    summaryTextPanel.add(taxValueContainer, c);

    c.gridx = 0;
    c.gridy = 3;
    totalTextContainer.setBackground(Colors.DARK_BLUE);
    totalText.setPreferredSize(new Dimension(80, 18));
    totalText.setForeground(Color.WHITE);
    totalText.setFont(summaryFont);
    totalTextContainer.add(totalText, BorderLayout.CENTER);
    summaryTextPanel.add(totalTextContainer, c);

    c.gridx = 1;
    c.gridy = 3;
    totalValueContainer.setBackground(Colors.DARK_BLUE);
    totalValue.setPreferredSize(new Dimension(266, 18));
    totalValue.setForeground(Color.WHITE);
    totalValue.setFont(summaryFont);
    totalValue.setHorizontalAlignment(SwingConstants.RIGHT);
    totalValueContainer.add(totalValue, BorderLayout.CENTER);
    summaryTextPanel.add(totalValueContainer, c);

    orderSummaryPanel.add(summaryTextPanel, BorderLayout.NORTH);

    buttonPanel.setBorder(new EmptyBorder(51, 27, 43, 22));
    buttonPanel.setBackground(Colors.DARK_BLUE);
    orderSummaryPanel.add(buttonPanel, BorderLayout.CENTER);

    cancelContainer.setOpaque(false);
    cancelContainer.setLayout(null);
    cancelContainer.setPreferredSize(new Dimension(134, 49));
    cancelButton.setBackground(Colors.DARK_BLUE);
    cancelButton.setForeground(Colors.RED);
    cancelButton.setFont(new Font("Inter", Font.BOLD, 17));
    cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    cancelButton.setBounds(4, 4, 126, 41);
    cancelButton.setBorder(null);
    cancelButton.setFocusPainted(false);
    cancelButton.addActionListener(e -> tabObserver.newEvent(new TabEvent(TabEvent.CLOSE)));
    cancelContainer.add(cancelButton);
    buttonPanel.add(cancelContainer, BorderLayout.WEST);

    checkoutContainer.setOpaque(false);
    checkoutContainer.setLayout(null);
    checkoutContainer.setPreferredSize(new Dimension(205, 49));
    checkoutButton.setFont(new Font("Inter", Font.BOLD, 17));
    checkoutButton.setBackground(Colors.BUTTON_BLUE);
    checkoutButton.setForeground(Color.WHITE);
    checkoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    checkoutButton.setBorder(null);
    checkoutButton.setFocusPainted(false);
    checkoutButton.setBounds(10, 4, 185, 41);
    checkoutButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        barangList.clear();
        for(PembelianList buylist : buyItemList){
          barangList.add(buylist.barang);
        }

        panelFlowObserver.newEvent(new PanelFlowEvent(new CheckoutPane(fixedBillDataIO, memberDataIO, VIPDataIO, customerDataIO, total, barangList), true));
      }
    });
    checkoutContainer.add(checkoutButton);
    buttonPanel.add(checkoutContainer, BorderLayout.EAST);

    buyListPanel.setBackground(Colors.DARK_BLUE);
    buyListPanel.setLayout(new BoxLayout(buyListPanel, BoxLayout.Y_AXIS));

    buyListScroll.getViewport().addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        buyItemList.forEach(item -> item.repaint());
      }
    });
    buyListScroll.setBorder(null);
    buyListScroll.getVerticalScrollBar().setUnitIncrement(10);
    buyListScroll.getVerticalScrollBar().setUI(new PlainScrollBar(Colors.DARK_BLUE, Colors.WHITE));
    buyPanel.add(buyListScroll, BorderLayout.CENTER);
  }

  public void handleAddItem(PembelianEvent e) {
    if (buyIdList.contains(e.barang.getId())) {
      int index = buyIdList.indexOf(e.barang.getId());
      int prevCount = buyItemList.get(index).count;
      buyItemList.get(index).setCount(prevCount + 1);
    } else {
      PembelianList temp = new PembelianList(e.barang);
      temp.setPreferredSize(new Dimension(0, 103));
      temp.setMaximumSize(new Dimension(346, 103));
      temp.setObserver(pembelianObserver);
      buyItemList.add(temp);
      buyListPanel.add(buyItemList.get(buyItemList.size() - 1));
      buyListPanel.add(Box.createRigidArea(new Dimension(0, 15)));
      buyIdList.add(e.barang.getId());
    }
    buyListPanel.revalidate();
    buyListPanel.repaint();

    // TODO: integrate discounts
    sub += e.barang.getHargaJual();
    total += e.barang.getHargaJual();

    subValue.setText(RupiahConverter.convert(sub));
    totalValue.setText(RupiahConverter.convert(total));
  }

  public void handleRemoveItem(PembelianEvent e) {
    sub = 0;
    total = discount + tax;

    for(int i = 0; i < buyItemList.size(); i++){
      if (buyItemList.get(i).barang.getId() == e.barang.getId()){
        buyItemList.remove(i);
        buyIdList.remove(i);
        break;
      }
    }

    buyListPanel.removeAll();
    for (int i = 0; i < buyItemList.size(); i++) {
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
    public int id;
    public double price;

    public DataObj(int id, String title, String subtitle, double price, String imagePath) {
      this.id = id;
      this.title = title;
      this.subtitle = subtitle;
      this.price = price;
      this.imagePath = imagePath;
    }
  }
}
