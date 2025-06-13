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
import java.io.*;


public class Dados {
    private static String[] tipoAssento = {"Plateia A", "Plateia B", "Frisa", "Camarote", "Balcão Nobre"};
    private static String caminhoImagemHome = "src/main/resources/PGHome.png";
    private static Utils util = new Utils();

    // Dados registrados da parte da Manhã
    public static ChartPanel dadosManha(int[] dadosManha){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int c = 0; c < 5; c++) {
            dataset.addValue(dadosManha[c], "Vendas", tipoAssento[c]);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Vendas na Parte da Manhã",
                "Tipo de Assento",
                "Quantidade",
                dataset
        );
        chart = design(chart);
        return new ChartPanel(chart);
    }


    // Dados registrados da parte da Tarde
    public static ChartPanel dadosTarde(int[] dadosTarde){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int c = 0; c < 5; c++) {
            dataset.addValue(dadosTarde[c], "Vendas", tipoAssento[c]);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Vendas na Parte da Tarde",
                "Tipo de Assento",
                "Quantidade",
                dataset
        );
        chart = design(chart);
        return new ChartPanel(chart);
    }


    // Dados registrados da parte da Noite
    public static ChartPanel dadosNoite(int[] dadosNoite){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int c = 0; c < 5; c++) {
            dataset.addValue(dadosNoite[c], "Vendas", tipoAssento[c]);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Vendas na Parte da Noite",
                "Tipo de Assento",
                "Quantidade",
                dataset
        );
        chart = design(chart);
        return new ChartPanel(chart);
    }


    // Dados registrados no Total
    public static ChartPanel dadosTotais(int[] dados){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int c = 0; c < 5; c++) {
            dataset.addValue(dados[c], "Vendas", tipoAssento[c]);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Relatório de Vendas Totais",
                "Tipo de Assento",
                "Quantidade",
                dataset
        );
        chart = design(chart);
        return new ChartPanel(chart);
    }


    // Formatação de Estilo/Design
    public static JFreeChart design(JFreeChart chart){
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 0, 255));
        renderer.setBarPainter(new StandardBarPainter());
        return chart;
    }

    // Coleta os dados do arquivo txt de a  cordo com o tipo de dado
    public static int[] coletarDados(String caminhoArquivo, String tipo) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = leitor.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] dadosColuna = linha.split(";");
                if (dadosColuna[0].equalsIgnoreCase(tipo)) {
                    int[] dadosFormatado = new int[dadosColuna.length - 1];
                    for (int i = 1; i < dadosColuna.length; i++) {
                        try {
                            dadosFormatado[i - 1] = Integer.parseInt(dadosColuna[i]);
                        } catch (NumberFormatException e) {
                            System.out.println("Erro ao converter valor para inteiro: " + dadosColuna[i]);
                            dadosFormatado[i - 1] = 0;
                        }
                    }
                    return dadosFormatado;
                }
            }
            System.out.println("Tipo \"" + tipo + "\" não encontrado no arquivo.");
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
