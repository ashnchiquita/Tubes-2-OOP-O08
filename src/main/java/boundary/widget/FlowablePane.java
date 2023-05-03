package boundary.widget;

import boundary.observer.panelflow.PanelFlowObserver;

import javax.swing.*;

public class FlowablePane extends JPanel {
    protected PanelFlowObserver panelFlowObserver;
    public FlowablePane(){
        super();
        panelFlowObserver = new PanelFlowObserver();
    }
    public void assignFlow(PanelFlowObserver observer){
        panelFlowObserver = observer;
    }
}
