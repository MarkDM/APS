/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.ArrayList;
import java.util.Random;
import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

/**
 * Classe que representa o cenário1 do jogo
 *
 * @author Marcos
 */
public class Cenario1 extends Cenario {

    private Window janela;
    private Scene cena;
    private Jogador jogador;
    private Keyboard teclado;

    private ArrayList<Npc> zumbis = new ArrayList<>();

    //private Npc zumbi;
    public Cenario1(Window janela) {
        this.janela = janela;
        cena = new Scene();

        /**
         * Carrega o cenário a partir do arquivo .scn
         */
        cena.loadFromFile(URL.scenario("Cenario1.scn"));
        teclado = janela.getKeyboard();
        jogador = new Jogador(640, 350);

        Random r = new Random();
        for (int i = 0; i < 15; i++) {
            Npc zumbi = new Npc(r.nextInt(janela.getWidth()), r.nextInt(janela.getHeight()));
            zumbis.add(zumbi);

        }

        //Som.play("Mars.wav");
        //Som.play("Enter_Sandman.mid");
        run();
    }

    private void run() {

        while (true) {
            //cena.draw();
            jogador.controle(janela, teclado);
            jogador.caminho(cena);

            //Move o cenário centralizabdo o objeto passado como parâmetro
            cena.moveScene(jogador);

            //Move o jogador conforme o movimento do cenário
            jogador.x += cena.getXOffset();
            jogador.y += cena.getYOffset();

            //Atualiza o jogador a cada refresh da tela
            jogador.draw();

            for (Npc zumbi : zumbis) {
                zumbi.caminho(cena);
                zumbi.perseguir(jogador);
                zumbi.x += cena.getXOffset();
                zumbi.y += cena.getYOffset();
                jogador.atirar(janela, cena, teclado, zumbi);
                zumbi.morrer();
                zumbi.atacar(jogador);
                zumbi.draw();
            }

            jogador.mostrarEnergia(janela);
            janela.delay(4);
            janela.update();

            mudarCenario();
        }
    }

    private void mudarCenario() {

        if (tileCollision(3, jogador, cena)) {
            new Cenario2(janela);
        }
    }

}
