package Boundary.LaporanUI.Component;

import Boundary.Util.Colors;
import Boundary.Util.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class RingkasanCard extends RoundedPanel {
    private final String judulRingkasan;
    private final String subRingkasan;

    public RingkasanCard(String judulRingkasan, String subRingkasan) {
        super(24, Colors.WHITE.getColor(), true, Colors.MED_GRAY.getColor(), 1);
        this.judulRingkasan = judulRingkasan;
        this.subRingkasan = subRingkasan;
        this.setPreferredSize(new Dimension(300, 100));
        // this.setLayout(new BorderLayout());
        JPanel labels = new JPanel();
        labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
        labels.setBackground(Colors.WHITE.getColor());

        // put judul and sub
        Font light =  new Font("Inter", Font.PLAIN, 17);
        Font medium = new Font("Inter", Font.BOLD, 24);

        // judul
        JLabel judulL = new JLabel(this.judulRingkasan);
        judulL.setFont(light);
        judulL.setForeground(Colors.DARK_BLUE.getColor());

        // sub
        JLabel subL = new JLabel(this.subRingkasan);
        subL.setFont(medium);
        subL.setForeground(Colors.DARK_BLUE.getColor());

        // add to panel
        labels.add(judulL);
        labels.add(Box.createVerticalStrut(15));
        labels.add(subL);
        labels.setBounds(100,10,280,80);

        this.add(labels);
    }
}
