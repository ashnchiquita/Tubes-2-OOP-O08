package boundary.panel.pembelian;

import java.awt.*;
import javax.swing.*;

import Util.Colors;
import boundary.widget.HintTextField;
import boundary.widget.RoundedPanel;

public class CreateMemberPanel extends JPanel {
  // UI Components
  private JButton exitButton = new JButton();
  private JLabel checkoutLabel = new JLabel("Data Member Baru");

  private JLabel namaTextLabel = new JLabel("Nama");
  private RoundedPanel namaFieldContainer = new RoundedPanel(40, Color.WHITE, true, Colors.DARK_BLUE.getColor(), 1);
  private JTextField namaField = new HintTextField("Masukkan nama pengguna...", 1);

  private JLabel teleponTextLabel = new JLabel("Nomor Telepon");
  private RoundedPanel teleponFieldContainer = new RoundedPanel(40, Color.WHITE, true, Colors.DARK_BLUE.getColor(), 1);
  private JTextField teleponField = new HintTextField("Masukkan nomor telepon...", 1);

  private JLabel memberTextLabel = new JLabel("Jenis Member");
  private JRadioButton memberOption = new JRadioButton("Member");
  private JRadioButton vipOption = new JRadioButton("VIP");
  ButtonGroup memberGroup = new ButtonGroup();

  private RoundedPanel createButtonContainer = new RoundedPanel(24, Colors.BUTTON_BLUE.getColor(), false, Color.WHITE,
      0);
  private JButton createButton = new JButton("+ Create Member");

  public CreateMemberPanel() {
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

    checkoutLabel.setBounds(122, 66, 330, 40);
    checkoutLabel.setFont(new Font("Inter", Font.BOLD, 32));
    checkoutLabel.setForeground(Colors.ORANGE.getColor());
    this.add(checkoutLabel);

    namaTextLabel.setBounds(119, 151, 140, 24);
    namaTextLabel.setFont(new Font("Inter", Font.BOLD, 15));
    namaTextLabel.setForeground(Colors.DARK_BLUE.getColor());
    this.add(namaTextLabel);

    namaFieldContainer.setLayout(null);
    namaFieldContainer.setBounds(120, 183, 380, 40);
    this.add(namaFieldContainer);

    namaField.setFont(new Font("Inter", Font.PLAIN, 17));
    namaField.setBackground(Color.WHITE);
    namaField.setForeground(Colors.DARK_BLUE.getColor());
    namaField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    namaField.setBorder(null);
    namaField.setBounds(15, 7, 350, 25);
    namaFieldContainer.add(namaField);

    teleponTextLabel.setBounds(119, 249, 140, 24);
    teleponTextLabel.setFont(new Font("Inter", Font.BOLD, 15));
    teleponTextLabel.setForeground(Colors.DARK_BLUE.getColor());
    this.add(teleponTextLabel);

    teleponFieldContainer.setLayout(null);
    teleponFieldContainer.setBounds(120, 282, 380, 40);
    this.add(teleponFieldContainer);

    teleponField.setFont(new Font("Inter", Font.PLAIN, 17));
    teleponField.setBackground(Color.WHITE);
    teleponField.setForeground(Colors.DARK_BLUE.getColor());
    teleponField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    teleponField.setBorder(null);
    teleponField.setBounds(15, 7, 350, 25);
    teleponFieldContainer.add(teleponField);

    memberTextLabel.setBounds(119, 353, 140, 24);
    memberTextLabel.setFont(new Font("Inter", Font.BOLD, 15));
    memberTextLabel.setForeground(Colors.DARK_BLUE.getColor());
    this.add(memberTextLabel);

    memberOption.setBounds(125, 378, 183, 32);
    memberOption.setFont(new Font("Inter", Font.BOLD, 15));
    memberOption.setForeground(Colors.DARK_BLUE.getColor());
    memberOption.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    memberOption.setFocusPainted(false);
    memberOption.setOpaque(false);
    this.add(memberOption);

    vipOption.setBounds(125, 407, 183, 32);
    vipOption.setFont(new Font("Inter", Font.BOLD, 15));
    vipOption.setForeground(Colors.DARK_BLUE.getColor());
    vipOption.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    vipOption.setFocusPainted(false);
    vipOption.setOpaque(false);
    this.add(vipOption);

    memberGroup.add(memberOption);
    memberGroup.add(vipOption);

    createButtonContainer.setLayout(null);
    createButtonContainer.setBounds(119, 472, 208, 45);
    this.add(createButtonContainer);

    createButton.setBorder(null);
    createButton.setBackground(Colors.BUTTON_BLUE.getColor());
    createButton.setFocusPainted(false);
    createButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    createButton.setBounds(9, 2, 194, 41);
    createButton.setForeground(Color.WHITE);
    createButton.setFont(new Font("Inter", Font.BOLD, 18));
    createButtonContainer.add(createButton);
  }
}
