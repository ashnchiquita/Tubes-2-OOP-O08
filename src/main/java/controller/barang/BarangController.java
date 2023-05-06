package controller.barang;

import lombok.Getter;
import lombok.Setter;
import model.Barang;
import controller.*;

import java.util.List;

@Setter
@Getter
public class BarangController implements GenericController<Barang> {
    BarangIO dataIO;

    public Barang getByID(int id) {
        return dataIO.getByID(id);
    }

    public List<Barang> getAll() {
        return dataIO.getAllBarang();
    }

    public boolean insert(Barang data) {
        return dataIO.insertBarang(data);
    }

    public boolean update(Barang newData) {
        return dataIO.updateBarang(newData);
    }

    public boolean delete(int id) {
        return dataIO.deleteBarang(id);
    }

    // tambahin sesuai kebutuhan
}
