package boundary.panel.member;

import boundary.panel.member.subpanel.DaftarMemberPanel;
import boundary.widget.FlowablePanel;

public class MemberPanel extends FlowablePanel {
    public MemberPanel(){
        super(new DaftarMemberPanel());
    }
}
