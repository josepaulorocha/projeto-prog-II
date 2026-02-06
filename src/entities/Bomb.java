package entities;

import main.GamePanel;
import main.ResourceManager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Bomb extends Entity{
    GamePanel gp;
    int timer = 0;
    // 3 segundos * 60 FPS = 180 frames
    int explodeTime = 180;

    BufferedImage bomb1, bomb2, bomb3;

    public boolean exploded = false;

    public Bomb(GamePanel gp, int x, int y) {
        this.gp = gp;

        // centraliza a bomba no tile
        this.x = (x/gp.tileSize) * gp.tileSize;
        this.y = (y/gp.tileSize) * gp.tileSize;

        getBombImages();
    }

    private void getBombImages() {
        bomb1 = ResourceManager.getTexture("/bomb/bomb_1.png");
        bomb2 = ResourceManager.getTexture("/bomb/bomb_2.png");
        bomb3 = ResourceManager.getTexture("/bomb/bomb_3.png");
    }

    public void update() {
        timer++;

        if(timer >= explodeTime) {
            exploded = true;
            // lógica de explosão
            System.out.println("BOOM!!!");
        }

        spriteCounter++;
        if (spriteCounter > 25) {
            spriteNum++;
            if (spriteNum > 4) { // ciclo de 4 sprites
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (spriteNum == 1) {
            image = bomb1; // pequena
        }
        if (spriteNum == 2) {
            image = bomb2; // média
        }
        if (spriteNum == 3) {
            image = bomb3; // grande
        }
        if (spriteNum == 4) {
            image = bomb2; // média
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
