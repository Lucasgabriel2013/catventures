package org.example;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JPanel {
    Frame frame;
    Main main;

    JButton foto = new JButton();
    JButton jogar = new JButton("Jogar");
    JButton creditos = new JButton("Créditos");
    JButton sair = new JButton("Sair");
    JPanel panel = new JPanel();

    TelaInicial(Frame frame, Main main) {
        this.frame = frame;
        this.main = main;

        setLayout(new BorderLayout());

        jogar.setFont(new Font("Arial", Font.BOLD, 40));
        creditos.setFont(new Font("Arial", Font.BOLD, 40));
        sair.setFont(new Font("Arial", Font.BOLD, 40));

        jogar.setBackground(new Color(205, 175, 42));
        sair.setBackground(new Color(205, 175, 42));
        creditos.setBackground(new Color(205, 175, 42));
        panel.setBackground(new Color(205, 175, 42));

        sair.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        jogar.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        creditos.setBorder(BorderFactory.createLineBorder(Color.black, 3));

        add(foto, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        panel.setLayout(new GridLayout(1, 3));
        panel.add(jogar);
        panel.add(creditos);
        panel.add(sair);
        Utils.setIcon(foto, new ImageIcon(getClass().getResource("/fundos/telaInicial.png")), 2000, 1200);

        jogar.addActionListener(_ -> frame.cardLayout.show(frame.getContentPane(), "escolha"));
        creditos.addActionListener(_ -> JOptionPane.showMessageDialog(this, "Criado por Lucas Gabriel Reiter - CatVentures Inc © v1.4", "Créditos", JOptionPane.INFORMATION_MESSAGE));
        sair.addActionListener(_ -> frame.dispose());
    }
}
