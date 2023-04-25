package Boundary.LaporanUI;

import Boundary.Util.Colors;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    public static void showUI() {
        JFrame mainFrame = new JFrame("Laporan Page Example");
        mainFrame.setSize(1280, 720);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.getContentPane().setBackground(Colors.WHITE.getColor());
        int vw = mainFrame.getWidth(), vh = mainFrame.getHeight();

        JPanel sideNav = new JPanel();
        sideNav.setPreferredSize(new Dimension(230, vh));
        sideNav.setBackground(Colors.LIGHT_BLUE.getColor());
        mainFrame.add(sideNav, BorderLayout.WEST);

        JPanel mainRight = new JPanel();
        mainRight.setLayout(new BorderLayout());
        mainRight.setBackground(Colors.WHITE.getColor());
        mainFrame.add(mainRight, BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Colors.DARK_BLUE.getColor());
        topPanel.setPreferredSize(new Dimension((int) (0.81 * vw), 48));
        mainRight.add(topPanel, BorderLayout.NORTH);

        mainRight.add(new LaporanPanel(), BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showUI();
            }
        });
    }
}
