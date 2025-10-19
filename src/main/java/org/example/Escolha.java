package org.example;

import javax.swing.*;
import java.awt.*;

public class Escolha extends JPanel {
    Frame frame;
    Main main;

    JLabel label = new JLabel("Escolha um gato: ", SwingConstants.CENTER);
    JButton gato0 = Utils.newButton("");
    JButton gato1 = Utils.newButton("");
    JButton gato2 = Utils.newButton("");
    JButton gato3 = Utils.newButton("");
    JButton gato4 = Utils.newButton("");
    JButton gato5 = Utils.newButton("");
    JButton gato6 = Utils.newButton("");
    JButton gato7 = Utils.newButton("");
    JButton gato9 = Utils.newButton("");
    JPanel panel = new JPanel(new GridLayout(1, 2));
    JPanel panel2 = new JPanel(new GridLayout(3, 1));
    JPanel panel3 = new JPanel(new GridLayout(3, 1));

    JButton escolher = new JButton("Escolher");
    JButton direita = new JButton();
    JButton esquerda = new JButton();
    JTextField nome = new JTextField();
    JLabel vida = new JLabel();
    JLabel dano = new JLabel();
    JTextArea descricao = new JTextArea();
    CardLayout cardLayout = new CardLayout();
    JPanel cardPanel = new JPanel(cardLayout);
    JPanel atr = new JPanel(new GridLayout(3, 1));
    JPanel separar = new JPanel(new GridLayout(1, 2));

    ImageIcon icon1 = new ImageIcon(getClass().getResource("/personagens/gato.png"));
    ImageIcon icon2 = new ImageIcon(getClass().getResource("/personagens/gato2.png"));
    ImageIcon icon3 = new ImageIcon(getClass().getResource("/personagens/gato3.png"));
    ImageIcon icon4 = new ImageIcon(getClass().getResource("/personagens/gato4.png"));
    ImageIcon icon5 = new ImageIcon(getClass().getResource("/personagens/gato5.png"));
    ImageIcon icon6 = new ImageIcon(getClass().getResource("/personagens/gato6.png"));
    ImageIcon icon7 = new ImageIcon(getClass().getResource("/personagens/gato7.png"));
    ImageIcon icon8 = new ImageIcon(getClass().getResource("/personagens/gato8.png"));
    ImageIcon icon9 = new ImageIcon(getClass().getResource("/personagens/miniCachorro.png"));

    int gatoAtual = 0;
    int quantiaGatos = 5;

    Escolha(Frame frame, Main main) {
        setLayout(new BorderLayout());

        setDescricao();

        this.main = main;
        this.frame = frame;

        Utils.setIcon(direita, new ImageIcon(getClass().getResource("/seta2.png")), 75, 75);
        Utils.setIcon(esquerda, new ImageIcon(getClass().getResource("/seta3.png")), 75, 75);

        setBackground(new Color(100, 155, 255));
        panel.setBackground(new Color(100, 155, 255));

        Utils.setIcon(vida, new ImageIcon(getClass().getResource("/coracao.png")), 150, 150);
        Utils.setIcon(dano, new ImageIcon(getClass().getResource("/espada.png")), 150, 150);

        vida.setHorizontalAlignment(SwingConstants.CENTER);
        dano.setHorizontalAlignment(SwingConstants.CENTER);
        nome.setHorizontalAlignment(SwingConstants.CENTER);

        Utils.setIcon(gato0, icon1, 800, 550);
        Utils.setIcon(gato1, icon2, 800, 550);
        Utils.setIcon(gato2, icon3, 800, 550);
        Utils.setIcon(gato3, icon4, 800, 550);
        Utils.setIcon(gato4, icon5, 800, 550);
        Utils.setIcon(gato5, icon6, 800, 550);
        Utils.setIcon(gato6, icon7, 800, 550);
        Utils.setIcon(gato7, icon8,  800, 550);
        Utils.setIcon(gato9, icon9, 800, 550);

        gato0.setFont(new Font("Arial", Font.BOLD, 26));
        gato1.setFont(new Font("Arial", Font.BOLD, 26));
        gato2.setFont(new Font("Arial", Font.BOLD, 26));
        gato3.setFont(new Font("Arial", Font.BOLD, 26));
        gato4.setFont(new Font("Arial", Font.BOLD, 26));
        gato5.setFont(new Font("Arial", Font.BOLD, 26));
        gato6.setFont(new Font("Arial", Font.BOLD, 26));
        gato7.setFont(new Font("Arial", Font.BOLD, 26));
        gato9.setFont(new Font("Arial", Font.BOLD, 26));
        escolher.setFont(new Font("Arial", Font.BOLD, 36));
        direita.setFont(new Font("Arial", Font.BOLD, 46));
        esquerda.setFont(new Font("Arial", Font.BOLD, 46));
        nome.setFont(new Font("Arial", Font.BOLD, 75));
        vida.setFont(new Font("Arial", Font.BOLD, 36));
        dano.setFont(new Font("Arial", Font.BOLD, 36));
        descricao.setFont(new Font("Arial", Font.BOLD, 36));

        nome.setEditable(false);
        descricao.setEditable(false);

        gato0.setBackground(new Color(208, 120, 59));
        gato1.setBackground(new Color(227, 191, 152));
        gato2.setBackground(new Color(140, 70, 0));
        gato3.setBackground(new Color(107, 107, 107));
        gato4.setBackground(new Color(37, 35, 35));
        gato5.setBackground(new Color(193, 193, 193));
        gato7.setBackground(new Color(83, 82, 82));
        gato9.setBackground(new Color(89, 62, 33));

        esquerda.setBackground(new Color(100, 155, 255));
        direita.setBackground(new Color(100, 155, 255));
        escolher.setBackground(new Color(205, 175, 42));

        add(label, BorderLayout.NORTH);
        label.setFont(new Font("Arial", Font.BOLD, 34));

        descricao.setLineWrap(true);
        descricao.setWrapStyleWord(true);

        add(panel, BorderLayout.CENTER);

        panel.add(cardPanel);
        panel.add(atr);
        atr.add(nome);

        atr.add(separar);
        separar.add(vida);
        separar.add(dano);

        atr.add(descricao);

        cardPanel.add(gato0, "gato 0");
        cardPanel.add(gato1, "gato 1");
        cardPanel.add(gato2, "gato 2");
        cardPanel.add(gato3, "gato 3");
        cardPanel.add(gato4, "gato 4");
        cardPanel.add(gato5, "gato 5");

        add(escolher, BorderLayout.SOUTH);

        add(panel2, BorderLayout.EAST);
        panel2.add(new JPanel());
        panel2.add(direita);

        add(panel3, BorderLayout.WEST);
        panel3.add(new JPanel());
        panel3.add(esquerda);

        escolher.addActionListener(_ -> escolha(gatoAtual));
        esquerda.addActionListener(_ -> esquerda());
        direita.addActionListener(_ -> direita());
    }

