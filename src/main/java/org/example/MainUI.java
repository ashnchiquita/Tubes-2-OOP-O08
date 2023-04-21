package org.example;

import javax.swing.*;
import java.awt.*;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class MainUI {
    public JPanel contentPanel;

    public MainUI(){
        JFrame mainWindow = new JFrame();
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints contentPanelGbc = new GridBagConstraints();
        contentPanelGbc.gridx = 1;
        contentPanelGbc.gridy = 1;
        contentPanelGbc.fill = GridBagConstraints.BOTH;
        contentPanelGbc.weightx = 3;
        contentPanelGbc.weighty = 30;
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);

        //TODO: Split sidepanel into new class
        GridBagConstraints sidePanelGbc = new GridBagConstraints();
        sidePanelGbc.gridx = 0;
        sidePanelGbc.gridy = 0;
        sidePanelGbc.gridheight = mainPanel.getHeight();
        sidePanelGbc.weightx = 0;
        sidePanelGbc.weighty = 1;
        sidePanelGbc.fill = GridBagConstraints.BOTH;
        JPanel sidePanel = new SideBar(300, Color.WHITE, Color.BLACK);

        //TODO: topBar logics
        TopBar topBar = new TopBar(30);
        GridBagConstraints topBarGbc = new GridBagConstraints();
        topBarGbc.gridx = 1;
        topBarGbc.gridy = 0;
        topBarGbc.weightx = 1;
        topBarGbc.weighty = 0;
        topBarGbc.fill = GridBagConstraints.BOTH;

        TopBarButton topBar1 = new TopBarButton();
        TopBarButton topBar2 = new TopBarButton();

        topBar.add(topBar1);
        topBar.add(topBar2);


        //TODO: Tidy up
        mainPanel.add(contentPanel, contentPanelGbc);
        mainPanel.add(sidePanel, sidePanelGbc);
        mainPanel.add(topBar, topBarGbc);
        mainPanel.setBackground(Color.PINK);

        this.contentPanel = contentPanel;

        loadHome();

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setMinimumSize(new Dimension(1280,720));
        //mainWindow.setResizable(false);
        mainWindow.add(mainPanel);
        mainWindow.pack();
        mainWindow.setVisible(true);
        mainWindow.setTitle("TubesTubesTubesWANGYWANGYWANGYAAAAAAAAAAAA");
    }

    public void loadHome(){
        JLabel testLabel = new JLabel("Test");
        this.contentPanel.removeAll();
        this.contentPanel.add(testLabel);
    }

    public static void main(String[] args) {
        new MainUI();
        System.out.print("Compile Success!");
    }
}