package org.example;

import javax.swing.*;

public class PaginaADM extends JFrame {
    private int ALTURA = 700;
    private int LARGURA = 900;

    public PaginaADM(){
        setTitle("Pagina de Administrador");
        setSize(LARGURA, ALTURA);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
    }

    public void iniciar(){
        setVisible(true);
    }

    public static void main(String[] args) {
        PaginaADM frame = new PaginaADM();
        frame.iniciar();
    }

}
