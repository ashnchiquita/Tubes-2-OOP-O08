package boundary.widget;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TopBar extends ScrollableButtonContainers {
    private Color bgColor;
    private Integer contentWidth;
    private Integer height;
    private Integer defaultButtonSize = 180;
    private String active;
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
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(homeButton.getStatus()) {
                    return;
                }
                ((TopBarButton) components.get(active)).changeStatus(false);
                homeButton.changeStatus(true);
                active = "homeButton";
                return;
            }
        });
        homeButton.changeStatus(true);
        active = "homeButton";

        //setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
        JScrollBar horizontalScrollBar = getHorizontalScrollBar();
        horizontalScrollBar.setUI(new PlainScrollBar(Color.WHITE, new Color(220, 220, 220)));
        horizontalScrollBar.setPreferredSize(new Dimension(horizontalScrollBar.getWidth(), 2));
        setColumnHeaderView(horizontalScrollBar);

        setViewportView(contentPanel);
    }

    public String getActive(){
        return active;
    }

    private void registerButtonLogic(TopBarButton addition, String name){
        addition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addition.getStatus()){
                    addition.changeStatus(false);
                    TopBarButton home = (TopBarButton) components.get("homeButton");
                    home.changeStatus(true);
                    home.doClick();
                    active = "homeButton";
                    return;
                }
                ((TopBarButton) components.get(active)).changeStatus(false);

                addition.changeStatus(true);
                active = name;
                return;
            }
        });
    }

    @Override
    public JComponent addButton(JButton addition, String name) throws IllegalArgumentException{
        //Note: name must be unique
        //addition must be TopBarButton
        if(!(addition instanceof TopBarButton))
            throw new IllegalArgumentException("Cannot put normal button into TopBar, use TopBarButton instead");

        JComponent retval = addComponent(addition, name);
        Integer rightmostLocation = 60 + buttonCount*defaultButtonSize;
        addition.setBounds( rightmostLocation,0,defaultButtonSize, height);
        buttonCount++;
        contentPanel.setPreferredSize(new Dimension(rightmostLocation+defaultButtonSize > 977? rightmostLocation+defaultButtonSize:977, height-10));
        registerButtonLogic((TopBarButton) addition, name);

        return retval;
    }

    public void removeButton(String name) throws IllegalArgumentException{
        if(!components.containsKey(name)){
            throw new IllegalArgumentException("Name does not exist");
        }
        Integer removedLocation = components.get(name).getX();
        contentPanel.remove(components.get(name));
        components.remove(name);
        buttonCount--;
        revalidate();
        components.values().forEach(v -> {
            Integer buttonLoc = v.getX();
            if(v.getX() > removedLocation){
                v.setLocation(buttonLoc - defaultButtonSize, 0);
            }
        });
        repaint();
    }
}
