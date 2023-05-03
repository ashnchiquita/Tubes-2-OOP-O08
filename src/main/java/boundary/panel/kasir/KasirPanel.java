package boundary.panel.kasir;

import boundary.observer.panelflow.PanelFlowEvent;
import boundary.panel.kasir.subpanel.PembelianPanel;
import boundary.widget.FlowablePanel;

import javax.swing.*;

public class KasirPanel extends FlowablePanel {
    //TODO: integrate controller
    public KasirPanel() {
        super(new PembelianPanel(0));
    }
}
