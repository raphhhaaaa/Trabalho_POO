import controller.TabuleiroController;
import model.Tabuleiro;
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

            janela.setVisible(true);
        });
      }
    }