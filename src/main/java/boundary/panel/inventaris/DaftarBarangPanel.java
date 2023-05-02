package boundary.panel.inventaris;

import boundary.widget.PressedButton;
import boundary.widget.RoundBorder;
import boundary.widget.RoundedPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class DaftarBarangPanel extends JPanel{
    private static JPanel headerPanel;
    private static JScrollPane scrollListPanel;
    private RoundedPanel createNewItemButtonPanel = new RoundedPanel(25, new Color(0x4C6EDF), false, Color.WHITE,  0);
    private RoundedPanel importButtonPanel = new RoundedPanel(25, new Color(0x4C6EDF), false, Color.WHITE,  0);
    private RoundedPanel totalBarangPanel = new RoundedPanel(25, Color.WHITE, true, new Color(0x5D82E8),  2);

    private void setupHeaderPanel(){
        headerPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 20, 60, 20);
        headerPanel.setBackground(new Color(255,255,255));
        headerPanel.setPreferredSize(new Dimension(1045,168));
        headerPanel.setBorder(paddingBorder);

        // Label "Daftar Barang"
        JLabel daftarBarangLabel = new JLabel("Daftar Barang");
        daftarBarangLabel.setHorizontalAlignment(SwingConstants.LEFT);
        daftarBarangLabel.setForeground(new Color(229, 151, 0));
        daftarBarangLabel.setFont(new Font("Inter", Font.BOLD, 33));
        headerPanel.add(daftarBarangLabel, BorderLayout.WEST);

        // Horizontal Divider
        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setPreferredSize(new Dimension(150, 1));
        separator1.setVisible(true);
        headerPanel.add(separator1);

        // Label "Total Barang"
        JLabel totalBarangLabel = new JLabel("Total Barang : 127");
        totalBarangLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalBarangLabel.setPreferredSize(new Dimension(180,38));
        totalBarangLabel.setFont(new Font("Inter", Font.PLAIN, 15));
        totalBarangLabel.setForeground(new Color(36,60,148));
        totalBarangPanel.add(totalBarangLabel, BorderLayout.WEST);
        headerPanel.add(totalBarangPanel, BorderLayout.WEST);

        // Horizontal Divider
        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(20, 1));
        separator2.setVisible(true);
        headerPanel.add(separator2);

        // Button Import
        JButton importButton = new JButton("Import");
        PressedButton buttonUI = new PressedButton(new Color(45,77,182));
        importButton.setFont(new Font("Inter", Font.PLAIN, 15));
        importButton.setBackground(new Color(76,110,223));
        importButton.setForeground(Color.WHITE);
        importButton.setOpaque(true);
        importButton.setFocusable(false);
        importButton.setPreferredSize(new Dimension(130,38));
        importButton.setUI(buttonUI);
        importButton.setBorder(new RoundBorder(20));
        importButtonPanel.add(importButton, BorderLayout.WEST);
        headerPanel.add(importButtonPanel, BorderLayout.WEST);

        // Horizontal Divider
        JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        separator3.setPreferredSize(new Dimension(20, 1));
        separator3.setVisible(true);
        headerPanel.add(separator3);

        // Button Tambah Baru
        JButton createNewItemButton = new JButton("+ Tambah Baru");
        createNewItemButton.setFont(new Font("Inter", Font.PLAIN, 15));
        createNewItemButton.setBackground(new Color(76,110,223));
        createNewItemButton.setForeground(Color.WHITE);
        createNewItemButton.setOpaque(true);
        createNewItemButton.setFocusable(false);
        createNewItemButton.setPreferredSize(new Dimension(180,38));
        createNewItemButton.setUI(buttonUI);
        createNewItemButton.setBorder(new RoundBorder(20));
        createNewItemButtonPanel.add(createNewItemButton, BorderLayout.WEST);
        headerPanel.add(createNewItemButtonPanel, BorderLayout.WEST);
    }

    private void setupTable(){
        // Table
        scrollListPanel = new JScrollPane();
        scrollListPanel.setBackground(Color.LIGHT_GRAY);
        scrollListPanel.setPreferredSize(new Dimension(1045,515));
        // Cell Renderer


        JTable itemList = new JTable(getData(), getColumnNames());
        itemList.setRowHeight(60);
        itemList.setDefaultEditor(Object.class, null); // make cells not editable
        itemList.getColumnModel().getColumn(0).setPreferredWidth(300);
        itemList.getColumnModel().getColumn(1).setPreferredWidth(300);
        itemList.getColumnModel().getColumn(2).setPreferredWidth(100);
        itemList.getColumnModel().getColumn(3).setPreferredWidth(100);
        itemList.getColumnModel().getColumn(4).setPreferredWidth(100);
        itemList.getColumnModel().getColumn(5).setPreferredWidth(200);
        scrollListPanel.setViewportView(itemList);

        // Set transparent table lines
        itemList.setGridColor(new Color(240, 240, 240));
        itemList.setIntercellSpacing(new Dimension(0, 5));

        // create a custom header renderer that doesn't paint any effects
        TableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createEmptyBorder());
                setHorizontalAlignment(JLabel.CENTER);
                setBackground(new Color(0xDAE2FF));
                setForeground(new Color(0x243C94));
                setFont(new Font("Inter", Font.BOLD, 12));
                return this;
            }
        };

        // Set blue background color for table header
        JTableHeader tableHeader = itemList.getTableHeader();
        tableHeader.setDefaultRenderer(headerRenderer);
        tableHeader.setAlignmentX(10);
        tableHeader.setFont(tableHeader.getFont().deriveFont(Font.BOLD, 14));
        tableHeader.setPreferredSize(new Dimension(994, 43));
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

    public DaftarBarangPanel(){
        this.setBackground(Color.WHITE);
        setupHeaderPanel();
        this.add(headerPanel,BorderLayout.NORTH);
        setupTable();
        this.add(scrollListPanel, BorderLayout.CENTER);
    }
}
