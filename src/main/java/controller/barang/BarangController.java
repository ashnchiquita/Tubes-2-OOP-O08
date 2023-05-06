package controller.barang;

import lombok.Getter;
import lombok.Setter;
import model.Barang;

import java.util.List;

@Setter @Getter
public class BarangController {
    BarangIO dataIO;

    public Barang getByID(int id) { return dataIO.getByID(id); }

    public List<Barang> getAllBarang()  { return dataIO.getAllBarang(); }

    public boolean insertBarang(Barang data) { return dataIO.insertBarang(data); }

    public boolean updateBarang(Barang newData) { return dataIO.updateBarang(newData); }

    public boolean deleteBarang(int id) { return dataIO.deleteBarang(id); }

    // tambahin sesuai kebutuhan
}
