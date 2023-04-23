package org.example;

import org.example.widgets.SideBar;

import javax.swing.*;
import java.awt.*;

public class Test {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame();

        /* testing sidebar */
        SideBar test = new SideBar(300, Color.BLACK, Color.WHITE);
        test.setMinimumSize(new Dimension(350, 720));
        test.setBackground(Color.BLACK);

        /* testing topLabel
        String rootPath = System.getProperty("user.dir");

        TopBar test = new TopBar();
        test.setMinimumSize(new Dimension(1000, 60));

        TopBarButton test1 = new TopBarButton();
        TopBarButton test2 = new TopBarButton();

        ImageIcon icon =  new ImageIcon(rootPath + "/test.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        test1.setHorizontalAlignment(SwingConstants.LEFT);
        test1.setIconTextGap(10);
        test1.setText("Home");
        test1.setIcon(icon);

        test.add(test1);
        test.add(test2);
        */

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setMinimumSize(test.getMinimumSize());
        mainWindow.add(test);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
}
