package org.example;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame extends JFrame {
    Batalha batalha = new Batalha(this);
    Main main = new Main(this);
    Escolha escolha = new Escolha(this, main);
    Loja loja = new Loja(this, main);
    Arena arena = new Arena(main, this, 0);
    TelaInicial telaInicial = new TelaInicial(this, main);
    Historia historia = new Historia(this);

    JButton sair = new JButton("Sair");

    CardLayout cardLayout = new CardLayout();
    Clip musicaFundo;
    Clip musicaBatalha;

    boolean final1 = false;
    boolean final2 = false;
    boolean final3 = false;

    String cardAtual = "tela1";

    static void main() throws Exception {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        new Frame();
    }

    Frame() {
        super("CatVentures");

        setLayout(cardLayout);
        setMinimumSize(new Dimension(1280, 720));

        sair.setFont(new Font("Arial", Font.BOLD, 26));

        musicaFundo = Utils.musicaFundo("/sons/musicaFundo.wav");
        musicaBatalha = Utils.musicaFundo("/sons/battleMusic.wav");
        musicaBatalha.stop();

        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (KeyEvent.VK_ESCAPE == e.getKeyCode() && cardAtual.equals("historia")) {
                    historia.voltar();
                }
            }
        });

        add(telaInicial, "inicio");
        add(loja, "loja");
        add(arena, "mundo");
        add(escolha, "escolha");
        add(main, "main");
        add(batalha, "batalha");
        add(historia, "historia");

        setUndecorated(true);
        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        show("inicial");

        sair.addActionListener(_ -> dispose());
    }

    public void toBatalha(Batalha novaBatalha) {
        getContentPane().remove(batalha);
        batalha = novaBatalha;
        add(batalha, "batalha");
        show("batalha");
        musicaFundo.stop();
        musicaBatalha.start();
    }

    public void toMain(int scene) {
        show("main");
        main.setScene(scene);
        main.clean();
        musicaBatalha.stop();
        musicaFundo.start();
    }

    public void toMain(int vida, int dano, ImageIcon icon, String nome) {
        toHistoria();
        historia.cutscene( 2, 2000,
                new Cena("Você foi um gato maltratado em seu reino...", main.gatos),
                new Cena("Então queria fugir de seu reino, mas não sabia como", main.gatos),
                new Cena("Até que você achou uma janela, e a quebrou", main.casteloQuebrado),
                new Cena("Você pulou a janela, e você saiu correndo pela floresta", main.floresta));
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
        Utils.sound("/sons/gatoSound1.wav", 6);
    }

    public void toArena(int scene) {
        arena = new Arena(main, this, scene);
        getContentPane().remove(arena);
        add(arena, "mundo");
        show("mundo");
    }

    public void toLoja(int moedas, int scene) {
        cardLayout.show(getContentPane(), "loja");
        loja.moedas = moedas;
        loja.scene = scene;
        loja.label.setText("Moedas: " + loja.moedas);
    }

    public void toHistoria() {
        show("historia");
    }

    public void show(String name) {
        cardLayout.show(getContentPane(), name);
        cardAtual = name;
    }
}