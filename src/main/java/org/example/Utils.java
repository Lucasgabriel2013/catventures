package org.example;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Utils {
    public static void setIcon(JLabel label, ImageIcon icon, int width, int height) {
        icon = new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        label.setIcon(icon);
    }

    public static void setIcon(JButton button, ImageIcon icon, int width, int height) {
        icon = new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        button.setIcon(icon);
    }

    public static void sound(String recource, float addVolume) {
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(Utils.class.getResourceAsStream(recource))) {

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            FloatControl volume2 = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume2.setValue(+addVolume);

            clip.start();
        } catch (IllegalArgumentException | LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }

    public static JButton newButton(String texto) {
        JButton button = new JButton(texto);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        return button;
    }
}
