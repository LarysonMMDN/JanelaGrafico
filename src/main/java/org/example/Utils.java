package org.example;

import javax.swing.*;
import java.awt.*;

public class Utils {
    public Utils(){}

    public JPanel criarPainel(int[] pos){
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(pos[0], pos[1], pos[2], pos[3]);
        return painel;
    }

    public JLabel criarLabel(String titulo, int tamanhoFonte, int[] pos){
        JLabel label = new JLabel(titulo);
        label.setFont(new Font("Segoe UI", Font.BOLD, tamanhoFonte));
        label.setBounds(pos[0], pos[1], pos[2], pos[3]);
        label.setForeground(Color.WHITE);
        return label;
    }

    public JLabel criarLabel(ImageIcon imagem){
        JLabel label = new JLabel(imagem);
        return label;
    }

    public JButton criarBotao(String titulo, int[] pos){
        JButton botao = new JButton(titulo);
        botao.setBounds(pos[0], pos[1], pos[2], pos[3]);
        return botao;
    }
}
