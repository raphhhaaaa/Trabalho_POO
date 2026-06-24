package model.pecas;

import model.Cor;
import model.Posicao;
import model.Tabuleiro;

public class Bispo extends Peca {

    public Bispo(Posicao posicao, Cor cor) {
        super(posicao, cor);
    }

    public String getLetra() {
        return "B";
    }

    public boolean[][] movimentosValidos(Tabuleiro tabuleiro) {
        boolean[][] matriz = new boolean[8][8];

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                int diferencaLinha = Math.abs(linha - getPosicao().getLinha());
                int diferencaColuna = Math.abs(coluna - getPosicao().getColuna());
                Posicao destino = new Posicao(linha, coluna);

                if (diferencaLinha == diferencaColuna
                        && diferencaLinha != 0
                        && tabuleiro.caminhoLivre(getPosicao(), destino)
                        && podeMover(tabuleiro, linha, coluna)) {
                    matriz[linha][coluna] = true;
                }
            }
        }

        return matriz;
    }
}
