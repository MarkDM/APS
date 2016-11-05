/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;
import jplay.GameImage;
import jplay.GameObject;
import jplay.Keyboard;
import jplay.Scene;
import jplay.Sprite;
import jplay.TileInfo;
import jplay.URL;
import jplay.Window;

/**
 *
 * @author Marcos
 */
public class Jogador extends ObjetoJogo {

    public double energia = 1000;
    private Font f = new Font("arial", Font.BOLD, 20);
    public int ataque = 200;
    ControleTiros tiros = new ControleTiros();

    public Jogador(int x, int y) {
        super(URL.sprite("panda.png"), 20);
        this.x = x;
        this.y = y;
        this.setTotalDuration(2000);
        ArrayList<Integer> tilesBloqueio = new ArrayList<>();
        tilesBloqueio.add(3);
        tilesBloqueio.add(11);
        tilesBloqueio.add(17);
        tilesBloqueio.add(15);
        tilesBloqueio.add(16);
        tilesBloqueio.add(10);
        tilesBloqueio.add(7);

        this.controle.setTiles(tilesBloqueio);
    }

    /**
     * Controle do movimento do personagem
     *
     * @param janela
     * @param teclado
     */
    public void controle(Window janela, Keyboard teclado) {

        if (teclado.keyDown(Keyboard.LEFT_KEY)) {

            if (this.x > 0) {
                this.x -= this.velocidade;
            }

            if (direcao != 1) {
                setSequence(4, 8);
                direcao = 1;
            }
            movendo = true;

        } else if (teclado.keyDown(Keyboard.RIGHT_KEY)) {

            if (this.x < janela.getWidth() - 50) {
                this.x += velocidade;
            }
            if (direcao != 2) {
                setSequence(12, 16);
                direcao = 2;
            }
            movendo = true;

        } else if (teclado.keyDown(Keyboard.UP_KEY)) {

            if (this.y > 0) {
                this.y -= velocidade;
            }
            if (direcao != 4) {
                setSequence(8, 12);
                direcao = 4;
            }
            movendo = true;

        } else if (teclado.keyDown(Keyboard.DOWN_KEY)) {

            if (this.y < janela.getHeight() - 60) {
                this.y += velocidade;
            }
            if (direcao != 5) {
                setSequence(0, 4);
                direcao = 5;
            }
            movendo = true;

        }

        if (movendo) {
            update();
            movendo = false;
        }

    }

    public void atirar(Window janela, Scene cena, Keyboard teclado, Lixo lixo) {
        if (teclado.keyDown(KeyEvent.VK_A)) {
            tiros.adicionaTiro(x - 10, y - 15, direcao, cena);
        }
        tiros.run(lixo, this);
    }

    public void perdeu(Window janela) {
        if (this.energia <= 0) {
            GameImage plano = new GameImage(URL.sprite("game_over.jpg"));
            Keyboard teclado = janela.getKeyboard();

            while (true) {
                plano.draw();
                janela.delay(20);
                janela.update();
                if (teclado.keyDown(Keyboard.ENTER_KEY)) {
                    Som.play("Mars.wav");
                    new Cenario1(janela);

                }

            }
        }
    }

    public void mostrarEnergia(Window janela) {
        janela.drawText("Health: " + this.energia, 30, 30, Color.GREEN, f);
    }

}
