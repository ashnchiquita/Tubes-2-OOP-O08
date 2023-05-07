package boundary.panel.kasir.subpanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import boundary.constants.Colors;
import boundary.observer.panelflow.PanelFlowEvent;
import boundary.observer.tab.TabEvent;
import boundary.widget.TabPane;

import model.*;
import controller.*;

import util.RupiahConverter;
import boundary.widget.RoundedPanel;

import java.awt.*;

public class TerimakasihPane extends TabPane {
  private GenericDataIO<Member> memberDataIO;
  private GenericDataIO<VIP> VIPDataIO;
  private float sub = 0f, discount = 0f, tax = 0f, total = 0f;
  private String memberName;

  // UI Components
  private JButton exitButton = new JButton();
  private JLabel terimakasihLabel = new JLabel("Terimakasih!");

  private JLabel billHeader = new JLabel("Fixed Bill");
  private JPanel summaryTextPanel = new JPanel(new GridBagLayout());

  private JPanel subTextContainer = new JPanel(new BorderLayout());
  private JLabel subText = new JLabel("Subtotal");

  private JPanel subValueContainer = new JPanel(new BorderLayout());
  private JLabel subValue = new JLabel(RupiahConverter.convert(sub));

  private JPanel discountTextContainer = new JPanel(new BorderLayout());
  private JLabel discountText = new JLabel("Diskon");

  private JPanel discountValueContainer = new JPanel(new BorderLayout());
  private JLabel discountValue = new JLabel(RupiahConverter.convert(discount));

  private JPanel taxTextContainer = new JPanel(new BorderLayout());
  private JLabel taxText = new JLabel("Pajak");

  private JPanel taxValueContainer = new JPanel(new BorderLayout());
  private JLabel taxValue = new JLabel(RupiahConverter.convert(tax));

  private JPanel totalTextContainer = new JPanel(new BorderLayout());
  private JLabel totalText = new JLabel("Total");

  private JPanel totalValueContainer = new JPanel(new BorderLayout());
  private JLabel totalValue = new JLabel(RupiahConverter.convert(total));

  private JLabel memberHeader = new JLabel("Member");
  private JLabel memberText = new JLabel("Transaksi ini dimiliki customer non-Member.");
  private RoundedPanel tambahButtonContainer = new RoundedPanel(36, Colors.BUTTON_BLUE, false, Color.WHITE,
      0);
  private JButton tambahButton = new JButton("+ Tambah");
  private JLabel memberNameText = new JLabel("");

  private RoundedPanel selesaiButtonContainer = new RoundedPanel(24, Colors.BUTTON_BLUE, false, Color.WHITE,
      0);
  private JButton selesaiButton = new JButton("Selesai");
  private FixedBill bill;
  private Customer customer;

  public TerimakasihPane(GenericDataIO<Member> memberDataIO, GenericDataIO<VIP> VIPDataIO, Customer customer, String memberName, FixedBill bill) {
    this.customer = customer;
    this.bill = bill;
    this.memberDataIO = memberDataIO;
    this.VIPDataIO = VIPDataIO;
    this.memberName = memberName;
    this.initializeUI();
  }

