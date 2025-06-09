package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class Teste02 extends JFrame {

    public Teste02() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10, "Vendas", "Palco A");
        dataset.addValue(20, "Vendas", "Palco B");
        dataset.addValue(30, "Vendas", "Frisa");

        JFreeChart chart = ChartFactory.createBarChart(
                "Vendas na Parte da Manhã",
                "Tipo de Assento",
                "Quantidade",
                dataset
        );

        // Remover o gradiente e usar cor sólida
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 0, 255)); // azul sólido

        // Remover gradiente
        renderer.setBarPainter(new StandardBarPainter());


        ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Teste02 example = new Teste02();
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
