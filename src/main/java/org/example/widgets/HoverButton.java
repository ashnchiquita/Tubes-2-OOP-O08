package org.example.widgets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class HoverButton extends JButton {

    private MouseAdapter mouseListener;
    protected Color previousColor;
    protected Color hoverColor;

    public  HoverButton(){
        setRolloverEnabled(false);

        hoverColor = Color.cyan;
        mouseListener = new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){
                previousColor = getBackground();
                setBackground(hoverColor);
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt){
                setBackground(previousColor);
                repaint();
            }
        };
        addMouseListener(mouseListener);
    }

    public HoverButton(Color hover){
        setRolloverEnabled(false);

        hoverColor = hover;

        mouseListener = new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt){
                previousColor = getBackground();
                setBackground(hoverColor);
                repaint();
            }

            public void mouseExited(java.awt.event.MouseEvent evt){
                setBackground(previousColor);
                repaint();
            }
        };
        addMouseListener(mouseListener);
    }
}
