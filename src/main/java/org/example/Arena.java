package org.example;

import javax.swing.*;
import java.awt.*;

public class Arena extends JPanel {
    Main main;
    Frame frame;
    Font font = new Font("Arial", Font.BOLD, 24);
    JPanel panel = new JPanel();
    JButton voltar = new JButton("Voltar para a tela");
    int scene;

    Arena(Main main, Frame frame, int scene) {
        this.main = main;
        this.frame = frame;
        this.scene = scene;

        panel.setLayout(new GridLayout(3, 2));

        setLayout(new BorderLayout());

        add(panel, BorderLayout.CENTER);
        add(voltar, BorderLayout.NORTH);

        JButton button = new JButton(main.cachorro.toString(), main.cachorro.icon);
        JButton button2 = new JButton(main.mini.toString(), main.mini.icon);
        JButton button3 = new JButton(main.rei.toString(), main.rei.icon);
        JButton button4 = new JButton(main.peixe.toString(), main.peixe.icon);
        JButton button5 = new JButton(main.peixeEstranho.toString(), new ImageIcon(main.peixeEstranho.icon.getImage().getScaledInstance(575, 425, Image.SCALE_SMOOTH)));

        voltar.setFont(new Font("Arial", Font.BOLD, 36));

        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);

        button.setBackground(new Color(50, 40, 40));
        button2.setBackground(new Color(90, 60, 60));
        button3.setBackground(Color.yellow);
        button4.setBackground(Color.cyan);
        button5.setBackground(Color.BLUE);

        button.setFont(font);
        button2.setFont(font);
        button3.setFont(font);
        button4.setFont(font);
        button5.setFont(font);

        button.addActionListener(_ -> batalha(1));
        button2.addActionListener(_ -> batalha(2));
        button3.addActionListener(_ -> batalha(3));
        button4.addActionListener(_ -> batalha(4));
        button5.addActionListener(_ -> batalha(5));

        voltar.addActionListener(_ -> frame.toMain(scene));
    }

    void batalha(int i) {
        switch (i) {
            case 1 -> frame.toBatalha(new Batalha(frame, main, main.gato, main.cachorro, scene, scene));
            case 2 -> frame.toBatalha(new Batalha(frame, main, main.gato, main.mini, scene, scene));
            case 3 -> frame.toBatalha(new Batalha(frame, main, main.gato, main.rei, scene, scene));
            case 4 -> frame.toBatalha(new Batalha(frame, main, main.gato, main.peixe, scene, scene));
            case 5 -> frame.toBatalha(new Batalha(frame, main, main.gato, main.peixeEstranho, scene, scene));
        }
    }
}