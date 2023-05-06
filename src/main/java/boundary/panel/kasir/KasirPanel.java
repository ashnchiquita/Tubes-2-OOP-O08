package boundary.panel.kasir;

import boundary.panel.kasir.subpanel.PembelianPane;
import boundary.widget.TabPanel;
import controller.barang.BarangController;
import controller.fixedbill.FixedBillController;
import controller.member.MemberController;
import controller.vip.VIPController;

public class KasirPanel extends TabPanel {
    //TODO: integrate controller
    public KasirPanel(BarangController barangController, FixedBillController billController, MemberController memberController, VIPController vipController) {
        super(new PembelianPane(barangController, billController, memberController, vipController));
    }
}
