package org.example;

import javax.swing.*;
import java.awt.*;

public class PaginaADM {
    private static final int ALTURA = 700;
    private static final int LARGURA = 1000;
    private JFrame frame;

    public PaginaADM() {
        inicializarFrame();
    }

    private void inicializarFrame() {
        frame = new JFrame("Página de Administrador");
        frame.setSize(LARGURA, ALTURA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
    }

    public void iniciar() {
        montarHomePage();
        frame.setVisible(true);
    }

    private void montarHomePage() {
        Utils util = new Utils();
        int[] posicaoPainelSuperior = {0, 0, LARGURA, 125};
        int[] posicaoPainelLateral = {0, 125, 275, ALTURA - 125};
        int[] posicaoPainelCentral = {275, 125, LARGURA - 275, ALTURA - 125};

        int[] posicaoLabelTitulo = {(LARGURA - 400) / 2, (125 - 80) / 2, 400, 80};

        JPanel painelSuperior = util.criarPainel(posicaoPainelSuperior);
        JPanel painelLateral = util.criarPainel(posicaoPainelLateral);
        JPanel painelCentral = util.criarPainel(posicaoPainelCentral);


        painelSuperior.setBackground(Color.RED);
        painelLateral.setBackground(Color.BLACK);
        painelCentral.setBackground(Color.blue);

        JLabel tituloPainel = util.criarLabel("Pagina de ADM", 50, posicaoLabelTitulo);
        painelSuperior.add(tituloPainel);

        frame.add(painelCentral);
        frame.add(painelLateral);
        frame.add(painelSuperior);
    }

    public static void main(String[] args) {
        PaginaADM paginaADM = new PaginaADM();
        paginaADM.iniciar();
    }
}
