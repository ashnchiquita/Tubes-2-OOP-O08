package boundary.observer.panelflow;

import java.util.ArrayList;

public class PanelFlowObserver {
    ArrayList<PanelFlowListener> listeners = new ArrayList<>();
    public void addListener(PanelFlowListener newListener) {
        listeners.add(newListener);
    }
    public void newEvent(PanelFlowEvent e) {
        for (PanelFlowListener listener : listeners) {
            if(e.type == PanelFlowEvent.RETRACT){
                listener.onRetract();
            }
            else if(e.type == PanelFlowEvent.FLOW){
                listener.onFlow(e);
                if(e.traceable){
                    listener.onTraceAbleFlow(e);
                }
                else if(!e.traceable){
                    listener.onUntraceAbleFlow(e);
                }
            }
        }
    }
}
