package model.pecas;

import model.Cor;
import model.Posicao;
import model.Tabuleiro;

public class Rei extends Peca {

    public Rei(Posicao posicao, Cor cor) {
        super(posicao, cor);
    }

    public String getLetra() {
        return "R";
    }

    public boolean[][] movimentosValidos(Tabuleiro tabuleiro) {
        boolean[][] matriz = new boolean[8][8];

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                int diferencaLinha = Math.abs(linha - getPosicao().getLinha());
                int diferencaColuna = Math.abs(coluna - getPosicao().getColuna());

                if (diferencaLinha <= 1
                        && diferencaColuna <= 1
                        && (diferencaLinha != 0 || diferencaColuna != 0)
                        && podeMover(tabuleiro, linha, coluna)) {
                    matriz[linha][coluna] = true;

                }
            }
        }

        return matriz;
    }
}
