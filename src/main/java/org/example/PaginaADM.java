package org.example;

import javax.swing.*;
import java.awt.*;

import static org.example.JanelaRelatorio.abasRelatorio;

public class PaginaADM {
    private static final int ALTURA = 700;
    private static final int LARGURA = 1000;
    private static final String CAMINHO_IMAGEM_HOME = "src/main/resources/PGHome.png";

    private JFrame frame;
    private Utils util = new Utils();

    // Constantes para posições de componentes
    private static final int[] POSICAO_PAINEL_SUPERIOR = {0, 0, LARGURA - 15, 125};
    private static final int[] POSICAO_PAINEL_LATERAL = {0, 125, 275, 575 - 30};
    private static final int[] POSICAO_PAINEL_CENTRAL = {275, 125, 725 - 15, 575 - 30};
    private static final int[] POSICAO_LABEL_TITULO = {(LARGURA - 400) / 2, (125 - 80) / 2, 400, 80};
    private static final int[] POSICAO_LABEL_HOME = {(POSICAO_PAINEL_CENTRAL[2] - 500) / 2, (POSICAO_PAINEL_CENTRAL[3] - 500) / 2, 500, 500};

    private JPanel painelSuperior;
    private JPanel painelLateral;
    private JPanel painelCentral;

    public PaginaADM() {
        inicializarFrame();
    }

    private void inicializarFrame() {
        frame = new JFrame("Página de Administrador");
        frame.setSize(LARGURA, ALTURA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
    }

    public void iniciar() {
        montarHomePage();
        frame.setVisible(true);
    }

    private void montarHomePage() {
        painelSuperior = util.criarPainel(POSICAO_PAINEL_SUPERIOR);
        painelLateral = util.criarPainel(POSICAO_PAINEL_LATERAL);
        painelCentral = util.criarPainel(POSICAO_PAINEL_CENTRAL);

        painelSuperior.setBackground(Color.RED);
        painelLateral.setBackground(Color.BLACK);
        painelCentral.setBackground(Color.WHITE);

        // Titulo Painel Superior
        JLabel tituloPainel = util.criarLabel("Pagina de ADM", 50, POSICAO_LABEL_TITULO);
        painelSuperior.add(tituloPainel);

        // Logo Pagina Inicial
        JLabel imagemHome = util.criarLabel(CAMINHO_IMAGEM_HOME, POSICAO_LABEL_HOME);
        painelCentral.add(imagemHome);

        painelSuperior.setBackground(Color.RED);
        painelLateral.setBackground(Color.BLACK);
        painelCentral.setBackground(Color.WHITE);

        // Logo Pagina Inicial
        JLabel ImagemHome = util.criarLabel(CAMINHO_IMAGEM_HOME, POSICAO_LABEL_HOME);
        painelCentral.add(ImagemHome);

        //
        int posx = (POSICAO_PAINEL_LATERAL[2] - 200) / 2;
        int[] posicaoBotaoPecaA = {posx, 50, 200, 50};
        int[] posicaoBotaoPecaB = {posx, 110, 200, 50};
        int[] posicaoBotaoPecaC = {posx, 170, 200, 50};
        int[] posicaoBotaoTotais = {posx, 230, 200, 50};
        int[] posicaoBotaoSair = {posx, 290, 200, 50};


        // Botões Da Pagina Lateral
        JButton botaoPecaA = util.criarBotao("Peça A", posicaoBotaoPecaA);
        JButton botaoPecaB = util.criarBotao("Peça B", posicaoBotaoPecaB);
        JButton botaoPecaC = util.criarBotao("Peça C", posicaoBotaoPecaC);
        JButton botaoDadosTotais = util.criarBotao("Dados Totais", posicaoBotaoTotais);
        JButton botaoSair = util.criarBotao("Sair", posicaoBotaoSair);

        // Coração
        botaoPecaA.addActionListener(e -> paginaDados(painelCentral, "PecaA"));
        botaoPecaB.addActionListener(e -> paginaDados(painelCentral, "PecaB"));
        botaoPecaC.addActionListener(e -> paginaDados(painelCentral, "PecaC"));
        botaoDadosTotais.addActionListener(e -> paginaDados(painelCentral));
        botaoSair.addActionListener(e -> frame.dispose());

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


    public JPanel paginaDados(JPanel painel, String peca){
        painel.removeAll();
        JTabbedPane dados = abasRelatorio(peca);
        dados.setBounds(0, 50, 725, 525);
        painel.add(dados);
        painel.revalidate();
        painel.repaint();
        return painel;
    }

    public JPanel paginaDados(JPanel painel){
        painel.removeAll();
        JTabbedPane dados = abasRelatorio();
        dados.setBounds(0, 50, 725, 525);
        painel.add(dados);
        painel.revalidate();
        painel.repaint();
        return painel;
    }

    public static void main(String[] args) {
        PaginaADM paginaADM = new PaginaADM();
        paginaADM.iniciar();
    }
}
