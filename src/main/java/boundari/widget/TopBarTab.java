package boundari.widget;

import boundari.constants.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TopBarTab extends TopBarButton{
    RoundedPanel marker;
    JButton closeButton;
    JLabel closeLabel;
    JLabel tabLabel;
    public TopBarTab(String name){
        super();
        setLayout(null);
        closeButton = new JButton();
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setOpaque(false);
        closeButton.setBounds(150, 12, 20, 20);
        closeButton.hide();
        closeLabel = new JLabel("X");
        closeLabel.setBounds(150, 12, 20, 20);
        closeLabel.hide();
        tabLabel = new JLabel(name);
        tabLabel.setBounds(45, 12, 130, 20);
        tabLabel.setFont(new Font("Inter", Font.PLAIN, 15));
        tabLabel.setForeground(Colors.WHITE);
        marker = new RoundedPanel(20, Colors.TOPBAR_MARKER_OFF, false, Colors.WHITE, 0);
        marker.setBounds(15, 12, 20, 20);
        add(marker);
        add(closeButton);
        add(closeLabel);
        add(tabLabel);
    }
    @Override
    public void changeStatus(Boolean status){
        super.changeStatus(status);
        remove(marker);
        if(status == true){
            marker = new RoundedPanel(20, Colors.TOPBAR_MARKER_ON, false, Colors.WHITE, 0);
            closeButton.show();
            closeLabel.show();
            tabLabel.setForeground(Colors.TOPBAR_MARKER_ON);
        }
        else{
            marker = new RoundedPanel(20, Colors.TOPBAR_MARKER_OFF, false, Colors.WHITE, 0);
            closeButton.hide();
            closeLabel.hide();
            tabLabel.setForeground(Colors.TOPBAR_MARKER_OFF);
        }
        marker.setBounds(15, 12, 20, 20);
        add(marker);
    }

    public void onClose(ActionListener listener){
        closeButton.addActionListener(listener);
    }
}
