package boundary.panel.kasir;

import boundary.panel.kasir.subpanel.PembelianPane;
import boundary.widget.TabPanel;

public class KasirPanel extends TabPanel {
    //TODO: integrate controller
    public KasirPanel() {
        super(new PembelianPane(0));
    }
}
