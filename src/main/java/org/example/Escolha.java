package org.example;

import javax.swing.*;
import java.awt.*;

public class Escolha extends JPanel {
    Frame frame;

    JLabel label = new JLabel("Escolha um gato: ", SwingConstants.CENTER);
    JButton gato1 = new JButton("Garfield: 40 vida, 20 dano");
    JButton gato2 = new JButton("Paçoca: 50 vida, 10 dano");
    JButton gato3 = new JButton("Tadela: 45 vida, 15 dano");
    JButton gato4 = new JButton("Sombra: 40 vida, 10 dano (X2 Level e Moedas)");
    JPanel panel = new JPanel();
    ImageIcon icon1 = new ImageIcon(getClass().getResource("/gato.png"));
    ImageIcon icon2 = new ImageIcon(getClass().getResource("/gato2.png"));
    ImageIcon icon3 = new ImageIcon(getClass().getResource("/gato3.png"));
    ImageIcon icon4 = new ImageIcon(getClass().getResource("/gato4.png"));

    Escolha(Frame frame) {
        setLayout(new BorderLayout());

        this.frame = frame;

        icon1 = new ImageIcon(icon1.getImage().getScaledInstance(275, 200, Image.SCALE_SMOOTH));
        gato1.setIcon(icon1);

        icon2 = new ImageIcon(icon2.getImage().getScaledInstance(275, 200, Image.SCALE_SMOOTH));
        gato2.setIcon(icon2);

        icon3 = new ImageIcon(icon3.getImage().getScaledInstance(275, 200, Image.SCALE_SMOOTH));
        gato3.setIcon(icon3);

        icon4 = new ImageIcon(icon4.getImage().getScaledInstance(275, 200, Image.SCALE_SMOOTH));
        gato4.setIcon(icon4);

        gato4.setFont(new Font("Arial", Font.BOLD, 26));
        gato2.setFont(new Font("Arial", Font.BOLD, 26));
        gato3.setFont(new Font("Arial", Font.BOLD, 26));
        gato1.setFont(new Font("Arial", Font.BOLD, 26));

        gato1.setBackground(new Color(208, 120, 59));
        gato2.setBackground(new Color(227, 191, 152));
        gato3.setBackground(new Color(140, 70, 0));
        gato4.setBackground(new Color(37, 35, 35));

        add(label, BorderLayout.NORTH);
        label.setFont(new Font("Arial", Font.BOLD, 34));

        add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(2, 2));

        panel.add(gato1);
        panel.add(gato2);
        panel.add(gato3);
        panel.add(gato4);

        gato1.addActionListener(_ -> escolha(1));
        gato2.addActionListener(_ -> escolha(2));
        gato3.addActionListener(_ -> escolha(3));
        gato4.addActionListener(_ -> escolha(4));
    }

    void escolha(int numero) {
        switch (numero) {
            case 1 -> frame.toMain(1, 40, 20, icon1, "Garfield");
            case 2 -> frame.toMain(1, 50, 10, icon2, "Paçoca");
            case 3 -> frame.toMain(100, 45, 15, icon3, "Tadela");
            case 4 -> frame.toMain(1, 40, 10, icon4, "Sombra");
        }
    }
}