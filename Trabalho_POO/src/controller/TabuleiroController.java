package controller;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Cor;
import model.Posicao;
import model.Tabuleiro;
import model.pecas.Peca;
import view.JanelaPrincipal;
import view.PainelTabuleiro;
import view.BotaoCasa;

import javax.swing.*;

public class TabuleiroController implements ActionListener {

    private Tabuleiro tabuleiroModel;
    private PainelTabuleiro tabuleiroView;
    private JanelaPrincipal janelaPrincipal;

    // memoria do clique
    private Posicao posicaoSelecionada = null;
    private boolean jogoAcabou = false;

    public TabuleiroController(Tabuleiro tabuleiroModel, JanelaPrincipal janelaPrincipal) {
        this.tabuleiroModel = tabuleiroModel;
        this.janelaPrincipal = janelaPrincipal;
        this.tabuleiroView = janelaPrincipal.getTabuleiro();


        // adiciona actionlistener pra cada um dos botoes (que representam cada casa do tabuleiro)
        BotaoCasa[][] botoes = tabuleiroView.getBotoes();

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                botoes[linha][coluna].addActionListener(this);
            }
        }

        // mouselisteners

        janelaPrincipal.getBtnVoltarMenu().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                janelaPrincipal.getBtnVoltarMenu().setContentAreaFilled(true);
                janelaPrincipal.getBtnVoltarMenu().setBackground(Color.lightGray);
                janelaPrincipal.getBtnVoltarMenu().setFont(new Font("Arial", Font.BOLD, 15));
                janelaPrincipal.getLabelVez().setBorder(BorderFactory.createEmptyBorder(10, 185, 10, 10));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                janelaPrincipal.getBtnVoltarMenu().setContentAreaFilled(false);
                janelaPrincipal.getBtnVoltarMenu().setFont(new Font("Arial", Font.BOLD, 18));
                janelaPrincipal.getLabelVez().setBorder(BorderFactory.createEmptyBorder(10, 160, 10, 10));
            }

        });

    }

    // o que acontece quando o botaoCasa é clicado
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (jogoAcabou) {
            return; // se o jogo acabou, ignora os cliques
        }

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
                tabuleiroView.destacarCasa(linha, coluna);
            } else {
                System.out.println("Não é a sua vez ou casa vazia!");
            }
        } else {
            // se clicou na mesma casa, cancela a seleção
            if (posicaoSelecionada.getLinha() == linha && posicaoSelecionada.getColuna() == coluna) {
                this.posicaoSelecionada = null;
                tabuleiroView.limparDestaques();
                return;
            }

            Posicao posicaoBotaoClicado = new Posicao(linha, coluna);

            boolean moveu = tabuleiroModel.moverPeca(posicaoSelecionada, posicaoBotaoClicado);
            if (moveu) {
                tabuleiroView.desenharPecas(tabuleiroModel);
                tabuleiroModel.mudaVez();
                janelaPrincipal.atualizarLabelVez(tabuleiroModel.getVez());

                if (tabuleiroModel.estaEmChequeMate(tabuleiroModel.getVez())) {
                    Posicao posRei = tabuleiroModel.getPosicaoRei(tabuleiroModel.getVez());
                    tabuleiroView.destacarXequeMate(posRei.getLinha(), posRei.getColuna(), posicaoBotaoClicado.getLinha(), posicaoBotaoClicado.getColuna());

                    String vencedor = tabuleiroModel.getVez() == Cor.BRANCA ? "PRETAS" : "BRANCAS";
                    
                    boolean querJogarDeNovo = janelaPrincipal.perguntarJogarNovamente(vencedor);

                    if (querJogarDeNovo) {
                        reiniciarJogo();
                        return; // sai do metodo para não resetar variáveis desnecessariamente
                    } else {
                        jogoAcabou = true; // trava o tabuleiro
                    }
                } else if (tabuleiroModel.estaEmCheque(tabuleiroModel.getVez())) {
                    janelaPrincipal.exibirAvisoXeque();
                }
            }

            this.posicaoSelecionada = null;
            if (!jogoAcabou) {
                tabuleiroView.limparDestaques();
            }
        }
    }

    private void reiniciarJogo() {
        this.tabuleiroModel = new Tabuleiro(); // Cria um novo jogo
        this.posicaoSelecionada = null;
        this.jogoAcabou = false;

        tabuleiroView.limparDestaques();
        tabuleiroView.desenharPecas(tabuleiroModel);
        janelaPrincipal.atualizarLabelVez(tabuleiroModel.getVez());
    }

    public Posicao getPosicaoSelecionada() {
        return posicaoSelecionada;
    }
}
