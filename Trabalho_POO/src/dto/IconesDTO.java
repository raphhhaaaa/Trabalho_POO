package dto;

import utils.ImageUtil;

import javax.swing.*;


/**
 * isso é um dto (data transfer object) é basicamente uma classe auxiliar pra transferencia de dados entre diferentes camadas
 * do código. no nosso caso, para transferir os icones pro PainelTabuleiro colocar na tela (view). é bom pq separa responsabi
 * lidaes e nao polui o codigo da view com lógica
 */

public class IconesDTO {

    // ICONES
    ImageIcon cavaloBranco = ImageUtil.redimensionaImagem("/resources/cavalo-branco.png", 64, 64);
    ImageIcon cavaloPreto = ImageUtil.redimensionaImagem("/resources/cavalo-preto.png", 64, 64);

    ImageIcon reiBranco = ImageUtil.redimensionaImagem("/resources/rei-branco.png", 64, 64);
    ImageIcon reiPreto = ImageUtil.redimensionaImagem("/resources/rei-preto.png", 64, 64);

    ImageIcon torreBranco = ImageUtil.redimensionaImagem("/resources/torre-branca.png", 64, 64);
    ImageIcon torrePreto = ImageUtil.redimensionaImagem("/resources/torre-preta.png", 64, 64);

    ImageIcon bispoBranco = ImageUtil.redimensionaImagem("/resources/bispo-branco.png", 64, 64);
    ImageIcon bispoPreto = ImageUtil.redimensionaImagem("/resources/bispo-preto.png", 64, 64);

    ImageIcon damaBranco = ImageUtil.redimensionaImagem("/resources/dama-branca.png", 64, 64);
    ImageIcon damaPreto = ImageUtil.redimensionaImagem("/resources/dama-preta.png", 64, 64);

    ImageIcon peaoBranco = ImageUtil.redimensionaImagem("/resources/peao-branco.png", 64, 64);
    ImageIcon peaoPreto = ImageUtil.redimensionaImagem("/resources/peao-preto.png", 64, 64);

    // getters
    public ImageIcon getCavaloBranco() {
        return cavaloBranco;
    }
    public ImageIcon getCavaloPreto() {
        return cavaloPreto;
    }
    public ImageIcon getReiBranco() {
        return reiBranco;
    }
    public ImageIcon getReiPreto() {
        return reiPreto;
    }
    public ImageIcon getTorreBranco() {
        return torreBranco;
    }
    public ImageIcon getTorrePreto() {
        return torrePreto;
    }
    public ImageIcon getBispoBranco() {
        return bispoBranco;
    }
    public ImageIcon getBispoPreto() {
        return bispoPreto;
    }
    public ImageIcon getDamaBranco() {
        return damaBranco;
    }
    public ImageIcon getDamaPreto() {
        return damaPreto;
    }
    public ImageIcon getPeaoBranco() {
        return peaoBranco;
    }
    public ImageIcon getPeaoPreto() {
        return peaoPreto;
    }
}




