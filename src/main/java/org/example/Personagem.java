package org.example;

import javax.swing.*;

public class Personagem {
    String nome;
    int vida;
    int dano;
    ImageIcon icon;

    int level;
    int kills;

    public Personagem(ImageIcon icon, int dano, int vida, String nome) {
        this.icon = icon;
        this.dano = dano;
        this.vida = vida;
        this.nome = nome;
    }
}
