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
    ImageIcon imagem2 = new ImageIcon(getClass().getResource("/fundo4.png"));
    JButton mundo = new JButton("???");
    JButton loja = new JButton("Abrir Loja");
    JPanel panel3 = new JPanel(new BorderLayout());

    int vida;
    int dano;
    int cenaAtual = 1;
    int moedas;

    Personagem gato = new Personagem(new ImageIcon(getClass().getResource("/gato.png")), vida, dano, "Gato");

    Personagem mini = new Personagem(new ImageIcon(getClass().getResource("/miniCachorro.png")), 10, 25, "Mini");
    Personagem cachorro = new Personagem(new ImageIcon(getClass().getResource("/cachorro.png")), 20, 65, "Cão");
    Personagem rei = new Personagem(new ImageIcon(getClass().getResource("/reiCachorro.png")), 50, 105, "Rei");

    Personagem peixe = new Personagem(new ImageIcon(getClass().getResource("/peixe.png")), 35, 85, "Peixe");

    Map<Integer, Mensagem> mensagemMap = Map.ofEntries(
            Map.entry(100, new Mensagem("Você é a Tadela, mas se perdeu de casa, foi à floresta e agora precisa passar por um rio, o que você faz?", "Tenta pular o rio", "Tenta nadar", new ImageIcon(getClass().getResource("/fundo1.png")))),
            Map.entry(-1, new Mensagem("Você venceu o jogo", "", "", new ImageIcon(gato.icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)))),
            Map.entry(0, new Mensagem("Você morreu", "", "", new ImageIcon(gato.icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)))),
            Map.entry(1, new Mensagem("Você é um gato, foi à floresta e agora precisa passar por um rio, o que você faz?", "Tenta pular o rio", "Tenta nadar", new ImageIcon(getClass().getResource("/fundo1.png")))),
            Map.entry(2, new Mensagem("Você caiu e a correnteza te levou ate o reino dos cães", "Tentar enfrentar um (20 dano, 65 vida)", "Se esconder", imagem)),
            Map.entry(3, new Mensagem("Estás escondido, e agora?", "Ir lentamente ao castelo", "Tentar voltar para casa", imagem)),
            Map.entry(4, new Mensagem("Achaste uma poção suspeita", "Tomar", "Não tomar", imagem)),
            Map.entry(5, new Mensagem("E agora?", "Enfrentar", "Entrar no castelo", imagem)),
            Map.entry(6, new Mensagem("Você entrou", "Desafiar o rei (50 dano, 105 vida)", "Sair do castelo", new ImageIcon(getClass().getResource("/fundo3.png")))),
            Map.entry(7, new Mensagem("Batalhar contra quem?", "Cachorro (20 dano, 65 vida)", "Mini cachorro (10 dano, 25 vida)", imagem)),
            Map.entry(8, new Mensagem("Escolha", "Voltar pro lago", "Batalhar com o rei", imagem)),

            Map.entry(10, new Mensagem("Você sente cheiro de peixe, o segue e para na lagoa", "Desafiar um peixe (35 vida, 85 dano)", "Voltar pro reino", imagem2)),
            Map.entry(11, new Mensagem("E agora?", "Desafiar um peixe (35 vida, 85 dano)", "Explorar", imagem2)),
            Map.entry(12, new Mensagem("Você entrou em umas ruinas aquaticas, algo estranha...", "Desafiar um peixe estranho", "Explorar", new ImageIcon(getClass().getResource("/fundo5.png"))))
    );

    Main(Frame frame) {
        gato.level = 1;

        this.frame = frame;

        setLayout(new BorderLayout());

        add(panel3, BorderLayout.NORTH);
        panel3.add(loja, BorderLayout.EAST);
        panel3.add(mundo, BorderLayout.WEST);
        panel3.add(atribuitos, BorderLayout.CENTER);

        mundo.setEnabled(false);
        panel.setLayout(new GridLayout(1, 2));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        textArea.setFont(new Font("Arial", Font.BOLD, 44));
        atribuitos.setFont(new Font("Arial", Font.BOLD, 24));
        button1.setFont(new Font("Arial", Font.BOLD, 36));
        button2.setFont(new Font("Arial", Font.BOLD, 36));
        mundo.setFont(new Font("Arial", Font.BOLD, 40));
        loja.setFont(new Font("Arial", Font.BOLD, 40));

        add(panel2, BorderLayout.CENTER);
        panel2.setLayout(new GridLayout(1, 2));
        panel2.add(textArea);
        panel2.add(icon);

        add(panel, BorderLayout.SOUTH);
        panel.add(button1);
        panel.add(button2);

        button1.addActionListener(_ -> escolha1());
        button2.addActionListener(_ -> escolha2());

        mundo.addActionListener(_ -> explorar());
        loja.addActionListener(_ -> abrirLoja());
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
            case 1, 100 -> cenaAtual = 2;
            case 2 -> frame.toBatalha(new Batalha(frame, this, gato, cachorro, 4, 2));
            case 3 -> cenaAtual = 4;
            case 4 -> randomPocao();
            case 5 -> cenaAtual = 7;
            case 6 -> frame.toBatalha(new Batalha(frame, this, gato, rei, 10, 6 ));
            case 7 -> frame.toBatalha(new Batalha(frame, this, gato, cachorro, 5, 5 ));
            case 8 -> cenaAtual = 10;

            case 10 -> frame.toBatalha(new Batalha(frame, this, gato, peixe, 11, 10 ));
            case 11 -> frame.toBatalha(new Batalha(frame, this, gato, peixe, 11, 11 ));
        }
        setScene(cenaAtual);
        clean();
    }

    private void randomPocao() {
        int random = (int) (Math.random() * 10);
        if (random < 6) {
            JOptionPane.showMessageDialog(this, "Era uma poção de vida (+15 vida)");
            gato.vidaMaxima = gato.vidaMaxima + 15;
            gato.vida = gato.vidaMaxima;
        } else {
            JOptionPane.showMessageDialog(this, "Era uma poção ruim (-5 dano)");
            gato.dano = gato.dano - 5;
        }
        cenaAtual = 5;
    }

    void escolha2() {
        switch (cenaAtual) {
            case 1, 100 -> cenaAtual = 2;
            case 2 -> cenaAtual = 3;
            case 3 -> {
                JOptionPane.showMessageDialog(this, "Você foi pego");
                frame.toBatalha(new Batalha(frame, this, gato, cachorro, 4, 3));
            }
            case 4, 6 -> cenaAtual = 5;
            case 5 -> cenaAtual = 6;
            case 7 -> frame.toBatalha(new Batalha(frame, this, gato, mini, 5, 5));
            case 8 -> frame.toBatalha(new Batalha(frame, this, gato, rei, 10, 6));

            case 10 -> cenaAtual = 8;
            case 11 -> {
                mundo.setEnabled(true);
                mundo.setText("Ir à arena");
            }
           // case 12 -> //
        }
        setScene(cenaAtual);
        clean();
    }

    public void clean() {
        if (gato.kills >= gato.level) {
            JOptionPane.showMessageDialog(this, "Você upou de level");
            gato.kills = 0;
            gato.level++;
            gato.vidaMaxima = gato.vidaMaxima + 10;
            gato.vida = gato.vida + 10;
            gato.dano = dano + 5;
        }
        vida = gato.vida;
        dano = gato.dano;
        atribuitos.setText("Vida: " + vida + " / " + gato.vidaMaxima + " || Dano: " + dano + " || LEVEL: " + gato.level + " || Level up: " + gato.kills + " / " + gato.level + " || Moedas: " + moedas);
    }

    void explorar() {
        frame.toMundoAberto(cenaAtual);
    }

    void abrirLoja() {
        frame.toLoja(moedas, cenaAtual);
    }
}