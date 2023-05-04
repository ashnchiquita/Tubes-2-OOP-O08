package boundary.observer.tab;

import java.util.ArrayList;

public class TabObserver {
    String name;
    public TabObserver(String name){
        this.name = name;
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
    }
}
