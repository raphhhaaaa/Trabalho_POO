package view;

import javax.swing.*;
import java.awt.*;

public class BotaoCasa extends JButton {
    private int linha;
    private int coluna;


    public BotaoCasa(int linha, int coluna)  {
        this.linha = linha;
        this.coluna = coluna;
        this.setSize(100,100);
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

}
