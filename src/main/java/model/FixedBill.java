package model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
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
public class FixedBill implements Serializable {
    @JsonProperty("id")
    private int id;
    @JsonProperty("cust")
    private Customer cust;
    @JsonProperty("keranjang")
    private ArrayList<Barang> keranjang;
    @JsonProperty("date")
    private LocalDate date;
    @JsonProperty("time")
    private LocalTime time;

    public void addBarang(Barang barang) {
        if (keranjang.contains(barang)) {
            int pos = keranjang.indexOf(barang);
            keranjang.get(pos).addJumlah(barang.getJumlah());
        } else {
            keranjang.add(barang);
        }
    }
}
