package controller;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import model.Cor;
import model.Movimento;
import model.Posicao;
import model.Tabuleiro;
import model.pecas.Peca;
import utils.AudioUtil;
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
    private boolean modoIA = false;

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

        if (modoIA && tabuleiroModel.getVez() == Cor.PRETA) {
            return;
        }

        // pega quem foi clicado
        BotaoCasa botaoClicado = (BotaoCasa) actionEvent.getSource();

        // pega linha e coluna do botao clicado
        int linha = botaoClicado.getLinha();
        int coluna = botaoClicado.getColuna();

        if (posicaoSelecionada == null) {
            Posicao pos = new Posicao(linha, coluna);
            Peca peca = tabuleiroModel.getPeca(pos);
            if (peca != null && peca.getCor() == tabuleiroModel.getVez()) {
                this.posicaoSelecionada = pos;
                tabuleiroView.destacarCasa(linha, coluna);
                
                // pede pro Model os movimentos e marca na View
                boolean[][] movimentos = peca.movimentosValidos(tabuleiroModel);
                for (int l = 0; l < 8; l++) {
                    for (int c = 0; c < 8; c++) {
                        if (movimentos[l][c]) {
                            if (tabuleiroModel.getPeca(l, c) == null) {
                                tabuleiroView.marcaMovimentosValidos(l, c);
                            } else {
                                tabuleiroView.marcaPecaParaEliminar(l, c);
                            }
                        }
                    }
                }
            } else {
                System.out.println("Não é a sua vez ou casa vazia!");
            }
        } else {
            // se clicou na mesma casa, cancela a seleção
            if (posicaoSelecionada.getLinha() == linha && posicaoSelecionada.getColuna() == coluna) {
                this.posicaoSelecionada = null;
                tabuleiroView.limparDestaques();
                tabuleiroView.desenharPecas(tabuleiroModel); // Restaura os icones originais
                return;
            }

            Posicao posicaoBotaoClicado = new Posicao(linha, coluna);

            boolean moveu = tabuleiroModel.moverPeca(posicaoSelecionada, posicaoBotaoClicado);
            if (moveu) {
                AudioUtil.tocarEfeitoSonoro("/resources/movimento.wav");
                tabuleiroView.desenharPecas(tabuleiroModel);
                tabuleiroModel.mudaVez();
                janelaPrincipal.atualizarLabelVez(tabuleiroModel.getVez());

                verificarXeque(posicaoBotaoClicado);

                if (!jogoAcabou && modoIA && tabuleiroModel.getVez() == Cor.PRETA) {
                    jogarIA();
                }
            }

            this.posicaoSelecionada = null;
            if (!jogoAcabou) {
                tabuleiroView.limparDestaques();
                tabuleiroView.desenharPecas(tabuleiroModel); // Restaura os icones originais
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

    public void comecarJogoNormal() {
        modoIA = false;
        reiniciarJogo();
    }

    public void comecarJogoComIA() {
        modoIA = true;
        reiniciarJogo();
    }

    private void verificarXeque(Posicao posicaoAtacante) {
        if (tabuleiroModel.estaEmChequeMate(tabuleiroModel.getVez())) {
            Posicao posRei = tabuleiroModel.getPosicaoRei(tabuleiroModel.getVez());
            tabuleiroView.destacarXequeMate(posRei.getLinha(), posRei.getColuna(), posicaoAtacante.getLinha(), posicaoAtacante.getColuna());

            String vencedor = tabuleiroModel.getVez() == Cor.BRANCA ? "PRETAS" : "BRANCAS";

            boolean querJogarDeNovo = janelaPrincipal.perguntarJogarNovamente(vencedor);

            if (querJogarDeNovo) {
                reiniciarJogo();
            } else {
                jogoAcabou = true; // trava o tabuleiro
            }
        } else if (tabuleiroModel.estaEmCheque(tabuleiroModel.getVez())) {
            janelaPrincipal.exibirAvisoXeque();
        }
    }

    private void jogarIA() {
        ArrayList<Movimento> movimentos = movimentosValidosIA(true);
        Random random = new Random();

        if (movimentos.size() == 0) {
            movimentos = movimentosValidosIA(false);
        }

        if (movimentos.size() == 0) {
            jogoAcabou = true;
            return;
        }

        Movimento movimento = movimentos.get(random.nextInt(movimentos.size()));
        boolean moveu = tabuleiroModel.moverPeca(movimento.getOrigem(), movimento.getDestino());

        if (moveu) {
            AudioUtil.tocarEfeitoSonoro("/resources/movimento.wav");
            tabuleiroView.desenharPecas(tabuleiroModel);
            tabuleiroModel.mudaVez();
            janelaPrincipal.atualizarLabelVez(tabuleiroModel.getVez());
            verificarXeque(movimento.getDestino());
        }
    }

    private ArrayList<Movimento> movimentosValidosIA(boolean precisaComer) {
        ArrayList<Movimento> movimentos = new ArrayList<Movimento>();

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = tabuleiroModel.getPeca(linha, coluna);
                if (peca != null && peca.getCor() == Cor.PRETA) {
                    boolean[][] matriz = peca.movimentosValidos(tabuleiroModel);
                    for (int linhaDestino = 0; linhaDestino < 8; linhaDestino++) {
                        for (int colunaDestino = 0; colunaDestino < 8; colunaDestino++) {
                            if (matriz[linhaDestino][colunaDestino]) {
                                Posicao origem = new Posicao(linha, coluna);
                                Posicao destino = new Posicao(linhaDestino, colunaDestino);
                                Peca pecaDestino = tabuleiroModel.getPeca(destino);
                                boolean come = pecaDestino != null && pecaDestino.getCor() == Cor.BRANCA;

                                if ((!precisaComer || come)
                                        && tabuleiroModel.movimentoNaoDeixaReiEmCheque(origem, destino, Cor.PRETA)) {
                                    movimentos.add(new Movimento(origem, destino));
                                }
                            }
                        }
                    }
                }
            }
        }

        return movimentos;
    }

    public Posicao getPosicaoSelecionada() {
        return posicaoSelecionada;
    }

    public Tabuleiro getTabuleiroModel() {
        return tabuleiroModel;
    }
}
