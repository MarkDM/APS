package jogo;

import java.util.LinkedList;
import jplay.Scene;
import jplay.Sound;
import jplay.URL;
import jplay.Window;

/**
 * Classe para fazer o controle de tiros do personagem, colisão com o inimigo etc
 *
 * @author Marcos
 */
public class ControleTiros {

    LinkedList<Tiro> tiros = new LinkedList<>();

    public void adicionaTiro(double x, double y, int caminho, Scene cena) {
        Tiro tiro = new Tiro(x, y, caminho);
        tiros.addFirst(tiro);
        cena.addOverlay(tiro);
        somDisparo();
    }

    /**
     * Tiros em um inimigo
     *
     * @param inimigo
     * @param jogador
     */
    public void run(Lixo lixo, Jogador jogador, Window janela) {
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.removeFirst();
            tiro.mover();
            tiros.addLast(tiro);

            if (tiro.collided(lixo)) {
                lixo.energia -= jogador.ataque;

                if (lixo.energia <= 0) {
                    //Remove a grama dos tiles de bloqueio do lixo
                    if (lixo.controle.getTiles().get(0) == 1) {
                        lixo.controle.getTiles().remove(0);
                    }
                    lixo.x = tiro.x + tiro.width;
                    lixo.y = tiro.y;
                    lixo.sumiu = true;
                } else {

                    lixo.x += 10;
                    lixo.y -= 5;

                    tiro.x = 100000;
                }

            }
        }

    }

    /**
     * Tiros independentes
     *
     * @param jogador
     */
//    public void run(Jogador jogador) {
//        for (int i = 0; i < tiros.size(); i++) {
//            Tiro tiro = tiros.removeFirst();
//            tiro.mover();
//            tiros.addLast(tiro);
//
//        }
//
//    }
    private void somDisparo() {
        new Sound(URL.audio("foom.wav")).play();
    }

}
