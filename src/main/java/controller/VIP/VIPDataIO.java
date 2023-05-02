package controller.VIP;

import java.util.List;

import model.VIP;

public interface VIPDataIO {
    VIP getByID(int id);

    List<VIP> getAllVIPs();

    boolean insertVIP(VIP data);

    boolean updateVIP(int id, VIP newData);

    boolean deleteVIP(int id);
}

