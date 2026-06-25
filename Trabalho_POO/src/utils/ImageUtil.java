package utils;

import javax.swing.ImageIcon;
import java.awt.Image;

public class ImageUtil {

    // isso aq é pq o icone que eu peguei da internet tava COMICAMENTE grande e tava ocupando a TELA INTEIRA
    public static ImageIcon redimensionaImagem(String path, int largura, int altura) {
        java.net.URL imgURL = ImageUtil.class.getResource(path);
        Image img;
        if (imgURL != null) {
            img = new ImageIcon(imgURL).getImage();
        } else {
            // Fallback caso a IDE não copie a pasta resources pro bin/out
            img = new ImageIcon("src" + path).getImage();
        }

        Image scaledImg = img.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }
}