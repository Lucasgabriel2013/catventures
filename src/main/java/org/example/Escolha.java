package org.example;

import javax.swing.*;
import java.awt.*;

public class Escolha extends JPanel {
    Frame frame;
    Main main;

    JLabel label = new JLabel("Escolha um gato: ", SwingConstants.CENTER);
    JButton gato1 = new JButton("Garfield: 40 vida, 20 dano");
    JButton gato2 = new JButton("Paçoca: 55 vida, 10 dano");
    JButton gato3 = new JButton("Tadela: 48 vida, 15 dano");
    JButton gato4 = new JButton("Tom: 85 vida, 6 dano");
    JButton gato5 = new JButton("Sombra: 35 vida, 10 dano (X2 Level e moedas)");
    JButton gato6 = new JButton("Mingau: 35 vida, 15 dano (10 vida / Batalha)");
    JPanel panel = new JPanel();
    ImageIcon icon1 = new ImageIcon(getClass().getResource("/personagens/gato.png"));
    ImageIcon icon2 = new ImageIcon(getClass().getResource("/personagens/gato2.png"));
    ImageIcon icon3 = new ImageIcon(getClass().getResource("/personagens/gato3.png"));
    ImageIcon icon4 = new ImageIcon(getClass().getResource("/personagens/gato4.png"));
    ImageIcon icon5 = new ImageIcon(getClass().getResource("/personagens/gato5.png"));
    ImageIcon icon6 = new ImageIcon(getClass().getResource("/personagens/gato6.png"));


    Escolha(Frame frame, Main main) {
        setLayout(new BorderLayout());

        this.main = main;
        this.frame = frame;

        setBackground(new Color(100, 155, 255));
        panel.setBackground(new Color(100, 155, 255));

        main.setIcon(gato1, icon1, 275, 200);
        main.setIcon(gato2, icon2, 275, 200);
        main.setIcon(gato3, icon3, 275, 200);
        main.setIcon(gato4, icon4, 275, 200);
        main.setIcon(gato5, icon5, 275, 200);
        main.setIcon(gato6, icon6, 275, 200);

        gato1.setFont(new Font("Arial", Font.BOLD, 26));
        gato2.setFont(new Font("Arial", Font.BOLD, 26));
        gato3.setFont(new Font("Arial", Font.BOLD, 26));
        gato4.setFont(new Font("Arial", Font.BOLD, 26));
        gato5.setFont(new Font("Arial", Font.BOLD, 26));
        gato6.setFont(new Font("Arial", Font.BOLD, 26));

        gato1.setBackground(new Color(208, 120, 59));
        gato2.setBackground(new Color(227, 191, 152));
        gato3.setBackground(new Color(140, 70, 0));
        gato4.setBackground(new Color(107, 107, 107));
        gato5.setBackground(new Color(37, 35, 35));
        gato6.setBackground(new Color(147, 147, 147));

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
    }

    void escolha(int numero) {
        switch (numero) {
            case 1 -> frame.toMain(40, 20, icon1, "Garfield");
            case 2 -> frame.toMain(55, 10, icon2, "Paçoca");
            case 3 -> frame.toMain(48, 15, icon3, "Tadela");
            case 4 -> frame.toMain(85, 6, icon4, "Tadela");
            case 5 -> frame.toMain(35, 10, icon5, "Sombra");
            case 6 -> frame.toMain(35, 15, icon6, "Mingau");
        }
    }
}