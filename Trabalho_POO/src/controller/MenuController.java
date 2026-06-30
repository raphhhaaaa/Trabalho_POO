package controller;

import utils.AudioUtil;
import utils.ImageUtil;
import view.JanelaPrincipal;
import view.MenuView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URL;
import java.sql.SQLOutput;

public class MenuController implements ActionListener {
    private MenuView menuView;
    private JanelaPrincipal janelaPrincipal;

    public MenuController(MenuView menuView, JanelaPrincipal janelaPrincipal) {
        this.menuView = menuView;
        this.janelaPrincipal = janelaPrincipal;
        
        // O controller captura o evento de clique dos botões
        this.menuView.getBtnJogar().addActionListener(this);
        this.menuView.getBtnSair().addActionListener(this);
        this.janelaPrincipal.getBtnVoltarMenu().addActionListener(this);
        this.menuView.getBtnToggleMusica().addActionListener(this);

        // mouselisteners

        menuView.getBtnJogar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuView.getBtnJogar().setSize(new Dimension(105, 45));
                AudioUtil.tocarEfeitoSonoro("/resources/som-select-menu.wav");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuView.getBtnJogar().setSize(new Dimension(100, 40));
            }
        });

        menuView.getBtnSair().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuView.getBtnSair().setSize(new Dimension(105, 45));
                AudioUtil.tocarEfeitoSonoro("/resources/som-select-menu.wav");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuView.getBtnSair().setSize(new Dimension(100, 40));
            }
        });

    }


    // o que acontece quando o btnJogar ou btnSair é clicado
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(menuView.getBtnJogar())) {
            // se foi o btn jogar que foi clicado, manda a view realizar a transição de tela
            janelaPrincipal.mostrarTabuleiro();
        } else if (e.getSource().equals(menuView.getBtnSair())) {
            // se foi o btn jogar que foi clicado, manda o programa encerrar
            System.exit(0);
        } else if (e.getSource().equals(janelaPrincipal.getBtnVoltarMenu())) {
            janelaPrincipal.mostrarMenu();
        } else if (e.getSource().equals(menuView.getBtnToggleMusica())) {
            // logica de mutar e desmutar musica e alternar o icone
            if (!AudioUtil.musicaTocando()) {
                System.out.println("musica tocando");
                AudioUtil.tocarMusica("/resources/Maarten-Schellekens-On-Cloud-Nine-_Jazz-Remix_.wav");
                menuView.getBtnToggleMusica().setIcon(ImageUtil.redimensionaImagem("/resources/icone-audio-aberto.png", 64, 64));
            } else {
                System.out.println("musica nao tocando");
                AudioUtil.pararMusica();
                menuView.getBtnToggleMusica().setIcon(ImageUtil.redimensionaImagem("/resources/icone-audio-fechado.png", 64, 64));
            }
        }

    }
}
