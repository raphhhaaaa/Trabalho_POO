import controller.MenuController;
import controller.TabuleiroController;
import model.Tabuleiro;
import utils.AudioUtil;
import view.JanelaPrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // inicializa o TopModel (aqueles cara tlgd)
            Tabuleiro tabuleiro = new Tabuleiro();

            // iicializa a View
            JanelaPrincipal janela = new JanelaPrincipal();

            // manda a View desenhar o estado atual do Model
            janela.getTabuleiro().desenharPecas(tabuleiro);

            TabuleiroController controller = new TabuleiroController(tabuleiro, janela);
            MenuController menuController = new MenuController(janela.getPainelMenu(), janela, controller);

            // comeca tocando musica por padrao
            AudioUtil.tocarMusica("/resources/Maarten-Schellekens-On-Cloud-Nine-_Jazz-Remix_.wav");


            janela.setVisible(true);
        });
      }
    }