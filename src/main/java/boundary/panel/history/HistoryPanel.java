package boundary.panel.history;

import boundary.panel.history.subpanel.HistoryPane;
import boundary.widget.TabPanel;
import controller.GenericDataIO;
import model.*;

public class HistoryPanel extends TabPanel {
    public HistoryPanel(GenericDataIO<FixedBill> fixedBillDataIO) {
        super(new HistoryPane(fixedBillDataIO));
    }
}
