package boundary.panel.inventaris.subpanel;

import boundary.constants.ResourcePath;
import boundary.observer.panelflow.PanelFlowEvent;
import boundary.widget.*;
import util.ImageFilter;

import model.*;
import controller.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TambahBarangPane extends TabPane {
    private GenericDataIO<Barang> barangDataIO;

    public TambahBarangPane(GenericDataIO<Barang> barangDataIO) {
        this.barangDataIO = barangDataIO;
        this.setBackground(Color.WHITE);
        setupLeftPanel();
        this.add(leftPanel, BorderLayout.NORTH);
        setupRightPanel();
        this.add(rightPanel, BorderLayout.NORTH);
    }

    // Setup
    private static JPanel rightPanel;
    private static JPanel leftPanel;
    private static JTextField fileTextField;
    private static File pluginStore = new File(ResourcePath.DATA + "/plugins.txt");

    private void setupLeftPanel() {
        /* Setting up left panel buttons */

        leftPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 20, 60, 20);
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(522, 670));
        leftPanel.setBorder(paddingBorder);

        // leftPanel.setLayout(new BorderLayout());

        // Header Atas
        JButton backButton = new JButton("<");
        backButton.setFont(new Font("Inter", Font.BOLD, 33));
        backButton.setForeground(new Color(229, 151, 0));
        backButton.setPreferredSize(new Dimension(60, 40));
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.addActionListener(e -> panelFlowObserver.newEvent(PanelFlowEvent.retract()));
        leftPanel.add(backButton, BorderLayout.NORTH);
        JLabel tambahBarangLabel = new JLabel("Tambah Barang Baru");
        tambahBarangLabel.setFont(new Font("Inter", Font.BOLD, 33));
        tambahBarangLabel.setForeground(new Color(229, 151, 0));
        tambahBarangLabel.setPreferredSize(new Dimension(375, 40));
        leftPanel.add(tambahBarangLabel, BorderLayout.NORTH);

        // Nama
        JLabel namaLabel = new JLabel("Nama");
        namaLabel.setFont(new Font("Inter", Font.BOLD, 15));
        namaLabel.setForeground(new Color(0x243C94));
        namaLabel.setPreferredSize(new Dimension(475, 40));
        leftPanel.add(namaLabel, BorderLayout.NORTH);
        JTextField namaTextField = new HintTextField("Nama", 33);
        namaTextField.setPreferredSize(new Dimension(475, 45));
        namaTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        namaTextField.setForeground(Color.BLACK);
        namaTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0X4B4FC4)),
                BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        leftPanel.add(namaTextField, BorderLayout.NORTH);

        // Kategori
        JLabel kategoriLabel = new JLabel("Kategori");
        kategoriLabel.setFont(new Font("Inter", Font.BOLD, 15));
        kategoriLabel.setForeground(new Color(0x243C94));
        kategoriLabel.setPreferredSize(new Dimension(475, 40));
        leftPanel.add(kategoriLabel, BorderLayout.NORTH);
        JTextField kategoriTextField = new HintTextField("Kategori", 33);
        kategoriTextField.setPreferredSize(new Dimension(475, 45));
        kategoriTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        kategoriTextField.setForeground(Color.BLACK);
        kategoriTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0X4B4FC4)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        leftPanel.add(kategoriTextField, BorderLayout.NORTH);

        // Harga Beli dan Harga Jual
        JLabel hargaBeliLabel = new JLabel("Harga beli");
        hargaBeliLabel.setFont(new Font("Inter", Font.BOLD, 15));
        hargaBeliLabel.setForeground(new Color(0x243C94));
        hargaBeliLabel.setPreferredSize(new Dimension(240, 40));
        leftPanel.add(hargaBeliLabel, BorderLayout.WEST);
        JLabel hargaJualLabel = new JLabel("Jual beli");
        hargaJualLabel.setFont(new Font("Inter", Font.BOLD, 15));
        hargaJualLabel.setForeground(new Color(0x243C94));
        hargaJualLabel.setPreferredSize(new Dimension(230, 40));
        leftPanel.add(hargaJualLabel, BorderLayout.WEST);

        JTextField hargaBeliTextField = new HintTextField("HargaBeli", 16);
        hargaBeliTextField.setPreferredSize(new Dimension(240, 40));
        hargaBeliTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        hargaBeliTextField.setForeground(Color.BLACK);
        hargaBeliTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0X4B4FC4)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        leftPanel.add(hargaBeliTextField, BorderLayout.WEST);

        JTextField hargaJualTextField = new HintTextField("HargaBeli", 16);
        hargaJualTextField.setPreferredSize(new Dimension(230, 40));
        hargaJualTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        hargaJualTextField.setForeground(Color.BLACK);
        hargaJualTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0X4B4FC4)), BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        leftPanel.add(hargaJualTextField, BorderLayout.WEST);

        // Stok
        JLabel stokLabel = new JLabel("Stok");
        stokLabel.setFont(new Font("Inter", Font.BOLD, 15));
        stokLabel.setForeground(new Color(0x243C94));
        stokLabel.setPreferredSize(new Dimension(475, 40));
        leftPanel.add(stokLabel, BorderLayout.NORTH);
        JTextField stokTextField = new HintTextField("Stok", 33);
        stokTextField.setPreferredSize(new Dimension(475, 45));
        stokTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        stokTextField.setForeground(new Color(0xD9D9D9));
        stokTextField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0X4B4FC4)),
                BorderFactory.createEmptyBorder(0, 10, 0, 0)));
        leftPanel.add(stokTextField, BorderLayout.NORTH);

        JButton createNewItemButton = new JButton("+ Tambah Baru");
        PressedButton buttonUI = new PressedButton(new Color(45, 77, 182));
        createNewItemButton.setFont(new Font("Inter", Font.PLAIN, 15));
        createNewItemButton.setBackground(new Color(76, 110, 223));
        createNewItemButton.setForeground(Color.WHITE);
        createNewItemButton.setOpaque(true);
        createNewItemButton.setFocusable(false);
        createNewItemButton.setPreferredSize(new Dimension(180, 43));
        createNewItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Barang b = Barang.builder()
                            .id()
                            .name(namaTextField.getText())
                            .kategori(kategoriTextField.getText())
                            .gambar(fileTextField.getText())
                            .hargaJual(Double.valueOf(hargaJualTextField.getText()))
                            .hargaBeli(Double.valueOf(hargaBeliTextField.getText()))
                            .jumlah(Integer.valueOf(stokTextField.getText()))
                            .build();
                    barangDataIO.insert(b);
                    panelFlowObserver.newEvent(new PanelFlowEvent(new DaftarBarangPane(barangDataIO), false));
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Invalid input!\n" + exception.toString());
                }
            }
        });
        createNewItemButton.setUI(buttonUI);
        createNewItemButton.setBorder(new RoundBorder(20));
        leftPanel.add(createNewItemButton, BorderLayout.WEST);
    }

    private void setupRightPanel() {
        /* Setting up left panel buttons */

        rightPanel = new JPanel();
        Border paddingBorder = BorderFactory.createEmptyBorder(75, 20, 60, 20);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setPreferredSize(new Dimension(400, 670));
        rightPanel.setBorder(paddingBorder);
        try {
            BufferedImage myPicture;
            myPicture = ImageIO.read(new File(ResourcePath.IMAGE + "/image_file_input.png"));
            JLabel picMag = new JLabel(new ImageIcon(myPicture));
            picMag.setBounds(68, 104, 314, 232);
            picMag.setOpaque(false);
            rightPanel.add(picMag);
        } catch (Exception e) {
            System.out.println("Image Loading Failure: " + e.getMessage());
        }

        // File Text Field
        JPanel fileTextFieldPanel = new RoundedPanel(32, Color.WHITE, true, new Color(0X4B4FC4), 1);
        fileTextField = new HintTextField("Link File", 10);
        fileTextField.setBounds(68, 120, 300, 55);
        fileTextField.setFont(new Font("Inter", Font.PLAIN, 15));
        fileTextField.setForeground(new Color(0x000000));
        fileTextField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        fileTextFieldPanel.setVisible(true);
        fileTextFieldPanel.add(fileTextField, BorderLayout.NORTH);
        rightPanel.add(fileTextFieldPanel);

        // File Button
        RoundedPanel fileButtonPanel = new RoundedPanel(25, new Color(0x4C6EDF), false, Color.WHITE, 0);
        JButton fileButton = new JButton("Cari File");
        PressedButton buttonUI = new PressedButton(new Color(45, 77, 182));
        fileButton.setFont(new Font("Inter", Font.PLAIN, 15));
        fileButton.setBackground(new Color(76, 110, 223));
        fileButton.setForeground(Color.WHITE);
        fileButton.setOpaque(true);
        fileButton.setFocusable(false);
        fileButton.setBounds(68, 120, 100, 40);
        fileButton.setUI(buttonUI);
        fileButton.setVisible(true);
        fileButtonPanel.add(fileButton, BorderLayout.WEST);
        fileButton.addActionListener(e -> seekFile());
        rightPanel.add(fileButtonPanel);

        rightPanel.setVisible(true);
        //rightPanel.setLayout(new BorderLayout());

    }

    private void seekFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih File");
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.addChoosableFileFilter(new ImageFilter());
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            Path path = Paths.get(fileToSave.getPath());
            Path rootPath = Paths.get(ResourcePath.ABSOLUTE);
            fileTextField.setText(rootPath.relativize(path).toString());
        } else {
            System.out.println("No Selection");
        }
    }
}