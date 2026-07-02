package view;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import model.Cor;
import view.BotaoCasa;
import view.PainelTabuleiro;

public class JanelaPrincipal extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private MenuView painelMenu;
    private PainelTabuleiro tabuleiro = new PainelTabuleiro();
    private JLabel labelVez;
    private JButton btnVoltarMenu;

    public JanelaPrincipal() {

        // instancia a tela de menu (sem injetar logica de transicao)
        painelMenu = new MenuView("/resources/plano-de-fundo-xadrez2.jpg");
        painelMenu.setBackground(Color.BLACK);

        // label que mostra a cor da peça dona da jogada
        labelVez = new JLabel("Vez das peças: BRANCAS", SwingConstants.CENTER);
        labelVez.setFont(new Font("Arial", Font.BOLD, 18));
        labelVez.setBorder(BorderFactory.createEmptyBorder(10, 160, 10, 10));

        // botão de voltar ao menu
        btnVoltarMenu = new JButton("\u2190 Voltar ao menu");
        btnVoltarMenu.setFocusPainted(false);
        btnVoltarMenu.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltarMenu.setContentAreaFilled(false); // Remove o fundo
        btnVoltarMenu.setBorderPainted(false);      // Remove a borda (opcional)
        btnVoltarMenu.setFocusPainted(false);
        btnVoltarMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // seta borda esquerda vazia ao redor do tabuleiro
        tabuleiro.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));


        // borda inferior com letras (A - H)
        JPanel painelLetras = new JPanel(new GridLayout(1, 8));
        String[] letras = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (String letra : letras) {
            JLabel lbl = new JLabel(letra, SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.BOLD, 14));
            painelLetras.add(lbl);
        }


        // borda esquerda com numeros (1 - 8)
        JPanel painelNumeros = new JPanel(new GridLayout(8, 1));
        String[] numeros = {"8", "7", "6", "5", "4", "3", "2", "1"};
        for (String num : numeros) {
            JLabel lbl = new JLabel(num, SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.BOLD, 14));
            lbl.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 10));
            painelNumeros.add(lbl);
        }


        // cria instancia do tabuleiro com panel
        JPanel tabuleiroEixoX = new JPanel(new BorderLayout());
        tabuleiroEixoX.add(tabuleiro, BorderLayout.CENTER);
        tabuleiroEixoX.add(painelLetras, BorderLayout.SOUTH);


        // cria tabuleiro com os dois paineis (letras e numeros)
        JPanel tabuleiroComEixos = new JPanel(new BorderLayout());
        tabuleiroComEixos.add(painelNumeros, BorderLayout.WEST);
        tabuleiroComEixos.add(tabuleiroEixoX, BorderLayout.CENTER);
        tabuleiroComEixos.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // cria painel de topo contendo o botão de voltar e a label de vez
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.add(btnVoltarMenu, BorderLayout.WEST);
        painelTopo.add(labelVez, BorderLayout.CENTER);

        // instancia painel final com o painelTopo e os/as bordas/paines de letras e numeros
        JPanel painelJogo = new JPanel(new BorderLayout());
        painelJogo.add(painelTopo, BorderLayout.NORTH);
        painelJogo.add(tabuleiroComEixos, BorderLayout.CENTER);

        // adiciona menu e tabuleiro à janela principal
        mainPanel.add(painelMenu, "menu");
        mainPanel.add(painelJogo, "tabuleiro");

        // vincula o painel desenhado à janela
        this.setContentPane(mainPanel);
        this.setTitle("Xadrez");
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centraliza a tela
        this.setResizable(false);

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

    public JButton getBtnVoltarMenu() {
        return btnVoltarMenu;
    }

    public JLabel getLabelVez() {
        return labelVez;
    }

    public void mostrarMenu() {
        cardLayout.show(mainPanel, "menu");
    }

    public void mostrarTabuleiro() {
        cardLayout.show(mainPanel, "tabuleiro");
        atualizarLabelVez(Cor.BRANCA);
    }

    public void atualizarLabelVez(Cor vez) {
        labelVez.setText("Vez das peças: " + vez.getNome() + "S");
        labelVez.revalidate();
        labelVez.repaint();
    }

    public void exibirAvisoXeque() {
        JOptionPane.showMessageDialog(this, "Xeque!", "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public boolean perguntarJogarNovamente(String vencedor) {
        int opcao = JOptionPane.showConfirmDialog(
            this, 
            "XEQUE-MATE! As peças " + vencedor + " venceram!\nDeseja jogar novamente?", 
            "Fim de Jogo", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE
        );
        return opcao == JOptionPane.YES_OPTION;
    }
}
