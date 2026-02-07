package entities;

import main.GamePanel;
import main.KeyHandler;

public class EntityFactory {
    GamePanel gp;

    public EntityFactory(GamePanel gp) {
        this.gp = gp;
    }

    public Entity createPlayer(KeyHandler keyH) {
        return new Player(gp, keyH);
    }

    public Bomb createBomb(int x, int y) {
        return new Bomb(gp, x, y);
    }

    public Explosion createExplosion(int x, int y, String type, String direction) {
        return new Explosion(gp, x, y, type, direction);
    }
}
