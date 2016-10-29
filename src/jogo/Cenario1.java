/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

/**
 * Classe que representa o cenário
 *
 * @author Marcos
 */
public class Cenario1 {

    private Window janela;
    private Scene cena;
    private Jogador jogador;
    private Keyboard teclado;
    private Npc zumbi;

    public Cenario1(Window janela) {
        this.janela = janela;
        cena = new Scene();

        /**
         * Carrega o cenário a partir do arquivo .scn
         */
        cena.loadFromFile(URL.scenario("Cenario1.scn"));
        teclado = janela.getKeyboard();
        jogador = new Jogador(640, 350);
        zumbi = new Npc(300, 300);

        Som.play("Mars.wav");
        run();
    }

    private void run() {

        while (true) {
            //cena.draw();
            jogador.controle(janela, teclado);
            jogador.caminho(cena);
            zumbi.caminho(cena);
            zumbi.perseguir(jogador.x, jogador.y);
            //Move o cenário centralizabdo o objeto passado como parâmetro
            cena.moveScene(jogador);

            //Move o jogador conforme o movimento do cenário
            jogador.x += cena.getXOffset();
            jogador.y += cena.getYOffset();

            zumbi.x += cena.getXOffset();
            zumbi.y += cena.getYOffset();

            //Atualiza o jogador a cada refresh da tela
            janela.delay(4);
            jogador.draw();
            zumbi.draw();
            janela.update();

        }
    }

}