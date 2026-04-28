import modelo.Cor;
import pecas.Peca;
import tabuleiro.Movimento;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro();
        Cor vez = Cor.BRANCA;
        boolean jogando = true;

        while (jogando) {
            tabuleiro.mostrarTabuleiro();
            System.out.println("Vez das pecas: " + vez.getNome());
            System.out.println("Pecas: P peao, T torre, C cavalo, B bispo, D dama, R rei");
            System.out.println("Digite sair para terminar");

            System.out.print("Letra da peca: ");
            String letra = scanner.nextLine().trim().toUpperCase();

            if (letra.equals("SAIR")) {
                jogando = false;
            } else {
                System.out.print("Posicao da peca: ");
                String textoOrigem = scanner.nextLine().trim().toLowerCase();

                System.out.print("Posicao de chegada: ");
                String textoDestino = scanner.nextLine().trim().toLowerCase();

                Posicao origem = lerPosicao(textoOrigem);
                Posicao destino = lerPosicao(textoDestino);

                if (origem == null || destino == null) {
                    System.out.println("Posicao invalida. Use exemplos como e2 ou a7.");
                } else {
                    Movimento movimento = new Movimento(origem, destino);
                    Peca peca = tabuleiro.getPeca(origem);

                    if (peca == null) {
                        System.out.println("Nao existe peca nessa posicao.");
                    } else if (peca.getCor() != vez) {
                        System.out.println("Essa peca nao e sua.");
                    } else if (!peca.getLetra().equals(letra)) {
                        System.out.println("A letra escolhida nao bate com a peca da posicao.");
                    } else {
                        boolean[][] matriz = peca.movimentosValidos(tabuleiro);
                        System.out.println("Matriz de movimentos validos:");
                        tabuleiro.mostrarMatriz(matriz);

                        if (matriz[movimento.getDestino().getLinha()][movimento.getDestino().getColuna()]) {
                            Peca comida = tabuleiro.getPeca(movimento.getDestino());
                            tabuleiro.moverPeca(movimento.getOrigem(), movimento.getDestino());

                            if (comida != null) {
                                System.out.println("Peca comida: " + comida.getDesenho());
                            }

                            vez = vez.oposta();
                        } else {
                            System.out.println("Movimento invalido.");
                        }
                    }
                }
            }
        }

        scanner.close();
        System.out.println("Jogo terminado.");
    }

    public static Posicao lerPosicao(String texto) {
        if (texto.length() != 2) {
            return null;
        }

        char colunaChar = texto.charAt(0);
        char linhaChar = texto.charAt(1);

        if (colunaChar < 'a' || colunaChar > 'h') {
            return null;
        }

        if (linhaChar < '1' || linhaChar > '8') {
            return null;
        }

        int coluna = colunaChar - 'a';
        int linha = 8 - (linhaChar - '0');

        return new Posicao(linha, coluna);
    }
}
