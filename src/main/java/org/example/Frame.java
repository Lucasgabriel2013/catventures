package org.example;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.io.IOException;

public class Frame extends JFrame {
    Batalha batalha = new Batalha(this);
    Main main = new Main(this);
    Escolha escolha = new Escolha(this, main);
    Loja loja = new Loja(this, main);
    Arena arena = new Arena(main, this, 0);

    CardLayout cardLayout = new CardLayout();
    Clip musicaFundo;

    static void main() throws Exception {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        new Frame();
    }

    Frame() {
        super("CatVentures");

        setLayout(cardLayout);
        setSize(800, 500);

        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/sons/musicaFundo.wav"))) {

            musicaFundo = AudioSystem.getClip();
            musicaFundo.open(audioIn);
            musicaFundo.loop(Clip.LOOP_CONTINUOUSLY);
            musicaFundo.start();

            FloatControl volume = (FloatControl) musicaFundo.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-5.0f);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        add(loja, "loja");
        add(arena, "mundo");
        add(escolha, "escolha");
        add(main, "main");
        add(batalha, "batalha");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        cardLayout.show(getContentPane(), "escolha");
    }

    public void toBatalha(Batalha novaBatalha) {
        getContentPane().remove(batalha);
        batalha = novaBatalha;
        add(batalha, "batalha");
        cardLayout.show(getContentPane(), "batalha");
    }

    public void toMain(int scene) {
        cardLayout.show(getContentPane(), "main");
        main.setScene(scene);
        main.clean();
    }

    public void toMain(int vida, int dano, ImageIcon icon, String nome) {
        main.setScene(1);
        main.gato.vidaMaxima = vida;
        main.gato.vida = vida;
        main.gato.dano = dano;
        main.gato.icon = icon;
        main.gato.nome = nome;
        main.clean();
        cardLayout.show(getContentPane(), "main");
        sound("/sons/gatoSound1.wav", 5);
    }

    public void toArena(int scene) {
        arena = new Arena(main, this, scene);
        getContentPane().remove(arena);
        add(arena, "mundo");
        cardLayout.show(getContentPane(), "mundo");
    }

    public void toLoja(int moedas, int scene) {
        cardLayout.show(getContentPane(), "loja");
        loja.moedas = moedas;
        loja.scene = scene;
        loja.label.setText("Moedas: " + loja.moedas);
    }

    public void sound(String recource, float addVolume) {
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(recource))) {

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            FloatControl volume2 = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume2.setValue(+addVolume);

            clip.start();
        } catch (IllegalArgumentException | LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }
}