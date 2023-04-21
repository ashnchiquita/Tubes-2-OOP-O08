package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TopBarButton extends HoverButton {
    private MouseAdapter mouseListener;
    private Boolean active;
    public TopBarButton(){
        super();
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.WHITE));
        active = false;
        mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(!active){
                    setBackground(Color.GREEN);
                    previousColor = Color.GREEN;
                    active = true;
                }
                else{
                    setBackground(Color.BLUE);
                    previousColor = Color.BLUE;
                    active = false;
                }
            }
        };
        addMouseListener(mouseListener);
        setPreferredSize(new Dimension(110, 0));

        setFocusPainted(false);
        setContentAreaFilled(true);

        setBackground(Color.BLUE);
    }

    public TopBarButton(Integer width, Color hover, Color def, Color clicked){
        super(hover);
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.WHITE));
        active = false;
        mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(!active){
                    setBackground(clicked);
                    previousColor = clicked;
                    active = true;
                }
                else{
                    setBackground(def);
                    previousColor = def;
                    active = false;
                }
            }
        };
        addMouseListener(mouseListener);
        setPreferredSize(new Dimension(width, 0));

        setFocusPainted(false);
        setContentAreaFilled(true);

        setBackground(def);
    }
}
