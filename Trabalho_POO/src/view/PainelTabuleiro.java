package view;

import dto.IconesDTO;
import model.pecas.Peca;
import model.Tabuleiro;
import utils.ImageUtil;

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
                    botao.setBackground(new Color(211, 206, 206));
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
        botoes[linha][coluna].setBackground(new Color(144, 241, 95));
    }

    public void marcaMovimentosValidos(int linha, int coluna) {
        botoes[linha][coluna].setIcon(ImageUtil.redimensionaImagem("/resources/dot.png", 32, 32));
    }

    public void marcaPecaParaEliminar(int linha, int coluna) {
        botoes[linha][coluna].setBackground(new Color(255, 102, 102));
    }

    public void destacarXequeMate(int linhaRei, int colunaRei, int linhaAtacante, int colunaAtacante) {
        Color vermelhoClaro = new Color(255, 102, 102);
        botoes[linhaRei][colunaRei].setBackground(vermelhoClaro);
        botoes[linhaAtacante][colunaAtacante].setBackground(vermelhoClaro);
        
        botoes[linhaRei][colunaRei].paintImmediately(0, 0, botoes[linhaRei][colunaRei].getWidth(), botoes[linhaRei][colunaRei].getHeight());
        botoes[linhaAtacante][colunaAtacante].paintImmediately(0, 0, botoes[linhaAtacante][colunaAtacante].getWidth(), botoes[linhaAtacante][colunaAtacante].getHeight());
    }

    public void limparDestaques() {
        for (int l = 0; l < 8; l++) {
            for (int c = 0; c < 8; c++) {
                if ((l + c) % 2 == 0) {
                    botoes[l][c].setBackground(Color.DARK_GRAY);
                } else {
                    botoes[l][c].setBackground(new Color(211, 206, 206));
                }
            }
        }
    }
}
