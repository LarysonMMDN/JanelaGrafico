package org.example;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class JanelaRelatorio {


    public static JTabbedPane abasRelatorio(String[] nomesAbas, ChartPanel[] paineis) {
        JTabbedPane abasDados = new JTabbedPane();
        abasDados.setFocusable(false);

        for (int i = 0; i < nomesAbas.length; i++) {
            JPanel painel = new JPanel();
            painel.setLayout(null);

            paineis[i].setBounds(0,0,710, 400);

            painel.add(paineis[i]);
            abasDados.addTab(nomesAbas[i], painel);
        }

        return abasDados;
    }

}
