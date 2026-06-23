package view;

import dto.IconesDTO;
import utils.ImageUtil;
import view.BotaoCasa;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

public class PainelTabuleiro extends JPanel {

    private IconesDTO iconesDTO = new IconesDTO();
    private List<List<BotaoCasa>> botoes = new ArrayList<>();

    public PainelTabuleiro() {

        setLayout(new GridLayout(8, 8));

        for (int linha = 0; linha < 8; linha++) {

            botoes.add(new ArrayList<>());

            for (int coluna = 0; coluna < 8; coluna++) {

                BotaoCasa botao = new BotaoCasa();


                // cavalo PRA CARALHO filho
                if ((linha + coluna) % 2 == 0) {
                    botao.setBackground(Color.BLACK);
                    botao.setIcon(iconesDTO.getCavaloBranco());
                } else {
                    botao.setBackground(Color.WHITE);
                    botao.setIcon(iconesDTO.getCavaloPreto());
                }

                botoes.get(linha).add(botao);
                add(botao);
            }
        }
    }

}
