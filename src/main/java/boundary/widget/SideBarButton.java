package boundary.widget;

import boundary.constants.ResourcePath;

import javax.swing.*;
import java.awt.*;

public class SideBarButton extends HoverButton {
    public SideBarButton(String iconpath, String text){
        super();
        ImageIcon icon = new ImageIcon(ResourcePath.ICON + iconpath);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(24,24, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        setHorizontalAlignment(SwingConstants.LEFT);
        setIconTextGap(20);

        setBorder(BorderFactory.createEmptyBorder());
        setFocusPainted(false);
        setContentAreaFilled(true);
        setForeground(Color.WHITE);
        setBackground(new Color(56,100,194));

        setFont(new Font("Rubik Light", 0, 20));
        setText(text);
        setIcon(icon);
        setBorder(BorderFactory.createEmptyBorder(0, 37, 0, 0));
    }
    public SideBarButton(String iconpath, String text, Color fgColor, Color bgColor){
        super();
        ImageIcon icon = new ImageIcon(ResourcePath.ICON + iconpath);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(24,24, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        setHorizontalAlignment(SwingConstants.LEFT);
        setIconTextGap(20);

        setBorder(BorderFactory.createEmptyBorder());
        setFocusPainted(false);
        setContentAreaFilled(true);
        setForeground(fgColor);
        setBackground(bgColor);

        setFont(new Font("Rubik Light", 0, 20));
        setText(text);
        setIcon(icon);
        setBorder(BorderFactory.createEmptyBorder(0, 37, 0, 0));
    }
}
