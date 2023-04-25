import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JButton;

public class HistoriTransaksiWindow {
    private static void showUI() {
        JFrame mainFrame = new JFrame("Histori Transaksi Window");
        mainFrame.setSize(1280, 720);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.getContentPane().setBackground(Color.WHITE);

        int vw = mainFrame.getWidth(), vh = mainFrame.getHeight();

        JPanel sideNav = new JPanel();
        sideNav.setPreferredSize(new Dimension(230, vh));
        sideNav.setBackground(new Color(56, 100, 194));
        mainFrame.add(sideNav, BorderLayout.WEST);

        JPanel mainRight = new JPanel();
        mainRight.setLayout(new BorderLayout());
        mainRight.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        mainFrame.add(mainRight, BorderLayout.CENTER);
        mainRight.setBackground(Color.WHITE);

        JScrollPane scrollListPanel = new JScrollPane();
        scrollListPanel.setBackground(Color.LIGHT_GRAY);
        scrollListPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // hide vertical scrollbar
        mainRight.add(scrollListPanel, BorderLayout.CENTER);

        // Create the header panel and add it to the mainRight panel

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // create a panel for the title
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false); // set panel to be transparent
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        topPanel.add(titlePanel); // add title panel to topPanel

        JButton headerLabel = new JButton("<   Histori Transaksi");
        headerLabel.setBackground(Color.WHITE); // set button background color to transparent
        headerLabel.setForeground(new Color(0xE5, 0x97, 0x00)); // set button text color
        headerLabel.setFont(new Font(headerLabel.getFont().getName(), Font.PLAIN, 40));
        headerLabel.setBorderPainted(false); 
        headerLabel.setHorizontalAlignment(SwingConstants.LEFT);

        titlePanel.add(headerLabel);

        // create a panel for the button and text box
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // set panel to be transparent
        buttonPanel.setLayout(new BorderLayout());

        topPanel.add(buttonPanel, BorderLayout.EAST);

        mainRight.add(topPanel, BorderLayout.NORTH);

        // add the table to mainRight
        mainRight.add(scrollListPanel, BorderLayout.CENTER);

        JTable itemList = new JTable(getData(), getColumnNames());
        itemList.setRowHeight(50);
        itemList.setDefaultEditor(Object.class, null); // make cells not editable
        itemList.getColumnModel().getColumn(0).setPreferredWidth(70);
        itemList.getColumnModel().getColumn(1).setPreferredWidth(200);
        itemList.getColumnModel().getColumn(2).setPreferredWidth(200);
        scrollListPanel.setViewportView(itemList);
        itemList.setIntercellSpacing(new Dimension(0, 0));
        itemList.setBackground(Color.WHITE);
        
        Font font = itemList.getFont();
        Font newFont = new Font(font.getName(), Font.PLAIN, font.getSize() + 2); // increase the size by 2
        itemList.setFont(newFont);

        // Center align all the columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < itemList.getColumnCount(); i++) {
            itemList.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Hide table lines
        itemList.setShowGrid(false);
        
        JTableHeader tableHeader = itemList.getTableHeader();
        tableHeader.setBackground(new Color(0xDAE2FF));
        tableHeader.setForeground(new Color(0x243C94)); // set the color of the header text
        tableHeader.setFont(tableHeader.getFont().deriveFont(Font.BOLD, 28)); // set the font style to bold
        
        // Set the preferred height of the table header to 30px
        Dimension headerSize = tableHeader.getPreferredSize();
        headerSize.height = 50;
        tableHeader.setPreferredSize(headerSize);
        
        // Set the custom TableCellRenderer for the table header
        TableCellRenderer headerRenderer = new TableCellRenderer() {
            JLabel label;
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (label == null) {
                    label = new JLabel();
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setOpaque(true);
                    label.setBackground(new Color(0xDAE2FF));
                    label.setForeground(new Color(0x243C94)); // set the color of the header text
                    label.setFont(label.getFont().deriveFont(Font.BOLD)); // set the font style to bold
                }
                label.setText(value != null ? value.toString() : "");
                return label;
            }
        };
        for (int i = 0; i < itemList.getColumnModel().getColumnCount(); i++) {
            itemList.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        
        JTextField rowCountField = new JTextField();
        rowCountField.setEditable(false); // make text box not editable
        rowCountField.setHorizontalAlignment(JTextField.CENTER); // center align text
        rowCountField.setPreferredSize(new Dimension(50, 25)); // set preferred size
        rowCountField.setText(String.valueOf(itemList.getRowCount())); // set initial text to the number of rows
        buttonPanel.add(rowCountField, BorderLayout.WEST); // add text box to button panel

        
        mainFrame.setVisible(true);
    }

    
    
    private static Object[][] getData() {
        Object[][] data = {
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

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showUI();
            }
        });
    }
}
    