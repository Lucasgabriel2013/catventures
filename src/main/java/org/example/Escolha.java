package org.example;

import javax.swing.*;
import java.awt.*;

public class Escolha extends JPanel {
    Frame frame;
    Main main;

    JLabel label = new JLabel("Escolha um gato: ", SwingConstants.CENTER);
    JButton gato1 = Utils.newButton("Garfield: 40 vida, 20 dano");
    JButton gato2 = Utils.newButton("Paçoca: 55 vida, 10 dano");
    JButton gato3 = Utils.newButton("Tadela: 48 vida, 15 dano");
    JButton gato4 = Utils.newButton("Tom: 85 vida, 6 dano");
    JButton gato5 = Utils.newButton("Sombra: 35 vida, 10 dano (x2 moedas e xp)");
    JButton gato6 = Utils.newButton("Mingau: 30 vida, 15 dano (5 vida / Batalha)");
    JButton gato7 = Utils.newButton("Caça: 30 vida, 25 dano (Especial: final 1)");
    JButton gato8 = Utils.newButton("Esqueleto: 35 vida, 18 dano (Especial: perder)");
    JButton gato9 = Utils.newButton("Mini Cão: 25 vida, 10 dano (Especial: final 2, x3)");
    JPanel panel = new JPanel();

    ImageIcon icon1 = new ImageIcon(getClass().getResource("/personagens/gato.png"));
    ImageIcon icon2 = new ImageIcon(getClass().getResource("/personagens/gato2.png"));
    ImageIcon icon3 = new ImageIcon(getClass().getResource("/personagens/gato3.png"));
    ImageIcon icon4 = new ImageIcon(getClass().getResource("/personagens/gato4.png"));
    ImageIcon icon5 = new ImageIcon(getClass().getResource("/personagens/gato5.png"));
    ImageIcon icon6 = new ImageIcon(getClass().getResource("/personagens/gato6.png"));
    ImageIcon icon7 = new ImageIcon(getClass().getResource("/personagens/gato7.png"));
    ImageIcon icon8 = new ImageIcon(getClass().getResource("/personagens/gato8.png"));
    ImageIcon icon9 = new ImageIcon(getClass().getResource("/personagens/miniCachorro.png"));

    Escolha(Frame frame, Main main) {
        setLayout(new BorderLayout());

        this.main = main;
        this.frame = frame;

        setBackground(new Color(100, 155, 255));
        panel.setBackground(new Color(100, 155, 255));

        Utils.setIcon(gato1, icon1, 275, 200);
        Utils.setIcon(gato2, icon2, 275, 200);
        Utils.setIcon(gato3, icon3, 275, 200);
        Utils.setIcon(gato4, icon4, 275, 200);
        Utils.setIcon(gato5, icon5, 275, 200);
        Utils.setIcon(gato6, icon6, 275, 200);
        Utils.setIcon(gato7, icon7, 275, 200);
        Utils.setIcon(gato8, icon8, 275, 200);
        Utils.setIcon(gato9, icon9, 275, 200);

        gato1.setFont(new Font("Arial", Font.BOLD, 26));
        gato2.setFont(new Font("Arial", Font.BOLD, 26));
        gato3.setFont(new Font("Arial", Font.BOLD, 26));
        gato4.setFont(new Font("Arial", Font.BOLD, 26));
        gato5.setFont(new Font("Arial", Font.BOLD, 26));
        gato6.setFont(new Font("Arial", Font.BOLD, 26));
        gato7.setFont(new Font("Arial", Font.BOLD, 26));
        gato8.setFont(new Font("Arial", Font.BOLD, 26));
        gato9.setFont(new Font("Arial", Font.BOLD, 26));

        gato1.setBackground(new Color(208, 120, 59));
        gato2.setBackground(new Color(227, 191, 152));
        gato3.setBackground(new Color(140, 70, 0));
        gato4.setBackground(new Color(107, 107, 107));
        gato5.setBackground(new Color(37, 35, 35));
        gato6.setBackground(new Color(147, 147, 147));
        gato8.setBackground(new Color(83, 82, 82));
        gato9.setBackground(new Color(89, 62, 33));

        add(label, BorderLayout.NORTH);
        label.setFont(new Font("Arial", Font.BOLD, 34));

        add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(3, 2));

        panel.add(gato1);
        panel.add(gato2);
        panel.add(gato3);
        panel.add(gato4);
        panel.add(gato5);
        panel.add(gato6);

        gato1.addActionListener(_ -> escolha(1));
        gato2.addActionListener(_ -> escolha(2));
        gato3.addActionListener(_ -> escolha(3));
        gato4.addActionListener(_ -> escolha(4));
        gato5.addActionListener(_ -> escolha(5));
        gato6.addActionListener(_ -> escolha(6));
        gato7.addActionListener(_ -> escolha(7));
        gato8.addActionListener(_ -> escolha(8));
        gato9.addActionListener(_ -> escolha(9));
    }

    void escolha(int numero) {
        switch (numero) {
            case 1 -> frame.toMain(40, 20, icon1, "Garfield");
            case 2 -> frame.toMain(55, 10, icon2, "Paçoca");
            case 3 -> frame.toMain(48, 15, icon3, "Tadela");
            case 4 -> frame.toMain(85, 6, icon4, "Tom");
            case 5 -> frame.toMain(35, 10, icon5, "Sombra");
            case 6 -> frame.toMain(30, 15, icon6, "Mingau");

            case 7 -> frame.toMain(30, 25, icon7, "Caça");
            case 8 -> frame.toMain(35, 18, icon8, "Esqueleto");
            case 9 -> frame.toMain(25, 10, icon9, "Mini Cão");
        }
    }

    public void newCat(int i) {
        panel.setLayout(new GridLayout(3, 3));
        if (i == 1) {
            panel.add(gato7);
        } else if (i == 2) {
            panel.add(gato8);
        } else if (i == 3) {
            panel.add(gato9);
        }
    }
}