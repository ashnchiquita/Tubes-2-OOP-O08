package boundary.observer.panelflow;

import boundary.widget.FlowablePane;
import boundary.widget.FlowablePanel;

import javax.swing.*;

public class PanelFlowEvent {
    public static final String FLOW = "Flow";
    public static final String RETRACT = "Retract";
    public static final PanelFlowEvent retract(){
        return new PanelFlowEvent(RETRACT);
    }

    public String type = FLOW;
    public FlowablePane destination;
    public boolean traceable;
    public PanelFlowEvent(FlowablePane destination, boolean traceable) {
        this.type = FLOW;
        this.destination = destination;
        this.traceable = traceable;
    }
    public PanelFlowEvent(String type) {
        this.type = type;
    }
}
