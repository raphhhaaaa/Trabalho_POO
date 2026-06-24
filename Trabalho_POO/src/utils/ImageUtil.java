package utils;

import javax.swing.ImageIcon;
import java.awt.Image;

public class ImageUtil {

    // isso aq é pq o icone que eu peguei da internet tava COMICAMENTE grande e tava ocupando a TELA INTEIRA
    public static ImageIcon redimensionaImagem(String path, int largura, int altura) {
        ImageIcon icon = new ImageIcon(ImageUtil.class.getResource(path));

        Image img = icon.getImage()
                .getScaledInstance(largura, altura, Image.SCALE_SMOOTH);

        return new ImageIcon(img);
    }
}