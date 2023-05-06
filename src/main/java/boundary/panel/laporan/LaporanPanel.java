package boundary.panel.laporan;

import boundary.panel.laporan.subpanel.LaporanPane;
import boundary.widget.TabPanel;
import controller.*;
import model.*;

public class LaporanPanel extends TabPanel {
    public LaporanPanel(GenericDataIO<FixedBill> fixedBillDataIO) {
        super(new LaporanPane(fixedBillDataIO));
    }
}
