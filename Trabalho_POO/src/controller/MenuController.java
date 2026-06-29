package controller;

import view.JanelaPrincipal;
import view.MenuView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        // mouselisteners

        menuView.getBtnJogar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuView.getBtnJogar().setSize(new Dimension(105, 45));
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
        }

    }
}
