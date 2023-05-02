package controller.VIP;

import java.util.*;

import controller.VIP.VIPDataIO;
import lombok.Setter;
import model.VIP;

public class VIPController {
    @Setter
    VIPDataIO dataIO;

    public List<VIP> getAllVIP() {
        List<VIP> VIPList = new ArrayList<>();
        VIPList = dataIO.getAllVIPs();
        return VIPList;
    }

    public boolean addNewVIP(String name, String phone) {
        List<VIP> VIPList = dataIO.getAllVIPs();
        int id = VIPList.size() + 1;

        VIP newVIP = VIP.builder().id(id).name(name).phone(phone).point(0).transactions(1).build();
        return dataIO.insertVIP(newVIP);
    }

    public boolean addTransactions(int id) {
        VIP VIP = dataIO.getByID(id);
        if (VIP == null) {
            return false;
        }

        VIP.setTransactions(VIP.getTransactions() + 1);
        return dataIO.updateVIP(id, VIP);
    }

    public boolean addPoint(int id, int point) {
        VIP VIP = dataIO.getByID(id);
        if (VIP == null) {
            return false;
        }

        VIP.setPoint(VIP.getPoint() + 1);
        return dataIO.updateVIP(id, VIP);
    }

    public boolean updateVIPData(int id, String name, String phone) {
        VIP VIP = dataIO.getByID(id);
        if (VIP == null) {
            return false;
        }

        VIP.setName(name);
        VIP.setPhone(phone);
        return dataIO.updateVIP(id, VIP);
    }
}
