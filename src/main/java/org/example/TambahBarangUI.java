package org.example;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TambahBarangUI extends JFrame {
    // Setup
    private static JFrame mainFrame;
    private static JPanel mainRight;
    private static JPanel mainLeft;

    // Header
    private static JButton backButton;
    private static JLabel tambahBarangLabel
            = new JLabel("Tambah Barang Baru");

    // Nama
    private static JLabel namaLabel
            = new JLabel("Nama");
    private static JTextField namaTextField;

    // Kategori
    private static JLabel kategoriLabel
            = new JLabel("Kategori");
    private static JTextField kategoriTextField;

    // Harga Beli
    private static JLabel hargaBeliLabel
            = new JLabel("Harga Beli");
    private static JTextField hargaBeliTextField;

    // Harga Jual
    private static JLabel hargaJualLabel
            = new JLabel("Harga Jual");
    private static JTextField hargaJualTextField;

    // Stok
    private static JLabel stokLabel
            = new JLabel("Stok");
    private static JTextField stokTextField;

    private static void setupMainFrame(JFrame parentFrame){

        // Main Right
        mainRight = new JPanel();
        mainRight.setLayout(new BorderLayout());
        mainRight.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        parentFrame.add(mainRight, BorderLayout.CENTER);
        int vw = parentFrame.getWidth();

        setupLeftPanel(mainRight);

        mainFrame.setVisible(true);
    }

    private static void setupLeftPanel(JPanel parentPanel){

        // Header Atas
        backButton = new JButton("Back");
        parentPanel.add(backButton,BorderLayout.NORTH);
        parentPanel.add(tambahBarangLabel,BorderLayout.NORTH);

        // Nama
        namaTextField = new JTextField("Nama");
        parentPanel.add(namaLabel, BorderLayout.NORTH);
        parentPanel.add(namaTextField, BorderLayout.NORTH);

        // Kategori
        kategoriTextField = new JTextField("Kategori");
        parentPanel.add(kategoriLabel, BorderLayout.NORTH);
//        parentPanel.add(kategoriTextField, BorderLayout.NORTH);


    }


    public static void main(String[] args) {
        mainFrame = new JFrame("Daftar Barang Baru");
        mainFrame.setSize(1280, 720);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.getContentPane().setBackground(Color.LIGHT_GRAY);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setupMainFrame(mainFrame);
            }
        });
    }

}
