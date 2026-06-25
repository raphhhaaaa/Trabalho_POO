package controller;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.Cor;
import model.Posicao;
import model.Tabuleiro;
import model.pecas.Peca;
import view.JanelaPrincipal;
import view.PainelTabuleiro;
import view.BotaoCasa;

public class TabuleiroController implements ActionListener {

    private Tabuleiro tabuleiroModel;
    private PainelTabuleiro tabuleiroView;
    private JanelaPrincipal janelaPrincipal;

    // memoria do clique
    private Posicao posicaoSelecionada = null;

    public TabuleiroController(Tabuleiro tabuleiroModel, JanelaPrincipal janelaPrincipal) {
        this.tabuleiroModel = tabuleiroModel;
        this.janelaPrincipal = janelaPrincipal;
        this.tabuleiroView = janelaPrincipal.getTabuleiro();

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

        if (posicaoSelecionada == null) {
            Posicao pos = new Posicao(linha, coluna);
            Peca peca = tabuleiroModel.getPeca(pos);
            if (peca != null && peca.getCor() == tabuleiroModel.getVez()) {
                this.posicaoSelecionada = pos;
            } else {
                System.out.println("Não é a sua vez ou casa vazia!");
            }
        } else {
            Posicao posicaoBotaoClicado = new Posicao(linha, coluna);

            boolean moveu = tabuleiroModel.moverPeca(posicaoSelecionada, posicaoBotaoClicado);
            if (moveu) {
                tabuleiroView.desenharPecas(tabuleiroModel);
                tabuleiroModel.mudaVez();
                janelaPrincipal.textoIndicaVez(tabuleiroModel.getVez(), janelaPrincipal);
            }

            this.posicaoSelecionada = null;
        }
    }

    public Posicao getPosicaoSelecionada() {
        return posicaoSelecionada;
    }
}
