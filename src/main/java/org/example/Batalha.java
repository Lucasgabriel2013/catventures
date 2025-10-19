package org.example;

import javax.swing.*;
import java.awt.*;

public class Batalha extends JPanel {
    Frame frame;
    Main main;
    JButton button = new JButton("Atacar");
    JButton button2 = new JButton("Fugir");
    JButton button3 = new JButton("Defender");
    JButton button4 = new JButton("Usar poção");
    JTextArea cat = new JTextArea();
    JTextArea enemy = new JTextArea();
    JTextArea textArea = new JTextArea();
    JLabel iconLabel = new JLabel();
    JLabel iconLabel2 = new JLabel();
    JPanel panel = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel(new BorderLayout());
    JProgressBar defense = new JProgressBar(0, 100);

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

    Batalha(Frame frame, Main main, Personagem jogador, Personagem inimigo, int victoryScene, int fugirScene, String frase) {
        this.frame = frame;
        this.main = main;

        button3.setEnabled(false);
        textArea.setText(frase);

        Utils.setIcon(button2, new ImageIcon(getClass().getResource("/correr.png")), 160, 160);
        Utils.setIcon(button3, new ImageIcon(getClass().getResource("/shield.png")), 144, 144);
        Utils.setIcon(button4, new ImageIcon(getClass().getResource("/pocao.png")), 225, 170);

        button4.setText("Usar poção: %s".formatted(frame.loja.pocoes));

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
        button4.setFont(new Font("Arial", Font.BOLD, 40));

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
        panel3.setLayout(new GridLayout(2, 2));
        panel3.add(button);
        panel3.add(button2);
        panel3.add(button3);
        panel3.add(button4);

        button.addActionListener(_ -> atacar());
        button2.addActionListener(_ -> fugir());
        button3.addActionListener(_ -> defendido = true);
        button4.addActionListener(_ -> pocao());
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
            Utils.sound("/sons/espada.wav", 6);
            textArea.setText("""
                    Você deu um ataque critico, turno do %s
                    Esperando...""".formatted(nome));
        } else if (random == 2) {
            textArea.setText("""
                    Você errou o ataque, turno do %s
                    Esperando...""".formatted(nome));
        } else {
            vidaDele = vidaDele - danoMeu;
            Utils.sound("/sons/espada.wav", 3);
            textArea.setText("""
                    Você atacou, turno do %s
                    Esperando...""".formatted(nome));
        }
        arrumar();
        if (verificar()) {

        } else {

            button.setEnabled(false);
            button2.setEnabled(false);

            timer = new Timer(3000, (_) -> {
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
            textArea.setText(nome + " atacou critico, seu turno");
        } else {
            vidaMinha = vidaMinha - danoDele;
            textArea.setText(nome + " atacou, seu turno");
        }
        gato.vida = vidaMinha;
        arrumar();
        Utils.sound("/sons/espada.wav", 6);
        Utils.sound("/sons/gatoSound1.wav", -6);
        button.setEnabled(true);
        button2.setEnabled(true);
        verificar();
    }

    void fugir() {
        button.setEnabled(false);
        button2.setEnabled(false);
        textArea.setText("Não consiguistes fugir");
        int random = (int) (Math.random() * 10);
        if (random > 5) {
            frame.toMain(fugir);
        }
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

    void pocao() {
        if (button4.getText().endsWith("(0)")) {

        } else {
            vidaMinha = frame.loja.usar();
            main.gato.vida = vidaMinha;
            arrumar();
            button4.setText("Usar poção: %s".formatted(frame.loja.pocoes));
            textArea.setText("Poção usada (+20 vida), seu turno");
        }
    }

    private boolean verificar() {
        if (button4.getText().equals("Usar poção: 0")) {
            button4.setEnabled(false);
        }
        if (vidaMinha <= 0) {
            frame.toMain(0);
            return true;
        } else if (vidaDele <= 0) {
            frame.toMain(scene);
            main.gato.kills = main.gato.kills + feio.xpRecebido * gato.xpMulti;
            main.moedas = main.moedas + feio.moedasRecebidas * gato.moedasMulti;

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