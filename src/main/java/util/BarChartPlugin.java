package util;

import model.FixedBill;
import model.Member;
import model.VIP;

import java.util.List;

public class BarChartPlugin implements ExampleChart<CategoryChart> {
    List<FixedBill> f;
    public BarChartPlugin(List<FixedBill> f) {
        this.f = f;
    }

    @Override
    public CategoryChart getChart() {
        int custCount = 0;
        int memCount = 0;
        int VIPCount = 0;

        for (FixedBill fb: f) {
            if (fb.getCust() instanceof VIP) {
                VIPCount++;
            } else if (fb.getCust() instanceof Member) {
                memCount++;
            } else {
                custCount++;
            }
        }

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Persentase Transaksi berdasarkan Jenis Customer").xAxisTitle("Jenis").yAxisTitle("Jumlah").build();

        // Customize Chart
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);

        // Series
        chart.addSeries("Member", memCount);
        chart.addSeries("VIP", VIPCount);
        chart.addSeries("Customer", custCount);
        chart.setTitle("Persentase Transaksi berdasarkan Jenis Customer");

        return chart;
    }
}
