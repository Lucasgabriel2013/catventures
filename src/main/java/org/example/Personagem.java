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
    int xpMulti = 1;
    int moedasMulti = 1;

    double xpRecebido;
    int moedasRecebidas;

    public Personagem(ImageIcon icon, int dano, int vida, double xp, int moedas, String nome) {
        this.icon = icon;
        this.dano = dano;
        this.vidaMaxima = vida;
        this.vida = vidaMaxima;
        this.nome = nome;
        this.xpRecebido = xp;
        this.moedasRecebidas = moedas;
    }

    public String toString() {
        return """
                %s:
                 %s vida,
                 %s dano
                """.formatted(this.nome, this.vida, this.dano);
    }
}