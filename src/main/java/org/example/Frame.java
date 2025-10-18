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
    TelaInicial telaInicial = new TelaInicial(this, main);

    JButton sair = new JButton("Sair");

    CardLayout cardLayout = new CardLayout();
    Clip musicaFundo;

    boolean final1 = false;
    boolean final2 = false;
    boolean final3 = false;

    static void main() throws Exception {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        new Frame();
    }

    Frame() {
        super("CatVentures");

        setLayout(cardLayout);
        setMinimumSize(new Dimension(1280, 720));

        sair.setFont(new Font("Arial", Font.BOLD, 26));

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

        add(telaInicial, "inicio");
        add(loja, "loja");
        add(arena, "mundo");
        add(escolha, "escolha");
        add(main, "main");
        add(batalha, "batalha");

        setUndecorated(true);
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        cardLayout.show(getContentPane(), "inicio");

        sair.addActionListener(_ -> sair());
    }

    void sair() {
        dispose();
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
        main.gato = new Personagem(icon, dano, vida, 0, 0, "Gato");
        loja.newSave();
        main.moedas = 0;

        main.vida = vida;
        main.dano = dano;
        main.gato.level = 1;
        main.gato.kills = 0;
        main.gato.vidaMaxima = vida;
        main.gato.nome = nome;
        main.clean();
        cardLayout.show(getContentPane(), "main");
        Utils.sound("/sons/gatoSound1.wav", 6);
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
}