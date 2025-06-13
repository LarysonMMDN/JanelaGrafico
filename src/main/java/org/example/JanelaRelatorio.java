package org.example;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;


public class JanelaRelatorio {
    private static String[] tipoAssento = {"Plateia A", "Plateia B", "Frisa", "Camarote", "Balcão Nobre"};
    private static String[] pecas = {"Manhã", "Tarde", "Noite"};
    public static String[] nomeDado;
    private static String caminhoArquivo = "src/main/java/org/example/Dados.txt";
    private static String caminhoImagem = "src/main/resources/TituloLogoMarca.png";
    private static String caminhoIco = "src/main/resources/Miguire.png";

    public static JFrame paginaRelatorio() {
        JFrame frame = new JFrame();
        frame.setTitle("Relatorio");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(caminhoIco);
        frame.setIconImage(icon.getImage());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(0, 0, 600, 50);
        painel.setBackground(new Color(238, 238, 238));
        ImageIcon logo = new ImageIcon(caminhoImagem);
        JLabel logoMarca = new JLabel(logo);
        logoMarca.setBounds(150, 0,300, 50);
        painel.add(logoMarca);

//        JTabbedPane dados = abasRelatorio();
//        dados.setBounds(0, 50, 583, 410);
//        dados.repaint();
//
//        frame.add(painel);
//        frame.add(dados);

        return frame;
    }


    public static JTabbedPane abasRelatorio(String peca) {

        JTabbedPane abasDados = new JTabbedPane();
        abasDados.setBounds(0, 0, 700, 550);
        abasDados.setFocusable(false);

        // Coleta de dados
        int[] DBManha = Dados.coletarDados(caminhoArquivo, "Manhã", peca);
        int[] DBTarde = Dados.coletarDados(caminhoArquivo, "Tarde", peca);
        int[] DBNoite = Dados.coletarDados(caminhoArquivo, "Noite", peca);
        int[] DBTotal = Dados.CalcularTotal(DBManha, DBTarde, DBNoite);

        // Cria os graficos
        nomeDado = new String[] {"Vendas", "Vendas", "Vendas", "Vendas", "Vendas"};
        ChartPanel manhaChart = Dados.dadosManha(DBManha, "Barra", tipoAssento, nomeDado);
        ChartPanel tardeChart = Dados.dadosTarde(DBTarde, "Barra", tipoAssento, nomeDado);
        ChartPanel noiteChart = Dados.dadosNoite(DBNoite, "Barra", tipoAssento, nomeDado);
        ChartPanel totalChart = Dados.dadosTotais(DBTotal, "Barra", tipoAssento, nomeDado);

        // Adiciona o no painel: Grafico, botão e dados
        JPanel manhaPanel = Dados.mostrarLPA(manhaChart, DBManha);
        JPanel tardePanel = Dados.mostrarLPA(tardeChart, DBTarde);
        JPanel noitePanel = Dados.mostrarLPA(noiteChart, DBNoite);
        JPanel totalPanel = Dados.mostrarLPA(totalChart, DBTotal);


        // Adicona as abas
        abasDados.addTab("Manhã", manhaPanel);
        abasDados.addTab("Tarde", tardePanel);
        abasDados.addTab("Noite", noitePanel);
        abasDados.addTab("Total", totalPanel);

        return abasDados;
    }
    public static JTabbedPane abasRelatorio() {

        JTabbedPane abasDados = new JTabbedPane();
        abasDados.setBounds(0, 0, 700, 550);
        abasDados.setFocusable(false);

        // Coleta de dados
        int[] DBManha = {14,19,25};
        int[] DBTarde = {10,28,17};
        int[] DBNoite = {16,21,18};
        int[] DBTotal = Dados.CalcularDadosTotal(DBManha, DBTarde, DBNoite);

        // Cria os graficos
        nomeDado = new String[] {"Peça A", "Peça B", "Peça C"};
        ChartPanel Ingresso = Dados.dadosManha(DBManha, "Linha", pecas, nomeDado);
        ChartPanel Turno = Dados.dadosTarde(DBTarde, "Linha", pecas, nomeDado);
        ChartPanel Lucro = Dados.dadosNoite(DBNoite, "Barra", pecas, nomeDado);
        ChartPanel dadosTotais = Dados.dadosTotais(DBTotal, "Barra", pecas, nomeDado);

        // Adiciona o no painel: Grafico, botão e dados
        JPanel manhaPanel = Dados.mostrarLPA(Ingresso, DBManha);
        JPanel tardePanel = Dados.mostrarLPA(Turno, DBTarde);
        JPanel noitePanel = Dados.mostrarLPA(Lucro, DBNoite);
        JPanel totalPanel = Dados.mostrarLPA(dadosTotais, DBTotal);

        // Adicona as abas
        abasDados.addTab("Ingresso", manhaPanel);
        abasDados.addTab("Turno", tardePanel);
        abasDados.addTab("Lucro", noitePanel);
        abasDados.addTab("Dados", totalPanel);

        return abasDados;
    }

}
