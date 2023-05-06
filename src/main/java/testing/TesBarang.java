package testing;

import controller.barang.BarangAdapterOBJ;
import controller.barang.BarangAdapterXML;
import controller.barang.BarangController;
import controller.barang.BarangAdapterJSON;

import model.Barang;

import java.util.Objects;

public class TesBarang {
    public static void main(String[] args) {
        BarangController dataIO = new BarangController();
        // dataIO.setDataIO(new BarangAdapterJSON("src/main/resources/data/tes_barang.json"));
        // dataIO.setDataIO(new BarangAdapterXML("src/main/resources/data/tes_barang.xml"));
        dataIO.setDataIO(new BarangAdapterOBJ("src/main/resources/data/tes_barang"));

        Barang b1 = Barang.builder().id().name("ayam").kategori("a").gambar("hai").hargaJual(2).hargaBeli(3).jumlah(4).build();
        Barang b2 = Barang.builder().id().name("ayam").kategori("a").gambar("hai").hargaJual(3).hargaBeli(4).jumlah(5).build();
        dataIO.getAllBarang();

//        dataIO.insertBarang(b1);
//        dataIO.insertBarang(b2);
//
//        b1.setHargaBeli(10);
//        dataIO.updateBarang(b1);
//
//        System.out.println(dataIO.getByID(1));
//
//        Objects.requireNonNull(dataIO.getAllBarang()).forEach(System.out::println);
//        System.out.println(dataIO.deleteBarang(1));
//        Objects.requireNonNull(dataIO.getAllBarang()).forEach(System.out::println);
    }
}
