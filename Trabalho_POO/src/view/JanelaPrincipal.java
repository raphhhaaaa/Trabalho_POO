package view;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
//        JPanel painelBase = new JPanel();
//        painelBase.setOpaque(true);
//        painelBase.setBounds(0, 0, 400, 300);

        JTextArea caixaTexto = new JTextArea("Vez das");
        caixaTexto.setForeground(Color.WHITE);
        caixaTexto.setBackground(Color.BLACK);
        caixaTexto.setEditable(false);
        caixaTexto.setSize(125, 30);
        caixaTexto.setFont(new Font("Arial", Font.BOLD, 20));
        caixaTexto.setLocation(220, 280); // Posição dentro do painel
        caixaTexto.setOpaque(true);

        JLayeredPane layeredPane = getLayeredPane();
//        layeredPane.add(painelBase, JLayeredPane.DEFAULT_LAYER);
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
}
