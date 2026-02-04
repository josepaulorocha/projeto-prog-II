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
}
