package main;

import entities.Bomb;
import entities.Entity;
import entities.Explosion;

import java.awt.Graphics2D;

public class PlayState implements GameState{
    GamePanel gp;

    private int gameOverTimer = 0;

    public PlayState(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void update() {
        if (gp.player1 != null) gp.player1.update();
        if (gp.player2 != null) gp.player2.update();

        boolean p1Dead = (gp.player1 != null && gp.player1.isDeathAnimationFinished());
        boolean p2Dead = (gp.player2 != null && gp.player2.isDeathAnimationFinished());

        if (p1Dead || p2Dead) {
            gameOverTimer++;

            if (gameOverTimer > 120) {
                gp.setState(new MenuState(gp));
                return;
            }
        }

        // atualiza as bombas
        for (int i = gp.bombs.size() - 1; i >= 0; i--) {
            Bomb b = gp.bombs.get(i);
            b.update();
            if (b.exploded) {
                gp.bombs.remove(i);
            }
        }

        // atualiza as explosões
        for (int i = gp.explosions.size() - 1; i >= 0; i--) {
            Explosion e = gp.explosions.get(i);
            e.update();
            if (e.isFinished()) {
                gp.explosions.remove(i);
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

        // desenha as explosões
        for (Explosion e : gp.explosions) {
            e.draw(g2);
        }

        if (gp.player1 != null) gp.player1.draw(g2);
        if (gp.player2 != null) gp.player2.draw(g2);
    }
}
