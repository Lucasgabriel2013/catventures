package org.example;

import javax.swing.*;
import java.awt.*;

public class Escolha extends JPanel {
    Frame frame;

    JLabel label = new JLabel("Escolha um gato: ", SwingConstants.CENTER);
    JButton gato1 = new JButton("Garfield: 10 vida, 20 ataque");
    JButton gato2 = new JButton("Paçoca: 20 vida, 10 vida");
    JButton gato3 = new JButton("Tadela: 15 vida, 15 vida");
    JPanel panel = new JPanel();
    ImageIcon icon1 = new ImageIcon(getClass().getResource("/gato.png"));
    ImageIcon icon2 = new ImageIcon(getClass().getResource("/gato2.png"));
    ImageIcon icon3 = new ImageIcon(getClass().getResource("/gato3.png"));

    Escolha(Frame frame) {
        setLayout(new BorderLayout());

        this.frame = frame;

        icon1 = new ImageIcon(icon1.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH));
        gato1.setIcon(icon1);

        icon2 = new ImageIcon(icon2.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH));
        gato2.setIcon(icon2);

        icon3 = new ImageIcon(icon3.getImage().getScaledInstance(250, 200, Image.SCALE_SMOOTH));
        gato3.setIcon(icon3);

        gato2.setFont(new Font("Arial", Font.BOLD, 26));
        gato3.setFont(new Font("Arial", Font.BOLD, 26));
        gato1.setFont(new Font("Arial", Font.BOLD, 26));

        gato1.setBackground(new Color(208, 120, 59));
        gato2.setBackground(new Color(227, 191, 152));
        gato3.setBackground(new Color(140, 70, 0));

        add(label, BorderLayout.NORTH);
        label.setFont(new Font("Arial", Font.BOLD, 34));

        add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(1, 3));

        panel.add(gato1);
        panel.add(gato2);
        panel.add(gato3);

        gato1.addActionListener(_ -> escolha(1));
        gato2.addActionListener(_ -> escolha(2));
        gato3.addActionListener(_ -> escolha(3));
    }

    void escolha(int numero) {
        switch (numero) {
            case 1 -> frame.toMain(1, 10, 20, icon1);
            case 2 -> frame.toMain(1, 20, 10, icon2);
            case 3 -> frame.toMain(1, 15, 15, icon3);
        }
    }
}
