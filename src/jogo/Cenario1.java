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
   

    private ArrayList<Lixo> lixos = new ArrayList<>();

    //private Npc zumbi;
    public Cenario1(Window janela) {
        this.janela = janela;
        cena = new Scene();

        /**
         * Carrega o cenário a partir do arquivo .scn
         */
        cena.loadFromFile(URL.scenario("Cenario1.scn"));
        teclado = janela.getKeyboard();
        jogador = new Jogador(20, janela.getWidth() / 2);

        gerarLatas();
        gerarSacosDeLixo();
        gerarPneus();

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

            int i = 0;

            for (Lixo lixo : lixos) {

                jogador.atirar(janela, cena, teclado, lixo);
                lixo.caminho(cena);
                lixo.flutuar(janela.getWidth() / 2 + 5 * i++, janela.getHeight());
                lixo.x += cena.getXOffset();
                lixo.y += cena.getYOffset();
                lixoAlcancouCachoeira(cena, lixo);
                lixo.draw();
            }
            //Atualiza o jogador a cada refresh da tela
            jogador.draw();
            jogador.mostrarEnergia(janela);
            jogador.perdeu(janela);
            janela.delay(4);

            janela.update();

        }
    }

    private void gerarSacosDeLixo() {
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            LixoSaco saco = new LixoSaco((janela.getWidth() / 2) + (i * 2) * r.nextInt(10), r.nextInt(20) * i, "lixo.png");
            lixos.add(saco);
        }
    }

    private void gerarLatas() {
        Random r = new Random();
        for (int i = 0; i < 15; i++) {
            LixoLatinha latinha = new LixoLatinha((janela.getWidth() / 2) + (i * 2) * r.nextInt(10), r.nextInt(20) * i, "lata.png");
            lixos.add(latinha);
        }
    }

    private void gerarPneus() {
        Random r = new Random();
        for (int i = 0; i < 8; i++) {
            LixoPneu pneu = new LixoPneu((janela.getWidth() / 2) + (i * 2) * r.nextInt(10), r.nextInt(20) * i, "pneu.png");
            lixos.add(pneu);
        }
    }

    private void lixoAlcancouCachoeira(Scene cena, Lixo lixo) {
        if (tileCollision(11, lixo, cena)) {
            lixo.sumir();
        }

    }

}
