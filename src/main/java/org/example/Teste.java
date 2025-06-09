package org.example;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Teste {

    /**
     * Gera um gráfico de barras com imagem de fundo e personalização de cores.
     *
     * @return ChartPanel contendo o gráfico de barras.
     */
    public static ChartPanel dados01() {
        // Criar dataset de vendas
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(11, "Vendas", "Janeiro");
        dataset.addValue(30, "Vendas", "Fevereiro");
        dataset.addValue(12, "ingressos", "Março");

        // Criar gráfico de barras
        JFreeChart chart = ChartFactory.createBarChart(
                "Vendas Mensais",  // Título
                "Mês",             // Eixo X
                "Quantidade",      // Eixo Y
                dataset            // Dados
        );

        // Fundo da janela do gráfico
        chart.setBackgroundPaint(Color.BLUE);

        // Borda da legenda
        chart.getLegend().setFrame(new BlockBorder(Color.BLACK));

        // Customização do gráfico
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        try {
            // Carregar imagem de fundo
            Image bgImage = ImageIO.read(new File("src/main/resources/Teatro Maguire.png"));
            plot.setBackgroundImage(bgImage);
            plot.setBackgroundImageAlpha(0.3f); // Transparência
        } catch (IOException e) {
            System.err.println("Erro ao carregar imagem de fundo: " + e.getMessage());
        }

        // Definir cor da série "Vendas"
        renderer.setSeriesPaint(0, new Color(200, 0, 100));
        plot.setRenderer(renderer);

        // Criar e configurar o painel do gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300, 300)); // Tamanho do gráfico
        return chartPanel;
    }

    /**
     * Gera um gráfico de linhas com duas séries e cores personalizadas.
     *
     * @return ChartPanel contendo o gráfico de linhas.
     */
    public static ChartPanel dados02() {
        // Criar dataset de vendas
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10, "Série A", "Jan");
        dataset.addValue(15, "Série A", "Fev");
        dataset.addValue(13, "Série A", "Mar");

        dataset.addValue(8, "Série B", "Jan");
        dataset.addValue(12, "Série B", "Fev");
        dataset.addValue(9, "Série B", "Mar");

        // Criar gráfico de linhas
        JFreeChart chart = ChartFactory.createLineChart(
                "Vendas por mês",  // Título
                "Mês",             // Eixo X
                "Valor",           // Eixo Y
                dataset            // Dados
        );

        // Customização do gráfico
        CategoryPlot plot = chart.getCategoryPlot();
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();

        // Definir cores das séries
        renderer.setSeriesPaint(0, Color.RED);  // Série A
        renderer.setSeriesPaint(1, Color.BLUE); // Série B

        plot.setRenderer(renderer);

        // Criar e configurar o painel do gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300, 300)); // Tamanho do gráfico
        return chartPanel;
    }
}
