package boundary.panel.member.subpanel;

import boundary.constants.Colors;
import boundary.constants.ResourcePath;
import boundary.widget.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DaftarMemberPanel extends FlowablePane {
    private static JPanel headerPanel;
    private static JScrollPane scrollListPanel;
    private RoundedPanel createNewItemButtonPanel = new RoundedPanel(25, new Color(0x4C6EDF), false, Color.WHITE,  0);
    private RoundedPanel importButtonPanel = new RoundedPanel(25, new Color(0x4C6EDF), false, Color.WHITE,  0);
    private RoundedPanel totalBarangPanel = new RoundedPanel(25, Color.WHITE, true, new Color(0x5D82E8),  2);
    private RoundedPanel periksaPanel = new RoundedPanel(25, new Color(0x4C6EDF), false, Color.WHITE,  0);

    private void setupHeaderPanel(){
        headerPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 40, 60, 20);
        headerPanel.setBackground(new Color(255,255,255));
        headerPanel.setPreferredSize(new Dimension(1000,168));
        headerPanel.setBorder(paddingBorder);

        // Label "Daftar Barang"
        JLabel daftarBarangLabel = new JLabel("Daftar Member");
        daftarBarangLabel.setHorizontalAlignment(SwingConstants.LEFT);
        daftarBarangLabel.setForeground(new Color(229, 151, 0));
        daftarBarangLabel.setFont(new Font("Inter", Font.BOLD, 33));
        headerPanel.add(daftarBarangLabel, BorderLayout.WEST);

        // Horizontal Divider
        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        separator1.setPreferredSize(new Dimension(280, 0));
        headerPanel.add(separator1);

        // Label "Total Barang"
        JLabel totalBarangLabel = new JLabel("Total Member : 127");
        totalBarangLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalBarangLabel.setPreferredSize(new Dimension(180,38));
        totalBarangLabel.setFont(new Font("Inter", Font.PLAIN, 15));
        totalBarangLabel.setForeground(new Color(36,60,148));
        totalBarangPanel.add(totalBarangLabel, BorderLayout.WEST);
        headerPanel.add(totalBarangPanel, BorderLayout.WEST);

        // Horizontal Divider
        JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(20, 0));
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
        //TODO: Import functionality
        importButtonPanel.add(importButton, BorderLayout.WEST);
        headerPanel.add(importButtonPanel, BorderLayout.WEST);

        // Horizontal Divider
        JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
        separator3.setPreferredSize(new Dimension(20, 0));
        separator3.setVisible(true);
        headerPanel.add(separator3);
    }

    private void setupTable(){
        // Table
        scrollListPanel = new JScrollPane();
        scrollListPanel.setBackground(Color.WHITE);
        scrollListPanel.setPreferredSize(new Dimension(900,450));
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
        itemList.setBorder(BorderFactory.createEmptyBorder());
        itemList.setShowVerticalLines(false);
        itemList.setBackground(Colors.WHITE);
        itemList.setPreferredScrollableViewportSize(itemList.getPreferredSize());
        scrollListPanel.setViewportView(itemList);
        scrollListPanel.getVerticalScrollBar().setUI(new PlainScrollBar(Colors.WHITE, Colors.SIDE_SLIDER_BLUE));

        // Set transparent table lines
        itemList.setGridColor(new Color(240, 240, 240));
        itemList.setIntercellSpacing(new Dimension(0, 5));
        itemList.setFillsViewportHeight(true);

        TableCellRenderer headerRenderer = new TableCellRenderer() {
            JLabel label;
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (label == null) {
                    label = new JLabel();
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setOpaque(true);
                    label.setBackground(new Color(0xEF0D4F)); //warna judul kolom
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

        // ini yg buat ngatur button perlihatkan
        class ButtonRenderer extends JButton implements TableCellRenderer, ActionListener {
            private JButton contohButton;
            private boolean isButtonClicked;
            public ButtonRenderer() {
                setOpaque(true);
                setBackground(new Color(0x4C6EDF));
                setForeground(Color.WHITE);
                setPreferredSize(new Dimension(25, 20));
                setBorderPainted(false);
                setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

                contohButton = new JButton("Contoh");
                contohButton.addActionListener(this);
                isButtonClicked = false;
            }

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                if (isSelected && column == 5 && hasFocus) {
                    if (!isButtonClicked) {
                        contohButton.doClick();
                        setBackground(new Color(0xEE063D));
                        isButtonClicked = true;
                    }
                } else {
                    setBackground(new Color(0x4C6EDF));
                    isButtonClicked = false;
                }

                if (column == 5) {
                    setText("Perlihatkan");
                }

                return this;
            }


            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == contohButton) {
                    JOptionPane.showMessageDialog(null, "Contoh button activated!");
                }
            }
        }



        // ini buat button edit
        class ButtonRenderer2 extends JButton implements TableCellRenderer {
            public ButtonRenderer2() {
                setOpaque(true);
                setPreferredSize(new Dimension(25, 25));
                setBorderPainted(false);
                setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
                ImageIcon icon = new ImageIcon(ResourcePath.ICON + "/edit.png");
                Image image = icon.getImage();
                Image newImage = image.getScaledInstance(25,25, Image.SCALE_SMOOTH);
                icon = new ImageIcon(newImage);
                setIcon(icon); // set the icon of the button
            }

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (isSelected) {
                    setBackground(Colors.TABLE_SELECTED);
                }
                if (!isSelected){
                    setBackground(Colors.WHITE);
                }
                return this;
            }
        }

        TableColumn column = itemList.getColumnModel().getColumn(5);
        column.setCellRenderer(new ButtonRenderer());

        TableColumn column2 = itemList.getColumnModel().getColumn(4);
        column2.setCellRenderer(new ButtonRenderer2());

        // Set blue background color for table header
        JTableHeader tableHeader = itemList.getTableHeader();
        tableHeader.setDefaultRenderer(headerRenderer);
        tableHeader.setAlignmentX(10);
        tableHeader.setFont(tableHeader.getFont().deriveFont(Font.BOLD, 14));
        tableHeader.setPreferredSize(new Dimension(600, 43));
    }
