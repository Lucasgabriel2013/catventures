package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Main extends JPanel {
    Frame frame;
    JTextArea textArea = new JTextArea("Texto");
    JLabel atribuitos = new JLabel("Vida", SwingConstants.CENTER);
    JPanel panel = new JPanel();
    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton icon = new JButton();
    JPanel panel2 = new JPanel();
    ImageIcon imagem = new ImageIcon(getClass().getResource("/fundo2.png"));

    int vida;
    int dano;
    int cenaAtual = 1;

    Personagem gato = new Personagem(new ImageIcon(getClass().getResource("/gato.png")), vida, dano, "gato");

    Personagem mini = new Personagem(new ImageIcon(getClass().getResource("/miniCachorro.png")), 10, 10, "Mini");
    Personagem cachorro = new Personagem(new ImageIcon(getClass().getResource("/cachorro.png")), 20, 25, "Cachorro");
    Personagem rei = new Personagem(new ImageIcon(getClass().getResource("/reiCachorro.png")), 50, 55, "Rei");

    Map<Integer, Mensagem> mensagemMap = Map.ofEntries(
            Map.entry(0, new Mensagem("Você morreu", "", "", gato.icon)),
            Map.entry(1, new Mensagem("Você é um gato, foi à floresta e agora precisa passar por um rio, o que você faz?", "Tenta pular o rio", "Tenta nadar", new ImageIcon(getClass().getResource("/fundo1.png")))),
            Map.entry(2, new Mensagem("Você caiu e a correnteza te levou ate o reino dos cães", "Tentar enfrentar um", "Se esconder", imagem)),
            Map.entry(3, new Mensagem("Estás escondido, e agora?", "Ir lentamente ao castelo", "Tentar voltar para casa", imagem)),
            Map.entry(4, new Mensagem("Achaste uma poção suspeita", "Tomar", "Não tomar", imagem)),
            Map.entry(5, new Mensagem("E agora?", "Enfrentar", "Entrar no castelo", imagem)),
            Map.entry(6, new Mensagem("Você entrou", "Desafiar o boss", "Sair do castelo", new ImageIcon(getClass().getResource("/fundo3.png")))),
            Map.entry(7, new Mensagem("Contra quem?", "Cachorro", "Mini cachorro", imagem)),
            Map.entry(8, new Mensagem("Você venceu o jogo", "", "", gato.icon))
    );


    Main(Frame frame) {
        setScene(1);

        gato.level = 1;

        this.frame = frame;

        setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(1, 2));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        textArea.setFont(new Font("Arial", Font.BOLD, 34));
        atribuitos.setFont(new Font("Arial", Font.BOLD, 20));
        button1.setFont(new Font("Arial", Font.BOLD, 26));
        button2.setFont(new Font("Arial", Font.BOLD, 26));

        add(panel2, BorderLayout.CENTER);
        panel2.setLayout(new GridLayout(1, 2));
        panel2.add(textArea);
        panel2.add(icon);

        add(atribuitos, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);
        panel.add(button1);
        panel.add(button2);

        button1.addActionListener(_ -> escolha1());
        button2.addActionListener(_ -> escolha2());
        if (gato.kills >= gato.level) {
            clean();
        }
    }

    public void setScene(int id) {
        cenaAtual = id;
        Mensagem mensagem = mensagemMap.get(id);

        icon.setIcon(mensagem.icon());
        textArea.setText(mensagem.mensagem());
        button1.setText(mensagem.resposta1());
        button2.setText(mensagem.resposta2());
    }

    void escolha1() {
        switch (cenaAtual) {
            case 1 -> cenaAtual = 2;
            case 2 -> frame.toBatalha(new Batalha(frame, this, gato, cachorro, 4, 2));
            case 3 -> cenaAtual = 4;
            case 4 -> randomPocao();
            case 5 -> cenaAtual = 7;
            case 6 -> frame.toBatalha(new Batalha(frame, this, gato, rei, 8, 6 ));
            case 7 -> frame.toBatalha(new Batalha(frame, this, gato, cachorro, 5, 5 ));
        }
        setScene(cenaAtual);
        clean();
    }

    private void randomPocao() {
        int random = (int) (Math.random() * 10);
        if (random > 5) {
            JOptionPane.showMessageDialog(this, "Era uma poção de vida (+15 vida)");
            gato.vida = gato.vida + 15;
        } else {
            JOptionPane.showMessageDialog(this, "Era uma poção ruim (-5 dano)");
            gato.dano = gato.dano - 5;
        }
        cenaAtual = 5;
    }

    void escolha2() {
        switch (cenaAtual) {
            case 1 -> cenaAtual = 2;
            case 2 -> cenaAtual = 3;
            case 3 -> {
                JOptionPane.showMessageDialog(this, "Você foi pego");
                frame.toBatalha(new Batalha(frame, this, gato, cachorro, 4, 3));
            }
            case 4, 6 -> cenaAtual = 5;
            case 5 -> cenaAtual = 6;
            case 7 -> frame.toBatalha(new Batalha(frame, this, gato, mini, 5, 5));
        }
        setScene(cenaAtual);
        clean();
    }

    public void clean() {
        if (gato.kills >= gato.level) {
            JOptionPane.showMessageDialog(this, "Upaste de level");
            gato.kills = 0;
            gato.level++;
            gato.vida = vida + 10;
            gato.dano = dano + 10;
        }
        vida = gato.vida;
        dano = gato.dano;
        atribuitos.setText("Vida: " + vida + " | Dano: " + dano + " | LEVEL: " + gato.level);
    }
}