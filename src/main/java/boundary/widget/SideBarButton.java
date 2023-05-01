package boundary.widget;

import javax.swing.*;
import java.awt.*;

public class SideBarButton extends HoverButton {
    public SideBarButton(String iconpath, String text){
        super();
        String rootPath = System.getProperty("user.dir");
        rootPath += "/res/img/";
        ImageIcon icon = new ImageIcon(rootPath + iconpath);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(24,24, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        setHorizontalAlignment(SwingConstants.LEFT);
        setIconTextGap(20);

        setBorder(BorderFactory.createEmptyBorder());
        setFocusPainted(false);
        setContentAreaFilled(true);

        setFont(new Font("Rubik Light", 0, 20));
        setText(text);
        setIcon(icon);
        setBorder(BorderFactory.createEmptyBorder(0, 37, 0, 0));
    }
}
