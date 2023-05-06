package boundary.observer.tab;


import java.util.ArrayList;

public class TabObserver {
    String name;
    String panelType;

    public TabObserver(String name, String panelType){
        this.name = name;
        this.panelType = panelType;
    }
    ArrayList<TabListener> listeners = new ArrayList<>();
    public void addListener(TabListener newListener){
        listeners.add(newListener);
    }
    public void newEvent(TabEvent e){
        if (e.type == TabEvent.CLOSE){
            for (TabListener listener : listeners){
                listener.closeTab(e, name);
            }
        }
        if (e.type == TabEvent.CLICK){
            for (TabListener listener : listeners){
                listener.clickTab(e, name, panelType);
            }
        }
    }
}
