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
    private static int count = 0;
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
    @JsonProperty("billing")
    private double billing;

    // TODO: gabisa contains
    public void addBarang(Barang barang) {
        if (keranjang.contains(barang)) {
            int pos = keranjang.indexOf(barang);
            keranjang.get(pos).addJumlah(barang.getJumlah());
        } else {
            keranjang.add(barang);
        }
        billing += barang.calcTotalHargaJual();
    }

    // TODO: gabisa contains
    public void removeBarang(Barang barang) {
        if (keranjang.contains(barang)) {
            int pos = keranjang.indexOf(barang);
            keranjang.get(pos).addJumlah(barang.getJumlah());
        } else {
            keranjang.add(barang);
        }
        billing -= barang.getHargaJual() * barang.getJumlah();
    }

    public void modifyBilling(double real) {
        billing += real;
    }

    public double calcTotalHargaJual() {
        double sum = 0.0;
        for (Barang b: keranjang) {
            sum += b.calcTotalHargaJual();
        }
        return sum;
    }

    public double calcTotalHargaBeli() {
        double sum = 0.0;
        for (Barang b: keranjang) {
            sum += b.calcTotalHargaBeli();
        }
        return sum;
    }

    public abstract static class FixedBillBuilder< C extends FixedBill, B extends FixedBill.FixedBillBuilder<C,B> > {
        private B id(int b) {
            this.id = ++count;
            return self();
        }

        public B id() {
            return id(0);
        }
    }
}
