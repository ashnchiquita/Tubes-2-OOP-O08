package boundary.observer.panelflow;

public interface PanelFlowListener {
    void onFlow(PanelFlowEvent e);
    void onTraceAbleFlow(PanelFlowEvent e);
    void onUntraceAbleFlow(PanelFlowEvent e);
    void onRetract();
}
