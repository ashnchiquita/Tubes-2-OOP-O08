package boundary.widget;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel{
    private Integer buttonCount;

    public TopBar(Integer height){
        super();
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(0, height));

        TopBarButton homeButton = new TopBarButton();
        GridBagConstraints homeButtonGbc = new GridBagConstraints();
        homeButtonGbc.gridx = 0;
        homeButtonGbc.gridy = 0;
        homeButtonGbc.fill = GridBagConstraints.BOTH;
        homeButtonGbc.weightx = 0;
        homeButtonGbc.weighty = 1;
        homeButton.setPreferredSize(new Dimension(60, 0));

        String rootPath = System.getProperty("user.dir");
        rootPath += "/res/img/";
        ImageIcon icon = new ImageIcon(rootPath + "Home.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(22,22, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        homeButton.setIcon(icon);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(true);
        homeButton.setBorderPainted(false);


        homeButton.setBackground(Color.WHITE);
        homeButton.previousColor = Color.WHITE;
        homeButton.changeStatus(true);

        this.add(homeButton, homeButtonGbc);
        homeButton.changeStatus(true);

        buttonCount = 1;
    }

    private void wrap(){
        JPanel rspacer = new JPanel();
        GridBagConstraints rspacerGbc = new GridBagConstraints();

        rspacerGbc.gridx = this.buttonCount;
        rspacerGbc.gridy = 0;
        rspacerGbc.fill = GridBagConstraints.BOTH;
        rspacerGbc.weightx = 1;
        rspacerGbc.weighty = 1;
        rspacer.setBackground(new Color(36, 60, 148));

        this.add(rspacer, rspacerGbc);
    }

    //TODO: error handling
    public void add(TopBarButton button){
        GridBagConstraints gbc = new GridBagConstraints();

        if(buttonCount != 1){
            this.remove(this.buttonCount);
        }

        gbc.gridx = this.buttonCount;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.WEST;

        this.buttonCount++;

        this.add(button, gbc);
        this.wrap();
    }

}
