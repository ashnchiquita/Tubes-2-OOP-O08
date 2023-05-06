package boundary.panel.member;

import boundary.panel.member.subpanel.DaftarMemberPane;
import boundary.widget.TabPanel;
import controller.fixedbill.FixedBillController;
import controller.member.MemberController;
import controller.vip.VIPController;

public class MemberPanel extends TabPanel {
    public MemberPanel(MemberController memberController, VIPController vipController, FixedBillController fixedBillController){
        super(new DaftarMemberPane(memberController, vipController, fixedBillController));
    }
}
