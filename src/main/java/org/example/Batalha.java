package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Batalha extends JPanel {
    Frame frame;
    Main main;
    JButton button = new JButton("Atacar");
    JButton button2 = new JButton("Fugir");
    JTextArea cat = new JTextArea();
    JTextArea enemy = new JTextArea();
    JTextArea textArea = new JTextArea("Seu turno");
    JLabel iconLabel = new JLabel();
    JLabel iconLabel2 = new JLabel();
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel(new BorderLayout());

    Timer timer;
    int danoDele;
    int vidaDele;
    int danoMeu;
    int vidaMinha;
    int scene;
    int fugir;
    String nome;

    Personagem gato;
    Personagem feio;

    Batalha(Frame frame) {
        this.frame = frame;
    }

    Batalha(Frame frame, Main main, Personagem jogador, Personagem inimigo, int victoryScene, int fugirScene) {
        this.frame = frame;
        this.main = main;

        scene = victoryScene;
        fugir = fugirScene;

        gato = jogador;
        feio = inimigo;

        setLayout(new BorderLayout());
        add(panel4, BorderLayout.CENTER);

        textArea.setFont(new Font("Arial", Font.BOLD, 30));
        cat.setFont(new Font("Arial", Font.BOLD, 20));
        enemy.setFont(new Font("Arial", Font.BOLD, 20));

        button.setFont(new Font("Arial", Font.BOLD, 40));
        button2.setFont(new Font("Arial", Font.BOLD, 40));

        add(panel, BorderLayout.WEST);
        ImageIcon iconCatFinal = new ImageIcon(jogador.icon.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH));
        iconLabel.setIcon(iconCatFinal);

        ImageIcon iconEnemyFinal = new ImageIcon(inimigo.icon.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH));
        iconLabel2.setIcon(iconEnemyFinal);

        vidaDele = inimigo.vida;
        vidaMinha = jogador.vida;

        danoDele = inimigo.dano;
        danoMeu = jogador.dano;
        nome = inimigo.nome;

        arrumar();

        panel.setLayout(new GridLayout(3, 1));
        setSize(800, 500);

        panel4.add(textArea, BorderLayout.CENTER);
        textArea.setLineWrap(true);
        textArea.setEditable(false);

        cat.setLineWrap(true);
        cat.setEditable(false);
        enemy.setLineWrap(true);
        enemy.setEditable(false);

        add(panel2, BorderLayout.EAST);
        panel2.setLayout(new GridLayout(3, 1));
        panel2.add(new JPanel());
        panel2.add(enemy);
        panel2.add(iconLabel2);

        panel.add(new JPanel());
        panel.add(cat);
        panel.add(iconLabel);

        panel4.add(panel3, BorderLayout.SOUTH);
        panel3.setLayout(new GridLayout(1, 2));
        panel3.add(button);
        panel3.add(button2);

        button.addActionListener(_ -> atacar());
        button2.addActionListener(_ -> fugir());
    }

    public void arrumar() {
        enemy.setText("""
                %s:
                Vida: %s
                Dano: %s
                """.formatted(nome, vidaDele, danoDele));

        cat.setText("""
                %s:
                Vida: %s
                Dano: %s
                """.formatted(gato.nome, vidaMinha, danoMeu));
    }

    void atacar() {
        int random = (int) (Math.random() * 10);
        if (random == 1) {
            vidaDele = vidaDele - danoMeu * 2;
            JOptionPane.showMessageDialog(this, "Dano critico");
        } else {
            vidaDele = vidaDele - danoMeu;
        }
        arrumar();
        if (verificar()) {

        } else {

            textArea.setText("""
                    Você atacou, turno do %s
                    Esperando...""".formatted(nome));
            button.setEnabled(false);
            button2.setEnabled(false);

            timer = new Timer(2000, (_) -> {
                if (random == 2) {
                    vidaMinha = vidaMinha - danoDele * 2;
                    JOptionPane.showMessageDialog(this, "Dano critico inimigo");
                } else {
                    vidaMinha = vidaMinha - danoDele;
                }
                gato.vida = vidaMinha;
                arrumar();
                textArea.setText(nome + " atacou, seu turno");
                button.setEnabled(true);
                button2.setEnabled(true);
                verificar();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    void fugir() {
        int random = (int) (Math.random() * 10);
        if (random > 5) {
            frame.toMain(fugir);
        } else {
            textArea.setText("""
                    Não consiguistes fugir, turno do %s...""".formatted(nome));
            button.setEnabled(false);
            button2.setEnabled(false);

            timer = new Timer(2000, (_) -> {
                if (random == 2) {
                    vidaMinha = vidaMinha - danoDele * 2;
                    JOptionPane.showMessageDialog(this, "Dano critico inimigo");
                } else {
                    vidaMinha = vidaMinha - danoDele;
                }
                gato.vida = vidaMinha;
                arrumar();
                textArea.setText(nome + " atacou, seu turno");
                button.setEnabled(true);
                verificar();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    private boolean verificar() {
        if (vidaMinha <= 0) {
            JOptionPane.showMessageDialog(null, "Você morreu");
            frame.toMain(0);
            return true;
        } else if (vidaDele <= 0) {
            JOptionPane.showMessageDialog(null, "Você venceu");

            frame.toMain(scene);
            System.out.println(feio.nome + gato.nome);
            switch (feio.nome) {
                case "Mini" -> {
                    if (gato.nome.equals("Sombra")) {
                        gato.kills = gato.kills + 0.25;
                        main.moedas = main.moedas + 3;
                    }
                    gato.kills = gato.kills + 0.25;
                    main.moedas = main.moedas + 3;
                }
                case "Cão" -> {
                    gato.kills = gato.kills + 0.5;
                    main.moedas = main.moedas + 5;
                }
                case "Rei" -> {
                    gato.kills++;
                    main.moedas = main.moedas + 10;
                }
                case "Peixe" -> {
                    gato.kills++;
                    main.moedas = main.moedas + 5;
                }
            }
            main.clean();
            return true;
        } else {
            return false;
        }
    }
}