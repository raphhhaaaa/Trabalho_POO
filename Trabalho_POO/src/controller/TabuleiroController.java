package controller;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.Posicao;
import model.Tabuleiro;
import view.PainelTabuleiro;
import view.BotaoCasa;

public class TabuleiroController implements ActionListener {

    private Tabuleiro tabuleiroModel;
    private PainelTabuleiro tabuleiroView;

    // memoria do clique
    private BotaoCasa botaoSelecionado = null;

    public TabuleiroController(Tabuleiro tabuleiroModel, PainelTabuleiro tabuleiroView) {
        this.tabuleiroModel = tabuleiroModel;
        this.tabuleiroView = tabuleiroView;

        BotaoCasa[][] botoes = tabuleiroView.getBotoes();

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                botoes[linha][coluna].addActionListener(this);
            }
        }
    }

    // interface interna do awt, chamada quando o botao é clicado
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // pega quem foi clicado
        BotaoCasa botaoClicado = (BotaoCasa) actionEvent.getSource();

        // pega linha e coluna do botao clicado
        int linha = botaoClicado.getLinha();
        int coluna = botaoClicado.getColuna();

        // debug, remover depois
        System.out.println("Clicaram na linha " + linha + " e coluna " + coluna);

        if (botaoSelecionado == null) {
            this.botaoSelecionado = botaoClicado;
        } else {
            Posicao posicaoBotaoSelecionado = new Posicao(botaoSelecionado.getLinha(), botaoSelecionado.getColuna());
            Posicao posicaoBotaoClicado = new Posicao(botaoClicado.getLinha(), botaoClicado.getColuna());

            tabuleiroModel.moverPeca(posicaoBotaoSelecionado,  posicaoBotaoClicado);

            this.botaoSelecionado = null;
        }

    }
}