    void escolha(int numero) {
        switch (numero) {
            case 0 -> frame.toMain(40, 20, icon1, "Garfield");
            case 1 -> frame.toMain(55, 10, icon2, "Paçoca");
            case 2 -> frame.toMain(48, 15, icon3, "Tadela");
            case 3 -> frame.toMain(85, 6, icon4, "Tom");
            case 4 -> frame.toMain(35, 10, icon5, "Sombra");
            case 5 -> frame.toMain(30, 15, icon6, "Mingau");

            case 6 -> frame.toMain(30, 25, icon7, "Caçador");
            case 7 -> frame.toMain(35, 18, icon8, "Esqueleto");
            case 8 -> frame.toMain(25, 10, icon9, "Mini Cão");
        }
    }

    public void esquerda() {
        if (gatoAtual != 0) {
            gatoAtual--;
        } else {
            gatoAtual = quantiaGatos;
        }
        cardLayout.show(cardPanel, "gato " + gatoAtual);
        setDescricao();
    }

    public void direita() {
        if (gatoAtual != quantiaGatos) {
            gatoAtual++;
        } else {
            gatoAtual = 0;
        }
        setDescricao();
        cardLayout.show(cardPanel, "gato " + gatoAtual);
    }

    public void setDescricao() {
        switch (gatoAtual) {
            case 0 -> {
                nome.setText("GARFIELD");
                vida.setText("40");
                dano.setText("20");
                descricao.setText("Um gato laranja, sem nada especial, só com fome");
            }

            case 1 -> {
                nome.setText("PAÇOCA");
                vida.setText("55");
                dano.setText("10");
                descricao.setText("Um gato lindo, mas normal");
            }

            case 2 -> {
                nome.setText("TADELA");
                vida.setText("48");
                dano.setText("15");
                descricao.setText("Tadelinha fofa e equilibrada, mas não no sono");
            }

            case 3 -> {
                nome.setText("TOM");
                vida.setText("85");
                dano.setText("6");
                descricao.setText("Tom caça Jerry, e por isso, é muito resistente");
            }

            case 4 -> {
                nome.setText("SOMBRA");
                vida.setText("35");
                dano.setText("10");
                descricao.setText("Sombra, ganha x2 mais xps e moedas");
            }

            case 5 -> {
                nome.setText("MINGAU");
                vida.setText("30");
                dano.setText("15");
                descricao.setText("É da magali, tem 5 de regeneração");
            }

            case 6 -> {
                if (frame.final1) {
                    nome.setText("CAÇADOR");
                    vida.setText("30");
                    dano.setText("25");
                    descricao.setText("Caça gatos que fogem, existem vários (final 1)");

                    cardPanel.add(gato6, "gato 6");
                    cardLayout.show(cardPanel, "gato 6");
                } else if (frame.final2) {
                    nome.setText("ESQUELETO");
                    vida.setText("35");
                    dano.setText("18");
                    descricao.setText("Será que tá bem? (final 2)");

                    cardPanel.add(gato7, "gato 7");
                    cardLayout.show(cardPanel, "gato 7");
                } else if (frame.final3) {
                    nome.setText("MINI CÃO");
                    vida.setText("25");
                    dano.setText("10");
                    descricao.setText("Ei, isso não é um gato (x3 xp e moedas) (final 3)");

                    cardPanel.add(gato9, "gato 8");
                    cardLayout.show(cardPanel, "gato 8");
                }
            }

            case 7 -> {
                if (frame.final2) {
                    nome.setText("ESQUELETO");
                    vida.setText("35");
                    dano.setText("18");
                    descricao.setText("Será que tá bem? (final 2)");

                    cardPanel.add(gato7, "gato 7");
                    cardLayout.show(cardPanel, "gato 7");
                } else if (frame.final3) {
                    nome.setText("MINI CÃO");
                    vida.setText("25");
                    dano.setText("10");
                    descricao.setText("Ei, isso não é um gato (x3 xp e moedas) (final 3)");

                    cardPanel.add(gato9, "gato 8");
                    cardLayout.show(cardPanel, "gato 8");
                }
            }

            case 8 -> {
                if (frame.final3) {
                    nome.setText("MINI CÃO");
                    vida.setText("25");
                    dano.setText("10");
                    descricao.setText("Ei, isso não é um gato (x3 xp e moedas) (final 3)");

                    cardPanel.add(gato9, "gato 8");
                    cardLayout.show(cardPanel, "gato 8");
                }
            }
        }
    }
}