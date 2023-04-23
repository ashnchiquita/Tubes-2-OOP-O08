package org.example.widgets;

import javax.swing.*;
import java.awt.*;

public class SideBar extends JPanel {
    private JLabel openingLabel;
    private JPanel underline;
    private JLabel lastLoginLabel;
    private JLabel lastLoginText;
    public SideBarButton kasirButton;
    public SideBarButton laporanButton;
    public SideBarButton memberButton;
    public SideBarButton inventarisButton;
    public SideBarButton pengaturanButton;

    public HoverButton refreshButton;

    //TODO: Logics?
    public SideBar(Integer width, Color bgColor, Color fgColor){
        setLayout(null);
        setPreferredSize(new Dimension(width,0));
        setBackground(bgColor);

        openingLabel = new JLabel("NamaApp");
        openingLabel.setBounds(19,37,143,30);
        openingLabel.setForeground(fgColor);
        openingLabel.setHorizontalAlignment(SwingConstants.LEFT);
        openingLabel.setVerticalAlignment(SwingConstants.TOP);
        openingLabel.setFont(new Font("Rubik-SemiBold", Font.BOLD, 25));

        underline = new JPanel();
        underline.setBackground(fgColor);
        underline.setBounds(20,89,165,1);

        kasirButton = new SideBarButton("Kasir.png", "Kasir");
        kasirButton.setForeground(fgColor);
        kasirButton.setBackground(bgColor);
        kasirButton.setBounds(0,130,340,60);

        laporanButton = new SideBarButton("Laporan.png", "Laporan");
        laporanButton.setForeground(fgColor);
        laporanButton.setBackground(bgColor);
        laporanButton.setBounds(0,190,340,60);

        memberButton = new SideBarButton("Member.png", "Member");
        memberButton.setForeground(fgColor);
        memberButton.setBackground(bgColor);
        memberButton.setBounds(0,250,340,60);

        inventarisButton = new SideBarButton("Inventaris.png", "Inventaris");
        inventarisButton.setForeground(fgColor);
        inventarisButton.setBackground(bgColor);
        inventarisButton.setBounds(0,310,340,60);

        pengaturanButton = new SideBarButton("Support.png", "Pengaturan");
        pengaturanButton.setForeground(fgColor);
        pengaturanButton.setBackground(bgColor);
        pengaturanButton.setBounds(0,370,340,60);

        refreshButton = new HoverButton();
        refreshButton.setForeground(fgColor);
        refreshButton.setBackground(bgColor);

        String rootPath = System.getProperty("user.dir");
        rootPath += "/res/img/";
        ImageIcon icon = new ImageIcon(rootPath + "Refresh.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(37,37, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        refreshButton.setIcon(icon);
        refreshButton.setBounds(37,520,37,37);
        refreshButton.setFocusPainted(false);
        refreshButton.setContentAreaFilled(true);
        refreshButton.setBorderPainted(false);

        lastLoginLabel = new JLabel("Last Login:");
        lastLoginLabel.setBounds(85,519,121,20);
        lastLoginLabel.setForeground(fgColor);
        lastLoginLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lastLoginLabel.setVerticalAlignment(SwingConstants.TOP);
        lastLoginLabel.setFont(new Font("Rubik-SemiBold", Font.BOLD, 13));


        lastLoginText = new JLabel("Monday, 16 Juli 2024");
        lastLoginText.setBounds(85,543,169,16);
        lastLoginText.setForeground(fgColor);
        lastLoginText.setHorizontalAlignment(SwingConstants.LEFT);
        lastLoginText.setVerticalAlignment(SwingConstants.TOP);
        lastLoginText.setFont(new Font("Rubik-SemiBold", Font.PLAIN, 13));


        add(openingLabel);
        add(underline);
        add(kasirButton);
        add(laporanButton);
        add(memberButton);
        add(inventarisButton);
        add(pengaturanButton);
        add(refreshButton);
        add(lastLoginLabel);
        add(lastLoginText);
    }
}
