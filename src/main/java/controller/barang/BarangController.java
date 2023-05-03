package controller.barang;

import lombok.Setter;
import model.Barang;

import java.util.List;

@Setter
public class BarangController {
    BarangIO dataIO;

    public List<Barang> getAllBarang() {
        return dataIO.getAllBarang();
    }

    public void showByID(int id) {
        System.out.println(dataIO.getByID(id));
      
    }

    //ngeprint smua barang
    public void showAllBarang() {
        System.out.println(getAllBarang());
    }

    // tambah jenis/nama barang baru
    public boolean addNewBarang(String name, String kategori) {
        List<Barang> barangList = dataIO.getAllBarang();
        int id = barangList.size() + 1;
    
        Barang newBarang = Barang.builder().id(id).name(name).kategori(kategori).hargaJual(0).hargaBeli(1).jumlah(0).gambar("").build();
        return dataIO.insertBarang(newBarang);
    }

    // tambah jumahnya
    public boolean addMoreBarang(int id) {
        Barang barang = dataIO.getByID(id);
        if (barang == null) {
          return false;
        }
    
        barang.setJumlah(barang.getJumlah() + 1);
        return dataIO.updateBarang(id, barang);
    }

    // kurangin jumlahnya
    public boolean kurangMoreBarang(int id) {
        Barang barang = dataIO.getByID(id);
        if (barang == null) {
          return false;
        }
    
        barang.setJumlah(barang.getJumlah() - 1);
        return dataIO.updateBarang(id, barang);
    }

    // ubah data suatu barang
    public boolean updateBarangData(int id, String name, String kategori) {
        Barang barang  = dataIO.getByID(id);
        if (barang == null) {
          return false;
        }
    
        barang.setName(name);
        barang.setKategori(kategori);
        return dataIO.updateBarang(id, barang);
    }

    public void deleteBarang(int id) {
        System.out.println(dataIO.deleteBarang(id));
    }
    
    // tambahin sesuai kebutuhan
}
