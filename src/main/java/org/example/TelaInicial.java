package org.example;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JPanel {
    Frame frame;
    Main main;

    JButton foto = new JButton();
    JButton jogar = new JButton("Jogar");
    JButton creditos = new JButton("Créditos");
    JPanel panel = new JPanel();

    TelaInicial(Frame frame, Main main) {
        this.frame = frame;
        this.main = main;

        setLayout(new BorderLayout());

        jogar.setFont(new Font("Arial", Font.BOLD, 40));
        creditos.setFont(new Font("Arial", Font.BOLD, 40));

        jogar.setBackground(new Color(205, 175, 42));
        creditos.setBackground(new Color(205, 175, 42));
        panel.setBackground(new Color(205, 175, 42));

        jogar.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        creditos.setBorder(BorderFactory.createLineBorder(Color.black, 3));

        add(foto, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        panel.setLayout(new GridLayout(1, 2));
        panel.add(jogar);
        panel.add(creditos);
        Utils.setIcon(foto, new ImageIcon(getClass().getResource("/fundos/telaInicial.png")), 2000, 1200);

        jogar.addActionListener(_ -> frame.cardLayout.show(frame.getContentPane(), "escolha"));
        creditos.addActionListener(_ -> JOptionPane.showMessageDialog(this, "Criado por Lucas Gabriel Reiter - CatVentures Inc ©", "Créditos", JOptionPane.INFORMATION_MESSAGE));
    }
}
