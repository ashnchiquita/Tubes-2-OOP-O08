package boundary.panel.laporan.subpanel;

import boundary.widget.*;
import boundary.constants.Colors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Arrays;

public class LaporanPane extends TabPane {
    private int vw = 1280, vh = 720;
    private boolean isIDFound;
    // TODO: hapus ini bwt contoh aj
    private static final String[] userIDs = {"1", "2", "3", "4"};
    private String currSelectedID = "";

    private void resetIDFound() {
        isIDFound = false;
    }

    private void laporanBtnAction() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih Nama File Laporan Penjualan");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            System.out.println("Nama File Laporan Penjualan: " + fileToSave.getAbsolutePath());
            // TODO: get laporan penjualan as pdf dengan path fileToSave.getAbsolutePath() + .pdf kalo perlu
        }
    }

    private void searchIDBtnAction(String id, JPanel fixedBillBtnP) {
        // TODO: ganti dengan cari ke data store
        System.out.println(id);
        List<String> idList = Arrays.asList(userIDs);
        isIDFound = idList.contains(id);
        currSelectedID = isIDFound? id : "";
        System.out.println(isIDFound);
        fixedBillBtnP.setVisible(isIDFound);
    }

    private void fixedBillBtnAction() {
        // TODO: ganti dengan cari ke data store
        if (isIDFound && !currSelectedID.equals("")) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Pilih Nama File Fixed Bill untuk ID " + currSelectedID);

            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                System.out.println("Nama File Fixed Bill untuk ID " + currSelectedID + ": " + fileToSave.getAbsolutePath());
                // TODO: get File Fixed Bill untuk ID as pdf dengan path fileToSave.getAbsolutePath() + .pdf kalo perlu
            }
        }
    }

    public LaporanPane() {
        isIDFound = false;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Colors.WHITE);
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

        JPanel ringkasanPsubL = new JPanel();
        ringkasanPsubL.setLayout(new BorderLayout());
        ringkasanPsubL.setBackground(Colors.WHITE);

        JLabel ringkasanL = new JLabel("Ringkasan Penjualan");
        ringkasanL.setFont(bold);
        ringkasanL.setForeground(Colors.ORANGE);
        ringkasanL.setHorizontalAlignment(0);
        ringkasanPsubL.add(ringkasanL, BorderLayout.WEST);

        JPanel ringkasanPsubCards = new JPanel();
        ringkasanPsubCards.setLayout(new BorderLayout());
        ringkasanPsubCards.setBackground(Colors.WHITE);

        JPanel ringkasanCards = new JPanel();
        ringkasanCards.setLayout(new BoxLayout(ringkasanCards, BoxLayout.X_AXIS));
        ringkasanCards.setBackground(Colors.WHITE);

        for (String[] strings : ringkasanStr) {
            JPanel container = new JPanel(new BorderLayout());
            container.setBackground(Colors.WHITE);
            container.setDoubleBuffered(true);

            RingkasanCard card = new RingkasanCard(strings[0], strings[1]);
            container.add(card, BorderLayout.CENTER);
            ringkasanCards.add(container);
            ringkasanCards.add(Box.createHorizontalStrut(15));
        }

        ringkasanPsubCards.add(ringkasanCards, BorderLayout.WEST);

        ringkasanP.add(ringkasanPsubL);
        ringkasanP.add(Box.createVerticalStrut(20));
        ringkasanP.add(ringkasanPsubCards);
        ringkasanP.setBorder(new EmptyBorder(25, 25, 25, 25));
        wrapperRingkasan.add(ringkasanP, BorderLayout.PAGE_START);

        // Subpanel: Laporan Penjualan
        JPanel wrapperLaporan = new JPanel(new BorderLayout());

        JPanel laporanP = new JPanel();
        laporanP.setLayout(new BoxLayout(laporanP, BoxLayout.Y_AXIS));

        JPanel laporanPsubL = new JPanel();
        laporanPsubL.setLayout(new BorderLayout());
        laporanPsubL.setBackground(Colors.WHITE);

        JLabel laporanL = new JLabel("Laporan Penjualan");
        laporanL.setFont(bold);
        laporanL.setForeground(Colors.ORANGE);
        laporanPsubL.add(laporanL, BorderLayout.WEST);

        JPanel laporanPsubBtn = new JPanel();
        laporanPsubBtn.setLayout(new BorderLayout());
        laporanPsubBtn.setBackground(Colors.WHITE);

        RoundedPanel laporanBtnP = new RoundedPanel(20, Colors.DARK_BLUE, false, Colors.WHITE, 0);
        laporanBtnP.setOpaque(false);
        laporanBtnP.setLayout(null);
        laporanBtnP.setPreferredSize(new Dimension(170, 40));
        laporanBtnP.setMaximumSize(new Dimension(170, 40));
        laporanBtnP.setMinimumSize(new Dimension(170, 40));
        JButton laporanBtn = new JButton("Unduh Laporan");
        laporanBtn.setFont(light);
        laporanBtn.setBackground(Colors.DARK_BLUE);
        laporanBtn.setForeground(Colors.WHITE);
        laporanBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        laporanBtn.setBorder(null);
        laporanBtn.setFocusPainted(false);
        laporanBtn.setBounds(0, 0, 170, 40);
        laporanBtn.addActionListener(e -> laporanBtnAction());

        laporanBtnP.add(laporanBtn);
        laporanPsubBtn.add(laporanBtnP, BorderLayout.WEST);

        laporanP.add(laporanPsubL);
        laporanP.add(Box.createVerticalStrut(20));
        laporanP.add(laporanPsubBtn);
        laporanP.setBorder(new EmptyBorder(25, 25, 25, 25));
        wrapperLaporan.add(laporanP, BorderLayout.PAGE_START);

        // Subpanel: Fixed Bill
        JPanel wrapperFixedBill = new JPanel(new BorderLayout());
        JPanel fixedBillP = new JPanel();
        fixedBillP.setLayout(new BoxLayout(fixedBillP, BoxLayout.Y_AXIS));

        JPanel fixedBillPsubL = new JPanel();
        fixedBillPsubL.setLayout(new BorderLayout());
        fixedBillPsubL.setBackground(Colors.WHITE);

        JLabel fixedBillL = new JLabel("Fixed Bill");
        fixedBillL.setFont(bold);
        fixedBillL.setForeground(Colors.ORANGE);
        fixedBillPsubL.add(fixedBillL, BorderLayout.WEST);

        JPanel fixedBillPsubSubL = new JPanel();
        fixedBillPsubSubL.setLayout(new BorderLayout());
        fixedBillPsubSubL.setBackground(Colors.WHITE);

        JLabel fixedBillSubL = new JLabel("ID Pengguna");
        fixedBillSubL.setFont(light);
        fixedBillSubL.setForeground(Colors.DARK_BLUE);
        fixedBillPsubSubL.add(fixedBillSubL, BorderLayout.WEST);

        JPanel fixedBillPsubID = new JPanel();
        fixedBillPsubID.setLayout(new BorderLayout());
        fixedBillPsubID.setBackground(Colors.WHITE);

        RoundedPanel fixedBillIDP = new RoundedPanel(20, Colors.WHITE, true, Colors.DARK_BLUE, 1);
        fixedBillIDP.setOpaque(false);
        fixedBillIDP.setLayout(null);
        fixedBillIDP.setPreferredSize(new Dimension(380, 40));
        fixedBillIDP.setMaximumSize(new Dimension(380, 40));
        fixedBillIDP.setMinimumSize(new Dimension(380, 40));
        JTextField idField = new HintTextField.Hint("Masukkan ID pengguna...", 1);
        idField.setFont(light);
        idField.setBackground(Colors.WHITE);
        idField.setForeground(Colors.DARK_BLUE);
        idField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        idField.setBorder(null);
        idField.setBounds(15, 7, 350, 25);
        fixedBillIDP.add(idField);
        fixedBillPsubID.add(fixedBillIDP, BorderLayout.WEST);

        JPanel fixedBillPsubSearchID = new JPanel();
        fixedBillPsubSearchID.setLayout(new BorderLayout());
        fixedBillPsubSearchID.setBackground(Colors.WHITE);

        RoundedPanel searchIDP = new RoundedPanel(20, Colors.DARK_BLUE, false, Colors.WHITE, 0);
        searchIDP.setOpaque(false);
        searchIDP.setLayout(null);
        searchIDP.setPreferredSize(new Dimension(170, 40));
        searchIDP.setMaximumSize(new Dimension(170, 40));
        searchIDP.setMinimumSize(new Dimension(170, 40));
        JButton searchIDBtn = new JButton("Cari ID");

        searchIDBtn.setFont(light);
        searchIDBtn.setBackground(Colors.DARK_BLUE);
        searchIDBtn.setForeground(Colors.WHITE);
        searchIDBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchIDBtn.setBorder(null);
        searchIDBtn.setFocusPainted(false);
        searchIDBtn.setBounds(0, 0, 170, 40);
        searchIDP.add(searchIDBtn);
        fixedBillPsubSearchID.add(searchIDP, BorderLayout.WEST);

        JPanel fixedBillPsubBtn = new JPanel();
        fixedBillPsubBtn.setLayout(new BorderLayout());
        fixedBillPsubBtn.setBackground(Colors.WHITE);

        RoundedPanel fixedBillBtnP = new RoundedPanel(20, Colors.DARK_BLUE, false, Colors.WHITE, 0);
        fixedBillBtnP.setOpaque(false);
        fixedBillBtnP.setLayout(null);
        fixedBillBtnP.setPreferredSize(new Dimension(170, 40));
        fixedBillBtnP.setMaximumSize(new Dimension(170, 40));
        fixedBillBtnP.setMinimumSize(new Dimension(170, 40));
        JButton fixedBillBtn = new JButton("Unduh Fixed Bill");
        // TODO: sepertinya lebih bagus pake ChangeListener: https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        // jadi tombol search ID gaperlu ada
        fixedBillBtn.addActionListener(e -> fixedBillBtnAction());
        fixedBillBtn.setFont(light);
        fixedBillBtn.setBackground(Colors.DARK_BLUE);
        fixedBillBtn.setForeground(Colors.WHITE);
        fixedBillBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        fixedBillBtn.setBorder(null);
        fixedBillBtn.setFocusPainted(false);
        fixedBillBtn.setBounds(0, 0, 170, 40);

        fixedBillBtnP.add(fixedBillBtn);
        fixedBillBtnP.setVisible(isIDFound);
        searchIDBtn.addActionListener(e -> searchIDBtnAction(idField.getText(), fixedBillBtnP));
        fixedBillPsubBtn.add(fixedBillBtnP, BorderLayout.WEST);

        fixedBillP.add(fixedBillPsubL, BorderLayout.WEST);
        fixedBillP.add(Box.createVerticalStrut(20));
        fixedBillP.add(fixedBillPsubSubL);
        fixedBillP.add(Box.createVerticalStrut(10));
        fixedBillP.add(fixedBillPsubID);
        fixedBillP.add(Box.createVerticalStrut(15));
        fixedBillP.add(fixedBillPsubSearchID);
        fixedBillP.add(Box.createVerticalStrut(15));
        fixedBillP.add(fixedBillPsubBtn);
        fixedBillP.setBorder(new EmptyBorder(25, 25, 25, 25));
        wrapperFixedBill.add(fixedBillP, BorderLayout.PAGE_START);

        ringkasanP.setOpaque(false);
        laporanP.setOpaque(false);
        fixedBillP.setOpaque(false);
        wrapperRingkasan.setBackground(Colors.WHITE);
        wrapperLaporan.setBackground(Colors.WHITE);
        wrapperFixedBill.setBackground(Colors.WHITE);

        // Add semua
        this.add(wrapperRingkasan);
        this.add(wrapperLaporan);
        this.add(wrapperFixedBill);
    }
}
