package org.example;
import javax.swing.*;
import java.awt.*;

public class settings extends JFrame {
    private JPanel panel;
    private JLabel namaApp;
    private JButton kasirButton;
    private JButton transaksiButton;
    private JButton laporanButton;
    private JButton memberButton;
    private JButton inventarisButton;
    private JButton pengaturanButton;
    private JButton homeButton;

    public settings() {
        initComponents();
    }

    private void initComponents() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(626, 423));
        panel.setBackground(new Color(-1));
        panel.setFont(new Font("Inter", 1, 33));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-14402412)), null, 2, 0, new Font(panel.getFont().getName(), panel.getFont().getStyle(), panel.getFont().getSize()), new Color(-14402412)));

        namaApp = new JLabel();
        namaApp.setFont(new Font("Arial", 1, 18));
        namaApp.setText("NamaApp");
        kasirButton = new JButton();
        kasirButton.setBackground(new Color(-11768097));
        kasirButton.setFont(new Font("Inter", 0, 15));
        kasirButton.setForeground(new Color(-1));
        kasirButton.setText("Kasir");

        transaksiButton = new JButton();
        transaksiButton.setBackground(new Color(-11768097));
        transaksiButton.setFont(new Font("Inter", 0, 15));
        transaksiButton.setForeground(new Color(-1));
        transaksiButton.setText("Transaksi");

        laporanButton = new JButton();
        laporanButton.setBackground(new Color(-11768097));
        laporanButton.setFont(new Font("Inter", 0, 15));
        laporanButton.setForeground(new Color(-1));
        laporanButton.setText("Laporan");

        memberButton = new JButton();
        memberButton.setBackground(new Color(-11768097));
        memberButton.setFont(new Font("Inter", 0, 15));
        memberButton.setForeground(new Color(-1));
        memberButton.setText("Member");

        inventarisButton = new JButton();
        inventarisButton.setBackground(new Color(-11768097));
        inventarisButton.setFont(new Font("Inter", 0, 15));
        inventarisButton.setForeground(new Color(-1));
        inventarisButton.setText("Inventaris");

        pengaturanButton = new JButton();
        pengaturanButton.setBackground(new Color(-11768097));
        pengaturanButton.setFont(new Font("Inter", 0, 15));
        pengaturanButton.setForeground(new Color(-1));
        pengaturanButton.setText("Pengaturan");

        homeButton = new JButton();
        homeButton.setBackground(new Color(-11768097));
        homeButton.setFont(new Font("Inter", 0, 15));
        homeButton.setForeground(new Color(-1));
        homeButton.setText("Home");

        // TODO: add ActionListeners for each button here
        kasirButton.addActionListener(e -> {
            // perform action for kasirButton here
        });
        transaksiButton.addActionListener(e -> {
            // perform action for transaksiButton here
        });
        laporanButton.addActionListener(e -> {
            // perform action for laporanButton here
        });
        memberButton.addActionListener(e -> {
            // perform action for memberButton here
        });
        inventarisButton.addActionListener(e -> {
            // perform action for inventarisButton here
        });
        pengaturanButton.addActionListener(e -> {
            // perform action for pengaturanButton here
        });
        homeButton.addActionListener(e -> {
            // perform action for homeButton here
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.pack();
        this.setVisible(true);
    }
}

