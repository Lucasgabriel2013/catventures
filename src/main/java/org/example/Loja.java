package org.example;

import javax.swing.*;
import java.awt.*;

public class Loja extends JPanel {
    Frame frame;
    Main main;

    int pocoes;

    JButton pocao = Utils.newButton("Poção: 10 moedas (x%s)".formatted(pocoes));
    JButton usar = Utils.newButton("Usar");
    JButton catnip = Utils.newButton("Catnip: 50 moedas");
    JButton level = Utils.newButton("Level: 75 moedas");

    JButton bronze = Utils.newButton("Bronze: 30 moedas");
    JButton prata = Utils.newButton("Prata: 75 moedas");
    JButton diamante = Utils.newButton("Diamante: 125 moedas");

    JButton button = new JButton("Voltar");
    JLabel label = new JLabel("Moedas: ", SwingConstants.CENTER);
    JPanel separar = new JPanel(new GridLayout(1, 2));
    JPanel panel = new JPanel(new GridLayout(2, 2));
    JPanel panel2 = new JPanel(new GridLayout(2, 2));
    JPanel panel3 = new JPanel(new BorderLayout());
    JPanel northPanel = new JPanel(new GridLayout(1, 2));

    JLabel itens = new JLabel("Itens:", SwingConstants.CENTER);
    JLabel espadas = new JLabel("Espadas:", SwingConstants.CENTER);

    ImageIcon starIcon = new ImageIcon(getClass().getResource("/star.png"));
    ImageIcon catnipIcon = new ImageIcon(getClass().getResource("/catnip.png"));
    ImageIcon pocaoIcon = new ImageIcon(getClass().getResource("/pocao.png"));

    ImageIcon espadaIcon1 = new ImageIcon(getClass().getResource("/espada2.png"));
    ImageIcon espadaIcon2 = new ImageIcon(getClass().getResource("/espada.png"));
    ImageIcon espadaIcon3 = new ImageIcon(getClass().getResource("/espada3.png"));

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
        Utils.setIcon(catnip, catnipIcon, 500, 350);

        Utils.setIcon(bronze, espadaIcon1, 400, 300);
        Utils.setIcon(prata, espadaIcon2, 400, 300);
        Utils.setIcon(diamante, espadaIcon3, 400, 300);

        add(northPanel, BorderLayout.NORTH);
        northPanel.add(label);
        northPanel.add(button);

        label.setFont(new Font("Arial", Font.BOLD, 34));
        button.setFont(new Font("Arial", Font.BOLD, 34));
        itens.setFont(new Font("Arial", Font.BOLD, 34));
        espadas.setFont(new Font("Arial", Font.BOLD, 34));
        pocao.setFont(new Font("Arial", Font.BOLD, 34));
        usar.setFont(new Font("Arial", Font.BOLD, 34));
        catnip.setFont(new Font("Arial", Font.BOLD, 34));
        level.setFont(new Font("Arial", Font.BOLD, 34));
        bronze.setFont(new Font("Arial", Font.BOLD, 34));
        prata.setFont(new Font("Arial", Font.BOLD, 34));
        diamante.setFont(new Font("Arial", Font.BOLD, 34));

        pocao.setBackground(new Color(0x9483FF));
        usar.setBackground(new Color(0x9483FF));

        catnip.setBackground(new Color(0x346200));
        level.setBackground(new Color(0x676300));

        bronze.setBackground(new Color(0xE3B572));
        prata.setBackground(new Color(0x787873));
        diamante.setBackground(new Color(0x288C87));

        add(separar, BorderLayout.CENTER);
        separar.add(panel);
        separar.add(panel2);

        panel2.add(espadas);
        panel2.add(bronze);
        panel2.add(prata);
        panel2.add(diamante);

        panel.add(itens);
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        panel2.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        panel.add(panel3);
        panel3.add(pocao, BorderLayout.CENTER);
        panel3.add(usar, BorderLayout.SOUTH);

        panel.add(catnip);
        panel.add(level);

        setVisible(true);

        pocao.addActionListener(_ -> compra1());
        usar.addActionListener(_ -> usar());

        prata.setEnabled(false);
        diamante.setEnabled(false);

        bronze.addActionListener(_ -> espada(1));
        prata.addActionListener(_ -> espada(2));
        diamante.addActionListener(_ -> espada(3));
        catnip.addActionListener(_ -> compra3());
        level.addActionListener(_ -> compra4());

        button.addActionListener(_ -> sair());
    }

    void compra1() {
        if (moedas >= 10) {
            JOptionPane.showMessageDialog(this, "Compra efetuada");
            moedas = moedas - 10;
            main.moedas = main.moedas - 10;
            pocoes++;
        } else {
            JOptionPane.showMessageDialog(this, "Você não tem moedas suficientes");
        }
        main.clean();
        label.setText("Moedas: " + this.moedas);
        pocao.setText("Poção: 10 moedas (x%s)".formatted(pocoes));
    }

    void espada(int i) {
        switch (i) {
            case 1 -> {
                if (moedas >= 30) {
                    moedas = moedas - 30;
                    main.moedas = main.moedas - 30;
                    main.gato.dano = main.gato.dano + 10;
                    bronze.setEnabled(false);
                    bronze.setText("Compra já feita");
                    prata.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Você não tem moedas suficientes");
                }
                main.clean();
                label.setText("Moedas: " + this.moedas);
            }

            case 2 -> {
                if (moedas >= 75) {
                    moedas = moedas - 75;
                    main.moedas = main.moedas - 75;
                    main.gato.dano = main.gato.dano + 20;
                    prata.setEnabled(false);
                    prata.setText("Compra já feita");
                    diamante.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Você não tem moedas suficientes");
                }
                main.clean();
                label.setText("Moedas: " + this.moedas);
            }

            case 3 -> {
                if (moedas >= 125) {
                    moedas = moedas - 125;
                    main.moedas = main.moedas - 125;
                    main.gato.dano = main.gato.dano + 30;
                    diamante.setEnabled(false);
                    diamante.setText("Compra já feita");
                } else {
                    JOptionPane.showMessageDialog(this, "Você não tem moedas suficientes");
                }
                main.clean();
                label.setText("Moedas: " + this.moedas);
            }
        }
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

    public int usar() {
        int i;
        if (pocoes > 0) {
            pocoes--;
            if (main.gato.vidaMaxima - 20 > main.gato.vida) {
                i = main.gato.vida + 20;
                main.gato.vida = i;
                pocao.setText("Poção: 10 moedas (x%s)".formatted(pocoes));
                main.gato.vida = i;
            } else {
                i = main.gato.vidaMaxima - main.gato.vida;
                main.gato.vida = main.gato.vidaMaxima;
                pocao.setText("Poção: 10 moedas (x%s)".formatted(pocoes));
            }
        } else {
            i = main.gato.vida;
        }
        main.clean();
        return i;
    }

    void sair() {
        frame.toMain(scene);
    }

    void newSave() {
        pocoes = 0;
        pocao.setText("Poção: 10 moedas (x%s)".formatted(pocoes));
        price = 75;
        catnip.setEnabled(true);
        catnip.setText("Catnip: 50 moedas");

        level.setText("Level: %s moedas".formatted(price));

        bronze.setEnabled(true);
        prata.setEnabled(false);
        diamante.setEnabled(false);

        bronze.setText("Bronze: 30 moedas");
        prata.setText("Prata: 75 moedas");
        diamante.setText("Diamante: 125 moedas");
    }
}