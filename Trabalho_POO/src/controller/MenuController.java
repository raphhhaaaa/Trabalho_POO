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
        
        // O controller capturao evento de clique do botão da view do menu
        this.menuView.getBtnJogar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // manda a view realizar a transição de tela
        janelaPrincipal.mostrarTabuleiro();
    }
}
