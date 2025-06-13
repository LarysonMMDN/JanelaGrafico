package org.example;

import javax.swing.*;
import java.awt.*;

import static org.example.JanelaRelatorio.abasRelatorio;

public class PaginaADM {
    private static final int ALTURA = 700;
    private static final int LARGURA = 1000;
    private static String caminhoImagemHome = "src/main/resources/PGHome.png";
    private JFrame frame;
    private Utils util = new Utils();

    public PaginaADM() {
        inicializarFrame();
    }

    private void inicializarFrame() {
        frame = new JFrame("Página de Administrador");
        frame.setSize(LARGURA, ALTURA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
        frame.setLayout(null);
    }

    public void iniciar() {
        montarHomePage();
        frame.setVisible(true);
    }

    private void montarHomePage() {

        // Array de posições dos Paineis
        int[] posicaoPainelSuperior = {0, 0, LARGURA - 15, 125};
        int[] posicaoPainelLateral = {0, 125, 275, 575 - 30};
        int[] posicaoPainelCentral = {275, 125, 725 - 15, 575 - 30};

        // Array de posições dos Labels
        int[] posicaoLabelTitulo = {(LARGURA - 400) / 2, (125 - 80) / 2, 400, 80};
        int[] posicaoLabelHome = {(posicaoPainelCentral[2] - 500) / 2, (posicaoPainelCentral[3] - 500) / 2, 500, 500};

        // Array de posições dos Botões
        int[] posicaoBotaoPecaA =  {(posicaoPainelLateral[2] - 200) / 2, 50, 200, 50};
        int[] posicaoBotaoPecaB =  {(posicaoPainelLateral[2] - 200) / 2, 110, 200, 50};
        int[] posicaoBotaoPecaC =  {(posicaoPainelLateral[2] - 200) / 2, 170, 200, 50};
        int[] posicaoBotaoTotais = {(posicaoPainelLateral[2] - 200) / 2, 230, 200, 50};
        int[] posicaoBotaoSair =   {(posicaoPainelLateral[2] - 200) / 2, 290, 200, 50};


        JPanel painelSuperior = util.criarPainel(posicaoPainelSuperior);
        JPanel painelLateral = util.criarPainel(posicaoPainelLateral);
        JPanel painelCentral = util.criarPainel(posicaoPainelCentral);

        painelSuperior.setBackground(Color.RED);
        painelLateral.setBackground(Color.BLACK);
        painelCentral.setBackground(Color.WHITE);

        // Titulo Painel Superior
        JLabel tituloPainel = util.criarLabel("Pagina de ADM", 50, posicaoLabelTitulo);
        painelSuperior.add(tituloPainel);

        // Logo Pagina Inicial
        JLabel ImagemHome = util.criarLabel(caminhoImagemHome, posicaoLabelHome);
        painelCentral.add(ImagemHome);

        // Botões Da Pagina Lateral
        JButton botaoPecaA = util.criarBotao("Peça A", posicaoBotaoPecaA);
        JButton botaoPecaB = util.criarBotao("Peça B", posicaoBotaoPecaB);
        JButton botaoPecaC = util.criarBotao("Peça C", posicaoBotaoPecaC);
        JButton botaoDadosTotais = util.criarBotao("Dados Totais", posicaoBotaoTotais);
        JButton botaoSair = util.criarBotao("Sair", posicaoBotaoSair);

        // Coração
        botaoPecaA.addActionListener(e -> {
            painelCentral.removeAll();
            paginaDados(painelCentral);
            painelCentral.revalidate();
            painelCentral.repaint();
        });

        // Adiconando Botões ao Painel Lateral
        painelLateral.add(botaoPecaA);
        painelLateral.add(botaoPecaB);
        painelLateral.add(botaoPecaC);
        painelLateral.add(botaoDadosTotais);
        painelLateral.add(botaoSair);

        frame.add(painelCentral);
        frame.add(painelLateral);
        frame.add(painelSuperior);
    }

    public JPanel paginaDados(JPanel painel){
        JTabbedPane dados = abasRelatorio();
        dados.setBounds(0, 50, 725, 525);
        painel.add(dados);
        return painel;
    }



    public static void main(String[] args) {
        PaginaADM paginaADM = new PaginaADM();
        paginaADM.iniciar();
    }
}
