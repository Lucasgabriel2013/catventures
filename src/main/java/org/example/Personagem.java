package org.example;

import javax.swing.*;

public class Personagem {
    String nome;
    int vidaMaxima;
    int vida;
    int dano;
    ImageIcon icon;

    double level;
    double kills;

    public Personagem(ImageIcon icon, int dano, int vida, String nome) {
        this.icon = icon;
        this.dano = dano;
        this.vidaMaxima = vida;
        this.vida = vidaMaxima;
        this.nome = nome;
    }

    public String toString() {
        return """
                %s:
                 %s vida,
                 %s dano
                """.formatted(this.nome, this.vida, this.dano);
    }
}