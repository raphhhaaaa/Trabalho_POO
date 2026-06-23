package model;

public class Posicao {

    // atributos
    private int linha;
    private int coluna;

    // construtor
    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    // getters & setters

    public int getLinha() {
        return linha;
    }
    public int getColuna() {
        return coluna;
    }

    public void setLinha(int linha) { this.linha = linha; }
    public void setColuna(int coluna) {this.coluna = coluna; }

    public void setValores(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public boolean igual(Posicao outra) {
        return linha == outra.getLinha() && coluna == outra.getColuna();
    }
}
