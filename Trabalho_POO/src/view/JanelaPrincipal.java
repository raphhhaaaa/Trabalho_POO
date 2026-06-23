package view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import view.BotaoCasa;
import view.PainelTabuleiro;

public class JanelaPrincipal extends JFrame {
    private JPanel mainPanel = new JPanel(new GridBagLayout());
    private PainelTabuleiro tabuleiro = new PainelTabuleiro();
    public JanelaPrincipal() {
        // Vincula o painel desenhado à janela
        this.setContentPane(tabuleiro);
        this.setTitle("Xadrez");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centraliza a tela
        this.setResizable(false);

        // ADICIONA OS ELEMENTOS AQUI

    }


    // roda janela principal
    public static void main(String[] args) {

        // Garante que a interface gráfica seja executada com segurança
        SwingUtilities.invokeLater(() -> {
            JanelaPrincipal frame = new JanelaPrincipal();
            frame.setVisible(true);
        });
    }
}
