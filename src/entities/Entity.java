package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public BufferedImage death1, death2, death3, death4, death5, death6, death7, death8, death9, death10;

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;

    public boolean alive = true;
}
