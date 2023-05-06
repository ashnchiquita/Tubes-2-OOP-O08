package boundary.panel.inventaris;

import boundary.panel.inventaris.subpanel.DaftarBarangPane;
import boundary.widget.TabPanel;
import model.*;
import controller.*;

public class InventarisPanel extends TabPanel {
    public InventarisPanel(GenericDataIO<Barang> barangDataIO) {
        super(new DaftarBarangPane(barangDataIO));
    }
}
