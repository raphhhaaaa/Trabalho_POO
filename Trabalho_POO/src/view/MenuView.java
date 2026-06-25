package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.FieldView;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuView extends JPanel {
    private JButton btnJogar;
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
        btnJogar = new JButton("Jogar");
        btnJogar.setFont(new Font("Arial", Font.BOLD, 15));
        btnJogar.setPreferredSize(new Dimension(100, 40));
        btnJogar.setFocusPainted(false);
        btnJogar.setBorderPainted(false);
        btnJogar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel titulo = new JLabel("Clique em Jogar para iniciar");
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setForeground(Color.BLACK);
        titulo.setBackground(Color.WHITE);
        titulo.setOpaque(true);
        titulo.setPreferredSize(new Dimension(350, 40));

        GridBagConstraints gbcTitulo = new GridBagConstraints();
        gbcTitulo.gridx = 0;
        gbcTitulo.gridy = 0;
        gbcTitulo.insets = new Insets(0, 0, 10, 0); // 50px de espaçamento abaixo do titulo

        this.add(titulo, gbcTitulo);

        GridBagConstraints gbcBotao = new GridBagConstraints();
        gbcBotao.gridx = 0;
        gbcBotao.gridy = 1;

        this.add(btnJogar, gbcBotao);
    }
    
    public JButton getBtnJogar() {
        return btnJogar;
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
