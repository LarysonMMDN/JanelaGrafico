package org.example;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;

import static org.example.JanelaRelatorio.abasRelatorio;

public class PaginaADM {
    private static final int ALTURA = 700;
    private static final int LARGURA = 1000;
    private static final String CAMINHO_IMAGEM_HOME = "src/main/resources/PGHome.png";
    private static final String CAMINHO_IMAGEM_LOGO = "src/main/resources/TituloLogoMarca.png";

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
        frame.setResizable(false);
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
        int posxC = (POSICAO_PAINEL_CENTRAL[2] - 200) / 2;
        int[] posicaoBotaoPecaA = {posx, 50, 200, 50};
        int[] posicaoBotaoPecaB = {posx, 110, 200, 50};
        int[] posicaoBotaoPecaC = {posx, 170, 200, 50};
        int[] posicaoBotaoTotais = {posx, 230, 200, 50};
        int[] posicaoBotaoSair = {posx, 290, 200, 50};
        int[] posicaoBotaoVoltar = {posxC, 290, 200, 50};


        // Botões Da Pagina Lateral
        JButton botaoPecaA = util.criarBotao("Peça A", posicaoBotaoPecaA);
        JButton botaoPecaB = util.criarBotao("Peça B", posicaoBotaoPecaB);
        JButton botaoPecaC = util.criarBotao("Peça C", posicaoBotaoPecaC);
        JButton botaoDadosTotais = util.criarBotao("Dados Totais", posicaoBotaoTotais);
        JButton botaoSair = util.criarBotao("Sair", posicaoBotaoSair);

        // Coração
        botaoPecaA.addActionListener(e -> paginaPecaA(painelCentral));
        botaoPecaB.addActionListener(e -> paginaPecaB(painelCentral));
        botaoPecaC.addActionListener(e -> paginaPecaC(painelCentral));
        botaoDadosTotais.addActionListener(e -> paginaDados(painelCentral));
        botaoSair.addActionListener(e -> frame.dispose());
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

    public JPanel paginaDados(JPanel painel){
        painel.removeAll();
        int[][] dadosTurno = {{18, 15, 10}, {32, 21, 5}, {20, 12, 24}};  // Pecas ABC, Manhã, Tarde, Noite -> quem vendeu mais e menos em cada turno
        int[][] dadosLucro = {{18, 15, 10}, {32, 21, 5}, {20, 12, 24}}; // Linha Dados lucros
        String[] tipos = {"Manhã", "Tarde", "Noite"};
        String[] nomes = {"Peça A", "Peça B", "Peça C"};

        int[] dadosTotal = {12, 19, 50}; // Dados da pizza
        String[] parteDadosPizza = {"Manhã", "Tarde", "Noite"};
        String[] nomesDadosPizza = {"Peças", "Peças", "Peças"};

        // dados da barra
        int[] dadosIngresso = {70, 38, 39}; // Pecas ABC, Manhã, Tarde, Noite -> quem vendeu mais e menos em cada turno
        String[] nomeColunaIngresso = {"Peça A", "Peça B", "Peça C"};
        String[] nomesDadosIngresso = {"Vendas", "Vendas", "Vendas"};

        ChartPanel painelIngresso = Dados.gerarGraficoVendas("Ingressos", dadosIngresso, TipoGrafico.BARRA, nomeColunaIngresso, nomesDadosIngresso);
        ChartPanel painelTurno = Dados.gerarGraficoVendas("Turno", dadosTurno, TipoGrafico.BARRA, tipos, nomes);
        ChartPanel painelLucro = Dados.gerarGraficoVendas("Lucros", dadosLucro, TipoGrafico.LINHA, tipos, nomes);
        ChartPanel painelTotal = Dados.gerarGraficoVendas("Totais", dadosTotal, TipoGrafico.PIZZA, parteDadosPizza, nomesDadosPizza);

        ChartPanel[] paineis = {painelIngresso, painelTurno, painelLucro, painelTotal};
        String[] nomesAbas = {"Ingresso", "Turno", "Lucro", "Total"};


        JTabbedPane Abas = abasRelatorio(nomesAbas, paineis);

        ImageIcon logo = new ImageIcon(CAMINHO_IMAGEM_LOGO);
        JLabel logoMarca = new JLabel(logo);
        logoMarca.setBounds(200, 0,300, 50);
        Abas.setBounds(0, 50, 700, 600);

        painel.add(logoMarca);
        painel.add(Abas);
        painel.revalidate();
        painel.repaint();
        return painel;
    }

