package boundary.widget;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class PressedRoundedButtonUI extends BasicButtonUI {

    private final Color pressedBackgroundColor;

    public PressedRoundedButtonUI(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setBorderPainted(false);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();
        Color color = model.isPressed() ? pressedBackgroundColor : b.getBackground();
        g.setColor(color);
        g.fillRect(0, 0, b.getWidth(), b.getHeight());
        super.paint(g, c);
    }
}
