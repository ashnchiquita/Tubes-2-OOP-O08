package boundary.widget;

import boundary.constants.Colors;
import boundary.constants.ResourcePath;

import javax.swing.*;
import java.awt.*;

public class SideBar extends ScrollableContainer {
    private Integer buttonCount = 0;
    private Color fgColor;
    private Color bgColor;
    private Integer contentHeight;
    private Integer width;
    private void adjustRefreshButton(){
        Integer bottomMostLocation = 60*buttonCount + 243;
        components.get("refreshButton").setBounds(37,60*buttonCount + 220,37,37);
        components.get("lastTransactionLabel").setBounds(85,60*buttonCount + 219,121,20);
        components.get("lastTransactionText").setBounds(85,bottomMostLocation,169,16);
        contentPanel.setPreferredSize(new Dimension(width-18, bottomMostLocation +30 > 678? bottomMostLocation + 100 : 678));
    }
    public JButton addButton(JButton addition, String name) throws IllegalArgumentException{
        //Note: name must be unique
        addComponent(addition, name);
        addition.setBounds(0,130 + buttonCount*60,340,60);
        buttonCount++;
        adjustRefreshButton();
        return addition;
    }
    public SideBar(Integer widthin, Color BgColor, Color FgColor){
        super();
        fgColor = FgColor;
        bgColor = BgColor;
        width = widthin;
        contentHeight = 678;
        setPreferredSize(new Dimension(width,0));
        //setBackground(bgColor);

        ImageIcon icon = new ImageIcon(ResourcePath.ICON + "/refresh.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(37,37, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Colors.WHITE));

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

        JLabel lastTransactionLabel = (JLabel) addComponent(new JLabel("Last Transaction:"), "lastTransactionLabel");
        lastTransactionLabel.setBounds(85,279,121,20);
        lastTransactionLabel.setForeground(fgColor);
        lastTransactionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lastTransactionLabel.setVerticalAlignment(SwingConstants.TOP);
        lastTransactionLabel.setFont(new Font("Rubik-SemiBold", Font.BOLD, 13));

        JLabel lastTransactionText = (JLabel) addComponent(new JLabel("Monday, 16 Juli 2024"), "lastTransactionText");
        lastTransactionText.setBounds(85,303,169,16);
        lastTransactionText.setForeground(fgColor);
        lastTransactionText.setHorizontalAlignment(SwingConstants.LEFT);
        lastTransactionText.setVerticalAlignment(SwingConstants.TOP);
        lastTransactionText.setFont(new Font("Rubik-SemiBold", Font.PLAIN, 13));

        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        getVerticalScrollBar().setUI(new PlainScrollBar(bgColor, Colors.SIDE_SLIDER_BLUE));
        //setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);

        setViewportView(contentPanel);
    }
}
