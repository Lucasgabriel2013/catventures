package org.example;

import javax.swing.*;

import java.awt.*;

public class MundoAberto extends JPanel {
    Frame frame;

    int x = 915;
    int y = 460;
    int col = 17;
    int row = 10;
    int cena = 0;

    int[][] posicoes = new int[32][18];

    MundoAberto(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (cena == 1) {
            posicoes[23][8] = 2;
            posicoes[9][17] = 1;
            posicoes[9][16] = 1;
            posicoes[9][15] = 1;

            if (posicoes[col][row] == 2) {
                g.drawImage(new ImageIcon(getClass().getResource("/fundos/chão1.png")).getImage(), 0, 0, 1920, 1080, null);
                g.drawImage(frame.main.gato.icon.getImage(), x, y, 150, 100, null);
                g.drawImage(new ImageIcon(getClass().getResource("/fundos/decoração1.png")).getImage(), 50, -120, 1920, 1080, null);
            } else {
                g.drawImage(new ImageIcon(getClass().getResource("/fundos/chão1.png")).getImage(), 0, 0, 1920, 1080, null);
                g.drawImage(new ImageIcon(getClass().getResource("/fundos/decoração1.png")).getImage(), 50, -120, 1920, 1080, null);
                g.drawImage(frame.main.gato.icon.getImage(), x, y, 150, 100, null);
            }

            if (col < 10) {
                frame.main.cenaAtual = 3;
                frame.toMain(frame.main.cenaAtual);
                arrumar();
            } else if (col > 23) {
                frame.toHistoria();
                frame.historia.cutscene(1002, 750,
                        new Cena("Você continuo na floresta, mas tinha vários caçadores", frame.main.floresta),
                        new Cena("Então, você foi pego", frame.main.gatoCaca.icon));
                arrumar();
            }

        } else if (cena == 2) {

            if (posicoes[col][row] == 2) {
                g.drawImage(new ImageIcon(getClass().getResource("/fundos/chão2.png")).getImage(), 0, 0, 1920, 1080, null);
                g.drawImage(frame.main.gato.icon.getImage(), x, y, 150, 100, null);
                g.drawImage(new ImageIcon(getClass().getResource("/fundos/decoração2.png")).getImage(), 0, 60, 1920, 940, null);
            } else {
                g.drawImage(new ImageIcon(getClass().getResource("/fundos/chão2.png")).getImage(), 0, 0, 1920, 1080, null);
                g.drawImage(new ImageIcon(getClass().getResource("/fundos/decoração2.png")).getImage(), 0, 60, 1920, 940, null);
                g.drawImage(frame.main.gato.icon.getImage(), x, y, 150, 100, null);
            }

            if (col < 5) {
               frame.main.cenaAtual = 4;
               frame.toMain(frame.main.cenaAtual);
               arrumar();
               
            } else if (col > 30) {
                frame.toHistoria();
                frame.historia.cutscene(1003, 750,
                        new Cena("Você desafiou o rei e ele riu de você", frame.main.rei.icon),
                        new Cena("Então, vocês irão batalhar", frame.main.gato.icon));
                arrumar();
            }
        }
    }

    public void arrumar() {
        x = 915;
        y = 460;
        col = 17;
        row = 9;
    }
}
