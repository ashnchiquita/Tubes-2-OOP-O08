package boundary.panel.inventaris.subpanel;

import boundary.constants.Colors;
import boundary.observer.panelflow.PanelFlowEvent;
import boundary.widget.*;

import model.*;
import controller.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DaftarBarangPane extends TabPane {
    private GenericDataIO<Barang> barangDataIO;
    private JPanel headerPanel;
    private JScrollPane scrollListPanel;
    private final RoundedPanel createNewItemButtonPanel = new RoundedPanel(25, new Color(0x4C6EDF), false, Color.WHITE,
            0);
    // private final RoundedPanel importButtonPanel = new RoundedPanel(25, new
    // Color(0x4C6EDF), false, Color.WHITE, 0);
    private final RoundedPanel totalBarangPanel = new RoundedPanel(25, Color.WHITE, true, new Color(0x5D82E8), 2);

    private void setupHeaderPanel() {
        headerPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 20, 60, 20);
        headerPanel.setBackground(new Color(255, 255, 255));
        headerPanel.setPreferredSize(new Dimension(1000, 168));
        headerPanel.setBorder(paddingBorder);

        // Label "Daftar Barang"
        JLabel daftarBarangLabel = new JLabel("Daftar Barang");
        daftarBarangLabel.setHorizontalAlignment(SwingConstants.LEFT);
        daftarBarangLabel.setForeground(new Color(229, 151, 0));
        daftarBarangLabel.setFont(new Font("Inter", Font.BOLD, 33));
        headerPanel.add(daftarBarangLabel, BorderLayout.WEST);

        // Horizontal Divider
        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setPreferredSize(new Dimension(220, 0));
        separator1.setVisible(true);
        headerPanel.add(separator1);

        // Label "Total Barang"
        JLabel totalBarangLabel = new JLabel("Total Barang : " + barangDataIO.getAll().size());
        totalBarangLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalBarangLabel.setPreferredSize(new Dimension(180, 38));
        totalBarangLabel.setFont(new Font("Inter", Font.PLAIN, 15));
        totalBarangLabel.setForeground(new Color(36, 60, 148));
        totalBarangPanel.add(totalBarangLabel, BorderLayout.WEST);
        headerPanel.add(totalBarangPanel, BorderLayout.WEST);

        // Horizontal Divider
        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(20, 0));
        separator2.setVisible(true);
        headerPanel.add(separator2);

        // Horizontal Divider
        JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        separator3.setPreferredSize(new Dimension(20, 0));
        separator3.setVisible(true);
        headerPanel.add(separator3);

        // Button Tambah Baru
        PressedButton buttonUI = new PressedButton(new Color(45, 77, 182));
        JButton createNewItemButton = new JButton("+ Tambah Baru");
        createNewItemButton.setFont(new Font("Inter", Font.PLAIN, 15));
        createNewItemButton.setBackground(new Color(76, 110, 223));
        createNewItemButton.setForeground(Color.WHITE);
        createNewItemButton.setOpaque(true);
        createNewItemButton.setFocusable(false);
        createNewItemButton.setPreferredSize(new Dimension(180, 38));
        createNewItemButton.setUI(buttonUI);
        createNewItemButton.setBorder(new RoundBorder(20));
        createNewItemButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        createNewItemButton.addActionListener(
                e -> panelFlowObserver.newEvent(new PanelFlowEvent(new TambahBarangPane(barangDataIO), true)));
        createNewItemButtonPanel.add(createNewItemButton, BorderLayout.WEST);
        headerPanel.add(createNewItemButtonPanel, BorderLayout.WEST);
    }

    private void setupTable() {
        // Table
        scrollListPanel = new JScrollPane();
        scrollListPanel.setBackground(Color.WHITE);
        scrollListPanel.setPreferredSize(new Dimension(900, 450));
        scrollListPanel.setBorder(BorderFactory.createEmptyBorder());
        // Cell Renderer

        JTable itemList = new JTable(getData(), getColumnNames());
        itemList.setRowHeight(50);
        itemList.setDefaultEditor(Object.class, null); // make cells not editable
        itemList.getColumnModel().getColumn(0).setPreferredWidth(100);
        itemList.getColumnModel().getColumn(1).setPreferredWidth(300);
        itemList.getColumnModel().getColumn(2).setPreferredWidth(200);
        itemList.getColumnModel().getColumn(3).setPreferredWidth(200);
        itemList.getColumnModel().getColumn(4).setPreferredWidth(200);
        itemList.getColumnModel().getColumn(5).setPreferredWidth(200);
        itemList.getColumnModel().getColumn(6).setPreferredWidth(200);
        itemList.setBorder(BorderFactory.createEmptyBorder());
        itemList.setShowVerticalLines(false);
        itemList.setBackground(Colors.WHITE);
        itemList.setPreferredScrollableViewportSize(itemList.getPreferredSize());

        itemList.setRowSelectionAllowed(false);
        itemList.setColumnSelectionAllowed(false);
        itemList.setCellSelectionEnabled(true);
        scrollListPanel.setViewportView(itemList);
        scrollListPanel.getVerticalScrollBar().setUI(new PlainScrollBar(Colors.WHITE, Colors.SIDE_SLIDER_BLUE));

        // Set transparent table lines
        itemList.setGridColor(new Color(240, 240, 240));
        itemList.setIntercellSpacing(new Dimension(0, 5));
        itemList.setFillsViewportHeight(true);

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
        tableHeader.setPreferredSize(new Dimension(600, 43));

        class ButtonRenderer extends JButton implements TableCellRenderer, ActionListener {
            private boolean isButtonClicked;
            private int index;

            public ButtonRenderer() {
                setOpaque(true);
                setBackground(new Color(0x4C6EDF));
                setForeground(Color.WHITE);
                setPreferredSize(new Dimension(25, 20));
                setBorderPainted(false);
                setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
                this.addActionListener(this);
                isButtonClicked = false;
            }

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                index = row;
                if (isSelected && column == 6 && hasFocus) {
                    if (!isButtonClicked) {
                        this.doClick();
                        setBackground(Colors.LOMBOK_RED);
                        isButtonClicked = true;
                    }
                } else {
                    setBackground(Colors.LOMBOK_RED_LESSOPAQUE);
                    isButtonClicked = false;
                }
                if (column == 6) {
                    setText("Hapus");
                }
                return this;
            }

            public void actionPerformed(ActionEvent e) {
                barangDataIO.delete((Integer) itemList.getValueAt(index, 0));
                panelFlowObserver.newEvent(new PanelFlowEvent(
                        new DaftarBarangPane(barangDataIO), false));
            }
        }
        TableCellRenderer nonselectableRenderer = new TableCellRenderer() {
            JLabel label;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                if (label == null) {
                    label = new JLabel();
                    label.setBackground(Colors.WHITE); // warna judul kolom
                    label.setForeground(Colors.BLACK); // set the color of the header text
                    label.setFont(label.getFont().deriveFont(Font.PLAIN)); // set the font style to bold
                }
                label.setText(value != null ? value.toString() : "");
                return label;
            }
        };
        TableColumn column = itemList.getColumnModel().getColumn(6);
        column.setCellRenderer(new ButtonRenderer());
        for (int i = 0; i < 6; i++) {
            itemList.getColumnModel().getColumn(i).setCellRenderer(nonselectableRenderer);
        }
    }

    private Object[][] getData() {
        List<Barang> listBarang = barangDataIO.getAll();
        Object[][] data = new Object[listBarang.size()][7];
        for (int i = 0; i < listBarang.size(); i++) {
            Barang barang = listBarang.get(i);
            data[i] = new Object[] {
                    barang.getId(), barang.getName(), barang.getKategori(), barang.getHargaBeli(),
                    barang.getHargaJual(), barang.getJumlah(), ""
            };
        }
        return data;
    }

    private static String[] getColumnNames() {

        return new String[] { "ID", "Nama", "Kategori", "Harga Beli", "Harga Jual", "Stok", "Hapus" };
    }

    public DaftarBarangPane(GenericDataIO<Barang> barangDataIO) {
        this.barangDataIO = barangDataIO;
        this.setBackground(Color.WHITE);
        setupHeaderPanel();
        this.add(headerPanel, BorderLayout.NORTH);
        setupTable();
        this.add(scrollListPanel, BorderLayout.CENTER);
    }
}
