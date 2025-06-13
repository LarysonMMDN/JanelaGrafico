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


public class Dados {
    private static Utils util = new Utils();


    public static ChartPanel gerarGrafico(DefaultCategoryDataset dataset, String tipoGrafico, String titulo){
        JFreeChart chart = null;
        switch (tipoGrafico){
            case "Barra":
                chart = ChartFactory.createBarChart(
                        titulo,
                        "Tipo de Assento",
                        "Quantidade",
                        dataset
                );
                break;
            case "Linha":
                chart = ChartFactory.createLineChart(
                        titulo,
                        "Tipo de Assento",
                        "Quantidade",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true, // Legenda
                        true, // Tooltips (exibição de dicas)
                        false
                );
                break;
            case "Pizza":
                PieDataset pieDataset = gerarPieDataset(dataset);
                chart = ChartFactory.createPieChart(
                        titulo,
                        pieDataset,
                        true,
                        true,
                        false
                );
                break;

        }

        return new ChartPanel(design(chart, "Barra"));
    }

    public static DefaultCategoryDataset gerarDataSet(int[] dados, String[] nomeDado, String[] colunas){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int c = 0; c < colunas.length; c++) {
            dataset.addValue(dados[c], nomeDado[c], colunas[c]);
        }
        return dataset;
    }
    private static PieDataset gerarPieDataset(DefaultCategoryDataset dataset) {
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        // Adiciona os dados ao PieDataset
        for (int i = 0; i < dataset.getColumnCount(); i++) {
            String categoria = dataset.getColumnKey(i).toString();
            double valor = dataset.getValue(0, i).doubleValue();
            pieDataset.setValue(categoria, valor);
        }

        return pieDataset;
    }

    // Dados registrados da parte da Manhã
    public static ChartPanel dadosManha(int[] dadosManha, String tipoGrafico, String[] tipoAssento, String[] nomeDado){
        DefaultCategoryDataset dataset = gerarDataSet(dadosManha, nomeDado, tipoAssento);
        ChartPanel chart = gerarGrafico(dataset, tipoGrafico, "Vendas na Parte da Manhã");
        return chart;
    }


    // Dados registrados da parte da Tarde
    public static ChartPanel dadosTarde(int[] dadosManha, String tipoGrafico, String[] tipoAssento, String[] nomeDado){
        DefaultCategoryDataset dataset = gerarDataSet(dadosManha, nomeDado, tipoAssento);
        ChartPanel chart = gerarGrafico(dataset, tipoGrafico, "Vendas na Parte da Tarde");
        return chart;
    }


    // Dados registrados da parte da Noite
    public static ChartPanel dadosNoite(int[] dadosManha, String tipoGrafico, String[] tipoAssento, String[] nomeDado){
        DefaultCategoryDataset dataset = gerarDataSet(dadosManha, nomeDado, tipoAssento);
        ChartPanel chart = gerarGrafico(dataset, tipoGrafico, "Vendas na Parte da Noite");
        return chart;
    }


    // Dados registrados no Total
    public static ChartPanel dadosTotais(int[] dadosManha, String tipoGrafico, String[] tipoAssento, String[] nomeDado){
        DefaultCategoryDataset dataset = gerarDataSet(dadosManha, nomeDado, tipoAssento);
        ChartPanel chart = gerarGrafico(dataset, tipoGrafico, "Vendas na Parte da Tarde");
        return chart;
    }
    // Dados registrados no Total
//    public static ChartPanel dadosComplementares(int[] dados){
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        for (int c = 0; c < 5; c++) {
//            dataset.addValue(dados[c], "Vendas", tipoAssento[c]);
//        }
//
//        JFreeChart chart = ChartFactory.createBarChart(
//                "Relatório de Vendas Totais",
//                "Tipo de Assento",
//                "Quantidade",
//                dataset
//        );
//        return new ChartPanel(design(chart, "Barra"));
//    }


    // Formatação de Estilo/Design
    public static JFreeChart design(JFreeChart chart, String tipoGrafico) {
        switch (tipoGrafico) {
            case "Barra": {
                if (chart.getPlot() instanceof CategoryPlot) {
                    CategoryPlot plot = (CategoryPlot) chart.getPlot();
                    if (plot.getRenderer() instanceof BarRenderer) {
                        BarRenderer br = (BarRenderer) plot.getRenderer();
                        br.setSeriesPaint(0, new Color(79, 129, 189)); // Azul
                        br.setBarPainter(new BarRenderer().getBarPainter());
                        br.setDrawBarOutline(false);
                    }
                }
                break;
            }

            case "Linha": {
                if (chart.getPlot() instanceof CategoryPlot) {
                    CategoryPlot plot = (CategoryPlot) chart.getPlot();
                    if (plot.getRenderer() instanceof LineAndShapeRenderer) {
                        LineAndShapeRenderer lr = (LineAndShapeRenderer) plot.getRenderer();
                        lr.setSeriesPaint(0, new Color(192, 80, 77)); // Vermelho
                        lr.setSeriesShapesVisible(0, true);
                        lr.setSeriesStroke(0, new BasicStroke(2.0f));
                    }
                }
                break;
            }

            case "Pizza": {
                if (chart.getPlot() instanceof PiePlot) {
                    PiePlot pie = (PiePlot) chart.getPlot();
                    pie.setSectionPaint("VIP", new Color(155, 187, 89));
                    pie.setSectionPaint("Padrão", new Color(128, 100, 162));
                    pie.setSectionPaint("Econômico", new Color(75, 172, 198));
                    pie.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
                    pie.setCircular(true);
                    pie.setLabelGap(0.02);
                }
                break;
            }
        }

        return chart;
    }


    // Coleta os dados do arquivo txt de a  cordo com o tipo de dado
    public static int[] coletarDados(String caminhoArquivo, String tipo, String peca) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            // Enquanto houver linhas no arquivo
            while ((linha = leitor.readLine()) != null) {
                // Quando encontrar a peça procurada
                if (linha.equals(peca)) {
                    // Procurar a linha subsequente com os dados
                    while ((linha = leitor.readLine()) != null) {
                        String[] dadosColuna = linha.split(";");

                        // Verificar se a linha tem dados suficientes e o tipo é o esperado
                        if (dadosColuna.length > 1 && dadosColuna[0].equalsIgnoreCase(tipo) || tipo.equalsIgnoreCase("DadosTotais")) {
                            int[] dadosFormatado = new int[dadosColuna.length - 1];

                            // Preencher o array de inteiros com os valores convertidos
                            for (int i = 1; i < dadosColuna.length; i++) {
                                try {
                                    dadosFormatado[i - 1] = Integer.parseInt(dadosColuna[i]);
                                } catch (NumberFormatException e) {
                                    System.out.println("Erro ao converter valor para inteiro: " + dadosColuna[i]);
                                    dadosFormatado[i - 1] = 0; // Valor padrão em caso de erro
                                }
                            }
                            return dadosFormatado; // Retorna os dados encontrados
                        }
                    }
                    // Se o tipo não for encontrado após passar por todas as linhas, retorna null
                    System.out.println("Tipo \"" + tipo + "\" não encontrado para a peça \"" + peca + "\".");
                    return null;
                }
            }

            System.out.println("Peça \"" + peca + "\" não encontrada no arquivo.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return null;
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
