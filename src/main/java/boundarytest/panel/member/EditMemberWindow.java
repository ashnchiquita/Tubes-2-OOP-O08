package boundary.panel.member;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditMemberWindow {
  private static void showUI() {
    JFrame mainFrame = new JFrame("Edit Member Window");
    mainFrame.setSize(1280, 720);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLayout(new BorderLayout());
    mainFrame.setBackground(Color.WHITE);

    int vw = mainFrame.getWidth(), vh = mainFrame.getHeight();

    JPanel sideNav = new JPanel();
    sideNav.setPreferredSize(new Dimension(230, vh));
    sideNav.setBackground(new Color(56, 100, 194));
    mainFrame.add(sideNav, BorderLayout.WEST);

    JPanel mainRight = new JPanel();
    mainRight.setLayout(new BorderLayout());
    mainFrame.add(mainRight, BorderLayout.CENTER);
    mainRight.setBackground(Color.WHITE);
    mainRight.setAlignmentX(Component.LEFT_ALIGNMENT);

    JPanel topPanel = new JPanel();
    topPanel.setBackground(new Color(36, 60, 148));
    topPanel.setMaximumSize(new Dimension(2800, 100));
    mainRight.add(topPanel, BorderLayout.NORTH);

    JButton editButton = new JButton("<   Edit Data Member");
    editButton.setBackground(Color.WHITE); // set button background color to transparent
    editButton.setForeground(new Color(0xE5, 0x97, 0x00)); // set button text color
    editButton.setFont(new Font(editButton.getFont().getName(), Font.PLAIN, 40));
    editButton.setBorderPainted(false); 
    editButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    
    JLabel nameLabel = new JLabel("Name");
    nameLabel.setForeground(new Color(0xE5, 0x97, 0x00));
    nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.PLAIN, 20));
    
    JLabel nameLabel2 = new JLabel("Telephone Number");
    nameLabel2.setForeground(new Color(0xE5, 0x97, 0x00));
    nameLabel2.setFont(new Font(nameLabel2.getFont().getName(), Font.PLAIN, 20));

    JLabel nameLabel3 = new JLabel("Jenis Member");
    nameLabel3.setForeground(new Color(0xE5, 0x97, 0x00));
    nameLabel3.setFont(new Font(nameLabel3.getFont().getName(), Font.PLAIN, 20));

    JTextField nameField = new JTextField(1);
    nameField.setFont(new Font(nameField.getFont().getName(), Font.PLAIN, 20));
    nameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    nameField.setMaximumSize(new Dimension(370, 20));
    
    JTextField nameField2 = new JTextField(1);
    nameField2.setFont(new Font(nameField.getFont().getName(), Font.PLAIN, 20));
    nameField2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    nameField2.setMaximumSize(new Dimension(370, 20)); // set preferred size
    
    JPanel checkboxPanel = new JPanel();
    checkboxPanel.setBackground(Color.WHITE);
    checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
    
    JCheckBox memberCheckbox = new JCheckBox("Member");
    memberCheckbox.setBackground(Color.WHITE);

    JCheckBox vipCheckbox = new JCheckBox("VIP");
    vipCheckbox.setBackground(Color.WHITE);
    
    checkboxPanel.add(memberCheckbox);
    checkboxPanel.add(vipCheckbox);
    
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(0,0,0,0));
    buttonPanel.setMaximumSize(new Dimension(350, 50));
    
    JButton deaktivasiButton = new JButton("Deaktivasi");
    deaktivasiButton.setBackground(new Color(0xEC, 0x66, 0x66));
    deaktivasiButton.setForeground(Color.WHITE);
    deaktivasiButton.setPreferredSize(new Dimension(150, 50));

    JButton simpanButton = new JButton("Simpan");
    simpanButton.setBackground(new Color(0x24, 0x3C, 0x94));
    simpanButton.setForeground(Color.WHITE);
    simpanButton.setPreferredSize(new Dimension(150, 50));

    buttonPanel.add(Box.createHorizontalStrut(20));
    buttonPanel.add(deaktivasiButton);
    buttonPanel.add(simpanButton);
    buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    
    JPanel IsiPanel = new JPanel();
    IsiPanel.setBackground(new Color(0,0,0,0));
    IsiPanel.add(Box.createVerticalStrut(20)); // add gap
    IsiPanel.add(nameLabel);
    IsiPanel.add(Box.createVerticalStrut(20)); // add gap
    IsiPanel.add(nameField);
    IsiPanel.add(Box.createVerticalStrut(20)); // add gap
    IsiPanel.add(nameLabel2);
    IsiPanel.add(Box.createVerticalStrut(20)); // add gap
    IsiPanel.add(nameField2);
    IsiPanel.add(Box.createVerticalStrut(20)); // add gap
    IsiPanel.add(nameLabel3);
    IsiPanel.add(Box.createVerticalStrut(5)); // add gap
    IsiPanel.add(checkboxPanel);
    IsiPanel.add(Box.createVerticalStrut(40)); // add gap

    IsiPanel.setLayout(new BoxLayout(IsiPanel, BoxLayout.Y_AXIS));

    JPanel ContainerIsi = new JPanel();
    ContainerIsi.add(IsiPanel);
    ContainerIsi.setAlignmentX(Component.LEFT_ALIGNMENT);
    ContainerIsi.setMaximumSize(new Dimension(350, 300));
    ContainerIsi.setBackground(Color.WHITE);


    mainRight.add(editButton);
    mainRight.add(ContainerIsi, BorderLayout.WEST);
    mainRight.add(buttonPanel);
    mainRight.setLayout(new BoxLayout(mainRight, BoxLayout.Y_AXIS));
    
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
