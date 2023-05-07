package model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Barang implements Serializable {
    private static final long serialVersionUID = 2L;
    private static int count = 0;
    private int id, jumlah;
    private String name, kategori, gambar;
    private double hargaJual, hargaBeli;

    public void addJumlah(int jumlah) {
        this.jumlah += jumlah;
    }

    public double calcTotalHargaJual() {
        return this.hargaJual * this.jumlah;
    }

    public double calcTotalHargaBeli() {
        return this.hargaJual * this.jumlah;
    }

    public abstract static class BarangBuilder< C extends Barang, B extends Barang.BarangBuilder<C,B> > {
        private B id(int b) {
            this.id = ++count;
            return self();
        }

        public B id() {
            return id(0);
        }
    }
}