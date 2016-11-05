package jogo;

import java.util.LinkedList;
import jplay.Scene;
import jplay.Sound;
import jplay.URL;

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
     * @param inimigo
     * @param jogador 
     */
    public void run(Personagem inimigo, Jogador jogador) {
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.removeFirst();
            tiro.mover();
            tiros.addLast(tiro);

            if (tiro.collided(inimigo)) {
                //Tira o tiro de cena
                tiro.x = 100_000;
                inimigo.energia -= jogador.ataque;
            }
        }

    }

    /**
     * Tiros independentes
     * @param jogador 
     */
    public void run(Jogador jogador) {
        for (int i = 0; i < tiros.size(); i++) {
            Tiro tiro = tiros.removeFirst();
            tiro.mover();
            tiros.addLast(tiro);

        }

    }

    private void somDisparo() {
        //new Sound(URL.audio("pistol.wav")).play();
    }

}
