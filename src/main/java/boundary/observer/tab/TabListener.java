package boundary.observer.tab;

import boundary.enums.PanelEnum;

public interface TabListener {
    void closeTab(TabEvent e, String tabName);
    void clickTab(TabEvent e, String tabName, PanelEnum paneltype);
}
