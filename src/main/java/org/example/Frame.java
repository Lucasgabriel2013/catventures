package org.example;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class Frame extends JFrame {
    Batalha batalha = new Batalha(this);
    Main main = new Main(this);
    Escolha escolha = new Escolha(this);
    Loja loja = new Loja(this, main);
    Arena arena = new Arena(main, this, 0);

    CardLayout cardLayout = new CardLayout();

    static void main() throws Exception {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        new Frame();
    }

    Frame() {
        super("CatVentures");

        setLayout(cardLayout);

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

    public void toMain(int scene, int vida, int dano, ImageIcon icon, String nome) {
        main.setScene(scene);
        main.gato.vidaMaxima = vida;
        main.gato.vida = vida;
        main.gato.dano = dano;
        main.gato.icon = icon;
        main.gato.nome = nome;
        main.clean();
        cardLayout.show(getContentPane(), "main");
    }

    public void toMundoAberto(int scene) {
        arena = new Arena(main, this, scene);
        getContentPane().remove(arena);
        add(arena, "mundo");
        cardLayout.show(getContentPane(), "mundo");
    }

    public void toLoja(int moedas, int scene) {
        cardLayout.show(getContentPane(), "loja");
        loja.moedas = moedas;
        loja.scene = scene;
    }
}