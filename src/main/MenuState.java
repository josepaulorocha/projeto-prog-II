package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MenuState implements GameState{
    GamePanel gp;
    BufferedImage background, menuTitle;

    public MenuState(GamePanel gp) {
        this.gp = gp;
        background = ResourceManager.getTexture("/menu/background.png");
        menuTitle = ResourceManager.getTexture("/menu/menu_1.png");
    }

    @Override
    public void update() {
        if (gp.keyH.enterPressed) {
            gp.keyH.enterPressed = false;

            gp.resetGame();
            gp.setState(new PlayState(gp));
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (background != null) {
            g2.drawImage(background, 0, 0, gp.screenWidth, gp.screenHeight, null);
        } else {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }

        if (menuTitle != null) {
            g2.drawImage(menuTitle, 0, 0, gp.screenWidth, gp.screenHeight, null);
        }
    }
}
