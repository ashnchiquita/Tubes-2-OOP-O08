package boundary.panel.member;

import boundary.panel.member.subpanel.DaftarMemberPane;
import boundary.widget.TabPanel;

public class MemberPanel extends TabPanel {
    public MemberPanel(){
        super(new DaftarMemberPane());
    }
}
