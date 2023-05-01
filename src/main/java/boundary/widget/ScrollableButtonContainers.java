package boundary.widget;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ScrollableButtonContainers extends JScrollPane {
    protected Map<String, JComponent> components = new HashMap<>();
    protected Integer buttonCount = 0;
    protected JPanel contentPanel = new JPanel(null);
    public JComponent addComponent(JComponent addition, String name){
        if(components.containsKey(name)){
            throw new IllegalArgumentException("Name already exists");
        }
        components.put(name, addition);
        contentPanel.add(addition);
        return addition;
    }
    public JComponent addButton(JButton addition, String name){
        JComponent retval = addComponent(addition, name);
        buttonCount++;
        return retval;
    }
}
