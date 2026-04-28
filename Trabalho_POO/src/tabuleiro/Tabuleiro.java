package tabuleiro;

import modelo.Cor;
import pecas.Bispo;
import pecas.Cavalo;
import pecas.Dama;
import pecas.Peao;
import pecas.Peca;
import pecas.Rei;
import pecas.Torre;

public class Tabuleiro {

    private Casa[][] casas;

    public Tabuleiro() {
        casas = new Casa[8][8];

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                casas[linha][coluna] = new Casa(new Posicao(linha, coluna));
            }
        }

        colocarPecas();
    }

    public void colocarPecas() {
        colocarPeca(new Torre(new Posicao(0, 0), Cor.PRETA));
        colocarPeca(new Cavalo(new Posicao(0, 1), Cor.PRETA));
        colocarPeca(new Bispo(new Posicao(0, 2), Cor.PRETA));
        colocarPeca(new Dama(new Posicao(0, 3), Cor.PRETA));
        colocarPeca(new Rei(new Posicao(0, 4), Cor.PRETA));
        colocarPeca(new Bispo(new Posicao(0, 5), Cor.PRETA));
        colocarPeca(new Cavalo(new Posicao(0, 6), Cor.PRETA));
        colocarPeca(new Torre(new Posicao(0, 7), Cor.PRETA));

        for (int coluna = 0; coluna < 8; coluna++) {
            colocarPeca(new Peao(new Posicao(1, coluna), Cor.PRETA));
            colocarPeca(new Peao(new Posicao(6, coluna), Cor.BRANCA));
        }

        colocarPeca(new Torre(new Posicao(7, 0), Cor.BRANCA));
        colocarPeca(new Cavalo(new Posicao(7, 1), Cor.BRANCA));
        colocarPeca(new Bispo(new Posicao(7, 2), Cor.BRANCA));
        colocarPeca(new Dama(new Posicao(7, 3), Cor.BRANCA));
        colocarPeca(new Rei(new Posicao(7, 4), Cor.BRANCA));
        colocarPeca(new Bispo(new Posicao(7, 5), Cor.BRANCA));
        colocarPeca(new Cavalo(new Posicao(7, 6), Cor.BRANCA));
        colocarPeca(new Torre(new Posicao(7, 7), Cor.BRANCA));
    }

    public boolean posicaoValida(int linha, int coluna) {
        return linha >= 0 && linha < 8 && coluna >= 0 && coluna < 8;
    }

    public Peca getPeca(int linha, int coluna) {
        if (!posicaoValida(linha, coluna)) {
            return null;
        }

        return casas[linha][coluna].getPeca();
    }

    public Peca getPeca(Posicao posicao) {
        return getPeca(posicao.getLinha(), posicao.getColuna());
    }

    public void colocarPeca(Peca peca) {
        Posicao posicao = peca.getPosicao();
        casas[posicao.getLinha()][posicao.getColuna()].setPeca(peca);
    }

    public boolean moverPeca(Posicao origem, Posicao destino) {
        Peca peca = getPeca(origem);

        if (peca == null) {
            return false;
        }

        if (!peca.validaMovimento(this, destino)) {
            return false;
        }

        casas[origem.getLinha()][origem.getColuna()].setPeca(null);
        peca.setPosicao(destino);
        casas[destino.getLinha()][destino.getColuna()].setPeca(peca);
        return true;
    }

    public boolean caminhoLivre(Posicao origem, Posicao destino) {
        int linhaAtual = origem.getLinha();
        int colunaAtual = origem.getColuna();
        int linhaDestino = destino.getLinha();
        int colunaDestino = destino.getColuna();

        int andaLinha = Integer.compare(linhaDestino, linhaAtual);
        int andaColuna = Integer.compare(colunaDestino, colunaAtual);

        linhaAtual = linhaAtual + andaLinha;
        colunaAtual = colunaAtual + andaColuna;

        while (linhaAtual != linhaDestino || colunaAtual != colunaDestino) {
            if (getPeca(linhaAtual, colunaAtual) != null) {
                return false;
            }

            linhaAtual = linhaAtual + andaLinha;
            colunaAtual = colunaAtual + andaColuna;
        }

        return true;
    }

    public void mostrarTabuleiro() {
        System.out.println();
        System.out.println("    a b c d e f g h");

        for (int linha = 0; linha < 8; linha++) {
            System.out.print(" " + (8 - linha) + "  ");

            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = getPeca(linha, coluna);

                if (peca == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(peca.getDesenho() + " ");
                }
            }

            System.out.println(" " + (8 - linha));
        }

        System.out.println("    a b c d e f g h");
        System.out.println();
    }

    public void mostrarMatriz(boolean[][] matriz) {
        System.out.println();
        System.out.println("    a b c d e f g h");

        for (int linha = 0; linha < 8; linha++) {
            System.out.print(" " + (8 - linha) + "  ");

            for (int coluna = 0; coluna < 8; coluna++) {
                if (matriz[linha][coluna]) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }

            System.out.println(" " + (8 - linha));
        }

        System.out.println("    a b c d e f g h");
        System.out.println();
    }
}
