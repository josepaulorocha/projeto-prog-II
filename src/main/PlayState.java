package main;

import entities.Bomb;

import java.awt.Graphics2D;

public class PlayState implements GameState{
    GamePanel gp;

    public PlayState(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void update() {
        gp.player.update();

        // atualiza as bombas
        for (int i = gp.bombs.size() - 1; i >= 0; i--) {
            Bomb b = gp.bombs.get(i);
            b.update();
            if (b.exploded) {
                gp.bombs.remove(i);
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        gp.tileM.draw(g2);

        // desenha as bombas
        for (Bomb b : gp.bombs) {
            b.draw(g2);
        }

        gp.player.draw(g2);
    }
}
