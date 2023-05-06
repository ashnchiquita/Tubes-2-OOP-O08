package controller.vip;

import lombok.Getter;
import lombok.Setter;
import model.VIP;

import java.util.List;

@Setter @Getter
public class VIPController {
    VIPIO dataIO;

    public VIP getByID(int id) { return dataIO.getByID(id); }

    public List<VIP> getAllVIP()  { return dataIO.getAllVIP(); }

    public boolean insertVIP(VIP data) { return dataIO.insertVIP(data); }

    public boolean updateVIP(VIP newData) { return dataIO.updateVIP(newData); }

    public boolean deleteVIP(int id) { return dataIO.deleteVIP(id); }

    // tambahin sesuai kebutuhan
}
