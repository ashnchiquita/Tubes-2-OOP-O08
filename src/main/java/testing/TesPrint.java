package testing;

import controller.MainController;
import controller.fixedbill.FixedBillAdapterXML;
import model.FixedBill;

import java.util.List;

public class TesPrint {
    public static void main(String[] args) {
        MainController controller = new MainController();
        controller.setFixedBillDataIO(new FixedBillAdapterXML("src/main/resources/data/tes_fixed_bill.xml"));
        int id = 4;
        String filePath1 = "src/main/resources/data/laporan_penjualan.pdf";
        String filePath2 = "src/main/resources/data/laporan_per_id.pdf";
        List<FixedBill> temp = controller.getFixedBillDataIO().getAll();
        FixedBill.laporanAll(temp, filePath1);
        FixedBill.laporanByID(id, temp, filePath2);
    }

}
