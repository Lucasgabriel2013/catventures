package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Main extends JPanel {
    Frame frame;
    JTextArea textArea = new JTextArea("Texto");
    JPanel panel = new JPanel();
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    JButton icon = new JButton();
    JPanel panel2 = new JPanel();
    JButton mundo = new JButton("???");
    JButton loja = new JButton("Loja");
    JPanel panel3 = new JPanel(new GridLayout(1, 7));

    ImageIcon heart = new ImageIcon(getClass().getResource("/coracao.png"));
    ImageIcon seta = new ImageIcon(getClass().getResource("/seta.png"));
    ImageIcon moeda = new ImageIcon(getClass().getResource("/moeda.png"));
    ImageIcon star = new ImageIcon(getClass().getResource("/star.png"));
    ImageIcon espada = new ImageIcon(getClass().getResource("/espada.png"));

    Font font = new Font("Arial", Font.BOLD, 40);
    Font font2 = new Font("Arial", Font.BOLD, 20);

    JLabel vidaAtr = new JLabel("", SwingConstants.CENTER);
    JLabel danoAtr = new JLabel("", SwingConstants.CENTER);
    JLabel levelAtr = new JLabel("", SwingConstants.CENTER);
    JLabel levelUp = new JLabel("", SwingConstants.CENTER);
    JLabel moedasAtr = new JLabel("", SwingConstants.CENTER);

    ImageIcon floresta = new ImageIcon(getClass().getResource("/fundos/fundo1.png"));
    ImageIcon cachorros = new ImageIcon(getClass().getResource("/fundos/fundo2.png"));
    ImageIcon castelo = new ImageIcon(getClass().getResource("/fundos/fundo3.png"));
    ImageIcon lago = new ImageIcon(getClass().getResource("/fundos/fundo4.png"));
    ImageIcon fundo = new ImageIcon(getClass().getResource("/fundos/fundo5.png"));
    ImageIcon gatos = new ImageIcon(getClass().getResource("/fundos/fundo6.png"));

    int vida;
    int dano;
    int cenaAtual = 1;
    int moedas;

    Personagem gato = new Personagem(new ImageIcon(getClass().getResource("/personagens/gato.png")), dano, vida, 0, 0, "Gato");

    Personagem mini = new Personagem(new ImageIcon(getClass().getResource("/personagens/miniCachorro.png")), 10, 25, 0.25, 3, "Mini Cão");
    Personagem cachorro = new Personagem(new ImageIcon(getClass().getResource("/personagens/cachorro.png")), 20, 65, 0.5, 5, "Cão");
    Personagem rei = new Personagem(new ImageIcon(getClass().getResource("/personagens/reiCachorro.png")), 50, 105, 1.0, 10, "Rei");

    Personagem peixe = new Personagem(new ImageIcon(getClass().getResource("/personagens/peixe.png")), 35, 85, 1.0, 5, "Peixe");
    Personagem peixeEstranho = new Personagem(new ImageIcon(getClass().getResource("/personagens/peixeSus.png")), 65, 140, 1.5, 10, "Peixe Sus");

    Personagem novoRei = new Personagem(new ImageIcon(getClass().getResource("/personagens/reiCachorro.png")), 100, 155, 2.0, 15, "Rei");
    Personagem gatoCaca = new Personagem(new ImageIcon(getClass().getResource("/personagens/gato7.png")), 20, 25, 0.25, 3, "Caçador");

    Map<Integer, Mensagem> mensagemMap = Map.ofEntries(
            Map.entry(0, new Mensagem(
                    "Você morreu", "Jogar novamente", "", "", "",
                    gatos)),

            Map.entry(1, new Mensagem(
                    "Você foi um gato maltratado em seu reino, agora podes fugir, pois tem 4 anos",
                    "Fugir", "", "", "",
                    gatos)),

            Map.entry(2, new Mensagem(
                    "A fuga foi um sucesso! Você está livre, mas o Reino dos Gatos enviou caçadores. Onde você se esconde?",
                    "Reino dos cães",
                    "Continuar na floresta", "", "",
                    floresta)),

            Map.entry(3, new Mensagem(
                    "Você chegou a salvo, mas os cães odeiam gatos, então, é melhor tomar cuidado",
                    "Enfrentar um mini-cão (10 dano, 25 vida)",
                    "Enfrentar um cachorro (20 dano, 65 vida)",
                    "Entrar no castelo", "",
                    cachorros)),

            Map.entry(4, new Mensagem(
                    "Você entrou no castelo, muito bonito, mas agora, deve tomar cuidado",
                    "Desafiar o boss",
                    "Sair do castelo", "", "",
                    castelo)),

            Map.entry(5, new Mensagem(
                    "Você matou o rei dos cachorros, agora, pode relaxar, mas onde?",
                    "No lago",
                    "Na floresta", "", "",
                    castelo)),

            Map.entry(6, new Mensagem(
                    "Você estava no lago... Uma corrente repentina te arrasta, levando você para o fundo da água.",
                    "Desafiar um peixe (35 dano, 85 vida)",
                    "Explorar", "", "",
                    lago)),

            Map.entry(6_01, new Mensagem(
                    "E agora?",
                    "Desafiar um peixe (35 dano, 85 vida)",
                    "Voltar a caverna", "", "",
                    lago)),

            Map.entry(7, new Mensagem(
                    "No fundo do lago, você encontra uma caverna submersa iluminada por algas.",
                    "Investigar a caverna",
                    "Nadar de volta à superfície",
                    "Explorar o fundo sem entrar na caverna",
                    "",
                    fundo)),

            Map.entry(7_01, new Mensagem(
                    "Você não achou nada no fundo, apenas algas",
                    "Entrar na caverna",
                    "", "", "",
                    fundo)),

            Map.entry(8, new Mensagem(
                    "Você consegue avançar pela caverna e encontra uma bifurcação submersa.",
                    "Seguir pelo túnel à esquerda",
                    "Seguir pelo túnel à direita", "", "",
                    fundo)),

            Map.entry(9, new Mensagem(
                    "Na esquerda, você achou um caminho a superfície, e o subiu, agora, estás na floresta e ouve barulhos no reino",
                    "Ir ao reino dos gatos", "", "", "",
                    floresta)),

            Map.entry(10, new Mensagem(
                    "Você chegou, e o reino está sendo atacado pelos cães, e agora?",
                    "Enfrentar o novo rei dos cachorros",
                    "",
                    "", "",
                    floresta)),

            Map.entry(11, new Mensagem(
                    "Você venceu, salvando seu ex-reino", "Jogar novamente", "", "", "",
                    gatos))

    );

    Main(Frame frame) {
        gato.level = 1;

        this.frame = frame;

        Utils.setIcon(vidaAtr, heart, 56, 56);
        Utils.setIcon(levelUp, seta, 56, 56);
        Utils.setIcon(moedasAtr, moeda, 56, 56);
        Utils.setIcon(levelAtr, star, 56, 56);
        Utils.setIcon(danoAtr, espada, 72, 72);

        setLayout(new BorderLayout());

        add(panel3, BorderLayout.NORTH);
        panel3.add(mundo);
        panel3.add(vidaAtr);
        panel3.add(danoAtr);
        panel3.add(levelAtr);
        panel3.add(levelUp);
        panel3.add(moedasAtr);
        panel3.add(loja);

        loja.setBackground(new Color(147, 49, 49));
        loja.setForeground(Color.white);

        mundo.setEnabled(false);
        panel.setLayout(new GridLayout(2, 2));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        textArea.setFont(font);
        button1.setFont(font);
        button2.setFont(font);
        button3.setFont(font);
        button4.setFont(font);
        mundo.setFont(font);
        loja.setFont(font);
        danoAtr.setFont(font2);
        levelAtr.setFont(font2);
        levelUp.setFont(font2);
        moedasAtr.setFont(font2);
        vidaAtr.setFont(font2);

        add(panel2, BorderLayout.CENTER);
        panel2.setLayout(new GridLayout(1, 2));
        panel2.add(textArea);
        panel2.add(icon);

        add(panel, BorderLayout.SOUTH);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        button1.addActionListener(_ -> escolha(1));
        button2.addActionListener(_ -> escolha(2));
        button3.addActionListener(_ -> escolha(3));
        button4.addActionListener(_ -> escolha(4));

        mundo.addActionListener(_ -> frame.toArena(cenaAtual));
        loja.addActionListener(_ -> frame.toLoja(moedas, cenaAtual));
    }

    void escolha(int i) {
        switch (cenaAtual) {
            case 0 -> {
                frame.cardLayout.show(frame.getContentPane(), "inicio");
                frame.escolha.newCat(2);
            }

            case 11 -> {
                frame.cardLayout.show(frame.getContentPane(), "inicio");
                frame.escolha.newCat(1);
            }

            case 1 -> cenaAtual = 2;

            case 2 -> {
                if (i == 1) {
                    cenaAtual = 3;
                } else if (i == 2) {
                    JOptionPane.showMessageDialog(this, "A floresta foi o lugar mais procurado pelos caçadores", "Você foi pego", JOptionPane.INFORMATION_MESSAGE);
                    frame.toBatalha(new Batalha(frame, this, gato, gatoCaca, 3, 3));
                }
            }

            case 3 -> {
                if (i == 1) {
                    frame.toBatalha(new Batalha(frame, this, gato, mini, 3, 3));
                } else if (i == 2) {
                    frame.toBatalha(new Batalha(frame, this, gato, cachorro, 3, 3));
                } else if (i == 3) {
                    cenaAtual = 4;
                }
            }

            case 4 -> {
                if (i == 1) {
                    frame.toBatalha(new Batalha(frame, this, gato, rei, 5, 4));
                } else if (i == 2) {
                    cenaAtual = 2;
                }
            }

            case 5 -> {
                if (i == 1) {
                    cenaAtual = 6;
                } else if (i == 2) {
                    JOptionPane.showMessageDialog(this, "Futuramente");
                }
            }

            case 6 -> {
                if (i == 1) {
                    frame.toBatalha(new Batalha(frame, this, gato, peixe, 6, 6));
                } else if (i == 2) {
                    cenaAtual = 7;
                    mundo.setEnabled(true);
                    mundo.setText("Arena");
                    mundo.setBackground(Color.gray);
                }
            }

            case 6_01 -> {
                if (i == 1) {
                    frame.toBatalha(new Batalha(frame, this, gato, peixe, 601, 601));
                } else if (i == 2) {
                    cenaAtual = 7;
                }
            }

            case 7 -> {
                if (i == 1) {
                    cenaAtual = 8;
                } else if (i == 2) {
                    cenaAtual = 601;
                } else if (i == 3) {
                    cenaAtual = 701;
                }
            }

            case 7_01 -> cenaAtual = 8;

            case 8 -> {
                if (i == 1) {
                    cenaAtual = 9;
                } else if (i == 2) {
                    JOptionPane.showMessageDialog(this, "Você achou um peixe estranho", "Estranho", JOptionPane.INFORMATION_MESSAGE);
                    frame.toBatalha(new Batalha(frame, this, gato, peixeEstranho, 8, 8));
                }
            }

            case 9 -> cenaAtual = 10;

            case 10 -> frame.toBatalha(new Batalha(frame, this, gato, novoRei, 11, 10));
        }
        setScene(cenaAtual);

        if (cenaAtual == 11 || cenaAtual == 0) {
            loja.setEnabled(false);
            mundo.setEnabled(false);
        }
        clean();
    }

    public void setScene(int id) {
        cenaAtual = id;
        Mensagem mensagem = mensagemMap.get(id);

        icon.setIcon(mensagem.icon());
        textArea.setText(mensagem.mensagem());
        button1.setText(mensagem.resposta1());
        button2.setText(mensagem.resposta2());
        button3.setText(mensagem.resposta3());
        button4.setText(mensagem.resposta4());

        button1.setVisible(!mensagem.resposta1().isEmpty());
        button2.setVisible(!mensagem.resposta2().isEmpty());
        button3.setVisible(!mensagem.resposta3().isEmpty());
        button4.setVisible(!mensagem.resposta4().isEmpty());
    }


    public void clean() {
        if (gato.nome.equals("Sombra") && gato.xpMulti < 2 && gato.moedasMulti < 2) {
            gato.xpMulti = 2;
            gato.moedasMulti = 2;
        }
        if (gato.kills >= gato.level) {
            gato.kills = 0;
            gato.level++;
            gato.vidaMaxima = gato.vidaMaxima + 10;
            gato.vida = gato.vida + 10;
            gato.dano = dano + 5;
            Utils.sound("/sons/levelUp.wav", 5f);
        }
        if (gato.vida < 0) {
            gato.vida = 0;
        }
        vida = gato.vida;
        dano = gato.dano;

        danoAtr.setText("Dano: " + dano);
        levelAtr.setText("LEVEL: " + gato.level);
        levelUp.setText("Level up: " + gato.kills + " / " + gato.level);
        moedasAtr.setText("Moedas: " + moedas);
        vidaAtr.setText("Vida: " + vida + " / " + gato.vidaMaxima);

        revalidate();
        repaint();
    }
}