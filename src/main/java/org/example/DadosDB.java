package org.example;

public class DadosDB {
    public int[][] dadosPecaA;
    public int[][] dadosPecaB;
    public int[][] dadosPecaC;

    public DadosDB(int[][] dadosPecaA, int[][] dadosPecaB, int[][] dadosPecaC){
        this.dadosPecaA = dadosPecaA;
        this.dadosPecaB = dadosPecaB;
        this.dadosPecaC = dadosPecaC;
    }

    public int[][] getDadosPecaA() {
        return dadosPecaA;
    }

    public void setDadosPecaA(int[][] dadosPecaA) {
        this.dadosPecaA = dadosPecaA;
    }

    public int[][] getDadosPecaC() {
        return dadosPecaC;
    }

    public void setDadosPecaC(int[][] dadosPecaC) {
        this.dadosPecaC = dadosPecaC;
    }

    public int[][] getDadosPecaB() {
        return dadosPecaB;
    }

    public void setDadosPecaB(int[][] dadosPecaB) {
        this.dadosPecaB = dadosPecaB;
    }
}
