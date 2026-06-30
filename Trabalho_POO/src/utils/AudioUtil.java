package utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class AudioUtil {

    private static Clip clip;

    public AudioUtil(Clip clip) {
        AudioUtil.clip = clip;
    }

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
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start(); // Inicia a reprodução
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado: " + caminhoArquivo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao reproduzir o áudio.");
        }
    }

    public static boolean musicaTocando() {
        return clip != null && clip.isActive() && clip.isRunning();
    }

    public static void pararMusica() {
        // Verifique se o Clip foi instanciado e está tocando
        if (clip != null && clip.isRunning()) {
            clip.stop();  // Para a execução do áudio
            clip.close(); // Libera os recursos do sistema
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
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start(); // Inicia a reprodução
            } else {
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado: " + caminhoArquivo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao reproduzir o áudio.");
        }
    }
}
