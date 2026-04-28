package pecas;

import modelo.Cor;
import tabuleiro.Posicao;

public class Peca {

    // atributos
    private Posicao posicao;
    private Cor cor;

    // construtor
    public Peca(Posicao posicao, Cor cor) {
        this.posicao = posicao;
        this.cor = cor;
    }

    public Peca() {}


    // getters & setters
    public Posicao getPosicao() {
        return posicao;
    }
    public Cor getCor() {
        return cor;
    }

    public void setPosicao(Posicao posicao) { this.posicao = posicao; }

    // metodos auxiliares

    public boolean validaMovimento(Posicao posicao) {}
}
