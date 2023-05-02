package boundary.widget;

import boundary.constants.Colors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RingkasanCard extends RoundedPanel {
    private final String judulRingkasan;
    private final String subRingkasan;

    public RingkasanCard(String judulRingkasan, String subRingkasan) {
        super(24, Colors.WHITE, true, Colors.MED_GRAY, 1);
        this.judulRingkasan = judulRingkasan;
        this.subRingkasan = subRingkasan;
        this.setPreferredSize(new Dimension(300, 100));
        // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        // this.setLayout(null);
        JPanel labels = new JPanel();
        labels.setLayout(new BoxLayout(labels, BoxLayout.Y_AXIS));
        labels.setBackground(Colors.WHITE);

        // put judul and sub
        Font light =  new Font("Inter", Font.PLAIN, 17);
        Font medium = new Font("Inter", Font.BOLD, 24);

        JPanel labelsSubL = new JPanel();
        labelsSubL.setLayout(new BorderLayout());
        labelsSubL.setBackground(Colors.WHITE);

        // judul
        JLabel judulL = new JLabel(this.judulRingkasan);
        judulL.setFont(light);
        judulL.setForeground(Colors.DARK_BLUE);
        labelsSubL.add(judulL, BorderLayout.WEST);

        JPanel labelsSubSubL = new JPanel();
        labelsSubSubL.setLayout(new BorderLayout());
        labelsSubSubL.setBackground(Colors.WHITE);

        // sub
        JLabel subL = new JLabel(this.subRingkasan);
        subL.setFont(medium);
        subL.setForeground(Colors.DARK_BLUE);
        labelsSubSubL.add(subL, BorderLayout.WEST);

        // add to panel
        labels.add(labelsSubL);
        labels.add(Box.createVerticalStrut(15));
        labels.add(labelsSubSubL);
        // labels.setBounds(10,10,280,80);
        labels.setBorder(new EmptyBorder(15,15,15,15));
        // labels.setBackground(Color.CYAN);
        labels.setOpaque(false);

        this.add(labels, BorderLayout.PAGE_START);
    }
}
