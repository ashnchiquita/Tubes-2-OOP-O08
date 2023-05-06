package boundary.panel.inventaris;

import boundary.panel.inventaris.subpanel.DaftarBarangPane;
import boundary.widget.TabPanel;
import controller.barang.BarangController;

public class InventarisPanel extends TabPanel {
    public InventarisPanel(BarangController controller){
        super(new DaftarBarangPane(controller));
    }
}
