package boundary.panel.inventaris;

import boundary.panel.inventaris.subpanel.DaftarBarangPane;
import boundary.widget.TabPanel;

public class InventarisPanel extends TabPanel {
    public InventarisPanel(){
        super(new DaftarBarangPane());
    }
}
