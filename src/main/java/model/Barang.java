package model;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
public class Barang implements Serializable{
    private final int id;
    private String name, kategori, gambar;
    private int hargaJual, hargaBeli, jumlah;
}
