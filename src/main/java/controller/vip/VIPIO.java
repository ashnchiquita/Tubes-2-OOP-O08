package controller.vip;

import model.Member;
import model.VIP;

import java.util.List;

public interface VIPIO {
    VIP getByID(int id);

    List<VIP> getAllVIP();

    public boolean insertVIP(VIP data);

    public boolean updateVIP(VIP newData);

    public boolean deleteVIP(int id);
}