//    JButton editButton = new JButton(new ImageIcon(ResourcePath.ICON + "/edit.png"));
//    JButton perlihatkanButton = new JButton("Perlihatkan");

    //TODO: Edit and history button functionality
    private static Object[][] getData() {
        JButton editButton = new JButton(new ImageIcon(ResourcePath.ICON + "/edit.png"));
        editButton.setBackground(new Color(0xFFFFFF));
        editButton.setForeground(Color.WHITE);
        editButton.setBorderPainted(false);
        editButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                editButton.setBackground(new Color(0xCDD6F6));
            }
            public void mouseExited(MouseEvent e) {
                editButton.setBackground(new Color(0x4C6EDF));
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Handle button click event
            }
        });

        JButton perlihatkanButton = new JButton("Perlihatkan");
        PressedButton buttonUI = new PressedButton(new Color(45,77,182));
        perlihatkanButton.setBackground(new Color(0x4C6EDF));
        perlihatkanButton.setForeground(Color.WHITE);
        perlihatkanButton.setBorderPainted(false);
        perlihatkanButton.setOpaque(true);
        perlihatkanButton.setFocusable(false);
        perlihatkanButton.setPreferredSize(new Dimension(130,38));
        perlihatkanButton.setUI(buttonUI);
        perlihatkanButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                perlihatkanButton.setBackground(new Color(0xCDD6F6));
            }
            public void mouseExited(MouseEvent e) {
                perlihatkanButton.setBackground(new Color(0x070303));
            }
        });
        perlihatkanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO: Handle button click event
            }
        });

        Object[][] data = {
                {" ", "jennie", "0812", "vip", editButton, perlihatkanButton},
                {" ", "rose", "021", "member", editButton, perlihatkanButton},
                {" ", "jisoo", "0896", "vip", editButton, perlihatkanButton},
        };
        return data;
    }


    private static String[] getColumnNames() {
        String[] columnNames = {"ID", "Nama", "No.Telepon", "Kategori", "Edit", "Riwayat"};
        return columnNames;
    }

    public DaftarMemberPanel(){
        this.setBackground(Color.WHITE);
        setupHeaderPanel();
        this.add(headerPanel,BorderLayout.NORTH);
        setupTable();
        this.add(scrollListPanel, BorderLayout.CENTER);
    }
}

