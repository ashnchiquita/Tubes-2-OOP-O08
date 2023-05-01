package boundary.widget;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SideBar extends ScrollableButtonContainers {
    private Color fgColor;
    private Color bgColor;
    private Integer contentHeight;
    private Integer width;
    //TODO: Logics?
    private void adjustRefreshButton(){
        Integer bottomMostLocation = 60*buttonCount + 243;
        components.get("refreshButton").setBounds(37,60*buttonCount + 220,37,37);
        components.get("lastLoginLabel").setBounds(85,60*buttonCount + 219,121,20);
        components.get("lastLoginText").setBounds(85,bottomMostLocation,169,16);
        contentPanel.setPreferredSize(new Dimension(width-18, bottomMostLocation +30 > 678? bottomMostLocation + 100 : 678));
    }
    @Override
    public JComponent addButton(JButton addition, String name) throws IllegalArgumentException{
        //Note: name must be unique
        JComponent retval = addComponent(addition, name);
        addition.setBounds(0,130 + buttonCount*60,340,60);
        buttonCount++;
        adjustRefreshButton();
        return retval;
    }
    public SideBar(Integer widthin, Color BgColor, Color FgColor){
        super();
        fgColor = FgColor;
        bgColor = BgColor;
        width = widthin;
        contentHeight = 678;
        setPreferredSize(new Dimension(width,0));
        //setBackground(bgColor);

        String rootPath = System.getProperty("user.dir");
        rootPath += "/res/img/";
        ImageIcon icon = new ImageIcon(rootPath + "Refresh.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(37,37, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.WHITE));

        contentPanel.setPreferredSize(new Dimension(width-18, contentHeight));
        contentPanel.setBackground(bgColor);
        contentPanel.setAutoscrolls(true);

        JLabel openingLabel = (JLabel) addComponent(new JLabel("Cashoria"), "openingLabel");
        openingLabel.setBounds(19,37,143,30);
        openingLabel.setForeground(fgColor);
        openingLabel.setHorizontalAlignment(SwingConstants.LEFT);
        openingLabel.setVerticalAlignment(SwingConstants.TOP);
        openingLabel.setFont(new Font("Rubik-SemiBold", Font.BOLD, 25));

        JPanel underline = (JPanel) addComponent(new JPanel(), "underline");
        underline.setBackground(fgColor);
        underline.setBounds(20,89,165,1);

        HoverButton refreshButton = (HoverButton) addComponent(new HoverButton(), "refreshButton");
        refreshButton.setForeground(fgColor);
        refreshButton.setBackground(bgColor);
        refreshButton.setIcon(icon);
        refreshButton.setBounds(37,280,37,37);
        refreshButton.setFocusPainted(false);
        refreshButton.setContentAreaFilled(true);
        refreshButton.setBorderPainted(false);

        JLabel lastLoginLabel = (JLabel) addComponent(new JLabel("Last Login:"), "lastLoginLabel");
        lastLoginLabel.setBounds(85,279,121,20);
        lastLoginLabel.setForeground(fgColor);
        lastLoginLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lastLoginLabel.setVerticalAlignment(SwingConstants.TOP);
        lastLoginLabel.setFont(new Font("Rubik-SemiBold", Font.BOLD, 13));

        JLabel lastLoginText = (JLabel) addComponent(new JLabel("Monday, 16 Juli 2024"), "lastLoginText");
        lastLoginText.setBounds(85,303,169,16);
        lastLoginText.setForeground(fgColor);
        lastLoginText.setHorizontalAlignment(SwingConstants.LEFT);
        lastLoginText.setVerticalAlignment(SwingConstants.TOP);
        lastLoginText.setFont(new Font("Rubik-SemiBold", Font.PLAIN, 13));


        //addButton(new SideBarButton("Support.png", "Test", fgColor, bgColor), "dummyButton1");
        //addButton(new SideBarButton("Support.png", "Test", fgColor, bgColor), "dummyButton2");
        //addButton(new SideBarButton("Support.png", "Test", fgColor, bgColor), "dummyButton3");
        //addButton(new SideBarButton("Support.png", "Test", fgColor, bgColor), "dummyButton4");
        //addButton(new SideBarButton("Support.png", "Test", fgColor, bgColor), "dummyButton5");
        //addButton(new SideBarButton("Support.png", "Test", fgColor, bgColor), "dummyButton6");
        //addButton(new SideBarButton("Support.png", "Test", fgColor, bgColor), "dummyButton3");

        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        getVerticalScrollBar().setUI(new PlainScrollBar(bgColor, new Color(9085684)));
        //setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);

        setViewportView(contentPanel);
    }
}
