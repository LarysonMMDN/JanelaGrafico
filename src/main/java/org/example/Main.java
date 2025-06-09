package org.example;


import javax.swing.*;
import java.awt.*;
import org.example.Teste.*;
import org.jfree.chart.ChartPanel;

public class Main extends JFrame {
    public static JFrame paginaRelatorio() {
        JFrame frame = new JFrame();
        frame.setTitle("Relatorio");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); // Necessário para usar setBounds nos componentes diretamente

        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(0, 0, 600, 50);
        painel.setBackground(new Color(238, 238, 238)); // Exemplo visual

        JTabbedPane dados = abasRelatorio(); // Suponho que seja um método que retorna abas
        dados.setBounds(0, 50, 583, 410); // Altura ajustada para caber na janela

        frame.add(painel);
        frame.add(dados);

        return frame;
    }


    public static JTabbedPane abasRelatorio(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JTabbedPane abasDados = new JTabbedPane();
        abasDados.setFocusable(false);

        JPanel label01 = new JPanel();
        JPanel label02 = new JPanel();
        JPanel label03 = new JPanel();
        JPanel label04 = new JPanel();
        ChartPanel teste01 = Teste.dados01();
        ChartPanel teste02 = Teste.dados02();

        label01.setBackground(Color.PINK);
        label02.setBackground(Color.BLUE);
        label03.setBackground(Color.RED);
        label04.setBackground(Color.WHITE);

        abasDados.addTab("Manhã", label01);
        abasDados.addTab("Tarde", label02);
        abasDados.addTab("Noite", teste02);
        abasDados.addTab("Total", teste01);



        return abasDados;
    }

    public static void main(String[] args) {
        JFrame mainFrame = JanelaRelatorio.paginaRelatorio();
        mainFrame.setVisible(true);

    }
}