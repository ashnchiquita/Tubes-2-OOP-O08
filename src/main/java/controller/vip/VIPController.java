package controller.vip;

import lombok.Getter;
import lombok.Setter;
import model.VIP;
import controller.*;

import java.util.List;

@Setter
@Getter
public class VIPController implements GenericController<VIP> {
    VIPIO dataIO;

    public VIP getByID(int id) {
        return dataIO.getByID(id);
    }

    public List<VIP> getAll() {
        return dataIO.getAllVIP();
    }

    public boolean insert(VIP data) {
        return dataIO.insertVIP(data);
    }

    public boolean update(VIP newData) {
        return dataIO.updateVIP(newData);
    }

    public boolean delete(int id) {
        return dataIO.deleteVIP(id);
    }

    // tambahin sesuai kebutuhan
}
