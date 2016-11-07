/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import jplay.Keyboard;
import jplay.Scene;
import jplay.TileInfo;
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
    public static int numLixosNaTela;
    private int numLixosPassaram = 0;
    private ArrayList<Lixo> lixos = new ArrayList<>();
    private Long horaStart = new Date().getTime();
    private Font f = new Font("arial", Font.BOLD, 20);

    
    public Cenario1(Window janela) {
        this.janela = janela;
        cena = new Scene();

        /**
         * Carrega o cenário a partir do arquivo .scn
         */
        cena.loadFromFile(URL.scenario("Cenario1.scn"));
        teclado = janela.getKeyboard();
        jogador = new Jogador(20, janela.getWidth() / 2);

        gerarLixo();

        numLixosNaTela = lixos.size();

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

            if (new Date().getTime() - horaStart > 7000) {
                gerarLixoExtra();
                this.horaStart = new Date().getTime();
            }

            for (Lixo lixo : lixos) {
                if (!lixo.sumiu) {
                    jogador.atirar(janela, cena, teclado, lixo);
                    lixo.caminho(cena);

                    lixo.flutuar(cena);
                    i++;
                    lixo.x += cena.getXOffset();
                    lixo.y += cena.getYOffset();
                    lixoPassouCachoeira(lixo);
                    lixoAlcancouCachoeira(lixo);
                    lixo.draw();
                }

            }

            //Atualiza o jogador a cada refresh da tela
            jogador.draw();
            qtdLixosPassaramCachoeira();
            perder();

            janela.delay(4);
            if (Cenario1.numLixosNaTela <= 0) {
                System.out.println(Cenario1.numLixosNaTela);
            }
            progressoJogo();
            janela.update();

        }
    }

    private void gerarLixo() {
        lixos.clear();
        gerarLatas();
        gerarPneus();
        gerarSacosDeLixo();
        Cenario1.numLixosNaTela = lixos.size();
    }

    private void gerarLixoExtra() {
        gerarLatas();
        gerarPneus();
        gerarSacosDeLixo();
        Cenario1.numLixosNaTela = lixos.size();
    }

    private void gerarSacosDeLixo() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
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

    private void lixoAlcancouCachoeira(Lixo lixo) {
        if (tileCollision(11, lixo, cena)) {
            lixo.velocidade = 1;
        }
    }

    public void lixoPassouCachoeira(Lixo lixo) {
        if (tileCollision(18, lixo, cena)) {
            lixo.sumir();
            Cenario1.numLixosNaTela--;
            numLixosPassaram++;
        }
    }

    private void qtdLixosPassaramCachoeira() {
        janela.drawText("Lixos que passaram: " + this.numLixosPassaram, 30, 30, Color.GREEN, f);
    }

    private void perder() {
        if (numLixosPassaram > 15) {
            jogador.perdeu(janela);
        }
    }

    private void progressoJogo() {
        if (Cenario1.numLixosNaTela <= 0) {
            gerarLixo();
        }
    }
}
