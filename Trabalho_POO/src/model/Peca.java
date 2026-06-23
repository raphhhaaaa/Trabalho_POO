package model;

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

    public String getLetra() {
        return "?";
    }

    public String getDesenho() {
        if (cor == Cor.BRANCA) {
            return getLetra();
        } else {
            return getLetra().toLowerCase();
        }
    }

    public boolean[][] movimentosValidos(Tabuleiro tabuleiro) {
        return new boolean[8][8];
    }

    public boolean validaMovimento(Tabuleiro tabuleiro, Posicao posicao) {
        boolean[][] movimentos = movimentosValidos(tabuleiro);
        return movimentos[posicao.getLinha()][posicao.getColuna()];
    }

    public boolean podeMover(Tabuleiro tabuleiro, int linha, int coluna) {
        if (!tabuleiro.posicaoValida(linha, coluna)) {
            return false;
        }

        Peca outra = tabuleiro.getPeca(linha, coluna);
        if (outra == null) {
            return true;
        }

        return outra.getCor() != cor;
    }
}
