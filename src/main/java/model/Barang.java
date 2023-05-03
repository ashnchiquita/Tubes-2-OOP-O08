package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Barang implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 3L;
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("kategori")
    private String kategori;
    @JsonProperty("hargaJual")
    private Integer hargaJual;
    @JsonProperty("hargaBeli")
    private Integer hargaBeli;
    @JsonProperty("jumlah")
    private Integer jumlah;
    @JsonProperty("gambar")
    private String gambar;

    public void addJumlah(int jumlah) {
        this.jumlah += jumlah;
    }

}