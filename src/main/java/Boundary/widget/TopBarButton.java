package boundary.widget;
import boundary.constants.Colors;

import javax.swing.*;
import java.awt.*;

public class TopBarButton extends HoverButton {
    private final Color defaultColor;
    private final Color activeColor;
    private Boolean active;

    public TopBarButton(){
        super();
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Colors.WHITE));
        active = false;
        defaultColor = new Color(75, 129, 238);
        activeColor = Colors.WHITE;
        setPreferredSize(new Dimension(180, 0));

        setFocusPainted(false);
        setContentAreaFilled(true);

        setBackground(defaultColor);
    }

    public boolean getStatus(){
        return active;
    };

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
