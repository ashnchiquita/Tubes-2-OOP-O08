package Boundary.LaporanUI;

import Boundary.LaporanUI.Component.RingkasanCard;
import Boundary.Util.Colors;
import Boundary.Util.HintTextField;
import Boundary.Util.RoundedPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LaporanPanel extends JPanel {
    private int vw = 1280, vh = 720;

    public LaporanPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Colors.WHITE.getColor());
        Font light =  new Font("Inter", Font.PLAIN, 17);
        Font medium = new Font("Inter", Font.BOLD, 24);
        Font bold = new Font("Inter", Font.BOLD, 33);

        // Subpanel: Ringkasan Penjualan
        // TODO: sambungin dengan statistik penjualan
        String[][] ringkasanStr = {
                {"GROSS SALES", "Rp10000"},
                {"NET SALES", "Rp10000"},
                {"GROSS PROFIT", "Rp10000"}
        };

        JPanel wrapperRingkasan = new JPanel(new BorderLayout());
        JPanel ringkasanP = new JPanel();
        ringkasanP.setLayout(new BoxLayout(ringkasanP, BoxLayout.Y_AXIS));

        JLabel ringkasanL = new JLabel("Ringkasan Penjualan");
        ringkasanL.setFont(bold);
        ringkasanL.setForeground(Colors.ORANGE.getColor());

        JPanel ringkasanCards = new JPanel();
        ringkasanCards.setLayout(new BoxLayout(ringkasanCards, BoxLayout.X_AXIS));
        GridBagConstraints c = new GridBagConstraints();
        ringkasanCards.setBackground(Colors.WHITE.getColor());

        for (String[] strings : ringkasanStr) {
            JPanel container = new JPanel(new BorderLayout());
            container.setBackground(Colors.WHITE.getColor());
            container.setDoubleBuffered(true);

            RingkasanCard card = new RingkasanCard(strings[0], strings[1]);
            container.add(card, BorderLayout.CENTER);
            ringkasanCards.add(container);
            ringkasanCards.add(Box.createHorizontalStrut(15));
        }

        ringkasanL.setHorizontalAlignment(0);
        ringkasanP.add(ringkasanL);
        ringkasanP.add(Box.createVerticalStrut(20));
        ringkasanP.add(ringkasanCards);
        ringkasanP.setBorder(new EmptyBorder(25, 25, 25, 25));
        wrapperRingkasan.add(ringkasanP, BorderLayout.PAGE_START);

        // Subpanel: Laporan Penjualan
        JPanel wrapperLaporan = new JPanel(new BorderLayout());

        JPanel laporanP = new JPanel();
        laporanP.setLayout(new BoxLayout(laporanP, BoxLayout.Y_AXIS));

        JLabel laporanL = new JLabel("Laporan Penjualan");
        laporanL.setFont(bold);
        laporanL.setForeground(Colors.ORANGE.getColor());

        RoundedPanel laporanBtnP = new RoundedPanel(20, Colors.DARK_BLUE.getColor(), false, Colors.WHITE.getColor(), 0);
        laporanBtnP.setOpaque(false);
        laporanBtnP.setLayout(null);
        laporanBtnP.setPreferredSize(new Dimension(170, 40));
        JButton laporanBtn = new JButton("Unduh Laporan");
        laporanBtn.setFont(light);
        laporanBtn.setBackground(Colors.DARK_BLUE.getColor());
        laporanBtn.setForeground(Colors.WHITE.getColor());
        laporanBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        laporanBtn.setBorder(null);
        laporanBtn.setFocusPainted(false);
        laporanBtn.setBounds(0, 0, 170, 40);
        laporanBtnP.add(laporanBtn);

        laporanP.add(laporanL);
        laporanP.add(Box.createVerticalStrut(20));
        laporanP.add(laporanBtnP);
        laporanP.setBorder(new EmptyBorder(25, 25, 25, 25));
        wrapperLaporan.add(laporanP, BorderLayout.PAGE_START);

        // Subpanel: Fixed Bill
        JPanel wrapperFixedBill = new JPanel(new BorderLayout());
        JPanel fixedBillP = new JPanel();
        fixedBillP.setLayout(new BoxLayout(fixedBillP, BoxLayout.Y_AXIS));

        JLabel fixedBillL = new JLabel("Fixed Bill");
        fixedBillL.setFont(bold);
        fixedBillL.setForeground(Colors.ORANGE.getColor());

        JLabel fixedBillSubL = new JLabel("ID Pengguna");
        fixedBillSubL.setFont(light);
        fixedBillSubL.setForeground(Colors.DARK_BLUE.getColor());

        RoundedPanel fixedBillIDP = new RoundedPanel(20, Colors.WHITE.getColor(), true, Colors.DARK_BLUE.getColor(), 1);
        fixedBillIDP.setOpaque(false);
        fixedBillIDP.setLayout(null);
        fixedBillIDP.setPreferredSize(new Dimension(380, 40));
        JTextField idField = new HintTextField("Masukkan ID pengguna...", 1);
        idField.setFont(light);
        idField.setBackground(Colors.WHITE.getColor());
        idField.setForeground(Colors.DARK_BLUE.getColor());
        idField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        idField.setBorder(null);
        idField.setBounds(15, 7, 350, 25);
        fixedBillIDP.add(idField);

        RoundedPanel searchIDP = new RoundedPanel(20, Colors.DARK_BLUE.getColor(), false, Colors.WHITE.getColor(), 0);
        searchIDP.setOpaque(false);
        searchIDP.setLayout(null);
        searchIDP.setPreferredSize(new Dimension(130, 40));
        JButton searchIDBtn = new JButton("Cari ID");
        searchIDBtn.setFont(light);
        searchIDBtn.setBackground(Colors.DARK_BLUE.getColor());
        searchIDBtn.setForeground(Colors.WHITE.getColor());
        searchIDBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchIDBtn.setBorder(null);
        searchIDBtn.setFocusPainted(false);
        searchIDBtn.setBounds(0, 0, 130, 40);
        searchIDP.add(searchIDBtn);

        RoundedPanel fixedBillBtnP = new RoundedPanel(20, Colors.DARK_BLUE.getColor(), false, Colors.WHITE.getColor(), 0);
        fixedBillBtnP.setOpaque(false);
        fixedBillBtnP.setLayout(null);
        fixedBillBtnP.setPreferredSize(new Dimension(170, 40));
        JButton fixedBillBtn = new JButton("Unduh Fixed Bill");
        fixedBillBtn.setFont(light);
        fixedBillBtn.setBackground(Colors.DARK_BLUE.getColor());
        fixedBillBtn.setForeground(Colors.WHITE.getColor());
        fixedBillBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        fixedBillBtn.setBorder(null);
        fixedBillBtn.setFocusPainted(false);
        fixedBillBtn.setBounds(0, 0, 170, 40);

        // TODO: implements logic of finding valid ID
        boolean found = true;
        fixedBillBtnP.setVisible(found);
        fixedBillBtnP.add(fixedBillBtn);

        fixedBillP.add(fixedBillL);
        fixedBillP.add(Box.createVerticalStrut(20));
        fixedBillP.add(fixedBillSubL);
        fixedBillP.add(Box.createVerticalStrut(10));
        fixedBillP.add(fixedBillIDP);
        fixedBillP.add(Box.createVerticalStrut(15));
        fixedBillP.add(searchIDP);
        fixedBillP.add(Box.createVerticalStrut(15));
        fixedBillP.add(fixedBillBtnP);
        fixedBillP.setBorder(new EmptyBorder(25, 25, 25, 25));
        wrapperFixedBill.add(fixedBillP, BorderLayout.PAGE_START);

        // Add semua
        this.add(wrapperRingkasan);
        this.add(wrapperLaporan);
        this.add(wrapperFixedBill);
    }
}
