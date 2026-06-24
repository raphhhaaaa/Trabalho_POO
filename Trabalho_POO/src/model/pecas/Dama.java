package model.pecas;

import model.Cor;
import model.Posicao;
import model.Tabuleiro;

public class Dama extends Peca {

    public Dama(Posicao posicao, Cor cor) {
        super(posicao, cor);
    }

    public String getLetra() {
        return "D";
    }

    public boolean[][] movimentosValidos(Tabuleiro tabuleiro) {
        boolean[][] matriz = new boolean[8][8];

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                int diferencaLinha = Math.abs(linha - getPosicao().getLinha());
                int diferencaColuna = Math.abs(coluna - getPosicao().getColuna());
                boolean reto = linha == getPosicao().getLinha() || coluna == getPosicao().getColuna();
                boolean diagonal = diferencaLinha == diferencaColuna;
                Posicao destino = new Posicao(linha, coluna);

                if ((reto || diagonal)
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
