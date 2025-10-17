package org.example;

import javax.swing.*;
import java.awt.*;

public class Loja extends JPanel {
    Frame frame;
    Main main;

    int pocoes;

    JButton pocao = Utils.newButton("Poção de vida: 10 moedas (x%s)".formatted(pocoes));
    JButton dano = Utils.newButton("Boost de dano: 30 moedas");
    JButton catnip = Utils.newButton("Catnip: 50 moedas");
    JButton level = Utils.newButton("Level: 75 moedas");

    JButton button = new JButton("Voltar");
    JLabel label = new JLabel("Moedas: ", SwingConstants.CENTER);
    JPanel panel = new JPanel(new GridLayout(2, 2));
    JPanel northPanel = new JPanel(new GridLayout(1, 2));

    ImageIcon starIcon = new ImageIcon(getClass().getResource("/star.png"));
    ImageIcon catnipIcon = new ImageIcon(getClass().getResource("/catnip.png"));
    ImageIcon danoIcon = new ImageIcon(getClass().getResource("/forca.png"));
    ImageIcon pocaoIcon = new ImageIcon(getClass().getResource("/pocao.png"));

    int moedas = 0;
    int scene;
    int price = 75;

    Loja(Frame frame, Main main) {
        this.frame = frame;
        this.main = main;

        label.setText("Moedas: " + this.moedas);

        setLayout(new BorderLayout());

        Utils.setIcon(level, starIcon, 300, 275);
        Utils.setIcon(pocao, pocaoIcon, 400, 300);
        Utils.setIcon(dano, danoIcon, 400, 300);
        Utils.setIcon(catnip, catnipIcon, 500, 350);

        add(northPanel, BorderLayout.NORTH);
        northPanel.add(label);
        northPanel.add(button);
        label.setFont(new Font("Arial", Font.BOLD, 34));
        button.setFont(new Font("Arial", Font.BOLD, 34));

        pocao.setFont(new Font("Arial", Font.BOLD, 34));
        dano.setFont(new Font("Arial", Font.BOLD, 34));
        catnip.setFont(new Font("Arial", Font.BOLD, 34));
        level.setFont(new Font("Arial", Font.BOLD, 34));

        pocao.setBackground(new Color(0x9483FF));
        dano.setBackground(new Color(0xE3B572));
        catnip.setBackground(new Color(0x346200));
        level.setBackground(new Color(0x676300));

        add(panel, BorderLayout.CENTER);
        panel.add(pocao);
        panel.add(dano);
        panel.add(catnip);
        panel.add(level);

        setVisible(true);

        pocao.addActionListener(_ -> compra1());
        dano.addActionListener(_ -> compra2());
        catnip.addActionListener(_ -> compra3());
        level.addActionListener(_ -> compra4());

        button.addActionListener(_ -> sair());
    }

    void compra1() {
        if (moedas >= 10) {
            JOptionPane.showMessageDialog(this, "Compra efetuada");
            moedas = moedas - 10;
            main.moedas = main.moedas - 10;
            main.gato.vida = main.gato.vidaMaxima;
            pocoes++;
        } else {
            JOptionPane.showMessageDialog(this, "Você não tem moedas suficientes");
        }
        main.clean();
        label.setText("Moedas: " + this.moedas);
        pocao.setText("Poção de vida: 10 moedas (x%s)".formatted(pocoes));
    }

    void compra2() {
        if (moedas >= 30) {
            moedas = moedas - 30;
            main.moedas = main.moedas - 30;
            main.gato.dano = main.gato.dano + 25;
            dano.setEnabled(false);
            dano.setText("Compra já feita");
        } else {
            JOptionPane.showMessageDialog(this, "Você não tem moedas suficientes");
        }
        main.clean();
        label.setText("Moedas: " + this.moedas);
    }

    void compra3() {
        if (moedas >= 50) {
            moedas = moedas - 50;
            main.moedas = main.moedas - 50;
            main.gato.xpMulti = main.gato.xpMulti * 3;
            catnip.setEnabled(false);
            catnip.setText("Compra já feita");
        } else {
            JOptionPane.showMessageDialog(this, "Você não tem moedas suficientes");
        }
        main.clean();
        label.setText("Moedas: " + this.moedas);
    }

    void compra4() {
        if (moedas >= price) {
            moedas = moedas - price;
            main.moedas = main.moedas - price;
            price = price + 25;
            main.gato.level++;
            level.setText("Level: %s moedas".formatted(price));
        } else {
            JOptionPane.showMessageDialog(this, "Você não tem moedas suficientes");
        }
        main.clean();
        label.setText("Moedas: " + this.moedas);
    }

    void sair() {
        frame.toMain(scene);
    }
}