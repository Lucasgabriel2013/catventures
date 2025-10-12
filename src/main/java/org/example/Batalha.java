package org.example;

import javax.swing.*;
import java.awt.*;

public class Batalha extends JPanel {
    Frame frame;
    JButton button = new JButton("Atacar");
    JTextArea cat = new JTextArea();
    JTextArea enemy = new JTextArea();
    JTextArea textArea = new JTextArea("Seu turno");
    JLabel iconLabel = new JLabel();
    JLabel iconLabel2 = new JLabel();
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();

    int danoDele;
    int vidaDele;
    int danoMeu;
    int vidaMinha;
    int scene;
    String nome;

    Personagem gato;
    Personagem feio;

    Batalha(Frame frame) {
        this.frame = frame;
    }

    Batalha(Frame frame, Personagem jogador, Personagem inimigo, int victoryScene) {
        this.frame = frame;
        scene = victoryScene;

        gato = jogador;
        feio = inimigo;

        setLayout(new BorderLayout());

        textArea.setFont(new Font("Arial", Font.BOLD, 30));
        cat.setFont(new Font("Arial", Font.BOLD, 20));
        enemy.setFont(new Font("Arial", Font.BOLD, 20));

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
        JMenuBar menubar = new JMenuBar();
        menubar.add(button);
        add(menubar, BorderLayout.NORTH);

        add(textArea, BorderLayout.CENTER);
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

        button.addActionListener(_ -> atacar());
    }

    public void arrumar() {
        enemy.setText("""
                %s:
                Vida: %s
                Dano: %s
                """.formatted(nome, vidaDele, danoDele));

        cat.setText("""
                Gato:
                Vida: %s
                Dano: %s
                """.formatted(vidaMinha, danoMeu));
    }

    void atacar() {
        vidaDele = vidaDele - danoMeu;
        arrumar();
        verificar();
        textArea.setText("""
                Você atacou, turno do %s
                Esperando...""".formatted(nome));
        button.setEnabled(false);

        var timer = new Timer(2000, (_) -> {
            vidaMinha = vidaMinha - danoDele;
            arrumar();
            textArea.setText(nome + " atacou, seu turno");
            button.setEnabled(true);
            verificar();
        });
        timer.setRepeats(false);
        timer.start();
    }


    private void verificar() {
        if (vidaMinha <= 0) {
            JOptionPane.showMessageDialog(null, "Você morreu");
            frame.toMain(0);
        } else if (vidaDele <= 0) {
            JOptionPane.showMessageDialog(null, "Você venceu");
            frame.toMain(scene);
            gato.kills++;
        }
    }
}