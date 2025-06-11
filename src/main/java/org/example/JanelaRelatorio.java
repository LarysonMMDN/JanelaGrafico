package org.example;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;


public class JanelaRelatorio {
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

        JTabbedPane dados = abasRelatorio();
        dados.setBounds(0, 50, 583, 410);

        frame.add(painel);
        frame.add(dados);

        return frame;
    }


    public static JTabbedPane abasRelatorio() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JTabbedPane abasDados = new JTabbedPane();
        abasDados.setFocusable(false);

        // Coleta de dados
        int[] DBManha = Dados.coletarDados(caminhoArquivo, "Manhã");
        int[] DBTarde = Dados.coletarDados(caminhoArquivo, "Tarde");
        int[] DBNoite = Dados.coletarDados(caminhoArquivo, "Noite");
        int[] DBTotal = Dados.CalcularTotal(DBManha, DBTarde, DBNoite);

        // Cria os graficos
        ChartPanel manhaChart = Dados.dadosManha(DBManha);
        ChartPanel tardeChart = Dados.dadosTarde(DBTarde);
        ChartPanel noiteChart = Dados.dadosNoite(DBNoite);
        ChartPanel totalChart = Dados.dadosTotais(DBTotal);

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

}
