package model.pecas;

import model.Cor;
import model.Posicao;
import model.Tabuleiro;

public class Torre extends Peca {

    public Torre(Posicao posicao, Cor cor) {
        super(posicao, cor);
    }

    public String getLetra() {
        return "T";
    }

    public boolean[][] movimentosValidos(Tabuleiro tabuleiro) {
        boolean[][] matriz = new boolean[8][8];

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Posicao destino = new Posicao(linha, coluna);

                if ((linha == getPosicao().getLinha() || coluna == getPosicao().getColuna())
                        && !destino.igual(getPosicao())
                        && tabuleiro.caminhoLivre(getPosicao(), destino)
                        && podeMover(tabuleiro, linha, coluna)) {
                    matriz[linha][coluna] = true;
                }
            }
        }

        return matriz;
    }
}
