package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
import java.io.*;

enum TipoGrafico {
    BARRA, LINHA, PIZZA
}

public class Dados {
    private static Utils util = new Utils();

    private static JFreeChart criarGrafico(DefaultCategoryDataset dataset, TipoGrafico tipo, String titulo) {
        return switch (tipo) {
            case BARRA -> ChartFactory.createBarChart(titulo, "Tipo de Assento", "Quantidade", dataset);
            case LINHA -> ChartFactory.createLineChart(titulo, "Tipo de Assento", "Quantidade", dataset,
                    PlotOrientation.VERTICAL, true, true, false);
            case PIZZA -> {
                PieDataset pieDataset = transformarParaPieDataset(dataset);
                yield ChartFactory.createPieChart(titulo, pieDataset, true, true, false);
            }
        };
    }

    private static DefaultCategoryDataset criarDataset(int[] valores, String[] series, String[] categorias) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < categorias.length; i++) {
            dataset.addValue(valores[i], series[i], categorias[i]);
        }
        return dataset;
    }
    private static DefaultCategoryDataset criarDataset(int[][] valores, String[] series, String[] categorias) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < categorias.length; i++) {
            for (int j = 0; j < valores[i].length; j++) {
                dataset.addValue(valores[i][j], series[i], categorias[j]);
            }
        }
        return dataset;
    }

    private static PieDataset transformarParaPieDataset(DefaultCategoryDataset dataset) {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for (int i = 0; i < dataset.getColumnCount(); i++) {
            Comparable<?> coluna = dataset.getColumnKey(i);
            Number valor = dataset.getValue(0, i);
            pieDataset.setValue(coluna, valor);
        }
        return pieDataset;
    }

    // Dados registrados da parte da Manhã
    public static ChartPanel gerarGraficoVendas(String titulo, int[] dados, TipoGrafico tipo, String[] tiposAssento, String[] rotulos) {
        var dataset = criarDataset(dados, rotulos, tiposAssento);
        var chart = criarGrafico(dataset, tipo, titulo);
        aplicarEstilo(chart, tipo, rotulos.length, tiposAssento);
        return new ChartPanel(chart);
    }

    public static ChartPanel gerarGraficoVendas(String titulo, int[][] dados, TipoGrafico tipo, String[] tiposAssento, String[] rotulos) {
        var dataset = criarDataset(dados, rotulos, tiposAssento);
        var chart = criarGrafico(dataset, tipo, titulo);
        aplicarEstilo(chart, tipo, rotulos.length, tiposAssento);
        return new ChartPanel(chart);
    }


    // Formatação de Estilo/Design
    private static void aplicarEstilo(JFreeChart chart, TipoGrafico tipo, int quantidade, String[] tiposAssento) {
        int[][] cores = {{79, 129, 189}, {192, 80, 77}, {128, 100, 162}, {155, 187, 89}};
        switch (tipo) {
            case BARRA -> {
                var plot = (CategoryPlot) chart.getPlot();
                var renderer = (BarRenderer) plot.getRenderer();
                for (int i = 0; i<quantidade; i++){
                    renderer.setSeriesPaint(0, new Color(cores[i][0], cores[i][1], cores[i][2]));
                }
                renderer.setDrawBarOutline(false);
            }
            case LINHA -> {
                var plot = (CategoryPlot) chart.getPlot();
                var renderer = (LineAndShapeRenderer) plot.getRenderer();
                for (int i = 0; i<quantidade; i++){
                    renderer.setSeriesPaint(i, new Color(cores[i][0], cores[i][1], cores[i][2]));
                    renderer.setSeriesStroke(i, new BasicStroke(2.0f));
                    renderer.setSeriesShapesVisible(i, true);
                }
            }
            case PIZZA -> {
                var pie = (PiePlot) chart.getPlot();
                pie.setSectionPaint("PEÇA A", new Color(79, 129, 189));
                pie.setSectionPaint("PEÇA B", new Color(192, 80, 77));
                pie.setSectionPaint("PEÇA C", new Color(155, 187, 89));

                pie.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
                pie.setCircular(true);
                pie.setLabelGap(0.02);
            }
        }
    }





    public static int[] CalcularTotal(int[] manha, int[] tarde, int[] noite){
        int[] total = new int[5];
        for (int c = 0; c < 5; c++) {
            total[c] = manha[c] + tarde[c] + noite[c];
        }
        return total;
    }
    public static int[] CalcularDadosTotal(int[] manha, int[] tarde, int[] noite){
        int[] total = new int[3];
        for (int c = 0; c < 3; c++) {
            total[c] = manha[c] + tarde[c] + noite[c];
        }
        return total;
    }


    public static JPanel mostrarLPA(ChartPanel chart, int[] dados) {
        JPanel painel = new JPanel();
        painel.setLayout(null);

        chart.setBounds(0, 0, 700, 400);
        painel.add(chart);

        int[] posicaoBotao = {500, 410, 200, 50};
        JButton botaoLucro = util.criarBotao("Lucro por Assento", posicaoBotao, dados);
        painel.add(botaoLucro);

        return painel;
    }


}
