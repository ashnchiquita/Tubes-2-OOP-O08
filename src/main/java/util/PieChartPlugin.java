package util;

import model.FixedBill;
import model.Member;
import model.VIP;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.demo.charts.ExampleChart;

import java.awt.*;
import java.util.List;

public class PieChartPlugin implements ExampleChart<PieChart> {
    List<FixedBill> f;

    public PieChartPlugin(List<FixedBill> f) {
        this.f = f;
    }
    @Override
    public PieChart getChart() {
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
        PieChart chart = new PieChartBuilder().width(800).height(600).title(getClass().getSimpleName()).build();

        // Customize Chart
        Color[] sliceColors = new Color[] { new Color(149, 172, 255), new Color(74, 104, 222), new Color(36, 60, 148)};
        chart.getStyler().setSeriesColors(sliceColors);

        chart.addSeries("Member", memCount);
        chart.addSeries("VIP", VIPCount);
        chart.addSeries("Customer", custCount);
        chart.setTitle("Persentase Transaksi berdasarkan Jenis Customer");
        return chart;
    }
}