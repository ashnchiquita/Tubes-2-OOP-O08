package boundary.widget;

import boundary.constants.Colors;
import boundary.constants.ResourcePath;
import boundary.enums.PanelEnum;
import boundary.observer.tab.TabEvent;
import boundary.observer.tab.TabListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TopBar extends ScrollableContainer implements TabListener {
    private Map<PanelEnum, ArrayList<String>> panelArrays = new HashMap<>();
    private Map<String, ArrayList<String>> panelTypeArrays = new HashMap<>();
    private Integer buttonCount = 0;
    private Color bgColor;
    private Integer contentWidth;
    private Integer height;
    private Integer defaultButtonSize = 180;
    private String active;
    public TopBar(Integer heightin, Color BgColor){
        super();

        panelArrays.put(PanelEnum.NULL, new ArrayList<>());
        panelArrays.put(PanelEnum.HOME, new ArrayList<>());
        panelArrays.put(PanelEnum.KASIR, new ArrayList<>());
        panelArrays.put(PanelEnum.LAPORAN, new ArrayList<>());
        panelArrays.put(PanelEnum.INVENTARIS, new ArrayList<>());
        panelArrays.put(PanelEnum.MEMBER, new ArrayList<>());
        panelArrays.put(PanelEnum.PENGATURAN, new ArrayList<>());

        bgColor = BgColor;
        height = heightin;
        contentWidth = 977;
        setPreferredSize(new Dimension(0, height));

        ImageIcon icon = new ImageIcon(ResourcePath.ICON + "/home_dark_blue.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(22,22, Image.SCALE_SMOOTH);
        final ImageIcon activeIcon = new ImageIcon(newImage);

        contentPanel.setPreferredSize(new Dimension(contentWidth, 0));
        contentPanel.setBackground(bgColor);
        contentPanel.setAutoscrolls(true);
        contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        setBorder(new EmptyBorder(0, 0, 0, 0));
        setViewportBorder(new EmptyBorder(0, 0, 0, 0));


        icon = new ImageIcon(ResourcePath.ICON + "/home_light_blue.png");
        image = icon.getImage();
        newImage = image.getScaledInstance(22,22, Image.SCALE_SMOOTH);
        final ImageIcon inactiveIcon = new ImageIcon(newImage);


        TopBarButton homeButton = (TopBarButton) addComponent(new TopBarButton(){
            @Override
            public void changeStatus(Boolean status) {
                super.changeStatus(status);
                if(status == true){
                    this.setIcon(activeIcon);
                }
                else{
                    this.setIcon(inactiveIcon);
                }
            }
        }, "homeButton");
        homeButton.setBounds(0,0,60,height);
        homeButton.setIcon(activeIcon);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(true);
        homeButton.setBorderPainted(false);
        homeButton.setBackground(Colors.WHITE);
        homeButton.previousColor = Colors.WHITE;
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(homeButton.getStatus()) {
                    return;
                }
                if(components.containsKey(active)){
                    ((TopBarButton) components.get(active)).changeStatus(false);
                }
                homeButton.changeStatus(true);
                active = "homeButton";
                return;
            }
        });
        homeButton.changeStatus(true);
        ArrayList<String> homeArray = panelArrays.get(PanelEnum.HOME);
        homeArray.add("homeButton");
        panelTypeArrays.put("homeButton", homeArray);
        active = "homeButton";

        //setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
        JScrollBar horizontalScrollBar = getHorizontalScrollBar();
        horizontalScrollBar.setUI(new PlainScrollBar(Colors.WHITE, Colors.LIGHT_GRAY));
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

    public TopBarButton addTab(TopBarTab addition, String name, PanelEnum type) throws IllegalArgumentException{
        //Note: name must be unique
        addComponent(addition, name);
        ArrayList<String> typeArray = panelArrays.get(type);
        typeArray.add(name);
        panelTypeArrays.put(name, typeArray);
        Integer rightmostLocation = 60 + buttonCount*defaultButtonSize;
        addition.setBounds( rightmostLocation,0,defaultButtonSize, height);
        buttonCount++;
        contentPanel.setPreferredSize(new Dimension(rightmostLocation+defaultButtonSize > 977? rightmostLocation+defaultButtonSize:977, height-10));
        registerButtonLogic(addition, name);
        addition.getObserver().addListener(this);

        return addition;
    }

    public void removeTab(String name) throws IllegalArgumentException{
        if(!components.containsKey(name)){
            throw new IllegalArgumentException("Name does not exist");
        }
        TopBarTab removal = (TopBarTab) components.get(name);
        Integer removedLocation = removal.getX();
        contentPanel.remove(removal);
        panelTypeArrays.get(name).remove(name);
        panelTypeArrays.remove(name);
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

    public boolean hasType(PanelEnum type){
        return !panelArrays.get(type).isEmpty();
    }
    public ArrayList<String> getTabsWithType(PanelEnum type){
        return panelArrays.get(type);
    }

    @Override
    public void closeTab(TabEvent e, String tabname) {
        removeTab(tabname);
    }
}
