package org.example;

import javax.swing.*;
import java.awt.*;

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
    JProgressBar defense = new JProgressBar(0, 100);
    JButton button3 = new JButton("Defender");

    Timer timer;
    Timer defenseTime;
    int danoDele;
    int vidaDele;
    int danoMeu;
    int vidaMinha;
    int scene;
    int fugir;
    int random;
    boolean defendido;
    String nome;

    Personagem gato;
    Personagem feio;

    Batalha(Frame frame) {
        this.frame = frame;
    }

    Batalha(Frame frame, Main main, Personagem jogador, Personagem inimigo, int victoryScene, int fugirScene) {
        this.frame = frame;
        this.main = main;

        button3.setEnabled(false);

        Utils.setIcon(button3, new ImageIcon(getClass().getResource("/shield.png")), 144, 144);
        Utils.setIcon(button2, new ImageIcon(getClass().getResource("/correr.png")), 160, 160);

        scene = victoryScene;
        fugir = fugirScene;

        gato = jogador;
        feio = inimigo;

        defense.setOrientation(SwingConstants.VERTICAL);

        ImageIcon icon = new ImageIcon(getClass().getResource("/espada.png"));
        Utils.setIcon(button, icon, 250, 250);

        setLayout(new BorderLayout());
        add(panel4, BorderLayout.CENTER);

        textArea.setFont(new Font("Arial", Font.BOLD, 30));
        cat.setFont(new Font("Arial", Font.BOLD, 20));
        enemy.setFont(new Font("Arial", Font.BOLD, 20));

        button.setFont(new Font("Arial", Font.BOLD, 40));
        button2.setFont(new Font("Arial", Font.BOLD, 40));
        button3.setFont(new Font("Arial", Font.BOLD, 40));

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
        panel2.add(defense);
        panel2.add(enemy);
        panel2.add(iconLabel2);

        panel.add(new JPanel());
        panel.add(cat);
        panel.add(iconLabel);

        panel4.add(panel3, BorderLayout.SOUTH);
        panel3.setLayout(new GridLayout(1, 3));
        panel3.add(button);
        panel3.add(button2);
        panel3.add(button3);

        button.addActionListener(_ -> atacar());
        button2.addActionListener(_ -> fugir());
        button3.addActionListener(_ -> defendido = true);
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
        random = (int) (Math.random() * 10);
        if (random == 1) {
            vidaDele = vidaDele - danoMeu * 2;
            JOptionPane.showMessageDialog(this, "Dano critico");
        } else if (random == 2) {
            JOptionPane.showMessageDialog(this, "Errou o ataque");
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
                button3.setEnabled(true);
                defenseTime = new Timer(10, _ -> {
                    defense.setValue(defense.getValue() + 1);
                    if (defense.getValue() < 95 && defense.getValue() < 100 && defendido) {
                        defendido = false;
                        defense.setValue(100);
                    }
                    if (defense.getValue() > 95 && defense.getValue() < 100 && defendido) {
                        defenseTime.stop();
                        button3.setEnabled(false);
                        defense.setValue(0);
                        defendido = false;
                        textArea.setText("Você defendeu o ataque de " + nome + ", seu turno");
                        button.setEnabled(true);
                        button2.setEnabled(true);
                    } else if (defense.getValue() == 100) {
                        defenseTime.stop();
                        button3.setEnabled(false);
                        defense.setValue(0);
                        defendido = false;
                        adversario();
                    }
                });
                defenseTime.start();
            });
            timer.setRepeats(false);
            timer.start();
            main.clean();
        }
    }

    private void adversario() {
        random = (int) (Math.random() * 10);
        if (random == 1) {
            vidaMinha = vidaMinha - danoDele * 2;
            JOptionPane.showMessageDialog(this, "Dano critico inimigo");
        } else {
            vidaMinha = vidaMinha - danoDele;
        }
        gato.vida = vidaMinha;
        arrumar();
        Utils.sound("/sons/gatoSound1.wav", 0);
        textArea.setText(nome + " atacou, seu turno");
        button.setEnabled(true);
        button2.setEnabled(true);
        verificar();
    }

    void fugir() {
        int random = (int) (Math.random() * 10);
        if (random > 5) {
            frame.toMain(fugir);
        } else {
            textArea.setText("Não consiguistes fugir, turno do %s...".formatted(nome));
            button.setEnabled(false);
            button2.setEnabled(false);

            timer = new Timer(2000, (_) -> {
                if (random == 1) {
                    vidaMinha = vidaMinha - danoDele * 2;
                    JOptionPane.showMessageDialog(this, "Dano critico inimigo");
                } else if (random == 2) {
                    JOptionPane.showMessageDialog(this, "Ele errou o ataque");
                } else {
                    vidaMinha = vidaMinha - danoDele;
                }
                gato.vida = vidaMinha;
                arrumar();
                textArea.setText(nome + " atacou, seu turno");
                button.setEnabled(true);
                verificar();
                button2.setEnabled(true);
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
            main.gato.kills = main.gato.kills + feio.xpRecebido;
            main.moedas = main.moedas + feio.moedasRecebidas;

            if (gato.nome.equals("Mingau") && main.gato.vida < main.gato.vidaMaxima) {
                if (main.gato.vida + 5 > main.gato.vidaMaxima) {
                    int i = main.gato.vidaMaxima - main.gato.vida;
                    main.gato.vida = main.gato.vida + i;
                } else {
                    main.gato.vida = main.gato.vida + 5;
                }
            }
            main.clean();
            return true;
        } else {
            return false;
        }
    }
}