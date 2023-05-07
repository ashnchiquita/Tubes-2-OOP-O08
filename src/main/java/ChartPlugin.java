import controller.MainController;
import controller.fixedbill.FixedBillAdapterXML;
import model.FixedBill;
import model.Member;
import model.VIP;
import org.knowm.xchart.XChartPanel;
import util.PieChartPlugin;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartPlugin {

    public static JPanel loadPanel(MainController mainController) {
        List<FixedBill> temp = mainController.getFixedBillDataIO().getAll();
        return new XChartPanel<>(new PieChartPlugin(temp).getChart());
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("XChart Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainController m = new MainController();
        m.setFixedBillDataIO(new FixedBillAdapterXML("src/main/resources/data/tes_fixed_bill.xml"));
        frame.add(loadPanel(m));

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(
                ChartPlugin::createAndShowGUI);
    }
}