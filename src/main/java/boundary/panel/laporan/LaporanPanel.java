package boundary.panel.laporan;

import boundary.panel.kasir.subpanel.PembelianPane;
import boundary.panel.laporan.subpanel.LaporanPane;
import boundary.widget.TabPanel;

public class LaporanPanel extends TabPanel {
    public LaporanPanel() {
        super(new LaporanPane());
    }
}
