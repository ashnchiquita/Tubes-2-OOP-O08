package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;

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

        // Header Panel
        JPanel headerPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 50, 60, 50);
        headerPanel.setBackground(new Color(255,255,255));
        headerPanel.setPreferredSize(new Dimension(1045,168));
        headerPanel.setBorder(paddingBorder);
        mainRight.add(headerPanel,BorderLayout.NORTH);


            // Label "Daftar Barang"
            JLabel daftarBarangLabel = new JLabel("Daftar Barang");
            daftarBarangLabel.setHorizontalAlignment(SwingConstants.LEFT);
            daftarBarangLabel.setForeground(new Color(229, 151, 0));
            daftarBarangLabel.setFont(new Font("Inter", Font.BOLD, 33));
            headerPanel.add(daftarBarangLabel, BorderLayout.WEST);

            // Horizontal Divider
            JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
            separator1.setPreferredSize(new Dimension(168, 1));
            separator1.setVisible(true);
            headerPanel.add(separator1);

            // Label "Total Barang"
            JLabel totalBarangLabel = new JLabel("Total Barang : 127");
            totalBarangLabel.setHorizontalAlignment(SwingConstants.LEFT);
            totalBarangLabel.setFont(new Font("Inter", Font.PLAIN, 18));
            totalBarangLabel.setForeground(new Color(36,60,148));
            totalBarangLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            headerPanel.add(totalBarangLabel, BorderLayout.WEST);

            // Horizontal Divider
            JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
            separator2.setPreferredSize(new Dimension(25, 1));
            separator2.setVisible(true);
            headerPanel.add(separator2);

            // Button Import
            JButton importButton = new JButton("Import");
            CustomButtonUI buttonUI = new CustomButtonUI(new Color(45,77,182));
            importButton.setFont(new Font("Inter", Font.PLAIN, 18));
            importButton.setBackground(new Color(76,110,223));
            importButton.setForeground(Color.WHITE);
            importButton.setOpaque(true);
            importButton.setFocusable(false);
            importButton.setPreferredSize(new Dimension(174,43));
            importButton.setUI(buttonUI);
            importButton.setBorder(new RoundBorder(20));
            headerPanel.add(importButton, BorderLayout.WEST);

            // Horizontal Divider
            JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
            separator3.setPreferredSize(new Dimension(25, 1));
            separator3.setVisible(true);
            headerPanel.add(separator3);

            // Button Tambah Baru
            JButton createNewItemButton = new JButton("+ Tambah Baru");
            createNewItemButton.setFont(new Font("Inter", Font.PLAIN, 18));
            createNewItemButton.setBackground(new Color(76,110,223));
            createNewItemButton.setForeground(Color.WHITE);
            createNewItemButton.setOpaque(true);
            createNewItemButton.setFocusable(false);
            createNewItemButton.setPreferredSize(new Dimension(174,43));
            createNewItemButton.setUI(buttonUI);
            createNewItemButton.setBorder(new RoundBorder(20));
            headerPanel.add(createNewItemButton, BorderLayout.WEST);

        // Table
        JScrollPane scrollListPanel = new JScrollPane();
        scrollListPanel.setBackground(Color.LIGHT_GRAY);
        mainRight.add(scrollListPanel, BorderLayout.CENTER);

        JTable itemList = new JTable(getData(), getColumnNames());
        itemList.setRowHeight(36);
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

        // Set blue background color for table header
        JTableHeader tableHeader = itemList.getTableHeader();
        tableHeader.setBackground(new Color(0xDAE2FF));
        tableHeader.setForeground(new Color(0x243C94)); // set the color of the header text
        tableHeader.setFont(tableHeader.getFont().deriveFont(Font.BOLD, 14));
        tableHeader.setPreferredSize(new Dimension(994, 43));
        tableHeader.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        tableHeader.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 5));
//       tableHeader.setBorder(new Color(240, 240, 240));

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

class RoundBorder implements Border {
    private int radius;

    public RoundBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(c.getBackground());
        g2.fill(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
        g2.setColor(c.getForeground());
        g2.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
    }
}

class CustomButtonUI extends BasicButtonUI {

    private Color pressedBackgroundColor;

    public CustomButtonUI(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setBorderPainted(false);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        Color color = model.isPressed() ? pressedBackgroundColor : b.getBackground();
        g.setColor(color);
        g.fillRect(0, 0, b.getWidth(), b.getHeight());
        super.paint(g, c);
    }
}
