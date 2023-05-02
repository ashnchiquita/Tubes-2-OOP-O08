package boundary.panel.pembelian;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import boundary.constants.Colors;
import boundary.widget.RoundedPanel;
import util.RupiahConverter;

import java.awt.*;

public class CheckoutPanel extends JPanel {
  private float sub = 0f, discount = 0f, tax = 0f, total = 0f;
  private String[] memberNameList = { "", "Kim", "Jisoo", "Unnie" };

  // UI Components
  private JButton exitButton = new JButton();
  private JLabel checkoutLabel = new JLabel("Cek Keluar");

  private JLabel detailHeader = new JLabel("Detail Pesanan");
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
  private JComboBox<String> memberDropdown = new JComboBox<>(memberNameList);

  private RoundedPanel unduhButtonContainer = new RoundedPanel(10, Color.WHITE, true, new Color(82, 117, 226), 2);
  private JButton unduhButton = new JButton("Unduh Transaksi");

  private RoundedPanel batalButtonContainer = new RoundedPanel(10, Color.WHITE, true, Colors.RED, 2);
  private JButton batalButton = new JButton("Batal");

  private RoundedPanel orderButtonContainer = new RoundedPanel(10, Colors.BUTTON_BLUE, false, Color.WHITE,
      0);
  private JButton orderButton = new JButton("Place Order");

  public CheckoutPanel() {
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

    checkoutLabel.setBounds(122, 66, 180, 40);
    checkoutLabel.setFont(new Font("Inter", Font.BOLD, 32));
    checkoutLabel.setForeground(Colors.ORANGE);
    this.add(checkoutLabel);

    detailHeader.setForeground(Colors.DARK_BLUE);
    detailHeader.setFont(new Font("Inter", Font.BOLD, 24));
    detailHeader.setBounds(105, 151, 193, 30);
    this.add(detailHeader);

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

    memberHeader.setBounds(105, 384, 125, 35);
    memberHeader.setFont(new Font("Inter", Font.BOLD, 24));
    memberHeader.setForeground(Colors.DARK_BLUE);
    this.add(memberHeader);

    memberDropdown.setBounds(105, 427, 373, 34);
    memberDropdown.setFocusable(false);
    memberDropdown.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    this.add(memberDropdown);

    unduhButtonContainer.setLayout(null);
    unduhButtonContainer.setBounds(105, 503, 373, 49);
    this.add(unduhButtonContainer);

    unduhButton.setBorder(null);
    unduhButton.setBackground(Color.WHITE);
    unduhButton.setFocusPainted(false);
    unduhButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    unduhButton.setBounds(5, 2, 363, 45);
    unduhButton.setForeground(new Color(82, 117, 225));
    unduhButton.setFont(new Font("Inter", Font.BOLD, 18));
    unduhButtonContainer.add(unduhButton);

    batalButtonContainer.setLayout(null);
    batalButtonContainer.setBounds(104, 569, 133, 47);
    this.add(batalButtonContainer);

    batalButton.setBorder(null);
    batalButton.setBackground(Color.WHITE);
    batalButton.setFocusPainted(false);
    batalButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    batalButton.setBounds(5, 2, 123, 43);
    batalButton.setForeground(Colors.RED);
    batalButton.setFont(new Font("Inter", Font.BOLD, 18));
    batalButtonContainer.add(batalButton);

    orderButtonContainer.setLayout(null);
    orderButtonContainer.setBounds(273, 569, 205, 47);
    this.add(orderButtonContainer);

    orderButton.setBorder(null);
    orderButton.setBackground(Colors.BUTTON_BLUE);
    orderButton.setFocusPainted(false);
    orderButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    orderButton.setBounds(5, 2, 195, 43);
    orderButton.setForeground(Color.WHITE);
    orderButton.setFont(new Font("Inter", Font.BOLD, 18));
    orderButtonContainer.add(orderButton);
  }
}