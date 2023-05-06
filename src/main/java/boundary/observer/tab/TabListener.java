package boundary.observer.tab;

public interface TabListener {
    void closeTab(TabEvent e, String tabName);
    void clickTab(TabEvent e, String tabName, String paneltype);
}
