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
    JButton icon = new JButton();
    JPanel panel2 = new JPanel();
    ImageIcon imagem = new ImageIcon(getClass().getResource("/fundos/fundo2.png"));
    ImageIcon imagem2 = new ImageIcon(getClass().getResource("/fundos/fundo4.png"));
    JButton mundo = new JButton("???");
    JButton loja = new JButton("Abrir Loja");
    JPanel panel3 = new JPanel(new GridLayout(1, 7));

    ImageIcon heart = new ImageIcon(getClass().getResource("/coracao.png"));
    ImageIcon seta = new ImageIcon(getClass().getResource("/seta.png"));
    ImageIcon moeda = new ImageIcon(getClass().getResource("/moeda.png"));
    ImageIcon star = new ImageIcon(getClass().getResource("/star.png"));

    Font font = new Font("Arial", Font.BOLD, 40);
    Font font2 = new Font("Arial", Font.BOLD, 20);

    JLabel vidaAtr = new JLabel("", SwingConstants.CENTER);
    JLabel danoAtr = new JLabel("", new ImageIcon(getClass().getResource("/espada.png")),  SwingConstants.CENTER);
    JLabel levelAtr = new JLabel("", SwingConstants.CENTER);
    JLabel levelUp = new JLabel("", SwingConstants.CENTER);
    JLabel moedasAtr = new JLabel("", SwingConstants.CENTER);

    int vida;
    int dano;
    int cenaAtual = 1;
    int moedas;

    Personagem gato = new Personagem(new ImageIcon(getClass().getResource("/Personagens/gato.png")), vida, dano, "Gato");

    Personagem mini = new Personagem(new ImageIcon(getClass().getResource("/Personagens/miniCachorro.png")), 10, 25, "Mini Cão");
    Personagem cachorro = new Personagem(new ImageIcon(getClass().getResource("/Personagens/cachorro.png")), 20, 65, "Cão");
    Personagem rei = new Personagem(new ImageIcon(getClass().getResource("/Personagens/reiCachorro.png")), 50, 105, "Rei");

    Personagem peixe = new Personagem(new ImageIcon(getClass().getResource("/Personagens/peixe.png")), 35, 85, "Peixe");
    Personagem peixeEstranho = new Personagem(new ImageIcon(getClass().getResource("/Personagens/peixeSus.png")), 65, 140, "Peixe Sus");

    Personagem novoRei = new Personagem(new ImageIcon(getClass().getResource("/Personagens/reiCachorro.png")), 100, 155, "Novo rei");
    Personagem gatoRei = new Personagem(new ImageIcon(getClass().getResource("/Personagens/gatoRei.png")), 100, 155, "Novo rei");

    Map<Integer, Mensagem> mensagemMap = Map.ofEntries(
            Map.entry(100, new Mensagem("Você é a Tadela, mas se perdeu de casa, foi à floresta e agora precisa passar por um rio, o que você faz?", "Tenta pular o rio", "Tenta nadar", new ImageIcon(getClass().getResource("/Personagens/gato3.png")))),
            Map.entry(0, new Mensagem("Você morreu", "", "", new ImageIcon(gato.icon.getImage().getScaledInstance(550, 400, Image.SCALE_SMOOTH)))),
            Map.entry(1, new Mensagem("Você é um gato, foi à floresta e agora precisa passar por um rio, o que você faz?", "Tenta pular o rio", "Tenta nadar", new ImageIcon(getClass().getResource("/fundos/fundo1.png")))),
            Map.entry(2, new Mensagem("Você caiu e a correnteza te levou ate o reino dos cães", "Tentar enfrentar um (20 dano, 65 vida)", "Se esconder", imagem)),
            Map.entry(3, new Mensagem("Estás escondido, e agora?", "Ir lentamente ao castelo", "Tentar voltar para casa", imagem)),
            Map.entry(4, new Mensagem("Achaste uma poção suspeita", "Tomar", "Não tomar", imagem)),
            Map.entry(5, new Mensagem("E agora?", "Enfrentar", "Entrar no castelo", imagem)),
            Map.entry(6, new Mensagem("Você entrou", "Desafiar o rei (50 dano, 105 vida)", "Sair do castelo", new ImageIcon(getClass().getResource("/fundos/fundo3.png")))),
            Map.entry(7, new Mensagem("Batalhar contra quem?", "Cachorro (20 dano, 65 vida)", "Mini cachorro (10 dano, 25 vida)", imagem)),
            Map.entry(8, new Mensagem("Escolha", "Voltar pro lago", "Batalhar com o rei", imagem)),

            Map.entry(10, new Mensagem("Você sente cheiro de peixe, o segue e para na lagoa", "Desafiar um peixe (85 vida, 35 dano)", "Voltar pro reino", imagem2)),
            Map.entry(11, new Mensagem("E agora?", "Desafiar um peixe (85 vida, 35 dano)", "Explorar", imagem2)),
            Map.entry(12, new Mensagem("Você entrou em umas ruinas aquaticas, algo estranha...", "Desafiar um peixe (85 vida, 35 dano)", "Sair do lago", new ImageIcon(getClass().getResource("/fundos/fundo5.png")))),
            Map.entry(13, new Mensagem("Você está na planicíe, escolha", "Reino dos gatos", "Voltar", new ImageIcon(getClass().getResource("/fundos/fundo1.png")))),
            Map.entry(14, new Mensagem("O reino está sendo invadido pelos cachorros, você tem duas opções...", "Enfrentar o novo rei", "Se juntar com os cachorros", new ImageIcon(getClass().getResource("/fundos/fundo1.png")))),

            Map.entry(15, new Mensagem("Você venceu o jogo, matou o novo rei dos cachorros", "", "", new ImageIcon(gatoRei.icon.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH)))),
            Map.entry(16, new Mensagem("Você venceu o jogo, se juntando aos cachorros", "", "", new ImageIcon(cachorro.icon.getImage().getScaledInstance(550, 400, Image.SCALE_SMOOTH)))),
            Map.entry(17, new Mensagem("Você fugiu, final secreto", "", "", new ImageIcon(gatoRei.icon.getImage().getScaledInstance(550, 400, Image.SCALE_SMOOTH))))
    );

    Main(Frame frame) {
        gato.level = 1;

        this.frame = frame;

        heart = new ImageIcon(heart.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH));
        vidaAtr.setIcon(heart);

        seta = new ImageIcon(seta.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH));
        levelUp.setIcon(seta);

        moeda = new ImageIcon(moeda.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH));
        moedasAtr.setIcon(moeda);

        star = new ImageIcon(star.getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH));
        levelAtr.setIcon(star);

        setLayout(new BorderLayout());

        add(panel3, BorderLayout.NORTH);
        panel3.add(mundo);
        panel3.add(vidaAtr);
        panel3.add(danoAtr);
        panel3.add(levelAtr);
        panel3.add(levelUp);
        panel3.add(moedasAtr);
        panel3.add(loja);

        mundo.setEnabled(false);
        panel.setLayout(new GridLayout(1, 2));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        textArea.setFont(font);
        button1.setFont(font);
        button2.setFont(font);
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

        button1.addActionListener(_ -> escolha1());
        button2.addActionListener(_ -> escolha2());

        mundo.addActionListener(_ -> frame.toArena(cenaAtual));
        loja.addActionListener(_ -> frame.toLoja(moedas, cenaAtual));
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
            case 6 -> frame.toBatalha(new Batalha(frame, this, gato, rei, 10, 6));
            case 7 -> frame.toBatalha(new Batalha(frame, this, gato, cachorro, 5, 5));
            case 8 -> cenaAtual = 10;

            case 10 -> frame.toBatalha(new Batalha(frame, this, gato, peixe, 11, 10));
            case 11, 12 -> frame.toBatalha(new Batalha(frame, this, gato, peixe, 11, 11));
            case 13 -> cenaAtual = 14;
            case 14 -> frame.toBatalha(new Batalha(frame, this, gato, novoRei, 15, 14));
        }
        setScene(cenaAtual);
        clean();
        if (cenaAtual == 15 || cenaAtual == 16 || cenaAtual == 0) {
            loja.setEnabled(false);
        }
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
                int random = (int) (Math.random() * 20);
                if (random == 1) {
                    cenaAtual = 17;
                    JOptionPane.showMessageDialog(this, "Final secreto");
                    loja.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Você foi pego");
                    frame.toBatalha(new Batalha(frame, this, gato, cachorro, 4, 3));
                }
            }
            case 4, 6 -> cenaAtual = 5;
            case 5 -> cenaAtual = 6;
            case 7 -> frame.toBatalha(new Batalha(frame, this, gato, mini, 5, 5));
            case 8 -> frame.toBatalha(new Batalha(frame, this, gato, rei, 10, 6));

            case 10 -> cenaAtual = 8;
            case 11 -> {
                mundo.setEnabled(true);
                mundo.setText("Ir à arena");
                cenaAtual = 12;
            }
            case 12 -> cenaAtual = 13;
            case 13 -> frame.toBatalha(new Batalha(frame, this, gato, mini, 13, 13));
            case 14 -> {
                frame.toBatalha(new Batalha(frame, this, gato, gatoRei, 16, 14));
                loja.setEnabled(false);
            }
        }
        setScene(cenaAtual);
        clean();
        if (cenaAtual >= 15 && cenaAtual <= 18 || cenaAtual == 0) {
            loja.setEnabled(false);
            mundo.setEnabled(false);
        }
    }

    public void clean() {
        if (gato.nome.equals("Sombra") && gato.xpMulti < 2 && gato.moedasMulti < 2) {
            gato.xpMulti = 2;
            gato.moedasMulti = 2;
        }
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

        danoAtr.setText("Dano: " + dano);
        levelAtr.setText("LEVEL: " + gato.level);
        levelUp.setText("Level up: " + gato.kills + " / " + gato.level );
        moedasAtr.setText("Moedas: " + moedas);
        vidaAtr.setText("Vida: " + vida + " / " + gato.vidaMaxima);
    }
}