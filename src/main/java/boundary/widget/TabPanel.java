package boundary.widget;

import boundary.observer.panelflow.PanelFlowEvent;
import boundary.observer.tab.TabObserver;

public class TabPanel extends FlowablePanel{
    protected TabObserver tabObserver;
    public TabPanel() {
        super();
    }
    public TabPanel(TabPane startPanel) {
        super(startPanel);
    }
    public TabPanel(TabPane startPanel, TabObserver observer) {
        super(startPanel);
        tabObserver = observer;
        startPanel.assignTabObserver(observer);
    }
    public void assignTab(TopBarTab tab){
        tabObserver = tab.getObserver();
        if(currentPanel instanceof TabPane){
            ((TabPane) currentPanel).assignTabObserver(tab.getObserver());
        }
    }

    @Override
    public void onFlow(PanelFlowEvent e){
        super.onFlow(e);
        if(e.destination instanceof TabPane){
            ((TabPane) e.destination).assignTabObserver(tabObserver);
        }
    }
}
