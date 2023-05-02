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
public class FixedBill implements Serializable {
    @JsonIgnore
    private static final long serialVersionUID = 3L;
    @JsonProperty("id")
    private int id;
    @JsonProperty("cust")
    private Customer cust;
    @JsonProperty("keranjang")
    private ArrayList<Barang> keranjang;
    @JsonProperty("date") @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate date;
    @JsonProperty("time") @JsonFormat(pattern="HH:mm:ss")
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
