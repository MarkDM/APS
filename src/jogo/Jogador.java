package jogo;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
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
 * Classe
 *
 * @author Marcos
 */
public class Jogador extends Personagem {

    public double energia = 1000;
    ControleTiros tiros = new ControleTiros();
    private Font f = new Font("arial", Font.BOLD, 20);
    public int ataque = 180;

    /**
     * Cria um novo jogador a partir de um sprite definindo sua posição no cenário
     *
     * @param x
     * @param y
     */
    public Jogador(int x, int y) {
        super(URL.sprite("jogador.png"), 20);
        this.x = x;
        this.y = y;
        this.setTotalDuration(2000);
    }

    public void atirar(Window janela, Scene cena, Keyboard teclado, Personagem inimigo) {
        if (teclado.keyDown(KeyEvent.VK_A)) {
            tiros.adicionaTiro(x + 19, y + 21, direcao, cena);
        }
        tiros.run(inimigo, this);
    }

    /**
     * Tiro independentes de inimigo
     *
     * @param janela
     * @param cena
     * @param teclado
     */
    public void atirar(Window janela, Scene cena, Keyboard teclado) {
        if (teclado.keyDown(KeyEvent.VK_A)) {
            tiros.adicionaTiro(x + 19, y + 21, direcao, cena);
        }
        tiros.run(this);
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
                setSequence(8, 12);
                direcao = 2;
            }
            movendo = true;

        } else if (teclado.keyDown(Keyboard.UP_KEY)) {

            if (this.y > 0) {
                this.y -= velocidade;
            }
            if (direcao != 4) {
                setSequence(12, 16);
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

    public void morrer(Window janela) {
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
