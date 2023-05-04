package boundary.widget;

import boundary.observer.tab.TabObserver;

public class TabPane extends FlowablePane{
    protected TabObserver tabObserver;
    public TabPane() {
        super();
    }
    public void assignTabObserver(TabObserver observer){
        tabObserver = observer;
    }

}
