package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TambahBarangUI extends JPanel {
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
        JLabel kategoriLabel = new JLabel("Kategori");
        kategoriLabel.setFont(new Font("Inter", Font.BOLD, 15));
        kategoriLabel.setForeground(new Color(0x243C94));
        kategoriLabel.setPreferredSize(new Dimension(475, 40));
        leftPanel.add(kategoriLabel, BorderLayout.NORTH);
        JTextField kategoriTextField = new JTextField("Kategori");
        kategoriTextField.setPreferredSize(new Dimension(475, 45));
        kategoriTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        kategoriTextField.setForeground(new Color(0xD9D9D9));
        kategoriTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0X4B4FC4)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        leftPanel.add(kategoriTextField, BorderLayout.NORTH);

        // Harga Beli dan Harga Jual
        JLabel hargaBeliLabel = new JLabel("Harga beli");
        hargaBeliLabel.setFont(new Font("Inter", Font.BOLD, 15));
        hargaBeliLabel.setForeground(new Color(0x243C94));
        hargaBeliLabel.setPreferredSize(new Dimension(240, 40));
        leftPanel.add(hargaBeliLabel, BorderLayout.WEST);
        JLabel hargaJualLabel = new JLabel("Jual beli");
        hargaJualLabel.setFont(new Font("Inter", Font.BOLD, 15));
        hargaJualLabel.setForeground(new Color(0x243C94));
        hargaJualLabel.setPreferredSize(new Dimension(230, 40));
        leftPanel.add(hargaJualLabel, BorderLayout.WEST);

        JTextField hargaBeliTextField = new JTextField("HargaBeli");
        hargaBeliTextField.setPreferredSize(new Dimension(240, 40));
        hargaBeliTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        hargaBeliTextField.setForeground(new Color(0xD9D9D9));
        hargaBeliTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0X4B4FC4)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        leftPanel.add(hargaBeliTextField, BorderLayout.WEST);

        JTextField hargaJualTextField = new JTextField("HargaBeli");
        hargaJualTextField.setPreferredSize(new Dimension(230, 40));
        hargaJualTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        hargaJualTextField.setForeground(new Color(0xD9D9D9));
        hargaJualTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0X4B4FC4)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        leftPanel.add(hargaJualTextField, BorderLayout.WEST);

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

        JButton createNewItemButton = new JButton("+ Tambah Baru");
        CustomButtonUI buttonUI = new CustomButtonUI(new Color(45,77,182));
        createNewItemButton.setFont(new Font("Inter", Font.PLAIN, 15));
        createNewItemButton.setBackground(new Color(76,110,223));
        createNewItemButton.setForeground(Color.WHITE);
        createNewItemButton.setOpaque(true);
        createNewItemButton.setFocusable(false);
        createNewItemButton.setPreferredSize(new Dimension(180,43));
        createNewItemButton.setUI(buttonUI);
        createNewItemButton.setBorder(new RoundBorder(20));
        leftPanel.add(createNewItemButton, BorderLayout.WEST);
    }

    private static void setupRightPanel() {
        /* Setting up left panel buttons */

        rightPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 20, 60, 20);
        rightPanel.setBackground(new Color(255, 255, 255));
        rightPanel.setPreferredSize(new Dimension(522, 670));
        rightPanel.setBorder(paddingBorder);

        JPanel filePanel = new JPanel();
        Border dashedBorder = BorderFactory.createDashedBorder(new Color(0x4B4FC4));
        filePanel.setBackground(new Color(0, 255, 255));
        filePanel.setPreferredSize(new Dimension(318, 333));
        filePanel.setBorder(paddingBorder);
        rightPanel.add(filePanel, BorderLayout.WEST);

    }

    public TambahBarangUI(){
        this.setBackground(Color.WHITE);
        setupLeftPanel();
        this.add(leftPanel, BorderLayout.NORTH);
        setupRightPanel();
        this.add(rightPanel, BorderLayout.NORTH);

    }

}