package controller.barang;

import java.util.List;

import model.Barang;

public interface BarangIO {
    public Barang getByID(int id);

    public List<Barang> getAllBarang();
  
    public boolean insertBarang(Barang data);
  
    public boolean updateBarang(int id, Barang newData);
  
    public boolean deleteBarang(int id);
}