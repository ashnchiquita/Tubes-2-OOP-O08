package boundary.widget;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TopBar extends ScrollableButtonContainers{
    private Color bgColor;
    private Integer contentWidth;
    private Integer height;
    public TopBar(Integer heightin, Color BgColor){
        super();
        bgColor = BgColor;
        height = heightin;
        contentWidth = 977;
        setPreferredSize(new Dimension(0, height));

        String rootPath = System.getProperty("user.dir");
        rootPath += "/res/img/";
        ImageIcon icon = new ImageIcon(rootPath + "Home.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(22,22, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        contentPanel.setPreferredSize(new Dimension(contentWidth, 0));
        contentPanel.setBackground(bgColor);
        contentPanel.setAutoscrolls(true);
        contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        setBorder(new EmptyBorder(0, 0, 0, 0));
        setViewportBorder(new EmptyBorder(0, 0, 0, 0));

        TopBarButton homeButton = (TopBarButton) addComponent(new TopBarButton(), "homeButton");
        homeButton.setBounds(0,0,60,height);
        homeButton.setIcon(icon);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(true);
        homeButton.setBorderPainted(false);
        homeButton.setBackground(Color.WHITE);
        homeButton.previousColor = Color.WHITE;
        homeButton.changeStatus(true);

        //setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
        JScrollBar horizontalScrollBar = getHorizontalScrollBar();
        horizontalScrollBar.setUI(new PlainScrollBar(Color.WHITE, new Color(220, 220, 220)));
        horizontalScrollBar.setPreferredSize(new Dimension(horizontalScrollBar.getWidth(), 2));
        setColumnHeaderView(horizontalScrollBar);

        setViewportView(contentPanel);
    }

    @Override
    public JComponent addButton(JButton addition, String name) throws IllegalArgumentException{
        //Note: name must be unique
        JComponent retval = addComponent(addition, name);
        Integer rightmostLocation = 60 + buttonCount*130;
        addition.setBounds( rightmostLocation,0,130, height);
        buttonCount++;
        contentPanel.setPreferredSize(new Dimension(rightmostLocation+130 > 977? rightmostLocation+130:977, height-10));
        return retval;
    }

    public void removeButton(String name) throws IllegalArgumentException{
        if(!components.containsKey(name)){
            throw new IllegalArgumentException("Name does not exist");
        }
        Integer removedLocation = components.get(name).getX();
        contentPanel.remove(components.get(name));
        components.remove(name);
        components.values().forEach(v -> {
            Integer buttonLoc = v.getX();
            if(v.getX() > removedLocation){
                v.setLocation(buttonLoc - 130, 0);
            }
        });
    }

}
