package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.itextpdf.text.*;
// import com.itextpdf.text.pdf.PdfPCell;
// import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
// import java.util.stream.Stream;

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
    @JsonProperty("date")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate date;
    @JsonProperty("time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime time;
    @JsonProperty("billing")
    private double billing;

    public void addBarang(Barang barang) {
        int pos = -1;

        for (int i = 0; i < keranjang.size(); i++) {
            if (keranjang.get(i).getId() == barang.getId()) {
                pos = i;
                break;
            }
        }

        if (pos != -1) {
            keranjang.get(pos).addJumlah(1);
        } else {
            barang.setJumlah(1);
            keranjang.add(barang);
        }

        billing += barang.getHargaJual();
    }

    public void removeBarang(Barang barang) {
        // ngurangin stok
        int pos = -1;

        for (int i = 0; i < keranjang.size(); i++) {
            if (keranjang.get(i).getId() == barang.getId()) {
                pos = i;
                break;
            }
        }

        if (pos != -1) {
            if (barang.getJumlah() < keranjang.get(pos).getJumlah()) {
                keranjang.get(pos).setJumlah(keranjang.get(pos).getJumlah() - barang.getJumlah());
                billing -= barang.getHargaJual() * barang.getJumlah();
            } else {
                keranjang.remove(pos);
                billing -= barang.getHargaJual() * keranjang.get(pos).getJumlah();
            }
        }
    }

    public void modifyBilling(double real) {
        billing += real;
    }

    public double calcTotalHargaJual() {
        double sum = 0.0;
        for (Barang b : keranjang) {
            sum += b.calcTotalHargaJual();
        }
        return sum;
    }

    public double calcTotalHargaBeli() {
        double sum = 0.0;
        for (Barang b : keranjang) {
            sum += b.calcTotalHargaBeli();
        }
        return sum;
    }

    public String formattedDateTime() {
        return (date != null && time != null ? date + " " + time : "-");
    }

    public void fixedBillPdf(Document document) throws DocumentException {
        Font subheading = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);

        Chunk tanggal = new Chunk("Tanggal, pukul     : " + formattedDateTime(), subheading);
        document.add(Chunk.NEWLINE);
        document.add(new Phrase(tanggal));

        Chunk billing = new Chunk("Total Bill               : " + getBilling(), subheading);
        document.add(Chunk.NEWLINE);
        document.add(new Phrase(billing));

        Chunk keranjang = new Chunk("Keranjang            : ", subheading);
        document.add(Chunk.NEWLINE);
        document.add(new Phrase(keranjang));

        for (Barang b : getKeranjang()) {
            Chunk barang = new Chunk(b.formattedStringList(), subheading);
            document.add(Chunk.NEWLINE);
            document.add(new Phrase(barang));
        }
    }

    public static void laporanByID(int id, List<FixedBill> temp, String filePath) {
        if (temp == null) {
            return;
        }

        List<FixedBill> filtered = temp.stream().filter(el -> el.getCust().getId() == id).collect(Collectors.toList());

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();

            Font heading = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Font med = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, BaseColor.BLACK);
            Font subheading = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
            Chunk title = new Chunk("Cashoria - Fixed Bill Report", heading);
            document.add(title);

            Chunk custTitle = new Chunk("Customer's ID       : " + id, med);
            document.add(Chunk.NEWLINE);
            document.add(new Phrase(custTitle));

            Chunk fbTitle = new Chunk("History Transaksi : ", med);
            document.add(Chunk.NEWLINE);
            document.add(new Phrase(fbTitle));

            Chunk line = new Chunk(
                    "____________________________________________________________________________________", subheading);
            document.add(Chunk.NEWLINE);
            document.add(new Phrase(line));

            for (FixedBill f : filtered) {
                f.fixedBillPdf(document);
                document.add(Chunk.NEWLINE);
                document.add(new Phrase(line));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void laporanAll(List<FixedBill> temp, String filePath) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(filePath)));

            document.open();

            Font heading = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Font med = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13, BaseColor.BLACK);
            Font subheading = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
            Chunk title = new Chunk("Cashoria - Laporan Penjualan", heading);
            document.add(title);

            Chunk fbTitle = new Chunk("History Penjualan: ", med);
            document.add(Chunk.NEWLINE);
            document.add(new Phrase(fbTitle));

            Chunk line = new Chunk(
                    "____________________________________________________________________________________", subheading);
            document.add(Chunk.NEWLINE);
            document.add(new Phrase(line));

            if (temp != null) {
                for (FixedBill f : temp) {
                    f.fixedBillPdf(document);
                    document.add(Chunk.NEWLINE);
                    document.add(new Phrase(line));
                }
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resetCount(int start) {
        count = start;
    }

    public static int checkCount() {
        return count;
    }

    public abstract static class FixedBillBuilder<C extends FixedBill, B extends FixedBill.FixedBillBuilder<C, B>> {
        private B id(int b) {
            this.id = ++count;
            return self();
        }

        public B id() {
            return id(0);
        }
    }
}
