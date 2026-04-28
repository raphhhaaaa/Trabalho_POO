package pecas;

import modelo.Cor;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public class Cavalo extends Peca {

    public Cavalo(Posicao posicao, Cor cor) {
        super(posicao, cor);
    }

    public String getLetra() {
        return "C";
    }

    public boolean[][] movimentosValidos(Tabuleiro tabuleiro) {
        boolean[][] matriz = new boolean[8][8];

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                int diferencaLinha = Math.abs(linha - getPosicao().getLinha());
                int diferencaColuna = Math.abs(coluna - getPosicao().getColuna());

                if (((diferencaLinha == 2 && diferencaColuna == 1)
                        || (diferencaLinha == 1 && diferencaColuna == 2))
                        && podeMover(tabuleiro, linha, coluna)) {
                    matriz[linha][coluna] = true;
                }
            }
        }

        return matriz;
    }
}
