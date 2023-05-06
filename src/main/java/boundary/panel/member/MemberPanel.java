package boundary.panel.member;

import boundary.panel.member.subpanel.DaftarMemberPane;
import boundary.widget.TabPanel;
import controller.*;
import model.*;

public class MemberPanel extends TabPanel {
    public MemberPanel(GenericDataIO<Member> memberDataIO, GenericDataIO<VIP> VIPDataIO,
            GenericDataIO<FixedBill> fixedBillDataIO) {
        super(new DaftarMemberPane(memberDataIO, VIPDataIO, fixedBillDataIO));
    }
}
