package controller;

import view.JanelaPrincipal;
import view.MenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private MenuView menuView;
    private JanelaPrincipal janelaPrincipal;

    public MenuController(MenuView menuView, JanelaPrincipal janelaPrincipal) {
        this.menuView = menuView;
        this.janelaPrincipal = janelaPrincipal;
        
        // O controller captura o evento de clique dos botões
        this.menuView.getBtnJogar().addActionListener(this);
        this.menuView.getBtnSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(menuView.getBtnJogar())) {
            // se foi o btn jogar que foi clicado, manda a view realizar a transição de tela
            janelaPrincipal.mostrarTabuleiro();
        } else if (e.getSource().equals(menuView.getBtnSair())) {
            // se foi o btn jogar que foi clicado, manda o programa encerrar
            System.exit(0);
        }

    }
}
