package boundary.panel.member.subpanel;

import boundary.constants.Colors;
import boundary.observer.panelflow.PanelFlowEvent;
import boundary.widget.PlainScrollBar;
import boundary.widget.RoundedPanel;
import boundary.widget.TabPane;

import model.*;
import controller.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class HistoriTransaksiPane extends TabPane {
    private GenericDataIO<FixedBill> fixedBillDataIO;
    private Member member;
    private JPanel headerPanel;
    private JScrollPane scrollListPanel;
    private RoundedPanel createNewItemButtonPanel = new RoundedPanel(25, new Color(0x4C6EDF), false, Color.WHITE, 0);
    private RoundedPanel importButtonPanel = new RoundedPanel(25, new Color(0x4C6EDF), false, Color.WHITE, 0);
    private RoundedPanel totalBarangPanel = new RoundedPanel(25, Color.WHITE, true, new Color(0x5D82E8), 2);
    Object[][] data;

    private void setupHeaderPanel() {
        headerPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 0, 60, 20);
        headerPanel.setBackground(new Color(255, 255, 255));
        headerPanel.setPreferredSize(new Dimension(1000, 168));
        headerPanel.setBorder(paddingBorder);

        JButton backButton = new JButton("<");
        backButton.setFont(new Font("Inter", Font.BOLD, 33));
        backButton.setForeground(new Color(229, 151, 0));
        backButton.setPreferredSize(new Dimension(60, 40));
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.addActionListener(e -> panelFlowObserver.newEvent(PanelFlowEvent.retract()));
        headerPanel.add(backButton, BorderLayout.WEST);

        // Label "Daftar Barang"
        JLabel historyTransaksi = new JLabel("Histori Transaksi");
        historyTransaksi.setHorizontalAlignment(SwingConstants.LEFT);
        historyTransaksi.setForeground(new Color(229, 151, 0));
        historyTransaksi.setFont(new Font("Inter", Font.BOLD, 33));
        headerPanel.add(historyTransaksi, BorderLayout.WEST);

        // Horizontal Divider
        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setPreferredSize(new Dimension(380, 0));
        separator1.setVisible(true);
        headerPanel.add(separator1);

        // Label "Total Barang"
        JLabel totalBarangLabel = new JLabel("Total Barang : 127");
        totalBarangLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalBarangLabel.setPreferredSize(new Dimension(180, 38));
        totalBarangLabel.setFont(new Font("Inter", Font.PLAIN, 15));
        totalBarangLabel.setForeground(new Color(36, 60, 148));
        totalBarangPanel.add(totalBarangLabel, BorderLayout.WEST);
        headerPanel.add(totalBarangPanel, BorderLayout.WEST);
    }

    private void setupTable() {
        // Table
        scrollListPanel = new JScrollPane();
        scrollListPanel.setBackground(Color.LIGHT_GRAY);
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
        itemList.setBorder(BorderFactory.createEmptyBorder());
        itemList.setShowVerticalLines(false);
        itemList.setBackground(Colors.WHITE);
        itemList.setPreferredScrollableViewportSize(itemList.getPreferredSize());
        itemList.setRowSelectionAllowed(false);
        itemList.setColumnSelectionAllowed(false);
        itemList.setCellSelectionEnabled(false);
        itemList.setFocusable(false);
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
    }

    private Object[][] getData() {
        List<FixedBill> bills = fixedBillDataIO.getAll();
        bills = bills.stream()
                .filter(bill -> bill.getCust().getId() == member.getId())
                .collect(Collectors.toList());
        data = new Object[bills.size()][5];
        for (int i = 0; i < bills.size(); i++) {
            // TODO: Resolve name, price, and discount
            FixedBill bill = bills.get(i);
            data[i] = new Object[] {
                    bill.getId(), "", bill.getDate(), "", ""
            };
        }
        return data;
    }

    private String[] getColumnNames() {
        String[] columnNames = { "ID", "Nama", "Tanggal Transaksi", "Subtotal", "Diskon" };
        return columnNames;
    }

    public HistoriTransaksiPane(Member member, GenericDataIO<FixedBill> fixedBillDataIO) {
        this.member = member;
        this.fixedBillDataIO = fixedBillDataIO;
        this.setBackground(Color.WHITE);
        setupHeaderPanel();
        this.add(headerPanel, BorderLayout.NORTH);
        setupTable();
        this.add(scrollListPanel, BorderLayout.CENTER);
    }
}
