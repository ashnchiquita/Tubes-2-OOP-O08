package org.example.widgets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TopBarButton extends HoverButton {
    private final Color defaultColor;
    private final Color activeColor;
    private final MouseAdapter mouseListener;
    private Boolean active;
    public TopBarButton(){
        super();
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.WHITE));
        active = false;
        defaultColor = new Color(75, 129, 238);
        activeColor = Color.white;
        mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(!active){
                    setBackground(activeColor);
                    previousColor = activeColor;
                    active = true;
                }
                else{
                    setBackground(defaultColor);
                    previousColor = defaultColor;
                    active = false;
                }
            }
        };
        addMouseListener(mouseListener);
        setPreferredSize(new Dimension(135, 0));

        setFocusPainted(false);
        setContentAreaFilled(true);

        setBackground(defaultColor);
    }

    public void changeStatus(Boolean status) {
        if(status == true){
            setBackground(activeColor);
            previousColor = activeColor;
            active = true;
        }
        else{
            setBackground(defaultColor);
            previousColor = defaultColor;
            active = false;
        }
    }
}