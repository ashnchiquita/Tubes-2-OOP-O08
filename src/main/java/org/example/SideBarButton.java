package org.example;

import javax.swing.*;
import java.awt.*;

public class SideBarButton extends HoverButton{
    public SideBarButton(String iconpath, String text){
        super();
        String rootPath = System.getProperty("user.dir");
        ImageIcon icon = new ImageIcon(rootPath + iconpath);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(20,20, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        setHorizontalAlignment(SwingConstants.LEFT);
        setIconTextGap(20);

        setBorder(BorderFactory.createEmptyBorder());
        setFocusPainted(false);
        setContentAreaFilled(true);

        setText(text);
        setIcon(icon);
    }
}
