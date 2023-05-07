package boundary.panel.kasir;

import boundary.panel.kasir.subpanel.PembelianPane;
import boundary.widget.TabPanel;

import model.*;
import controller.*;

public class KasirPanel extends TabPanel {
    public KasirPanel(GenericDataIO<Barang> barangDataIO, GenericDataIO<FixedBill> fixedBillDataIO,
            GenericDataIO<Member> memberDataIO, GenericDataIO<VIP> VIPDataIO) {
        super(new PembelianPane(barangDataIO, fixedBillDataIO, memberDataIO, VIPDataIO));
    }
}
