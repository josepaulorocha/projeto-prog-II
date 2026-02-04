package main;

import java.awt.Graphics2D;

public class PlayState implements GameState{
    GamePanel gp;

    public PlayState(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void update() {
        gp.player.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        gp.tileM.draw(g2);
        gp.player.draw(g2);
    }
}
