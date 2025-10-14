package org.example;

import javax.swing.*;
import java.awt.*;

public class Loja extends JPanel {
    Frame frame;
    Main main;

    JButton pocao = new JButton("Poção de vida: Primeira compra gratuita");
    JButton dano = new JButton("Boost eterno de dano: 30 moedas");
    JButton catnip = new JButton("Catnip: 50 moedas");
    JButton button = new JButton("Voltar");
    JLabel label = new JLabel("Moedas: ", SwingConstants.CENTER);
    JPanel panel = new JPanel(new GridLayout(3, 1));
    JPanel northPanel = new JPanel(new GridLayout(1, 2));

    int moedas = 0;
    int scene;

    Loja(Frame frame, Main main) {
        this.frame = frame;
        this.main = main;

        label.setText("Moedas: " + this.moedas);

        setLayout(new BorderLayout());

        pocao.setIcon(new ImageIcon(getClass().getResource("/pocao.png")));
        dano.setIcon(new ImageIcon(getClass().getResource("/forca.png")));
        catnip.setIcon(new ImageIcon(getClass().getResource("/catnip.png")));

        add(northPanel, BorderLayout.NORTH);
        northPanel.add(label);
        northPanel.add(button);
        label.setFont(new Font("Arial", Font.BOLD, 34));
        button.setFont(new Font("Arial", Font.BOLD, 34));
        pocao.setFont(new Font("Arial", Font.BOLD, 34));
        dano.setFont(new Font("Arial", Font.BOLD, 34));
        catnip.setFont(new Font("Arial", Font.BOLD, 34));

        add(panel, BorderLayout.CENTER);
        panel.add(pocao);
        panel.add(dano);
        panel.add(catnip);

        setVisible(true);

        pocao.addActionListener(_ -> compra1());
        dano.addActionListener(_ -> compra2());
        catnip.addActionListener(_ -> compra3());

        button.addActionListener(_ -> sair());
    }

    void compra1() {
        if (pocao.getText().endsWith("gratuita")) {
            JOptionPane.showMessageDialog(this, "Compra efetuada, vida maxíma");
            main.gato.vida = main.gato.vidaMaxima;
            pocao.setText("Poção de vida: 10 moedas");
        } else if (moedas >= 10) {
            JOptionPane.showMessageDialog(this, "Compra efetuada, vida maxíma");
            moedas = moedas - 10;
            main.moedas = main.moedas - 10;
            main.gato.vida = main.gato.vidaMaxima;
        } else {
            JOptionPane.showMessageDialog(this, "Você não tem moedas suficientes");
        }
        main.clean();
        label.setText("Moedas: " + this.moedas);
    }

    void compra2() {
        if (moedas >= 30) {
            JOptionPane.showMessageDialog(this, "Compra efetuada, dano upado");
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
            JOptionPane.showMessageDialog(this, "Compra efetuada, x3 xp ganho");
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

    void sair() {
        frame.toMain(scene);
    }
}