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
    Cor vez = Cor.BRANCA;

    // memoria do clique
    private BotaoCasa botaoSelecionado = null;

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
        
        this.janelaPrincipal.textoIndicaVez(this.vez, this.janelaPrincipal);
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
            Posicao pos = new Posicao(linha, coluna);
            Peca peca = tabuleiroModel.getPeca(pos);
            if (peca != null && peca.getCor() == this.vez) {
                this.botaoSelecionado = botaoClicado;
            } else {
                System.out.println("Não é a sua vez ou casa vazia!");
            }
        } else {
            Posicao posicaoBotaoSelecionado = new Posicao(botaoSelecionado.getLinha(), botaoSelecionado.getColuna());
            Posicao posicaoBotaoClicado = new Posicao(botaoClicado.getLinha(), botaoClicado.getColuna());

            boolean moveu = tabuleiroModel.moverPeca(posicaoBotaoSelecionado,  posicaoBotaoClicado);
            if (moveu) {
                tabuleiroView.desenharPecas(tabuleiroModel); // ainda precisa revisar essa parada o movimento ta meio estranho e parandon de funcionar as vezes
                mudaVez(this.vez);
                janelaPrincipal.textoIndicaVez(this.vez, janelaPrincipal);
            }

            this.botaoSelecionado = null;
        }
    }

    public void mudaVez(Cor vez) {
        if (vez.getNome().equals("BRANCA"))
            setVez(Cor.PRETA);
        else setVez(Cor.BRANCA);
    }

    public Cor getVez() {
        return vez;
    }

    public void setVez(Cor vez) {
        this.vez = vez;
    }

    public BotaoCasa getBotaoSelecionado() {
        return botaoSelecionado;
    }
}
