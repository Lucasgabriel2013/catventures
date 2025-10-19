package org.example;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
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

    public static void sound(String resource, float addVolume) {
        try {
            BufferedInputStream bis = new BufferedInputStream(Utils.class.getResourceAsStream(resource));
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bis);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

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

    public static Clip musicaFundo(String resource) {
        try {

            BufferedInputStream bis = new BufferedInputStream(Utils.class.getResourceAsStream(resource));
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bis);

            var clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-6.0f);

            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}