package entities;

import main.GamePanel;
import main.ResourceManager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Explosion extends Entity {
    GamePanel gp;

    private int timer = 0;
    private int lifeTime = 30; // duração da explosão
    private String type;

    public Explosion(GamePanel gp, int x, int y, String type, String direction) {
        this.gp = gp;
        this.x = x;
        this.y = y;
        this.type = type;
        this.direction = direction;

        this.spriteNum = 1;
        this.spriteCounter = 0;

        if(type.equals("brick")) {
            lifeTime = 36;
        }
    }

    public void update() {
        timer++;

        int maxSprites = 5;
        if(type.equals("brick")) maxSprites = 6;

        spriteCounter++;
        if(spriteCounter > maxSprites) {
            spriteNum++;
            if(spriteNum > maxSprites) spriteNum = maxSprites;
            spriteCounter = 0;
        }
    }

    public boolean isFinished() {
        return timer >= lifeTime;
    }

    public void draw(Graphics2D g2) {
        // carrega a imagem baseada no tipo e no frame atual
        String path;

        if (type.equals("brick")) {
            path = "/tiles/brick_" + (spriteNum + 1) + ".png";
        } else {
            path = "/explosion/explosion_" + type + "_" + spriteNum + ".png";
        }

        BufferedImage image = ResourceManager.getTexture(path);

        if (image != null) {
            if (!type.equals("brick")) {
                double angle = 0;
                if (type.equals("middle")) {
                    if (direction.equals("left") || direction.equals("right")) angle = 90;
                } else if (type.equals("end")) {
                    switch (direction) {
                        case "down": angle = 180; break;
                        case "right": angle = 90; break;
                        case "left": angle = 270; break;
                        default: angle = 0; break;
                    }
                }

                if (angle != 0) {
                    Graphics2D g2d = (Graphics2D) g2.create();
                    g2d.rotate(Math.toRadians(angle), x + gp.tileSize / 2.0, y + gp.tileSize / 2.0);
                    g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
                    g2d.dispose();
                    return;
                }
            }

            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }
    }
}