package boundary.panel.member;

import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
// import javax.swing.border.LineBorder;
// import javax.swing.event.ChangeEvent;
// import javax.swing.event.ChangeListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
// import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class MemberWindow {
    private static void showUI() {
        JFrame mainFrame = new JFrame("Member Window");
        mainFrame.setSize(1280, 720);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.getContentPane().setBackground(Color.LIGHT_GRAY);

        int vw = mainFrame.getWidth(), vh = mainFrame.getHeight();

        JPanel sideNav = new JPanel();
        sideNav.setPreferredSize(new Dimension(230, vh));
        sideNav.setBackground(new Color(56, 100, 194));
        mainFrame.add(sideNav, BorderLayout.WEST);

        JPanel mainRight = new JPanel();
        mainRight.setLayout(new BorderLayout());
        mainRight.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        mainFrame.add(mainRight, BorderLayout.CENTER);

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
        topPanel.add(titlePanel); // add title panel to topPanel

        // create label for the title
        JLabel headerLabel = new JLabel("Daftar Member");
        headerLabel.setForeground(Color.decode("#E59700")); // set font color
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30)); // set larger font size
        headerLabel.setBorder(new EmptyBorder(0, 10, 0, 0)); // add some padding to the left
        titlePanel.add(headerLabel, BorderLayout.NORTH); // add label to title panel

        // create a panel for the button and text box
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // set panel to be transparent
        buttonPanel.setLayout(new BorderLayout());

        // button
        JButton myButton = new JButton("Import / Export");
        myButton.setForeground(Color.WHITE);
        myButton.setBackground(Color.decode("#E59700"));
        buttonPanel.add(myButton, BorderLayout.CENTER); // add button to button panel
        buttonPanel.setBorder(new EmptyBorder(0, 10, 0, 10)); // add some padding to the right

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
        itemList.getColumnModel().getColumn(3).setPreferredWidth(200);
        scrollListPanel.setViewportView(itemList);
        itemList.setIntercellSpacing(new Dimension(0, 0));
        
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
        
        class ButtonRenderer extends JButton implements TableCellRenderer {
            public ButtonRenderer() {
                setOpaque(true);
                setBackground(new Color(0x4C6EDF));
                setForeground(Color.WHITE);
                setPreferredSize(new Dimension(25, 20));
                setBorderPainted(false);
                setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
            }
        
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (isSelected) {
                    setBackground(new Color(0xCDD6F6));
                }
                if (!isSelected){
                    setBackground(new Color(0x4C6EDF));
                }
                if (value instanceof String) {
                    setText((String)value);
                } else if (value instanceof JButton) {
                    setText(((JButton)value).getText());
                    setIcon(((JButton)value).getIcon());
                }
        
                return this;
            }
        }
        
        TableColumn column = itemList.getColumnModel().getColumn(5);
        column.setCellRenderer(new ButtonRenderer());
        
        mainFrame.setVisible(true);
    }

    
    
    private static Object[][] getData() {
        Object[][] data = {
                {" ", "jennie", "0812", "vip", "", new JButton("Perlihatkan")},
                {" ", "rose", "021", "member", "", new JButton("Perlihatkan")},
                {" ", "jisoo", "0896", "vip", "", new JButton("Perlihatkan")},
                {" ", "lisa", "911", "member", "", new JButton("Perlihatkan")},
                {" ", "jennie", "0812", "vip", "", new JButton("Perlihatkan")},
                {" ", "rose", "021", "member", "", new JButton("Perlihatkan")},
                {" ", "jisoo", "0896", "vip", "", new JButton("Perlihatkan")},
                {" ", "lisa", "911", "member", "", new JButton("Perlihatkan")},
                {" ", "jennie", "0812", "vip", "", new JButton("Perlihatkan")},
                {" ", "rose", "021", "member", "", new JButton("Perlihatkan")},
                {" ", "jisoo", "0896", "vip", "", new JButton("Perlihatkan")},
                {" ", "lisa", "911", "member", "", new JButton("Perlihatkan")},
                {" ", "jennie", "0812", "vip", "", new JButton("Perlihatkan")},
                {" ", "rose", "021", "member", "", new JButton("Perlihatkan")},
                {" ", "jisoo", "0896", "vip", "", new JButton("Perlihatkan")},
                {" ", "lisa", "911", "member", "", new JButton("Perlihatkan")},
                {" ", "jennie", "0812", "vip", "", new JButton("Perlihatkan")},
                {" ", "rose", "021", "member", "", new JButton("Perlihatkan")},
                {" ", "jisoo", "0896", "vip", "", new JButton("Perlihatkan")},
                {" ", "lisa", "911", "member", "", new JButton("Perlihatkan")},
        };
        return data;
    }
    


    private static String[] getColumnNames() {
        String[] columnNames = {"ID", "Nama", "No.Telepon", "Kategori", "Edit", "Riwayat"};
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
    