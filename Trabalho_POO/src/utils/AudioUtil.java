package utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class AudioUtil {

    private static Clip clipMusica;
    private static Clip clipEfeito;

    public AudioUtil() {}

    public static void tocarMusica(String caminhoArquivo) {
        try {
            java.net.URL urlSom = AudioUtil.class.getResource(caminhoArquivo);
            
            if (urlSom == null) {
                File arquivoSom = new File("src" + caminhoArquivo);
                if (arquivoSom.exists()) {
                    urlSom = arquivoSom.toURI().toURL();
                }
            }

            if (urlSom != null) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(urlSom);
                clipMusica = AudioSystem.getClip();
                clipMusica.open(audioInput);
                clipMusica.start(); // Inicia a reprodução
                clipMusica.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado: " + caminhoArquivo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao reproduzir o áudio.");
        }
    }

    public static boolean musicaTocando() {
        return clipMusica != null && clipMusica.isActive() && clipMusica.isRunning();
    }

    public static void pararMusica() {
        // Verifique se o Clip foi instanciado e está tocando
        if (clipMusica != null && clipMusica.isRunning()) {
            clipMusica.stop();  // Para a execução do áudio
            clipMusica.close(); // Libera os recursos do sistema
        }
    }

    public static void tocarEfeitoSonoro(String caminhoArquivo) {
        try {
            java.net.URL urlSom = AudioUtil.class.getResource(caminhoArquivo);

            if (urlSom == null) {
                File arquivoSom = new File("src" + caminhoArquivo);
                if (arquivoSom.exists()) {
                    urlSom = arquivoSom.toURI().toURL();
                }
            }

            if (urlSom != null) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(urlSom);
                clipEfeito = AudioSystem.getClip();
                clipEfeito.open(audioInput);
                clipEfeito.start(); // Inicia a reprodução
            } else {
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado: " + caminhoArquivo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao reproduzir o áudio.");
        }
    }
}
