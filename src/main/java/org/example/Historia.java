package org.example;

import javax.swing.*;
import java.awt.*;

public class Historia extends JPanel {
    Frame frame;

    JPanel panel = new JPanel(new GridLayout(4, 1));
    JButton button = new JButton("Continuar");

    Timer t1;

    int cena;

    Historia(Frame frame) {
        this.frame = frame;

        setLayout(new BorderLayout());
        button.setVisible(false);

        add(panel, BorderLayout.CENTER);

        button.setFont(new Font("Arial", Font.BOLD, 36));

        button.setBackground(new Color(100, 155, 255));
        add(button, BorderLayout.EAST);

        button.addActionListener(_ -> voltar());
    }

    public void cutscene(int scene, int timer, Cena... cenas) {
        this.cena = scene;
        panel.removeAll();
        panel.setLayout(new GridLayout(cenas.length, 1));
        final int[] cenaAtual = {0};

        t1 = new Timer(timer, _ -> {
            if (cenaAtual[0] < cenas.length) {
                JLabel quadro = new JLabel();
                quadro.setFont(new Font("Arial", Font.BOLD, 36));
                Utils.setIcon(quadro, cenas[cenaAtual[0]].icone(), 500, 300);
                quadro.setText(cenas[cenaAtual[0]].texto());
                quadro.setVisible(true);
                panel.add(quadro);
                panel.revalidate();
                panel.repaint();
                cenaAtual[0]++;
            } else {
                t1.stop();
                button.setVisible(true);
            }
        });
        t1.start();
    }

    public void voltar() {
        switch (cena) {
            case 1000 -> frame.cardLayout.show(frame.getContentPane(), "inicio");
            case 1001 -> frame.toBatalha(new Batalha(frame, frame.main, frame.main.gato, frame.main.peixeEstranho, 8, 8, "Você achou um peixe estranha"));
            case 1002 -> frame.toBatalha(new Batalha(frame, frame.main, frame.main.gato, frame.main.gatoCaca, 3, 3, "Há caçadores na floresta"));
            case 1002_1 -> frame.toBatalha(new Batalha(frame, frame.main, frame.main.gato, frame.main.gatoCaca, 5, 5, "Ainda há caçadores na floresta"));
            case 1003 -> frame.toBatalha(new Batalha(frame, frame.main, frame.main.gato, frame.main.rei, 5, 4, ""));
            case 1004 -> frame.toBatalha(new Batalha(frame, frame.main, frame.main.gato, frame.main.novoRei, 11, 10, ""));
            case 1005 -> frame.toBatalha(new Batalha(frame, frame.main, frame.main.gato, frame.main.gatoRei, 12, 10, ""));
            default -> frame.toMain(cena);
        }
        button.setVisible(false);
        t1.stop();
    }
}
