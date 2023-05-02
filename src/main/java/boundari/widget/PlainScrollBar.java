package boundari.widget;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class PlainScrollBar extends BasicScrollBarUI {
    private Color bgColor;
    private Color fgColor;
    public PlainScrollBar(Color bgcolor, Color fgcolor){
        super();
        bgColor = bgcolor;
        fgColor = fgcolor;
    }

    private class NullButton extends JButton {
        public NullButton() {
            setBorder(BorderFactory.createEmptyBorder());
        }

        @Override
        public void paint(Graphics grphcs) {
        }
    }

    @Override
    protected JButton createIncreaseButton(int i) {
        return new NullButton();
    }

    @Override
    protected JButton createDecreaseButton(int i) {
        return new NullButton();
    }
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Integer orientation = scrollbar.getOrientation();
        Integer size;
        Integer x;
        Integer y;
        Integer width;
        Integer height;
        if (orientation == JScrollBar.VERTICAL){
            size = trackBounds.width;
            x = trackBounds.x + ((trackBounds.width - size)/2);
            y = trackBounds.y;
            width = size;
            height = trackBounds.height;
        } else{
            size = trackBounds.height;
            x = 0;
            y = trackBounds.y + ((trackBounds.height - size)/2);
            width = trackBounds.width;
            height = size;
        }
        graphics.setColor(bgColor);
        graphics.fillRect(x, y, width, height);
    }
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Integer orientation = scrollbar.getOrientation();
        Integer x = trackBounds.x;
        Integer y = trackBounds.y;
        Integer width = trackBounds.width - 8;
        Integer height = trackBounds.height;
        if (orientation == JScrollBar.VERTICAL){
            y += 8;
            height -= 16;
        } else{
            x += 8;
            width -= 16;
        }
        graphics.setColor(fgColor);
        graphics.fillRoundRect(x, y, width, height, 10, 10);
    }
}
