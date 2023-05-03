package boundary.panel.member.subpanel;

import boundary.widget.FlowablePane;
import boundary.widget.PressedButton;
import boundary.widget.RoundBorder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EditDataMemberPanel extends FlowablePane {
    // Setup
    private static JPanel rightPanel;
    private static JPanel leftPanel;

    private static void setupLeftPanel(){
        /* Setting up left panel buttons */

        leftPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 20, 60, 20);
        leftPanel.setBackground(new Color(255,255,255));
        leftPanel.setPreferredSize(new Dimension(522,670));
        leftPanel.setBorder(paddingBorder);

//        leftPanel.setLayout(new BorderLayout());

        // Header Atas
        JButton backButton = new JButton("Back");
//        backButton.setPreferredSize(new Dimension(994, 43));
        leftPanel.add(backButton,BorderLayout.NORTH);
        JLabel tambahBarangLabel = new JLabel("Tambah Barang Baru");
        tambahBarangLabel.setFont(new Font("Inter", Font.BOLD, 33));
        tambahBarangLabel.setForeground(new Color(229, 151, 0));
        tambahBarangLabel.setPreferredSize(new Dimension(375, 40));
        leftPanel.add(tambahBarangLabel,BorderLayout.NORTH);

        // Nama
        JLabel namaLabel = new JLabel("Nama");
        namaLabel.setFont(new Font("Inter", Font.BOLD, 15));
        namaLabel.setForeground(new Color(0x243C94));
        namaLabel.setPreferredSize(new Dimension(475, 40));
        leftPanel.add(namaLabel, BorderLayout.NORTH);
        JTextField namaTextField = new JTextField("Nama");
        namaTextField.setPreferredSize(new Dimension(475, 45));
        namaTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        namaTextField.setForeground(new Color(0xD9D9D9));
        namaTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0X4B4FC4)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        leftPanel.add(namaTextField, BorderLayout.NORTH);

        // Kategori
        JLabel kategoriLabel = new JLabel("Telephone Number");
        kategoriLabel.setFont(new Font("Inter", Font.BOLD, 15));
        kategoriLabel.setForeground(new Color(0x243C94));
        kategoriLabel.setPreferredSize(new Dimension(475, 40));
        leftPanel.add(kategoriLabel, BorderLayout.NORTH);
        JTextField kategoriTextField = new JTextField("Telephone Number");
        kategoriTextField.setPreferredSize(new Dimension(475, 45));
        kategoriTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        kategoriTextField.setForeground(new Color(0xD9D9D9));
        kategoriTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0X4B4FC4)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        leftPanel.add(kategoriTextField, BorderLayout.NORTH);

        // Stok
        JLabel stokLabel = new JLabel("Stok");
        stokLabel.setFont(new Font("Inter", Font.BOLD, 15));
        stokLabel.setForeground(new Color(0x243C94));
        stokLabel.setPreferredSize(new Dimension(475, 40));
        leftPanel.add(stokLabel, BorderLayout.NORTH);
        JTextField stokTextField = new JTextField("Stok");
        stokTextField.setPreferredSize(new Dimension(475, 45));
        stokTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        stokTextField.setForeground(new Color(0xD9D9D9));
        stokTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0X4B4FC4)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        leftPanel.add(stokTextField, BorderLayout.NORTH);

        JRadioButton memberRadio = new JRadioButton("Member");
        memberRadio.setBackground(Color.WHITE);
        
        JRadioButton vipRadio = new JRadioButton("VIP");
        vipRadio.setBackground(Color.WHITE);
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(memberRadio);
        buttonGroup.add(vipRadio);
        
        JPanel radioPanel = new JPanel();
        radioPanel.setBackground(Color.WHITE);
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.setPreferredSize(new Dimension(475, 45));
        radioPanel.setFont(new Font("Inter", Font.PLAIN, 15));
        
        radioPanel.add(memberRadio);
        radioPanel.add(vipRadio);
        
        leftPanel.add(radioPanel, BorderLayout.NORTH);
        
        
        JButton createNewItemButton = new JButton("Deaktivasi");
        PressedButton buttonUI = new PressedButton(new Color(45,77,182));
        createNewItemButton.setFont(new Font("Inter", Font.PLAIN, 15));
        createNewItemButton.setBackground(new Color(236,102,102));
        createNewItemButton.setForeground(Color.WHITE);
        createNewItemButton.setOpaque(true);
        createNewItemButton.setFocusable(false);
        createNewItemButton.setPreferredSize(new Dimension(180,43));
        createNewItemButton.setUI(buttonUI);
        createNewItemButton.setBorder(new RoundBorder(20));
        // leftPanel.add(createNewItemButton, BorderLayout.WEST);

        JButton createNewItemButton2 = new JButton("Simpan");
        PressedButton buttonUI2 = new PressedButton(new Color(45,77,182));
        createNewItemButton2.setFont(new Font("Inter", Font.PLAIN, 15));
        createNewItemButton2.setBackground(new Color(76,110,223));
        createNewItemButton2.setForeground(Color.WHITE);
        createNewItemButton2.setOpaque(true);
        createNewItemButton2.setFocusable(false);
        createNewItemButton2.setPreferredSize(new Dimension(180,43));
        createNewItemButton2.setUI(buttonUI2);
        createNewItemButton2.setBorder(new RoundBorder(20));
        leftPanel.add(createNewItemButton2, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0,0,0,0));
        buttonPanel.setMaximumSize(new Dimension(350, 50));
        buttonPanel.add(createNewItemButton);
        buttonPanel.add(createNewItemButton2);
        leftPanel.add(buttonPanel, BorderLayout.NORTH);
    }

    private static void setupRightPanel() {
        /* Setting up left panel buttons */

        rightPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 20, 60, 20);
        rightPanel.setBackground(new Color(255, 255, 255));
        rightPanel.setPreferredSize(new Dimension(522, 670));
        rightPanel.setBorder(paddingBorder);


//        filePanel.setBorder(BorderFactory.createEmptyBorder(300, 0, 0, 0));
//        rightPanel.setLayout(new BorderLayout());

    }

    public EditDataMemberPanel(){
        this.setBackground(Color.WHITE);
        setupLeftPanel();
        this.add(leftPanel, BorderLayout.NORTH);
        setupRightPanel();
        this.add(rightPanel, BorderLayout.NORTH);

    }    
}
