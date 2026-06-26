package view;

import controller.TabuleiroController;
import dto.IconesDTO;
import model.pecas.Peca;
import model.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class PainelTabuleiro extends JPanel {

    private IconesDTO iconesDTO = new IconesDTO();
    private BotaoCasa[][] botoes = new BotaoCasa[8][8];

    public PainelTabuleiro() {
        setLayout(new GridLayout(8, 8));

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                BotaoCasa botao = new BotaoCasa(linha, coluna);

                if ((linha + coluna) % 2 == 0) {
                    botao.setBackground(Color.DARK_GRAY);
                } else {
                    botao.setBackground(Color.WHITE);
                }
                botoes[linha][coluna] = botao;
                add(botao);
            }
        }
    }

    public void desenharPecas(Tabuleiro tabuleiroModel) {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                BotaoCasa botao = botoes[linha][coluna];
                Peca peca = tabuleiroModel.getPeca(linha, coluna);

                if (peca == null) {
                    botao.setIcon(null);
                } else {
                    botao.setIcon(obterIconeParaPeca(peca));
                }
            }
        }
    }

    public ImageIcon obterIconeParaPeca(Peca peca) {
        String classe = peca.getClass().getSimpleName();
        String cor = peca.getCor().getNome(); // "BRANCA" ou "PRETA"

        if (cor.equals("BRANCA")) {
            switch (classe) {
                case "Peao": return iconesDTO.getPeaoBranco();
                case "Torre": return iconesDTO.getTorreBranco();
                case "Cavalo": return iconesDTO.getCavaloBranco();
                case "Bispo": return iconesDTO.getBispoBranco();
                case "Dama": return iconesDTO.getDamaBranco();
                case "Rei": return iconesDTO.getReiBranco();
            }
        } else {
            switch (classe) {
                case "Peao": return iconesDTO.getPeaoPreto();
                case "Torre": return iconesDTO.getTorrePreto();
                case "Cavalo": return iconesDTO.getCavaloPreto();
                case "Bispo": return iconesDTO.getBispoPreto();
                case "Dama": return iconesDTO.getDamaPreto();
                case "Rei": return iconesDTO.getReiPreto();
            }
        }
        return null;
    }

    public BotaoCasa[][] getBotoes() {
        return botoes;
    }

    public void destacarCasa(int linha, int coluna) {
        botoes[linha][coluna].setBackground(Color.lightGray);
    }

    public void limparDestaques() {
        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                if ((l + c) % 2 == 0) {
                    botoes[l][c].setBackground(Color.DARK_GRAY);
                } else {
                    botoes[l][c].setBackground(Color.WHITE);
                }
            }
        }
    }
}
