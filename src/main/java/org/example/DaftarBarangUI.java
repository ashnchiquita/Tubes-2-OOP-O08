package org.example;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class DaftarBarangUI extends JFrame{
    private static JFrame mainFrame;
    private static JPanel mainRight;

    private static void setupMainFrame() {
        // Setup Main Frame
        mainFrame = new JFrame("Test Window");
        mainFrame.setSize(1280, 720);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.getContentPane().setBackground(Color.LIGHT_GRAY);
    }
    private static void setupRightPanel(){

        // Setup mainRight Panel
        mainRight = new JPanel();
        mainRight.setLayout(new BorderLayout());
        mainRight.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        mainFrame.add(mainRight, BorderLayout.CENTER);

    }

    private static void showUI() {
        setupMainFrame();
        setupRightPanel();

        int vw = mainFrame.getWidth(), vh = mainFrame.getHeight();

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255,255,255));
        headerPanel.setPreferredSize(new Dimension((int) (0.81 * vw), 48));
        mainRight.add(headerPanel,BorderLayout.NORTH);
            // Label "Daftar Barang"
            JLabel daftarBarangLabel = new JLabel("Daftar Barang");
            daftarBarangLabel.setHorizontalAlignment(SwingConstants.LEFT);
            headerPanel.add(daftarBarangLabel, BorderLayout.WEST);

            // Horizontal Divider

            // Label "Total Barang"
            JLabel totalBarangLabel = new JLabel("Total Barang : 127");
            totalBarangLabel.setHorizontalAlignment(SwingConstants.LEFT);
            headerPanel.add(totalBarangLabel, BorderLayout.WEST);
            // Button Import
            JButton importButton = new JButton("Import");
            headerPanel.add(importButton, BorderLayout.WEST);
            // Button Tambah Baru
            JButton createNewItemButton = new JButton("+ Tambah Baru");
            headerPanel.add(createNewItemButton, BorderLayout.WEST);

        // Table
        JScrollPane scrollListPanel = new JScrollPane();
        scrollListPanel.setBackground(Color.LIGHT_GRAY);
        mainRight.add(scrollListPanel, BorderLayout.CENTER);

        JTable itemList = new JTable(getData(), getColumnNames());
        itemList.setRowHeight(150);
        itemList.setDefaultEditor(Object.class, null); // make cells not editable
        itemList.getColumnModel().getColumn(0).setPreferredWidth(300);
        itemList.getColumnModel().getColumn(1).setPreferredWidth(300);
        itemList.getColumnModel().getColumn(2).setPreferredWidth(100);
        itemList.getColumnModel().getColumn(3).setPreferredWidth(100);
        itemList.getColumnModel().getColumn(4).setPreferredWidth(100);
        itemList.getColumnModel().getColumn(5).setPreferredWidth(200);
        scrollListPanel.setViewportView(itemList);

        // Set transparent table lines
        itemList.setGridColor(new Color(0, 0, 0, 0));
        itemList.setIntercellSpacing(new Dimension(0, 0));

        // Set blue background color for table header
        JTableHeader tableHeader = itemList.getTableHeader();
        tableHeader.setBackground(new Color(56, 100, 194));
        tableHeader.setForeground(Color.WHITE);

        mainFrame.setVisible(true);
    }

    private static Object[][] getData() {
        Object[][] data = {
                {"Salad Tuna", "(Must choose level)", "$10.99",
                        "5000", "2000", "2000"},
                {"Beef Contoh", "", "$10.99",
                        "5000", "2000", "2000"},
                {"Iga Bakar", "(Must choose level)", "$10.99",
                        "5000", "2000", "2000"},
                {"Salad Egg", "", "$10.99",
                        "5000", "2000", "2000"},
                {"Salad Tuna", "(Must choose level)", "$10.99",
                        "5000", "2000", "2000"},
        };
        return data;
    }


    private static String[] getColumnNames() {
        String[] columnNames = {"ID", "Nama", "Kategori", "Harga Beli", "Harga Jual", "Stok"};
        return columnNames;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showUI();
            }
        });
    }
}
