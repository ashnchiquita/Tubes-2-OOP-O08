package boundary.panel.inventaris;

import boundary.panel.inventaris.subpanel.DaftarBarangPanel;
import boundary.panel.member.subpanel.DaftarMemberPanel;
import boundary.widget.FlowablePanel;

public class InventarisPanel extends FlowablePanel {
    public InventarisPanel(){
        super(new DaftarBarangPanel());
    }
}
