package tabuleiro;

import pecas.Peca;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    // atributos
    private Peca peca;
    private Integer colunas;
    private Integer linhas;

    // construtor
    public Tabuleiro() {}

    // métodos

    private String geraMatriz() {

        List<List<Integer>> matriz = new ArrayList<>();

        for (int l = 0; l <= 8; l++) {
            List<Quadrado> listaLinha = new ArrayList<>();
            List<Quadrado> listaColuna = new ArrayList<>();
            Quadrado quadrado = new Quadrado();

            listaLinha



            matriz.add(listaLinha);
            for (int c = 0; c <= 8; c++) {

            }
        }
    }
}
