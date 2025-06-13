package org.example;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Utils {
    private static String caminhoIco = "src/main/resources/Miguire.png";
    private static Utils util = new Utils();

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

    public JLabel criarLabel(String caminho, int[] pos){
        ImageIcon imagem = new ImageIcon(caminho);
        JLabel label = new JLabel(imagem);
        label.setBounds(pos[0], pos[1], pos[2], pos[3]);
        return label;
    }

    public JButton criarBotao(String titulo, int[] pos){
        JButton botao = new JButton(titulo);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botao.setBorder(new LineBorder(Color.black, 1));
        botao.setBackground(Color.RED);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBounds(pos[0], pos[1], pos[2], pos[3]);
        return botao;
    }
    public JButton criarBotao(String titulo, int[] pos, int[] dados){
        JButton botao = new JButton(titulo);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botao.setBorder(new LineBorder(Color.black, 1));
        botao.setBackground(Color.RED);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBounds(pos[0], pos[1], pos[2], pos[3]);
        botao.addActionListener(e -> {
            popup(dados);
        });
        return botao;
    }


    public static void popup(int[] dados){
        ImageIcon novaImagem = ResizingImage(50, 50, caminhoIco);
        JOptionPane.showMessageDialog(
                null,
                "<html><b>Lucro assento Plateia A:</b>  R$ "   + dados[0] * 40 + "</html>\n" +
                        "<html><b>Lucro assento Plateia B:</b> R$ "    + dados[1] * 60 + "</html>\n" +
                        "<html><b>Lucro assento Frisa:</b> R$ "        + dados[2] * 120 + "</html>\n" +
                        "<html><b>Lucro assento Camarote:</b> R$ "     + dados[3] * 80 + "</html>\n" +
                        "<html><b>Lucro assento Balcão Nobre:</b> R$ " + dados[4] * 250 + "</html>\n",
                "Relatorio LPA",
                JOptionPane.PLAIN_MESSAGE,
                novaImagem
        );
    }

    public static ImageIcon ResizingImage(int altura, int largura, String caminhoImagem) {
        ImageIcon imagemOriginal = new ImageIcon(caminhoImagem);
        Image imagemRedimensionada  = imagemOriginal.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        ImageIcon novaImagem = new ImageIcon(imagemRedimensionada );
        return novaImagem;
    }
}
