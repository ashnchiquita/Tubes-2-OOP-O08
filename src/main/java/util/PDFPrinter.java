package util;

import controller.GenericDataIO;
import model.FixedBill;

import javax.swing.*;

public class PDFPrinter implements Runnable {
    Integer type, id;
    String path;
    GenericDataIO<FixedBill> fixedBillDataIO;

    public PDFPrinter(Integer type, String path, Integer id, GenericDataIO<FixedBill> fixedBillDataIO) {
        this.type = type;
        this.path = path;
        this.id = id;
        this.fixedBillDataIO = fixedBillDataIO;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            if (type == 1) {
                FixedBill.laporanAll(fixedBillDataIO.getAll(), path + ".pdf");
            } else if (type == 2) {
                FixedBill.laporanByID(id, fixedBillDataIO.getAll(), path + ".pdf");
            } else if (type == 3) {
                FixedBill.laporanByBillID(id, fixedBillDataIO.getAll(), path + ".pdf");
            }
            JOptionPane.showMessageDialog(null, "Pdf printing finished successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Printing failed\n" + e.toString());
        }
    }
}
