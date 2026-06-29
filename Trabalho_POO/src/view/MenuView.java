package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.FieldView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MenuView extends JPanel {
    private JButton btnJogar;
    private JButton btnSair;
    private Image imagemFundo;

    public MenuView(String caminhoImagem) {
        this.setLayout(new GridBagLayout());
        try {
            java.net.URL imgURL = getClass().getResource(caminhoImagem);
            if (imgURL != null) {
                imagemFundo = ImageIO.read(imgURL);
            } else {
                // Tenta carregar usando a pasta src diretamente caso a IDE não tenha copiado pro out/bin
                imagemFundo = ImageIO.read(new File("src" + caminhoImagem));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar a imagem: " + caminhoImagem);
            e.printStackTrace();
        }

        // instâncias e cfg dos elementos da tela //

        btnJogar = new JButton("Jogar");
        btnJogar.setFont(new Font("Arial", Font.BOLD, 15));
        btnJogar.setPreferredSize(new Dimension(100, 40));
        btnJogar.setFocusPainted(false);
        btnJogar.setBorderPainted(false);
        btnJogar.setCursor(new Cursor(Cursor.HAND_CURSOR));


        btnSair = new JButton("Sair");
        btnSair.setFont(new Font("Arial", Font.BOLD, 15));
        btnSair.setPreferredSize(new Dimension(100, 40));
        btnSair.setFocusPainted(false);
        btnSair.setBorderPainted(false);
        btnSair.setCursor(new Cursor(Cursor.HAND_CURSOR));



        JLabel titulo = new JLabel("Xadrez 1.0");
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setForeground(Color.WHITE);
        titulo.setBackground(Color.WHITE);
        titulo.setOpaque(false);
        titulo.setPreferredSize(new Dimension(230, 40));

        // ---------------------------------------------------------------//

        // configuração layout dos elementos da tela //

        GridBagConstraints gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridx = 0;
        gbcTitulo.gridy = 0;
        gbcTitulo.anchor = GridBagConstraints.EAST;
        gbcTitulo.weightx = 1.0;
        gbcTitulo.insets = new Insets(0, 0, 20, 20); // 50px de margem à esquerda, 10px abaixo
        this.add(titulo, gbcTitulo);

        GridBagConstraints gbcBotao = new GridBagConstraints();
        gbcBotao.gridx = 0;
        gbcBotao.gridy = 1;
        gbcBotao.anchor = GridBagConstraints.EAST;
        gbcBotao.insets = new Insets(0, 0, 100, 95); // 50px de margem à esquerda
        this.add(btnJogar, gbcBotao);

        GridBagConstraints gbcBotaoSair = new GridBagConstraints();
        gbcBotaoSair.gridx = 0;
        gbcBotaoSair.gridy = 1;
        gbcBotaoSair.anchor = GridBagConstraints.EAST;
        gbcBotaoSair.insets = new Insets(0, 0, 0, 95); // 50px de margem à esquerda
        this.add(btnSair, gbcBotaoSair);


        // --------------------------------------------------------------- //
    }
    
    public JButton getBtnJogar() {
        return btnJogar;
    }

    public JButton getBtnSair() {
        return btnSair;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a imagem cobrindo toda a largura e altura do painel
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
