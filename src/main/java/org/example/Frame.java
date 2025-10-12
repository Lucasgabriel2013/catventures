package org.example;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class Frame extends JFrame {
    Batalha batalha = new Batalha(this);
    Main main = new Main(this);
    Escolha escolha = new Escolha(this);
    CardLayout cardLayout = new CardLayout();

    static void main() throws Exception {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        new Frame();
    }

    Frame() {
        super("CatVentures");

        setLayout(cardLayout);

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

    public void toMain(int scene, int vida, int dano, ImageIcon icon) {
        main.setScene(scene);
        main.gato.vida = vida;
        main.gato.dano = dano;
        main.gato.icon = icon;
        main.clean();
        cardLayout.show(getContentPane(), "main");
    }
}
