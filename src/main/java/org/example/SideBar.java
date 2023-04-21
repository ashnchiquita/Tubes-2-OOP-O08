package org.example;

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
        openingLabel.setBounds(50,60,100,100);
        openingLabel.setForeground(fgColor);
        openingLabel.setHorizontalAlignment(SwingConstants.LEFT);
        openingLabel.setVerticalAlignment(SwingConstants.TOP);

        underline = new JPanel();
        underline.setBackground(fgColor);
        underline.setBounds(50,100,100,1);

        kasirButton = new SideBarButton("/test.png", "Kasir");
        kasirButton.setForeground(fgColor);
        kasirButton.setBackground(bgColor);
        kasirButton.setBounds(0,150,340,60);
        kasirButton.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

        laporanButton = new SideBarButton("/test.png", "Laporan");
        laporanButton.setForeground(fgColor);
        laporanButton.setBackground(bgColor);
        laporanButton.setBounds(0,210,340,60);
        laporanButton.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

        memberButton = new SideBarButton("/test.png", "Member");
        memberButton.setForeground(fgColor);
        memberButton.setBackground(bgColor);
        memberButton.setBounds(0,270,340,60);
        memberButton.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

        inventarisButton = new SideBarButton("/test.png", "Inventaris");
        inventarisButton.setForeground(fgColor);
        inventarisButton.setBackground(bgColor);
        inventarisButton.setBounds(0,330,340,60);
        inventarisButton.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

        pengaturanButton = new SideBarButton("/test.png", "Pengaturan");
        pengaturanButton.setForeground(fgColor);
        pengaturanButton.setBackground(bgColor);
        pengaturanButton.setBounds(0,390,340,60);
        pengaturanButton.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

        refreshButton = new HoverButton();
        refreshButton.setForeground(fgColor);
        refreshButton.setBackground(bgColor);
        refreshButton.setBounds(50,500,50,50);

        lastLoginLabel = new JLabel("Last Login:");
        lastLoginLabel.setBounds(120,505,100,100);
        lastLoginLabel.setForeground(fgColor);
        lastLoginLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lastLoginLabel.setVerticalAlignment(SwingConstants.TOP);

        lastLoginText = new JLabel("Monday, 16 Juli 2024");
        lastLoginText.setBounds(120,530,100,100);
        lastLoginText.setForeground(fgColor);
        lastLoginText.setHorizontalAlignment(SwingConstants.LEFT);
        lastLoginText.setVerticalAlignment(SwingConstants.TOP);


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
