package controller.barang;

import model.Barang;
import model.Member;

import java.util.List;

public interface BarangIO {
    public Barang getByID(int id);

    public List<Barang> getAllBarang();

    public boolean insertBarang(Barang data);

    public boolean updateBarang(Barang newData);

    public boolean deleteBarang(int id);
}
