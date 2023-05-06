package boundary.panel.laporan;

import boundary.panel.laporan.subpanel.LaporanPane;
import boundary.widget.TabPanel;
import controller.fixedbill.FixedBillController;

public class LaporanPanel extends TabPanel {
    public LaporanPanel(FixedBillController controller) {
        super(new LaporanPane(controller));
    }
}
