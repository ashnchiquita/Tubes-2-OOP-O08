package member;

import java.util.ArrayList;
import java.util.List;

public class ListMember {
    // asumsi nama & no telp gakan bubah -> final
    private final List<Member> members = new ArrayList<>();
    private final List<Vip> vips = new ArrayList<>();

    public void addMember(Member member) {
        members.add(member);
    }

    public void addVip(Vip vip) {
        vips.add(vip);
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Vip> getVIPs() {
        return vips;
    }

    public List<Object> getList() {
        List<Object> list = new ArrayList<>();
        list.addAll(members);
        list.addAll(vips);
        return list;
    }
    
    public void printMemberList() {
        System.out.println("=== List of Members ===");
        for (Member member : members) {
            System.out.println(member.getNama() + " - " + member.getNoTelp());
        }
    
        System.out.println("=== List of VIPs ===");
        for (Vip vip : vips) {
            System.out.println(vip.getNama() + " - " + vip.getNoTelp());
        }
    }
    
}