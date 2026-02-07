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
        // utiliza do Padrão de Projeto Flyweight para minimizar o custo de memória e otimizar perfomance
        bomb1 = ResourceManager.getTexture("/bomb/bomb_1.png");
        bomb2 = ResourceManager.getTexture("/bomb/bomb_2.png");
        bomb3 = ResourceManager.getTexture("/bomb/bomb_3.png");
    }

    public void update() {
        timer++;

        if(timer >= explodeTime) {
            exploded = true;
            fire(2);
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

    // lógica de explosão
    private void fire(int power) {
        // centro da explosão
        gp.explosions.add(new Explosion(gp, x, y, "center", ""));

        // explode nas 4 direções
        createExplosionsInDirection(0, -1, power, "up");
        createExplosionsInDirection(0, 1, power, "down");
        createExplosionsInDirection(-1, 0, power, "left");
        createExplosionsInDirection(1, 0, power, "right");
    }

    private void createExplosionsInDirection(int dx, int dy, int power, String direction) {
        for (int i = 1; i <= power; i++) {
            int targetX = x + (dx * i * gp.tileSize);
            int targetY = y + (dy * i * gp.tileSize);

            int col = targetX / gp.tileSize;
            int row = targetY / gp.tileSize;

            // verifica limites do mapa
            if (col < 0 || col >= gp.maxScreenCol || row < 0 || row >= gp.maxScreenRow) break;

            int tile = gp.tileM.mapTileNum[col][row];

            if (tile == gp.tileM.TILE_WALL) break;

            // define se é ponta ou meio
            String type = (i == power) ? "end" : "middle";

            gp.explosions.add(new Explosion(gp, targetX, targetY, type, direction));

            if (tile == gp.tileM.TILE_BRICK) {
                gp.tileM.mapTileNum[col][row] = gp.tileM.TILE_GRASS;
                gp.explosions.add(new Explosion(gp, targetX, targetY, "brick", ""));
                break;
            }
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
