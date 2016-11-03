/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;


import jplay.URL;


/**
 *
 * @author Marcos
 */
public class Npc extends Personagem {

    private double ataque = 1;

    public Npc(int x, int y) {
        super(URL.sprite("zumbi.png"), 16);
        this.x = x;
        this.y = y;
        this.velocidade = 0.3;
        this.setTotalDuration(2000);
    }

    public void perseguir(Jogador_ jogador) {

        if (!alcancouJogador(this, jogador)) {

            if (estaPertodoJogador(jogador.x, jogador.y, this.x, this.y)) {

                if (this.x > jogador.x && this.y <= jogador.y + 50 && this.y >= jogador.y - 50) {
                    //moveTo(x, y, velocidade);

                    if (direcao != 1) {
                        setSequence(5, 8);
                        direcao = 1;
                    }

                    movendo = true;
                } else if (this.x < jogador.x && this.y <= jogador.y + 50 && this.y >= -50) {
                    //moveTo(x, y, velocidade);
                    if (direcao != 2) {
                        setSequence(9, 12);
                        direcao = 2;
                    }
                    movendo = true;
                } else if (this.y > jogador.y) {
                    //moveTo(x, y, velocidade);
                    if (direcao != 4) {
                        setSequence(13, 16);
                        direcao = 4;
                    }
                    movendo = true;
                } else if (this.y < jogador.y) {
                    //moveTo(x, y, velocidade);

                    if (direcao != 5) {
                        setSequence(1, 4);
                        direcao = 5;
                    }
                    movendo = true;
                }

                if (movendo) {

                    moveTo(jogador.x, jogador.y, velocidade);
                    update();

                    movendo = false;
                }

            }

        } else {
            atacar(jogador);
        }

    }

    private boolean alcancouJogador(Npc npc, Jogador_ jogador) {

        return npc.collided(jogador);

    }

    private boolean estaPertodoJogador(double x1, double y1, double x2, double y2) {

        double distancia = Math.abs((x1 * y1) - (x2 * y2));

        return distancia < 134000;
    }

    public void morrer() {
        if (this.energia <= 0) {
            this.velocidade = 0;
            this.ataque = 0;
            this.direcao = 0;
            this.movendo = false;
            this.x = 1_000_000;
        }

    }

    public void atacar(Jogador_ jogador) {
        
        //Mudar frames aki

        jogador.energia -= this.ataque;

    }

}
