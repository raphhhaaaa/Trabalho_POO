package test;

import model.Cor;
import model.Posicao;
import model.Tabuleiro;
import model.pecas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TabuleiroTest {

    private Tabuleiro tabuleiro;

    @BeforeEach
    public void setUp() {
        // Executado antes de CADA teste para garantir um tabuleiro limpo e intocado
        tabuleiro = new Tabuleiro();
    }

    @Test
    public void testPosicaoInicial() {
        // Verifica se a Torre Preta está na posição (0,0)
        Peca peca = tabuleiro.getPeca(0, 0);
        assertNotNull(peca, "Deveria haver uma peça em (0,0)");
        assertTrue(peca instanceof Torre, "A peça em (0,0) deveria ser uma Torre");
        assertEquals(Cor.PRETA, peca.getCor(), "A Torre deveria ser preta");

        // Verifica se o Rei Branco está na posição (7,4)
        Peca rei = tabuleiro.getPeca(7, 4);
        assertNotNull(rei, "Deveria haver uma peça em (7,4)");
        assertTrue(rei instanceof Rei, "A peça em (7,4) deveria ser um Rei");
        assertEquals(Cor.BRANCA, rei.getCor(), "O Rei deveria ser branco");

        // Verifica se o meio do tabuleiro está vazio
        assertNull(tabuleiro.getPeca(4, 4), "A casa central deveria começar vazia");
    }

    @Test
    public void testMovimentoValidoPeao() {
        // O Peão branco está na linha 6. Ele deve poder andar 2 casas na primeira vez.
        boolean moveu = tabuleiro.moverPeca(new Posicao(6, 4), new Posicao(4, 4));
        
        assertTrue(moveu, "O peão deveria conseguir mover duas casas no primeiro turno.");
        assertTrue(tabuleiro.getPeca(4, 4) instanceof Peao, "O peão deveria estar na nova casa.");
        assertNull(tabuleiro.getPeca(6, 4), "A casa antiga do peão deveria estar vazia.");
    }

    @Test
    public void testMovimentoInvalidoColisao() {
        // Tenta mover a Torre branca de (7,0) para (5,0), mas o Peão branco está no meio do caminho em (6,0)
        boolean moveu = tabuleiro.moverPeca(new Posicao(7, 0), new Posicao(5, 0));
        
        assertFalse(moveu, "A Torre não deve poder pular uma peça aliada.");
        assertTrue(tabuleiro.getPeca(7, 0) instanceof Torre, "A Torre deve permanecer na casa de origem.");
    }

    @Test
    public void testMovimentoSuicida() {
        // Força um cenário de Xeque conhecido como "Mate do Louco" (ou Fool's Mate) invertido, 
        // mas aqui vamos apenas dar xeque e testar a reação do Model.
        
        tabuleiro.moverPeca(new Posicao(6, 5), new Posicao(5, 5)); // Peão Branco move f3
        tabuleiro.mudaVez();
        tabuleiro.moverPeca(new Posicao(1, 4), new Posicao(3, 4)); // Peão Preto move e5
        tabuleiro.mudaVez();
        tabuleiro.moverPeca(new Posicao(6, 6), new Posicao(4, 6)); // Peão Branco move g4
        tabuleiro.mudaVez();
        tabuleiro.moverPeca(new Posicao(0, 3), new Posicao(4, 7)); // Dama Preta move para h4 dando Xeque
        tabuleiro.mudaVez();

        // Verifica se a lógica do tabuleiro detectou o Xeque corretamente
        assertTrue(tabuleiro.estaEmCheque(Cor.BRANCA), "O Rei Branco deveria estar em xeque pela Dama preta.");

        // As peças brancas tentam ignorar o Xeque e mover um Cavalo no outro lado do tabuleiro
        boolean ignorouXeque = tabuleiro.moverPeca(new Posicao(7, 1), new Posicao(5, 2));

        // A trava deve bloquear a jogada
        assertFalse(ignorouXeque, "O jogo não deve permitir ignorar um xeque (movimento suicida).");
    }

    @Test
    public void testLogicaXequeMate() {
        // Constrói o cenário clássico do "Mate do Pastor" (Scholar's Mate)
        tabuleiro.moverPeca(new Posicao(6, 4), new Posicao(4, 4)); // Peão e4
        tabuleiro.mudaVez();
        tabuleiro.moverPeca(new Posicao(1, 4), new Posicao(3, 4)); // Peão e5
        tabuleiro.mudaVez();
        tabuleiro.moverPeca(new Posicao(7, 5), new Posicao(4, 2)); // Bispo c4
        tabuleiro.mudaVez();
        tabuleiro.moverPeca(new Posicao(0, 1), new Posicao(2, 2)); // Cavalo c6
        tabuleiro.mudaVez();
        tabuleiro.moverPeca(new Posicao(7, 3), new Posicao(3, 7)); // Dama h5
        tabuleiro.mudaVez();
        tabuleiro.moverPeca(new Posicao(0, 6), new Posicao(2, 5)); // Cavalo f6 (Um erro fatal)
        tabuleiro.mudaVez();
        
        // Xeque-Mate! Dama captura f7 protegida pelo bispo
        tabuleiro.moverPeca(new Posicao(3, 7), new Posicao(1, 5)); 
        tabuleiro.mudaVez();

        // Agora verifica se a inteligência do Tabuleiro atesta o Xeque-Mate
        assertTrue(tabuleiro.estaEmChequeMate(Cor.PRETA), "O Rei Preto deveria estar matematicamente em Xeque-Mate.");
    }
}
