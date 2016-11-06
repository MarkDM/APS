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
    // private ArrayList<Peixe> peixes = new ArrayList<>();
    private Font f = new Font("arial", Font.BOLD, 20);

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

        gerarLixo();
        //gerarPeixes();

        //totalPeixes = peixes.size();
        numLixosNaTela = lixos.size();
        //Som.play("Mars.wav");
        //Som.play("Enter_Sandman.mid");
        run();
    }

    private void run() {

        boolean gambiarraPraLixo = false;

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

            if (new Date().getTime() - horaStart > 10000) {
                gerarLixoExtra();
                this.horaStart = new Date().getTime();
            }

            for (Lixo lixo : lixos) {

//                if (lixo.sumiu) {
//                    lixo.sumir();
//                    Cenario1.numLixosNaTela--;
//                }
                jogador.atirar(janela, cena, teclado, lixo);
                lixo.caminho(cena);

//                if (i < peixes.size()) {
//                    if (!peixes.get(i).morto) {
//                        lixo.flutuar((int) peixes.get(i).x, (int) peixes.get(i).y);
//                    }
//
//                } else if (!peixes.get(0).morto) {
//                    lixo.flutuar((int) peixes.get(0).x, (int) peixes.get(0).y);
//                } else {
//                    lixo.flutuar(cena);
//                }
//                
                lixo.flutuar(cena);
                i++;
                lixo.x += cena.getXOffset();
                lixo.y += cena.getYOffset();
                lixoPassouCachoeira(lixo);
                lixoAlcancouCachoeira(lixo);
                lixo.draw();
            }

//            for (Peixe peixe : peixes) {
//
//                Random r = new Random();
//
//                for (Lixo lixo : lixos) {
//                    lixoAlcancouPeixe(lixo, peixe);
//                }
//                // peixe.nadar(0, 12, cena);
//                peixe.x += cena.getXOffset();
//                peixe.y += cena.getYOffset();
//                peixe.draw();
//            }
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

//    public void gerarPeixes() {
//        Random r = new Random();
//
//        for (int i = 0; i < 3; i++) {
//            Peixe peixe = new Peixe("peixe1.png", 12, r.nextInt(janela.getWidth()), 950);
//            peixes.add(peixe);
//        }
//
//        for (int i = 0; i < 4; i++) {
//            Peixe peixe = new Peixe("peixe2.png", 12, r.nextInt(janela.getWidth()), 950);
//            peixes.add(peixe);
//        }
//
//        for (int i = 0; i < 5; i++) {
//            Peixe peixe = new Peixe("peixe3.png", 16, r.nextInt(janela.getWidth()), 950);
//            peixes.add(peixe);
//        }
//    }
//    private void lixoAlcancouPeixe(Lixo lixo, Peixe peixe) {
//
//        Random r = new Random();
//
//        if (lixo.collided(peixe)) {
//            totalPeixes--;
//            Cenario1.numLixosNaTela--;
//            peixe.morrer();
//            lixo.velocidade = 0;
//            lixo.sumir();
//        }
//
//        if (tileCollision(18, lixo, cena)) {
//            lixo.sumir();
//            Cenario1.numLixosNaTela--;
//        }
//
//    }
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
