package org.example;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel{
    private Integer buttonCount;

    public TopBar(Integer height){
        super();
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(0, height));
        buttonCount = 0;
    }

    private void wrap(){
        JPanel spacer = new JPanel();
        GridBagConstraints spacerGbc = new GridBagConstraints();

        spacerGbc.gridx = this.buttonCount;
        spacerGbc.gridy = 0;
        spacerGbc.fill = GridBagConstraints.BOTH;
        spacerGbc.weightx = 1;
        spacerGbc.weighty = 1;
        spacer.setBackground(Color.YELLOW);

        this.add(spacer, spacerGbc);
    }

    //TODO: error handling
    public void add(TopBarButton button){
        GridBagConstraints gbc = new GridBagConstraints();

        if(buttonCount != 0){
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
