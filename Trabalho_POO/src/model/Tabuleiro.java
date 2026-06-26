package model;

import model.pecas.*;

public class Tabuleiro {
    private Casa[][] casas;
    private Cor vez = Cor.BRANCA;

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

        try {
            // Salva o estado para reverter se ficar em xeque
            Peca pecaCapturada = getPeca(destino);

            casas[origem.getLinha()][origem.getColuna()].setPeca(null);
            peca.setPosicao(destino);
            casas[destino.getLinha()][destino.getColuna()].setPeca(peca);

            // Verifica se o próprio rei ficou em xeque
            if (estaEmCheque(peca.getCor())) {
                // Reverte o movimento
                casas[origem.getLinha()][origem.getColuna()].setPeca(peca);
                peca.setPosicao(origem);
                casas[destino.getLinha()][destino.getColuna()].setPeca(pecaCapturada);
                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

    // --- MÉTODOS DE CHEQUE E CHEQUE-MATE ---

    public Posicao getPosicaoRei(Cor cor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca p = getPeca(i, j);
                if (p instanceof Rei && p.getCor() == cor) {
                    return p.getPosicao();
                }
            }
        }
        return null;
    }

    public boolean estaEmCheque(Cor cor) {
        Posicao posRei = getPosicaoRei(cor);
        if (posRei == null) return false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca p = getPeca(i, j);
                if (p != null && p.getCor() != cor) {
                    boolean[][] movs = p.movimentosValidos(this);
                    if (movs[posRei.getLinha()][posRei.getColuna()]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean estaEmChequeMate(Cor cor) {
        if (!estaEmCheque(cor)) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Peca p = getPeca(i, j);
                if (p != null && p.getCor() == cor) {
                    boolean[][] movs = p.movimentosValidos(this);
                    for (int linha = 0; linha < 8; linha++) {
                        for (int coluna = 0; coluna < 8; coluna++) {
                            if (movs[linha][coluna]) {
                                Posicao origem = p.getPosicao();
                                Posicao destino = new Posicao(linha, coluna);
                                Peca pecaCapturada = getPeca(destino);

                                casas[origem.getLinha()][origem.getColuna()].setPeca(null);
                                p.setPosicao(destino);
                                casas[destino.getLinha()][destino.getColuna()].setPeca(p);

                                boolean aindaEmCheque = estaEmCheque(cor);

                                casas[origem.getLinha()][origem.getColuna()].setPeca(p);
                                p.setPosicao(origem);
                                casas[destino.getLinha()][destino.getColuna()].setPeca(pecaCapturada);

                                if (!aindaEmCheque) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public Cor getVez() {
        return vez;
    }

    public void setVez(Cor vez) {
        this.vez = vez;
    }

    public void mudaVez() {
        if (this.vez == Cor.BRANCA) {
            this.vez = Cor.PRETA;
        } else {
            this.vez = Cor.BRANCA;
        }
    }
}