package boundary.widget;

import boundary.observer.panelflow.PanelFlowEvent;
import boundary.observer.panelflow.PanelFlowListener;
import boundary.observer.panelflow.PanelFlowObserver;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class FlowablePanel extends JPanel implements PanelFlowListener {
    protected Stack<FlowablePane> panelTrace = new Stack<>();
    protected PanelFlowObserver observer = new PanelFlowObserver();
    protected FlowablePane currentPanel;
    public FlowablePanel(FlowablePane startPanel) {
        super(new GridLayout());

        currentPanel = startPanel;
        currentPanel.assignFlow(observer);
        add(currentPanel);

        setBorder(BorderFactory.createEmptyBorder());
        observer.addListener(this);
    }
    private void switchPanel(FlowablePane panel){
        removeAll();
        revalidate();
        add(panel);
        repaint();
    }
    public void onFlow(PanelFlowEvent e) {
        e.destination.assignFlow(observer);
        switchPanel(e.destination);
    }
    public void onUntraceAbleFlow(PanelFlowEvent e) {
        panelTrace.clear();
    }
    public void onTraceAbleFlow(PanelFlowEvent e) {
        panelTrace.push(currentPanel);
        currentPanel = e.destination;
    }
    public void onRetract() {
        if(!panelTrace.isEmpty()){
            currentPanel = panelTrace.pop();
            switchPanel(currentPanel);
        }
    }
}