  private void initializeUI() {
    this.setLayout(null);
    this.setBackground(Color.WHITE);

    ImageIcon buttonImage = new ImageIcon(
        "/home/rma1403/Documents/Programming/kuliah/Tubes-2-OOP-O08/src/main/resources/assets/icon/left-arrow.png");
    ImageIcon buttonImageScaled = new ImageIcon(
        buttonImage.getImage().getScaledInstance(18, 23, java.awt.Image.SCALE_SMOOTH));
    exitButton.setIcon(buttonImageScaled);
    exitButton.setBounds(67, 73, 20, 25);
    exitButton.setBorder(null);
    exitButton.setBackground(Color.WHITE);
    exitButton.setFocusPainted(false);
    exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    this.add(exitButton);

    terimakasihLabel.setBounds(122, 66, 250, 40);
    terimakasihLabel.setFont(new Font("Inter", Font.BOLD, 32));
    terimakasihLabel.setForeground(Colors.ORANGE);
    this.add(terimakasihLabel);

    billHeader.setForeground(Colors.DARK_BLUE);
    billHeader.setFont(new Font("Inter", Font.BOLD, 24));
    billHeader.setBounds(105, 151, 193, 30);
    this.add(billHeader);

    summaryTextPanel.setBackground(Color.WHITE);
    summaryTextPanel.setBounds(106, 201, 355, 135);
    Font summaryFont = new Font("Inter", Font.BOLD, 17);

    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 0;
    subTextContainer.setBackground(Color.WHITE);
    subTextContainer.setBorder(new EmptyBorder(0, 0, 20, 0));
    subText.setPreferredSize(new Dimension(80, 18));
    subText.setForeground(Colors.DARK_BLUE);
    subText.setFont(summaryFont);
    subTextContainer.add(subText, BorderLayout.CENTER);
    summaryTextPanel.add(subTextContainer, c);

    c.gridx = 1;
    c.gridy = 0;
    subValueContainer.setBackground(Color.WHITE);
    subValueContainer.setBorder(new EmptyBorder(0, 0, 20, 0));
    subValue.setPreferredSize(new Dimension(266, 18));
    subValue.setForeground(Colors.DARK_BLUE);
    subValue.setFont(summaryFont);
    subValue.setHorizontalAlignment(SwingConstants.RIGHT);
    subValueContainer.add(subValue, BorderLayout.CENTER);
    summaryTextPanel.add(subValueContainer, c);

    c.gridx = 0;
    c.gridy = 1;
    discountTextContainer.setBackground(Color.WHITE);
    discountTextContainer.setBorder(new EmptyBorder(0, 0, 20, 0));
    discountText.setPreferredSize(new Dimension(80, 18));
    discountText.setForeground(Colors.DARK_BLUE);
    discountText.setFont(summaryFont);
    discountTextContainer.add(discountText, BorderLayout.CENTER);
    summaryTextPanel.add(discountTextContainer, c);

    c.gridx = 1;
    c.gridy = 1;
    discountValueContainer.setBackground(Color.WHITE);
    discountValueContainer.setBorder(new EmptyBorder(0, 0, 20, 0));
    discountValue.setPreferredSize(new Dimension(266, 18));
    discountValue.setForeground(Colors.DARK_BLUE);
    discountValue.setFont(summaryFont);
    discountValue.setHorizontalAlignment(SwingConstants.RIGHT);
    discountValueContainer.add(discountValue, BorderLayout.CENTER);
    summaryTextPanel.add(discountValueContainer, c);

    c.gridx = 0;
    c.gridy = 2;
    taxTextContainer.setBackground(Color.WHITE);
    taxTextContainer.setBorder(new EmptyBorder(0, 0, 20, 0));
    taxText.setPreferredSize(new Dimension(80, 18));
    taxText.setForeground(Colors.DARK_BLUE);
    taxText.setFont(summaryFont);
    taxTextContainer.add(taxText, BorderLayout.CENTER);
    summaryTextPanel.add(taxTextContainer, c);

    c.gridx = 1;
    c.gridy = 2;
    taxValueContainer.setBackground(Color.WHITE);
    taxValueContainer.setBorder(new EmptyBorder(0, 0, 20, 0));
    taxValue.setPreferredSize(new Dimension(266, 18));
    taxValue.setForeground(Colors.DARK_BLUE);
    taxValue.setFont(summaryFont);
    taxValue.setHorizontalAlignment(SwingConstants.RIGHT);
    taxValueContainer.add(taxValue, BorderLayout.CENTER);
    summaryTextPanel.add(taxValueContainer, c);

    c.gridx = 0;
    c.gridy = 3;
    totalTextContainer.setBackground(Color.WHITE);
    totalText.setPreferredSize(new Dimension(80, 18));
    totalText.setForeground(Colors.DARK_BLUE);
    totalText.setFont(summaryFont);
    totalTextContainer.add(totalText, BorderLayout.CENTER);
    summaryTextPanel.add(totalTextContainer, c);

    c.gridx = 1;
    c.gridy = 3;
    totalValueContainer.setBackground(Color.WHITE);
    totalValue.setPreferredSize(new Dimension(266, 18));
    totalValue.setForeground(Colors.DARK_BLUE);
    totalValue.setFont(summaryFont);
    totalValue.setHorizontalAlignment(SwingConstants.RIGHT);
    totalValueContainer.add(totalValue, BorderLayout.CENTER);
    summaryTextPanel.add(totalValueContainer, c);

    this.add(summaryTextPanel);

    memberHeader.setBounds(105, 394, 125, 35);
    memberHeader.setFont(new Font("Inter", Font.BOLD, 24));
    memberHeader.setForeground(Colors.DARK_BLUE);
    this.add(memberHeader);

    memberText.setBounds(105, 425, 350, 25);
    memberText.setFont(new Font("Inter", Font.PLAIN, 15));
    memberText.setForeground(new Color(194, 194, 194));
    if (memberName == "") {
      this.add(memberText);
    }

    memberNameText.setBounds(105, 445, 350, 25);
    memberNameText.setFont(new Font("Inter", Font.BOLD, 18));
    memberNameText.setHorizontalAlignment(SwingConstants.CENTER);
    memberNameText.setText(memberName);
    if (memberName != "") {
      this.add(memberNameText);
    }

    tambahButtonContainer.setLayout(null);
    tambahButtonContainer.setBounds(105, 454, 350, 36);
    if (memberName == "") {
      this.add(tambahButtonContainer);
    }

    tambahButton.setBorder(null);
    tambahButton.setBackground(Colors.BUTTON_BLUE);
    tambahButton.setFocusPainted(false);
    tambahButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    tambahButton.setBounds(12, 2, 326, 32);
    tambahButton.setForeground(Color.WHITE);
    tambahButton.setFont(new Font("Inter", Font.BOLD, 16));
    tambahButton.addActionListener(
        e -> panelFlowObserver.newEvent(new PanelFlowEvent(new CreateMemberPane(memberDataIO, VIPDataIO, customer), false)));
    tambahButtonContainer.add(tambahButton);

    selesaiButtonContainer.setLayout(null);
    selesaiButtonContainer.setBounds(180, 527, 205, 43);
    this.add(selesaiButtonContainer);

    selesaiButton.setBorder(null);
    selesaiButton.setBackground(Colors.BUTTON_BLUE);
    selesaiButton.setFocusPainted(false);
    selesaiButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    selesaiButton.setBounds(9, 2, 187, 39);
    selesaiButton.setForeground(Color.WHITE);
    selesaiButton.setFont(new Font("Inter", Font.PLAIN, 18));
    selesaiButton.addActionListener(e -> tabObserver.newEvent(new TabEvent(TabEvent.CLOSE)));
    selesaiButtonContainer.add(selesaiButton);
  }
}