    public JPanel paginaPecaA(JPanel painel){
        painel.removeAll();
        int[] dados = {10, 20, 30, 30};
        int[][] dadosLucro = {{18, 15, 10}, {32, 21, 5}, {20, 12, 24}, {26, 14, 22}};
        String[] tipos = {"Manhã", "Tarde", "Noite", "Total"};
        String[] nomes = {"Vendas", "Vendas", "Vendas", "Vendas"};

        ChartPanel painelManha = Dados.gerarGraficoVendas("Vendas na Parte da Manhã", dados, TipoGrafico.BARRA, tipos, nomes);
        ChartPanel painelTarde = Dados.gerarGraficoVendas("Vendas na Parte da Tarde", dados, TipoGrafico.BARRA, tipos, nomes);
        ChartPanel painelNoite = Dados.gerarGraficoVendas("Vendas na Parte da Noite", dadosLucro, TipoGrafico.LINHA, tipos, nomes);
        ChartPanel painelTotal = Dados.gerarGraficoVendas("Vendas Totais", dados, TipoGrafico.PIZZA, tipos, nomes);

        ChartPanel[] paineis = {painelManha, painelTarde, painelNoite, painelTotal};
        String[] nomesAbas = {"Manhã", "Tarde", "Noite", "Total"};

        JTabbedPane Abas = abasRelatorio(nomesAbas, paineis);

        ImageIcon logo = new ImageIcon(CAMINHO_IMAGEM_LOGO);
        JLabel logoMarca = new JLabel(logo);
        logoMarca.setBounds(200, 0,300, 50);
        Abas.setBounds(0, 50, 710, 600);

        painel.add(logoMarca);
        painel.add(Abas);
        painel.revalidate();
        painel.repaint();
        return painel;
    }

    public JPanel paginaPecaB(JPanel painel){
        painel.removeAll();
        int[] dados = {10, 20, 30, 30};
        int[][] dadosLucro = {{18, 15, 10}, {32, 21, 5}, {20, 12, 24}, {26, 14, 22}};
        String[] tipos = {"Manhã", "Tarde", "Noite", "Total"};
        String[] nomes = {"Vendas", "Vendas", "Vendas", "Vendas"};
        String[] nomesLucro = {"Manhã", "Tarde", "Noite", "Total"};

        ChartPanel painelManha = Dados.gerarGraficoVendas("Vendas na Parte da Manhã", dados, TipoGrafico.BARRA, tipos, nomes);
        ChartPanel painelTarde = Dados.gerarGraficoVendas("Vendas na Parte da Tarde", dados, TipoGrafico.BARRA, tipos, nomes);
        ChartPanel painelNoite = Dados.gerarGraficoVendas("Vendas na Parte da Noite", dadosLucro, TipoGrafico.LINHA, tipos, nomesLucro);
        ChartPanel painelTotal = Dados.gerarGraficoVendas("Vendas Totais", dados, TipoGrafico.PIZZA, tipos, nomes);

        ChartPanel[] paineis = {painelManha, painelTarde, painelNoite, painelTotal};
        String[] nomesAbas = {"Manhã", "Tarde", "Noite", "Total"};

        JTabbedPane Abas = abasRelatorio(nomesAbas, paineis);

        ImageIcon logo = new ImageIcon(CAMINHO_IMAGEM_LOGO);
        JLabel logoMarca = new JLabel(logo);
        logoMarca.setBounds(200, 0,300, 50);
        Abas.setBounds(0, 50, 710, 600);

        painel.add(logoMarca);
        painel.add(Abas);
        painel.revalidate();
        painel.repaint();
        return painel;
    }

    public JPanel paginaPecaC(JPanel painel){
        painel.removeAll();
        int[] dados = {10, 20, 30, 30};
        String[] tipos = {"Manhã", "Tarde", "Noite", "Total"};
        String[] nomes = {"Vendas", "Vendas", "Vendas", "Vendas"};

        ChartPanel painelManha = Dados.gerarGraficoVendas("Vendas na Parte da Manhã", dados, TipoGrafico.BARRA, tipos, nomes);
        ChartPanel painelTarde = Dados.gerarGraficoVendas("Vendas na Parte da Tarde", dados, TipoGrafico.BARRA, tipos, nomes);
        ChartPanel painelNoite = Dados.gerarGraficoVendas("Vendas na Parte da Noite", dados, TipoGrafico.LINHA, tipos, nomes);
        ChartPanel painelTotal = Dados.gerarGraficoVendas("Vendas Totais", dados, TipoGrafico.PIZZA, tipos, nomes);

        ChartPanel[] paineis = {painelManha, painelTarde, painelNoite, painelTotal};
        String[] nomesAbas = {"Manhã", "Tarde", "Noite", "Total"};

        JTabbedPane Abas = abasRelatorio(nomesAbas, paineis);

        ImageIcon logo = new ImageIcon(CAMINHO_IMAGEM_LOGO);
        JLabel logoMarca = new JLabel(logo);
        logoMarca.setBounds(200, 0,300, 50);
        Abas.setBounds(0, 50, 710, 600);

        painel.add(logoMarca);
        painel.add(Abas);
        painel.revalidate();
        painel.repaint();
        return painel;
    }

    public static void main(String[] args) {
        PaginaADM paginaADM = new PaginaADM();
        paginaADM.iniciar();
    }
}
