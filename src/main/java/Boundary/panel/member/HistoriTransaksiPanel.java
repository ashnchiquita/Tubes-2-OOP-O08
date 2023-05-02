package boundary.panel.member;

import boundary.widget.PressedButton;
import boundary.widget.RoundBorder;
import boundary.widget.RoundedPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class HistoriTransaksiPanel extends JPanel{
    private static JPanel headerPanel;
    private static JScrollPane scrollListPanel;
    private RoundedPanel createNewItemButtonPanel = new RoundedPanel(25, new Color(0x4C6EDF), false, Color.WHITE,  0);
    private RoundedPanel importButtonPanel = new RoundedPanel(25, new Color(0x4C6EDF), false, Color.WHITE,  0);
    private RoundedPanel totalBarangPanel = new RoundedPanel(25, Color.WHITE, true, new Color(0x5D82E8),  2);

    private void setupHeaderPanel(){
        headerPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 0, 60, 20);
        headerPanel.setBackground(new Color(255,255,255));
        headerPanel.setPreferredSize(new Dimension(1045,168));
        headerPanel.setBorder(paddingBorder);


        JButton backButton = new JButton("< Histori Transaksi");
        backButton.setFont(new Font("Inter", Font.BOLD, 33));
        backButton.setForeground(new Color(229, 151, 0));
        backButton.setPreferredSize(new Dimension(450, 40));
        backButton.setBackground(Color.WHITE);
//        backButton.setPreferredSize(new Dimension(994, 43));

        headerPanel.add(backButton, BorderLayout.WEST);
        // Label "Daftar Barang"

        // Horizontal Divider
        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setPreferredSize(new Dimension(150, 1));
        separator1.setVisible(true);
        headerPanel.add(separator1);

        // Horizontal Divider
        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(20, 1));
        separator2.setVisible(true);
        headerPanel.add(separator2);

        // Horizontal Divider
        JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        separator3.setPreferredSize(new Dimension(20, 1));
        separator3.setVisible(true);
        headerPanel.add(separator3);

        // Label "Total Barang"
        JLabel totalBarangLabel = new JLabel("Total Barang : 127");
        totalBarangLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalBarangLabel.setPreferredSize(new Dimension(180,38));
        totalBarangLabel.setFont(new Font("Inter", Font.PLAIN, 15));
        totalBarangLabel.setForeground(new Color(36,60,148));
        totalBarangPanel.add(totalBarangLabel, BorderLayout.WEST);
        headerPanel.add(totalBarangPanel, BorderLayout.WEST);
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
                {" ", "jennie", "29/04/2023", "$3", ""},
                {" ", "rose", "05/10/2023", "$10", ""},
                {" ", "jisoo", "25/12/2023", "$5", ""},
                {" ", "lisa", "31/12/2023", "$8", ""},
                {" ", "jennie", "29/04/2023", "$3", ""},
                {" ", "rose", "05/10/2023", "$10", ""},
                {" ", "jisoo", "25/12/2023", "$5", ""},
                {" ", "lisa", "31/12/2023", "$8", ""},
                {" ", "jennie", "29/04/2023", "$3", ""},
                {" ", "rose", "05/10/2023", "$10", ""},
                {" ", "jisoo", "25/12/2023", "$5", ""},
                {" ", "lisa", "31/12/2023", "$8", ""},
                {" ", "jennie", "29/04/2023", "$3", ""},
                {" ", "rose", "05/10/2023", "$10", ""},
                {" ", "jisoo", "25/12/2023", "$5", ""},
                {" ", "lisa", "31/12/2023", "$8", ""},
        };
        return data;
    }


    private static String[] getColumnNames() {
        String[] columnNames = {"ID", "Nama", "Tanggal Transaksi", "Subtotal", "Diskon"};
        return columnNames;
    }

    public HistoriTransaksiPanel(){
        this.setBackground(Color.WHITE);
        setupHeaderPanel();
        this.add(headerPanel,BorderLayout.NORTH);
        setupTable();
        this.add(scrollListPanel, BorderLayout.CENTER);
    }
}
