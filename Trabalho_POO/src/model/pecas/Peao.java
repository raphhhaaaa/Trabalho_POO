package model.pecas;

import model.Cor;
import model.Posicao;
import model.Tabuleiro;

public class Peao extends Peca {

    public Peao(Posicao posicao, Cor cor) {
        super(posicao, cor);
    }

    public String getLetra() {
        return "P";
    }

    public boolean[][] movimentosValidos(Tabuleiro tabuleiro) {
        boolean[][] matriz = new boolean[8][8];
        int direcao;
        int linhaInicial;

        if (getCor() == Cor.BRANCA) {
            direcao = -1;
            linhaInicial = 6;
        } else {
            direcao = 1;
            linhaInicial = 1;
        }

        int linha = getPosicao().getLinha();
        int coluna = getPosicao().getColuna();
        int umaLinha = linha + direcao;
        int duasLinhas = linha + (direcao * 2);

        if (tabuleiro.posicaoValida(umaLinha, coluna)
                && tabuleiro.getPeca(umaLinha, coluna) == null) {
            matriz[umaLinha][coluna] = true;

            if (linha == linhaInicial
                    && tabuleiro.getPeca(duasLinhas, coluna) == null) {
                matriz[duasLinhas][coluna] = true;
            }
        }

        if (tabuleiro.posicaoValida(umaLinha, coluna - 1)
                && tabuleiro.getPeca(umaLinha, coluna - 1) != null
                && tabuleiro.getPeca(umaLinha, coluna - 1).getCor() != getCor()) {
            matriz[umaLinha][coluna - 1] = true;
        }

        if (tabuleiro.posicaoValida(umaLinha, coluna + 1)
                && tabuleiro.getPeca(umaLinha, coluna + 1) != null
                && tabuleiro.getPeca(umaLinha, coluna + 1).getCor() != getCor()) {
            matriz[umaLinha][coluna + 1] = true;
        }

        return matriz;
    }
}
