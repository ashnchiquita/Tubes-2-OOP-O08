package boundary.widget;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SideBar extends JScrollPane {
    private Color fgColor;
    private Color bgColor;

    private Map<String, JComponent> components;
    private Integer buttonCount;

    private JPanel contentPanel;
    private Integer contentHeight;
    private ImageIcon refreshIcon;
    //TODO: Logics?
    private void initCoreComponents(Integer width){
        components = new HashMap<>();
        buttonCount = 0;
        contentHeight = 768;

        String rootPath = System.getProperty("user.dir");
        rootPath += "/res/img/";
        ImageIcon icon = new ImageIcon(rootPath + "Refresh.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(37,37, Image.SCALE_SMOOTH);
        refreshIcon = new ImageIcon(newImage);

        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setPreferredSize(new Dimension(width-18, contentHeight));
        contentPanel.setBackground(bgColor);
        contentPanel.setAutoscrolls(true);
        add(contentPanel);

        JLabel openingLabel = (JLabel) addComponent(new JLabel("NamaApp"), "openingLabel");
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

    }
    private void adjustRefreshButton(){
        components.get("refreshButton").setBounds(37,60*buttonCount + 220,37,37);
        components.get("lastLoginLabel").setBounds(85,60*buttonCount + 219,121,20);
        components.get("lastLoginText").setBounds(85,60*buttonCount + 243,169,16);
    }
    public JComponent addComponent(JComponent addition, String name) throws IllegalArgumentException{
        //Note: name must be unique
        if(components.containsKey(name)){
            throw new IllegalArgumentException("Name already exists");
        }
        components.put(name, addition);
        contentPanel.add(addition);
        return addition;
    }
    public JComponent addButton(JButton addition, String name) throws IllegalArgumentException{
        //Note: name must be unique
        JComponent retval = addComponent(addition, name);
        addition.setBounds(0,130 + buttonCount*60,340,60);
        buttonCount++;
        adjustRefreshButton();
        return retval;
    }
    public SideBar(Integer width, Color BgColor, Color FgColor){
        fgColor = FgColor;
        bgColor = BgColor;
        setPreferredSize(new Dimension(width,0));
        //setBackground(bgColor);

        initCoreComponents(width);

        addButton(new SideBarButton("Kasir.png", "Kasir", fgColor, bgColor), "kasirButton");
        addButton(new SideBarButton("Laporan.png", "Laporan", fgColor, bgColor), "laporanButton");
        addButton(new SideBarButton("Member.png", "Member", fgColor, bgColor), "MemberButton");
        addButton(new SideBarButton("Inventaris.png", "Inventaris", fgColor, bgColor), "inventarisButton");
        addButton(new SideBarButton("Support.png", "Pengaturan", fgColor, bgColor), "pengaturanButton");
        addButton(new SideBarButton("Support.png", "Test", fgColor, bgColor), "dummyButton1");
        addButton(new SideBarButton("Support.png", "Test", fgColor, bgColor), "dummyButton2");
        //addButton(new SideBarButton("Support.png", "Test", fgColor, bgColor), "dummyButton3");
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        getVerticalScrollBar().setUI(new PlainScrollBar(bgColor, new Color(9085684)));
        //setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);

        setViewportView(this.contentPanel);
    }
}
