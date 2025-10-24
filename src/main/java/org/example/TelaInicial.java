package org.example;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JPanel {
    Frame frame;
    Main main;

    JButton jogar = new JButton("Jogar");
    JButton creditos = new JButton("Créditos");
    JButton sair = new JButton("Sair");
    JPanel panel = new JPanel();

    TelaInicial(Frame frame, Main main) {
        this.frame = frame;
        this.main = main;
        setLayout(null);

        jogar.setFont(new Font("Arial", Font.BOLD, 40));
        creditos.setFont(new Font("Arial", Font.BOLD, 40));
        sair.setFont(new Font("Arial", Font.BOLD, 40));

        jogar.setBackground(new Color(205, 175, 42));
        sair.setBackground(new Color(205, 175, 42));
        creditos.setBackground(new Color(205, 175, 42));
        panel.setBackground(new Color(205, 175, 42));

        sair.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        jogar.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        creditos.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        add(panel, BorderLayout.SOUTH);
        add(jogar);
        add(creditos);
        add(sair);

        jogar.setBounds(735, 490, 400, 150);
        creditos.setBounds(735, 690, 400, 150);
        sair.setBounds(735, 890, 400, 150);

        jogar.addActionListener(_ -> frame.cardLayout.show(frame.getContentPane(), "escolha"));
        creditos.addActionListener(_ -> creditos());
        sair.addActionListener(_ -> frame.dispose());
    }

    void creditos() {
        frame.toHistoria();
        frame.historia.cutscene(1000, 1000,
                new Cena("CRÉDITOS:", frame.main.peixe.icon),
                new Cena("CatVentures®", frame.main.cachorro.icon),
                new Cena("Desenvolvido por:", frame.main.gato.icon),
                new Cena("Lucas Gabriel Reiter", frame.main.gatoRei.icon));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(new ImageIcon(getClass().getResource("/fundos/telaInicial.png")).getImage(), 0, 0, null);
    }
}