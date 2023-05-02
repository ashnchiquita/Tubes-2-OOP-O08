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
    private int id;
    private String name, kategori, gambar;
    private int hargaJual, hargaBeli, jumlah;

    public void addJumlah(int jumlah) {
        this.jumlah += jumlah;
    }
}