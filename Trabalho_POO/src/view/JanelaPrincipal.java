package view;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import controller.TabuleiroController;
import model.Cor;
import view.BotaoCasa;
import view.PainelTabuleiro;

public class JanelaPrincipal extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private MenuView painelMenu;
    private PainelTabuleiro tabuleiro = new PainelTabuleiro();

    public JanelaPrincipal() {

        // instancia a tela de menu (sem injetar logica de transicao)
        painelMenu = new MenuView("/resources/Papel-de-Parede-de-Jogo-de-Xadres.jpg");
        painelMenu.setBackground(Color.BLACK);

        mainPanel.add(painelMenu, "menu");
        mainPanel.add(tabuleiro, "tabuleiro");

        // Vincula o painel desenhado à janela
        this.setContentPane(mainPanel);
        this.setTitle("Xadrez");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centraliza a tela
        this.setResizable(false);

        // ADICIONA OS ELEMENTOS AQUI
    }

    // auxiliar
    public void textoIndicaVez(Cor vez, JFrame frame) {
        JTextArea caixaTexto = new JTextArea("Vez das peças: " + vez.getNome() + "S");
        caixaTexto.setForeground(Color.WHITE);
        caixaTexto.setBackground(Color.BLACK);
        caixaTexto.setEditable(false);
        caixaTexto.setSize(250, 32);
        caixaTexto.setFont(new Font("Arial", Font.BOLD, 20));
        caixaTexto.setLocation(170, 280); // Posição dentro do painel
        caixaTexto.setOpaque(true);

        JLayeredPane layeredPane = frame.getLayeredPane();
        layeredPane.add(caixaTexto, JLayeredPane.POPUP_LAYER);

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caixaTexto.setVisible(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    // roda janela principal
    public static void main(String[] args) {

        // Garante que a interface gráfica seja executada com segurança
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal frame = new JanelaPrincipal();
            frame.setVisible(true);
        });
    }

    public PainelTabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public MenuView getPainelMenu() {
        return painelMenu;
    }

    public void mostrarTabuleiro() {
        cardLayout.show(mainPanel, "tabuleiro");
        textoIndicaVez(Cor.BRANCA, this);
    }
}
